package com.example.demo.service;

import com.example.demo.beans.UserDetailsBean;

import com.example.demo.beans.UserLoginBean;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    HashMap<String, UserDetailsBean> users = new HashMap<>();
    UserLoginBean userDetail = new UserLoginBean();

    public boolean checkDuplication(UserDetailsBean user) {
       if(users.containsKey(user.getEmailId())) {
           return true;
       }
       else return false;
    }

    public UserLoginBean getUserDetail() {
        return userDetail;
    }

    public boolean checkAdmin(String em, String pass) {
        if(em.equals("admin@admin") && pass.equals("pass")){
            userDetail.setEmailId(em);
            userDetail.setPassword(pass);
            return true;
        }
        else return false;
    }

    public boolean checkUser(String em, String pass) {
        if(checkAdmin(em,pass)){
            return true;
        }
        if(users.containsKey(em)){
            if(users.get(em).getPassword().equals(pass)) {
                userDetail.setEmailId(em);
                userDetail.setPassword(pass);
                return true;
            }
            else return false;
        }
        else return false;
    }

    @Override
    public HashMap<String,UserDetailsBean> showAllUsers(){
        return users;
    }

    @Override
    public boolean addUser(UserDetailsBean user) {
        if(!checkDuplication(user)) {
            SimpleDateFormat ft =
                    new SimpleDateFormat ("dd/MM/yyyy E 'at' hh:mm:ss a zzz");
            user.setDate(ft.format(new Date()));
            users.put(user.getEmailId(), user);
            userDetail.setEmailId(user.getEmailId());
            userDetail.setPassword(user.getPassword());
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
            SimpleDateFormat ft =
                    new SimpleDateFormat ("dd/MM/yyyy E 'at' hh:mm:ss a zzz");
            user.setDate(ft.format(new Date()));
            users.put(user.getEmailId(), user);
            users.remove(Id);
            return true;
        }
        else if (users.containsKey(user.getEmailId())) {
            SimpleDateFormat ft =
                    new SimpleDateFormat ("dd/MM/yyyy E 'at' hh:mm:ss a zzz");
            user.setDate(ft.format(new Date()));
            users.replace(user.getEmailId(), user);
            return true;
        }
        else {
            return false;
        }
    }

}
