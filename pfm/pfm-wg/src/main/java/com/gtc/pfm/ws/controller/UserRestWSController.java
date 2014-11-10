/**
 * 
 */
package com.gtc.pfm.ws.controller;

import com.gtc.exceptions.InvalidArgumentException;
import com.gtc.exceptions.ObjectExistsException;
import com.gtc.exceptions.ObjectNotFoundException;
import com.gtc.exceptions.ProviderException;
import com.gtc.pfm.domain.User;
import com.gtc.pfm.persistence.repository.user.UserRepository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * This class exposes business methods of {@link UserRepository} as restful web service
 * 
 * @author stanriku
 *
 */
@RequestMapping("/user")
@Controller
public class UserRestWSController extends PfmRestWSController {

    private static Log logger = LogFactory.getLog(UserRestWSController.class);
    
    /**
     * default constructor
     */
    public UserRestWSController() {
        
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/findUserById/{id}", 
            headers="Accept=application/json")
    public @ResponseBody User findUserById(@PathVariable String id) {
        
        User user = null;
        
        try {
            user = this.userService.findUserById(id);
        } catch (ProviderException e) {
            logger.error("an error occured while finding user by id:"+id,e);
        } catch (ObjectNotFoundException e) {
            logger.error("",e);
        } catch (InvalidArgumentException e) {
        	logger.error("an error occured while finding user by id:"+id,e);
		}
        
        return user;
    }
    
    @RequestMapping(method=RequestMethod.POST, value="/creaetUser", 
            headers="Accept=application/json")
    public @ResponseBody String creaetUser(@RequestBody User user) {
        
        String userId = null;
        try {
            userId = userService.creaetUser(user);
        } catch (ProviderException e) {
            logger.error("an error occured while finding user:"+user,e);
        } catch (ObjectExistsException e) {
           logger.error("user already exists:"+user,e);
        }
        
        return userId;
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/findUserByName/{name}", 
            headers="Accept=application/json")
    public @ResponseBody User findUserByName(@PathVariable String name) {
        
        User user = null;
        
        try {
            user = this.userService.findUserByName(name);
        } catch (ProviderException e) {
            logger.error("an error occured while finding user by name:"+name,e);
        } catch (ObjectNotFoundException e) {
            logger.error("user not found by name:"+name,e);
        } catch (InvalidArgumentException e) {
        	logger.error("an error occured while finding user by name:"+name,e);
		}
        
        return user;
    }
    
    @RequestMapping(method=RequestMethod.GET, value="/findAllUsers", 
            headers="Accept=application/json")
    public @ResponseBody List<User> findAllUsers(@RequestParam("fr") int firstRow, @RequestParam("lr") int lastRow) {
        
        List<User> users = null;
        try {
            users = userService.findAllUsers(firstRow, lastRow);
        } catch (ProviderException e) {
            logger.error("an error occured while finding all users.first row["+firstRow+"], last row["+lastRow+"]",e);
        }
        
        return users;
    }

}
