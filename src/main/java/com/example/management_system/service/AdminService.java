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
    @Autowired
    private InvigilationService is;

    //管理员添加用户
    public User addUser(User user) {
        return ur.saveAndFlush(user);
    }

    //修管理员修改用户信息
    public User updateUser(User user) {
        return us.updateInformation(user);
    }
}
