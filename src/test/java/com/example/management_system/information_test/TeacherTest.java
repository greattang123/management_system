package com.example.management_system.information_test;

import com.example.management_system.entity.User;
import com.example.management_system.repository.UserRepository;
import com.example.management_system.service.AdminService;
import com.example.management_system.service.AuthorityService;
import com.example.management_system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherTest {
    @Autowired
    private UserRepository ur;
    @Autowired
    private AdminService as;
    @Autowired
    private UserService us;
    @Autowired
    private AuthorityService aus;

    @Test
    public void addUserTest() {
        User user = new User("Sun", "1", "1");
        as.addUser(user);
        log.debug(user.getName());
    }

    @Test
    public void updateTest() {
        User user = new User(1);
        user.setNumber("5");
        us.updateInformation(user);
    }

    @Test
    public void updateAuthorityTest() {
        User user = us.getUser("5");
        log.debug("{}", user.getAuthority());
        log.debug("{}", aus.updateAuthority("5").getAuthority());
    }
}
