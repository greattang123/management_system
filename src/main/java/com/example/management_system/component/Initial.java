package com.example.management_system.component;

import com.example.management_system.entity.User;
import com.example.management_system.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@Transactional
public class Initial implements InitializingBean {
    @Autowired
    private PasswordEncoder pe;
    @Autowired
    private UserRepository ur;

    @Override
    public void afterPropertiesSet() throws Exception {
        if(ur.count()==0){
            User user=new User();
            user.setAuthority(User.SUPER_ADMIN_AUTHORITY);
            user.setName("GT");
            user.setNumber("666");
            user.setPassword(pe.encode(user.getNumber()));
            ur.save(user);
           /* User user=new User();
            user.setAuthority(User.USER_AUTHORITY);
            user.setName("Sun");
            user.setNumber("3");
            user.setPassword(pe.encode(user.getNumber()));
            ur.save(user);

            User user1=new User();
            user1.setAuthority(User.ADMIN_AUTHORITY);
            user1.setName("Earth");
            user1.setNumber("2");
            user1.setPassword(pe.encode(user1.getNumber()));
            ur.save(user1);*/
        }
    }
}
