package com.example.management_system.controller;

import com.example.management_system.entity.Invigilation;
import com.example.management_system.entity.InvigilationAdapter;
import com.example.management_system.entity.User;
import com.example.management_system.service.AdminService;
import com.example.management_system.service.InvigilationService;
import com.example.management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


import java.util.Map;

@Transactional
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private InvigilationService is;
    @Autowired
    private AdminService as;
    @Autowired
    private UserService us;

    //添加用户
    @PostMapping("/add")
    public Map postUser(@RequestBody User user) {

        return Map.of("newTeacher",  as.addUser(user));
    }

    //修改用户
    @PostMapping("/update")
    public Map patchUser(@RequestBody User user) {
        as.updateUser(user);
        return Map.of("user", user);
    }

    @GetMapping("/listTeachers")
    public Map listTeachers() {
        return Map.of("teachers", us.findAllUser());
    }

    @PostMapping("/add/invigilation")
    public Map addInvigilation(@RequestBody InvigilationAdapter ia) {
//        is.assign(ia);
        return Map.of("invigilation", is.assign(ia, true));
    }

    @GetMapping("/invigilations")
    public Map getInvigilations(){
        return Map.of("invigilations",is.listAdapter() );
    }

    @PostMapping("/update/invigilation")
    public Map updateInvigilation(@RequestBody InvigilationAdapter ia){
        return Map.of("invigilation",is.updateInformation(ia));
    }
}
