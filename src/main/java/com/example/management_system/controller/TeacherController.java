package com.example.management_system.controller;

import com.example.management_system.entity.User;
import com.example.management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Transactional
@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
    @Autowired
    private UserService us;

    @PatchMapping("/update")
    public Map update(@RequestBody User user){
        return Map.of("user", us.updateInformation(user));
    }
}



