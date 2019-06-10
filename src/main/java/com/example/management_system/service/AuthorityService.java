package com.example.management_system.service;

import com.example.management_system.entity.User;
import com.example.management_system.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AuthorityService {
    @Autowired
    private UserRepository ur;
    @Autowired
    private UserService us;

    //修改用户权限
    public User updateAuthority(int id) {
        User user = us.getUser(id);
        //添加普通用户为管理员
        if (user.getAuthority() == User.USER_AUTHORITY) {
            user.setAuthority(User.ADMIN_AUTHORITY);
        } else { //删除管理员(权限)
            user.setAuthority(User.USER_AUTHORITY);
        }
        ur.saveAndFlush(user);
        return user;
    }
}
