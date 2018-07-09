package com.homedepot.springbootdocker.controller;

import com.homedepot.springbootdocker.dao.UserDao;
import com.homedepot.springbootdocker.manager.UserManager;
import com.homedepot.springbootdocker.models.User;
import com.homedepot.springbootdocker.models.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {


    private static UserManager userManager = new UserManager();
    /**
     * Method to test application
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/test", method = { RequestMethod.GET  })
    public String testApplication(){
        return "Hello World";
    }


    /**
     * Method to return userName from input credentials
     * @param user
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/login", method = { RequestMethod.POST  })
    public UserResponse loginUser(@RequestBody User user){
        return userManager.login(user);
    }


    /**
     * Method to create user
     * @param user
     * @return
     */
    @CrossOrigin
    @RequestMapping(value = "/create", method = { RequestMethod.POST  })
    public UserResponse createUser(@RequestBody User user){
        return userManager.createUser(user);
    }
}

