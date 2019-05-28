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
       public User updateAuthority(String number){
        User user=us.getUser(number);
        if(user.getAuthority()==User.USER_AUTHORITY){
            user.setAuthority(User.ADMIN_AUTHORITY);
        }
        else {
            user.setAuthority(User.USER_AUTHORITY);
        }
        ur.saveAndFlush(user);
      return   ur.refresh(user);//add
    }
}
