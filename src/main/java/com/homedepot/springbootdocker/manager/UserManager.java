package com.homedepot.springbootdocker.manager;

import com.homedepot.springbootdocker.dao.UserDao;
import com.homedepot.springbootdocker.models.User;
import com.homedepot.springbootdocker.models.UserResponse;
import org.springframework.web.bind.annotation.RequestBody;

public class UserManager {

    private static UserDao userDao = new UserDao();

    public UserResponse login(@RequestBody User user){
        UserResponse userResponse = new UserResponse(false,"");
        try {
            boolean isUserExist = userDao.checkUserExistence(user);
            if(isUserExist) {
                userResponse.setValid(true);
                userResponse.setMessage("User has been logged in successfully");
            }
            else
                userResponse.setMessage("Invalid UserId/Password");
        } catch (Exception e) {
            userResponse.setMessage("Error while login");
        }
        return userResponse;
    }

    public UserResponse createUser(@RequestBody User user){
        UserResponse userResponse = new UserResponse(false,"");
        try {
            boolean isUserExist = userDao.checkUserExistence(user);
            if(!isUserExist) {
                boolean createUser = userDao.createUser(user);
                if(createUser){
                    userResponse.setValid(true);
                    userResponse.setMessage("User has been create successfully");
                }
            }
            else
                userResponse.setMessage("User already exist");
        } catch (Exception e) {
            userResponse.setMessage("Error while creating user");
        }
        return userResponse;
    }


}
