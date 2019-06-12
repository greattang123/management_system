package com.example.management_system.service;

import com.example.management_system.entity.Exam;
import com.example.management_system.entity.Invigilation;
import com.example.management_system.entity.InvigilationAdapter;
import com.example.management_system.entity.User;
import com.example.management_system.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@Transactional
public class AdminService {
    @Autowired
    private UserRepository ur;
    @Autowired
    private UserService us;
    @Autowired
    private InvigilationService is;
    @Autowired
    private PasswordEncoder pe;
    //管理员添加用户
    public User addUser(User user) {
        user.setPassword(pe.encode(user.getNumber()));
        return ur.saveAndFlush(user);
    }

    //修管理员修改用户信息
    public User updateUser(User user) {
        return us.updateInformation(user);
    }

    //管理员分派监考任务
    public void addInvigilation(InvigilationAdapter invigilation){
       /* int allocateTimes=invigilation.getAllocatedPersons();  //state初始为0
        int totalTimes=invigilation.getNeedPersons();//needPersons初始为2
        int rest=totalTimes-allocateTimes;
        for(int i=0;i<rest;i++)*/

            is.assign(invigilation);

    }

    public void sendMessage(Exam exam){
        List<Invigilation> allocated=is.findByExam(exam.getId());
        User teacher=allocated.get(0).getTeacher();
        log.debug("{}",exam.getStartTime());
        log.debug("{}",exam.getClassroom());
        for(Invigilation i:allocated){
            log.debug("{}",i.getTeacher().getName());
        }
        log.debug("{}",teacher.getFrequency());
    }

    public boolean isConflict(User teacher,Exam exam){
        if(teacher.getFrequency()!=0){
            List<Invigilation>list=is.findByTeacher(teacher.getId());
            for(Invigilation i:list){
                if(i.getExam().getStartTime().toString().equals(exam.getStartTime().toString())){
//                   log.debug("Hi");
                    return true;
                }
            }
        }

        return false;
    }

}
