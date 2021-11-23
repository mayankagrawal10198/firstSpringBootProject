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
        return users;
    }

    @Override
    public boolean addUser(UserDetailsBean user) {
        if(!checkDuplication(user)) {
            users.put(user.getEmailId(), user);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public UserDetailsBean getUser(String Id){
        if(users.containsKey(Id)){
            return users.get(Id);
        }
        else {
            return null;
        }
    }

    @Override
    public boolean deleteUser(String Id) {
        if(users.containsKey(Id)){
            users.remove(Id);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean updateUser(String Id, UserDetailsBean user) {
        if(!checkDuplication(user)) {
            users.put(user.getEmailId(), user);
            users.remove(Id);
            return true;
        }
        else {
            return false;
        }
    }

}
