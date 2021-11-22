package com.example.demo.service;

import com.example.demo.beans.UserDetailsBean;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;

@Component
public class UserDetailsServiceImpl implements UserDetailsService{

    HashMap<String, UserDetailsBean> users = new HashMap<>();

    public boolean checkDuplication(UserDetailsBean user) {
       if(users.containsKey(user.getEmailId())) {
           return true;
       }
       else return false;
    }

    @Override
    public HashMap<String,UserDetailsBean> showUsers(){
        if(users != null) {
            return users;
        }
        else  {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }

    @Override
    public void addUser(UserDetailsBean user) {
        if(!checkDuplication(user)) {
            users.put(user.getEmailId(), user);
        }
        else {
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED);
        }
    }

    @Override
    public UserDetailsBean getUser(String Id){
        if(users.containsKey(Id)){
            return users.get(Id);
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void deleteUser(String Id) {
        if(users.containsKey(Id)){
            users.remove(Id);
        }
        else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public void updateUser(UserDetailsBean user) {
        if(!checkDuplication(user)) {
            users.put(user.getEmailId(), user);
        }
        else {
            throw new ResponseStatusException(HttpStatus.ALREADY_REPORTED);
        }
    }

}
