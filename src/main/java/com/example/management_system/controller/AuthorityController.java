package com.example.management_system.controller;

import com.example.management_system.entity.User;
import com.example.management_system.service.AuthorityService;
import com.example.management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Transactional
@RestController
@RequestMapping("/api")
public class AuthorityController {
    @Autowired
    private AuthorityService as;
    @Autowired
    private UserService us;
    @PostMapping("/authority}")
    public Map patchAuthority(@RequestBody User user){
        as.updateAuthority(user.getId());
        return Map.of("user",us.getUser(user.getId()));
    }
}
