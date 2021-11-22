package com.example.demo.service;

import com.example.demo.beans.UserDetailsBean;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

public class UserDetailsServiceImpl implements UserDetailsService{

    AtomicLong counter = new AtomicLong();

    HashMap<Long, UserDetailsBean> Users = new HashMap<>();

    public HashMap<Long,UserDetailsBean> showUsers(){
        return Users;
    }
    public void addUser(UserDetailsBean user) {
        long id = counter.incrementAndGet();
        user.setId(id);
        Users.put(id, user);
    }
    public UserDetailsBean getUser(Long Id){
        return Users.get(Id);
    }
    public void deleteUser(Long Id) {
        Users.remove(Id);
    }
    public void updateUser(UserDetailsBean user) {
        Users.put(user.getId(), user);
    }

}
