package com.example.management_system.service;

import com.example.management_system.entity.Exam;
import com.example.management_system.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Service
@Transactional
public class ExamService {
    @Autowired
    private ExamRepository er;
    public Exam findExamByTimeAndClassroom(LocalDateTime startTime,int classroom){
        return er.findByTimeAndClassroom(startTime, classroom);
    }
    //保证所有考试不冲突
    public Exam addExam(Exam exam){
        LocalDateTime startTime=exam.getStartTime();
        int classroom=exam.getClassroom();
        if(findExamByTimeAndClassroom(startTime,classroom)==null){
            er.save(exam);
            return exam;
        }
        return null;
    }
}

