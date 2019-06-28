package com.example.management_system.service;

import com.example.management_system.entity.Course;
import com.example.management_system.entity.CourseSchedule;
import com.example.management_system.repository.CourseScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CourseScheduleService {
    @Autowired
    private CourseScheduleRepository csr;
    public List<Course> coursesFindByTeacher(int tid){
        return csr.coursesFindByTeacher(tid);
    }

}
