/**
 * 
 */
package com.gtc.pfm.ws.controller.test.integration;


import static org.springframework.test.web.server.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.server.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.server.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.server.setup.MockMvcBuilders.standaloneSetup;

import com.gtc.exceptions.ProviderException;
import com.gtc.pfm.domain.Package;
import com.gtc.pfm.domain.RowFilter;
import com.gtc.pfm.persistence.repository.mongo.test.PackageRepositoryTest;
import com.gtc.pfm.service.package_.PackageService;
import com.gtc.pfm.ws.controller.PackageRestWSController;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * Unit test class for restful webservice operations of {@literal Package} layer.
 * There is no mock implementation of service objects or repository objects.The class extends
 * {@link PackageRepositoryTest} and loads test context xml files of it.In that way actual repository
 * and service implementations run on test db configuration.Here is list of loaded spring context
 * xml files for this test
 * 
 * <li>PackageRestWSControllerTest-context.xml: scans base-package="com.gtc.pfm"</li>
 * <li>applicationContext-test.xml through PackageRepositoryTest class: scans package repositories</li>
 * <li>MongoRepositoryTestBase-context.xml through MongoRepositoryTestBase class:provides test db configuration</li>
 * 
 * 
 * @author stanriku
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration defaults to PackageRestWSControllerTest-context.xml in the same package
@ContextConfiguration
public class PackageRestWSControllerTest extends PackageRepositoryTest{

    @Autowired
    private PackageService packageService;
    
    /**
     * 
     * @throws Exception
     */
    @BeforeClass
    public static void beforeCalss() throws Exception {
        PackageRepositoryTest.beforeCalss();
        
    }

    /**
     * 
     * @throws Exception
     */
    @AfterClass
    public static void afterClass() throws Exception {
        PackageRepositoryTest.afterClass();
    }
    
    
    /**
     * 
     */
    @Before 
    public void setUp() {

        mongoTemplate.dropCollection("package");
        mongoTemplate.createCollection("package");

    }
    
    @After 
    public void tearDown() throws ProviderException {
        getRepository().deleteAll(Package.class);
    }
    
    @Test
    public void testFindPackageById() throws Exception {
        
        populatePackages();
        
        RowFilter rowFilter = new RowFilter();
        
        List<Package> packages = getRepository().findAllPackages(rowFilter);
        
        PackageRestWSController pws = new PackageRestWSController();
        pws.setPackageService(this.packageService);
       
        
        standaloneSetup(pws)
                .build()
                .perform(get("/package/findPackageById/"+packages.get(0).getId()).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().mimeType(MediaType.APPLICATION_JSON));
                //.andExpect(content().string(findPackageByIdJsonResult()))
                //.andExpect(jsonPath("id").value(packages.get(0).getId())).andDo(print());
    }
    
    private String findPackageByIdJsonResult() throws IOException {
        return jsonResultFromFile("PackageRestWSControllerTest-FindPackageById-json.txt");
    }

    private String jsonResultFromFile(String filePath) throws IOException {
        final URL url = getClass().getResource(filePath);
        final List<String> lines = FileUtils.readLines(new File(url.getFile()));
        final StringBuilder builder = new StringBuilder();
        for (String line : lines) {
            builder.append(StringUtils.trim(line));
        }
        return builder.toString();
    }

    /**
     * @return the packageService
     */
    public PackageService getPackageService() {
        return packageService;
    }

    /**
     * @param packageService the packageService to set
     */
    public void setPackageService(PackageService packageService) {
        this.packageService = packageService;
    }
    
}
