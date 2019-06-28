package com.example.management_system.information_test;

import com.example.management_system.entity.Course;
import com.example.management_system.entity.CourseSchedule;
import com.example.management_system.entity.User;
import com.example.management_system.service.CourseScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherCoursesTest {
    @Autowired
    private CourseScheduleService css;
    @Test
    public void test(){
        User user=new User(1);
        List<Course> courses=css.coursesFindByTeacher(user.getId());
        courses.forEach(c->{
          log.debug(c.getName()+"\n");
        });
    }
}
