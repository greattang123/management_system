package com.example.management_system.service;

import com.example.management_system.entity.User;
import com.example.management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AdminService {
    @Autowired
    private UserRepository ur;
    @Autowired
    private UserService us;
    //添加用户
    public User addUser(User user){
        return ur.saveAndFlush(user);
    }
    public User updateUser(User user){
      return  us.updateInformation(user);
    }
}
