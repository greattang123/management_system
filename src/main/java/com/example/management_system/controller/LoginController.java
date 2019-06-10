package com.example.management_system.controller;

import com.example.management_system.component.EncryptComponent;
import com.example.management_system.entity.User;
import com.example.management_system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api")
public class LoginController {
    private static final String USER_ROLE = "15ade689eff335c";
    private static final String ADMIN_ROLE = "ff2587edaa6828bde3";
    private static final String SUPER_ADMIN_ROLE = "2356afcd332d";
    @Autowired
    private UserService us;
    @Autowired
    private PasswordEncoder pe;
    @Autowired
    private EncryptComponent ec;

    @PostMapping("/login")
    public void login(@RequestBody User user, HttpServletResponse response) {
        Optional.ofNullable(us.getUser(user.getNumber()))
                .ifPresentOrElse(u -> {
                    if (!pe.matches(user.getPassword(), u.getPassword())) {
                        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户名或密码错误");
                    }
                    Map map = Map.of("uid", u.getId(), "aid", u.getAuthority());
                    //生成加密token
                    String token = ec.encrypt(map);
                    //Header创建自定义属性token和role
                    response.setHeader("token", token);
                    String role = null;
                    if (u.getAuthority() == User.USER_AUTHORITY) {
                        role = USER_ROLE;
                    } else if (u.getAuthority() == User.ADMIN_AUTHORITY) {
                        role = ADMIN_ROLE;
                    }else if(u.getAuthority()==User.SUPER_ADMIN_AUTHORITY){
                        role=SUPER_ADMIN_ROLE;
                    }
                    response.setHeader("role", role);
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "用户名或密码错误");
                });
    }

}
