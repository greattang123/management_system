package com.example.management_system.service;

import com.example.management_system.entity.User;
import com.example.management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository ur;

    //基于工号获取教师详细信息
    public User getUser(String number) {
        return ur.findByNumber(number);
    }
    /*public void updateTitle(String newTitle,String number){
     *//*User user=getUser(number);
        user.setTitle(newTitle);*//*
        getUser(number).setTitle(newTitle);
    }
    public  void updateIntro(String newIntro,String number){
        getUser(number).setIntro(newIntro);
    }
    public  void updatePhoneNumber(String newPhoneNumber,String number){
        getUser(number).setPhoneNumber(newPhoneNumber);
    }*/


    //修改个人信息
    public User updateInformation(User user) {
        return Optional.ofNullable(ur.findById(user.getId()))
                .or(() -> {
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "无权限");
                })
                .map(a -> ur.saveAndFlush(user))
                .get();
    }

    public List<User> findAllUser(){
        return ur.findAllUser();
    }

}


