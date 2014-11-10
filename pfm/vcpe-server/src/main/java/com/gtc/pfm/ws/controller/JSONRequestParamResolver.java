/**
 * 
 */
package com.gtc.pfm.ws.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.type.TypeFactory;
import org.codehaus.jackson.type.JavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.GenericCollectionTypeResolver;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.util.Assert;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ValueConstants;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.util.WebUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * @author stanriku
 *
 */
public class JSONRequestParamResolver implements HandlerMethodArgumentResolver {

    @Autowired
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;
    
    MappingJacksonHttpMessageConverter mappingJacksonHttpMessageConverter = null;
    
    public JSONRequestParamResolver() {
        
    }
    
    public void initJsonConverter() {
        
        if(mappingJacksonHttpMessageConverter != null)
            return;
        
        synchronized (JSONRequestParamResolver.class) {
            if(mappingJacksonHttpMessageConverter == null){
                boolean found = false;
                List<HttpMessageConverter<?>> messageConverters = requestMappingHandlerAdapter.getMessageConverters();
                
                if(messageConverters == null)
                    messageConverters = new ArrayList<HttpMessageConverter<?>>();
                
                for (HttpMessageConverter<?> httpMessageConverter : messageConverters) {
                    if(httpMessageConverter instanceof MappingJacksonHttpMessageConverter){
                        found = true;
                        break;
                    }
                }
                if(!found){
                    mappingJacksonHttpMessageConverter = new MappingJacksonHttpMessageConverter();
                    messageConverters.add(mappingJacksonHttpMessageConverter);
                }
            }
        }
        
    }
    
    private ObjectMapper objectMapper = new ObjectMapper(); 
    
    private Map<MethodParameter, NamedValueInfo> namedValueInfoCache =
            new ConcurrentHashMap<MethodParameter, NamedValueInfo>(256);


    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> paramType = parameter.getParameterType();
        if (parameter.hasParameterAnnotation(JSONRequestParameter.class)) {
            return paramType.getName().startsWith("com.gtc");
        }
        return false;
    }
    
    public final Object resolveArgument(
            MethodParameter parameter, ModelAndViewContainer mavContainer,
            NativeWebRequest webRequest, WebDataBinderFactory binderFactory)
            throws Exception {

        initJsonConverter();
        Class<?> paramType = parameter.getParameterType();
        NamedValueInfo namedValueInfo = getNamedValueInfo(parameter);

        Object arg = resolveName(namedValueInfo.name, parameter, webRequest);
        if (arg == null) {
           if (namedValueInfo.required) {
                handleMissingValue(namedValueInfo.name, parameter);
            }
            arg = handleNullValue(namedValueInfo.name, arg, paramType);
        }

       
        JavaType javaType = getJavaType(paramType,null);
        arg = objectMapper.readValue(String.valueOf(arg), javaType);
        
        return arg;
    }

    protected JavaType getJavaType(Type type, Class<?> contextClass) {
        return (contextClass != null) ?
            TypeFactory.type(type, TypeFactory.type(contextClass)) :
            TypeFactory.type(type);
    }
    /**
     * Obtain the named value for the given method parameter.
     */
    private NamedValueInfo getNamedValueInfo(MethodParameter parameter) {
        NamedValueInfo namedValueInfo = this.namedValueInfoCache.get(parameter);
        if (namedValueInfo == null) {
            namedValueInfo = createNamedValueInfo(parameter);
            namedValueInfo = updateNamedValueInfo(parameter, namedValueInfo);
            this.namedValueInfoCache.put(parameter, namedValueInfo);
        }
        return namedValueInfo;
    }

    /**
     * Create the {@link NamedValueInfo} object for the given method parameter. Implementations typically
     * retrieve the method annotation by means of {@link MethodParameter#getParameterAnnotation(Class)}.
     * @param parameter the method parameter
     * @return the named value information
     */
    protected NamedValueInfo createNamedValueInfo(MethodParameter parameter) {
        JSONRequestParameter annotation = parameter.getParameterAnnotation(JSONRequestParameter.class);
        return (annotation != null) ?
                new NamedValueInfo(annotation) :
                new NamedValueInfo();
    }

    /**
     * Create a new NamedValueInfo based on the given NamedValueInfo with sanitized values.
     */
    private NamedValueInfo updateNamedValueInfo(MethodParameter parameter, NamedValueInfo info) {
        String name = info.name;
        if (info.name.length() == 0) {
            name = parameter.getParameterName();
            Assert.notNull(name, "Name for argument type [" + parameter.getParameterType().getName()
                        + "] not available, and parameter name information not found in class file either.");
        }
        String defaultValue = (ValueConstants.DEFAULT_NONE.equals(info.defaultValue) ? null : info.defaultValue);
        return new NamedValueInfo(name, info.required, defaultValue);
    }

    /**
     * Resolves the given parameter type and value name into an argument value.
     * @param name the name of the value being resolved
     * @param parameter the method parameter to resolve to an argument value
     * @param request the current request
     * @return the resolved argument. May be {@code null}
     * @throws Exception in case of errors
     */
    protected  Object resolveName(String name, MethodParameter parameter, NativeWebRequest webRequest)
            throws Exception {
        Object arg;

        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        MultipartHttpServletRequest multipartRequest =
            WebUtils.getNativeRequest(servletRequest, MultipartHttpServletRequest.class);

        if (MultipartFile.class.equals(parameter.getParameterType())) {
            assertIsMultipartRequest(servletRequest);
            Assert.notNull(multipartRequest, "Expected MultipartHttpServletRequest: is a MultipartResolver configured?");
            arg = multipartRequest.getFile(name);
        }
        else if (isMultipartFileCollection(parameter)) {
            assertIsMultipartRequest(servletRequest);
            Assert.notNull(multipartRequest, "Expected MultipartHttpServletRequest: is a MultipartResolver configured?");
            arg = multipartRequest.getFiles(name);
        }
        /*else if ("javax.servlet.http.Part".equals(parameter.getParameterType().getName())) {
            assertIsMultipartRequest(servletRequest);
            arg = servletRequest.getPart(name);
        }*/
        else {
            arg = null;
            if (multipartRequest != null) {
                List<MultipartFile> files = multipartRequest.getFiles(name);
                if (!files.isEmpty()) {
                    arg = (files.size() == 1 ? files.get(0) : files);
                }
            }
            if (arg == null) {
                String[] paramValues = webRequest.getParameterValues(name);
                if (paramValues != null) {
                    arg = paramValues.length == 1 ? paramValues[0] : paramValues;
                }
            }
        }

        return arg;
    }

    private void assertIsMultipartRequest(HttpServletRequest request) {
        String contentType = request.getContentType();
        if (contentType == null || !contentType.toLowerCase().startsWith("multipart/")) {
            throw new MultipartException("The current request is not a multipart request");
        }
    }

    private boolean isMultipartFileCollection(MethodParameter parameter) {
        Class<?> paramType = parameter.getParameterType();
        if (Collection.class.equals(paramType) || List.class.isAssignableFrom(paramType)){
            Class<?> valueType = GenericCollectionTypeResolver.getCollectionParameterType(parameter);
            if (valueType != null && valueType.equals(MultipartFile.class)) {
                return true;
            }
        }
        return false;
    }

    protected void handleMissingValue(String paramName, MethodParameter parameter) throws ServletException {
        throw new MissingServletRequestParameterException(paramName, parameter.getParameterType().getSimpleName());
    }
    /**
     * A {@code null} results in a {@code false} value for {@code boolean}s or an exception for other primitives.
     */
    private Object handleNullValue(String name, Object value, Class<?> paramType) {
        if (value == null) {
            if (Boolean.TYPE.equals(paramType)) {
                return Boolean.FALSE;
            }
            else if (paramType.isPrimitive()) {
                throw new IllegalStateException("Optional " + paramType + " parameter '" + name +
                        "' is present but cannot be translated into a null value due to being declared as a " +
                        "primitive type. Consider declaring it as object wrapper for the corresponding primitive type.");
            }
        }
        return value;
    }


    /**
     * Represents the information about a named value, including name, whether it's required and a default value.
     */
    protected static class NamedValueInfo {

        private final String name;

        private final boolean required;

        private final String defaultValue;

        protected NamedValueInfo(String name, boolean required, String defaultValue) {
            this.name = name;
            this.required = required;
            this.defaultValue = defaultValue;
        }
        
        protected NamedValueInfo() {
            this("", false, ValueConstants.DEFAULT_NONE);
        }

        protected NamedValueInfo(JSONRequestParameter annotation) {
            this(annotation.value(), annotation.required(), annotation.defaultValue());
        }
    }


    /**
     * @return the requestMappingHandlerAdapter
     */
    public RequestMappingHandlerAdapter getRequestMappingHandlerAdapter() {
        return requestMappingHandlerAdapter;
    }

    /**
     * @param requestMappingHandlerAdapter the requestMappingHandlerAdapter to set
     */
    public void setRequestMappingHandlerAdapter(
            RequestMappingHandlerAdapter requestMappingHandlerAdapter) {
        this.requestMappingHandlerAdapter = requestMappingHandlerAdapter;
    }

    /**
     * @return the objectMapper
     */
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    /**
     * @param objectMapper the objectMapper to set
     */
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    
    
}
