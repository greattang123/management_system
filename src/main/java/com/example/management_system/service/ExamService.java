package com.example.management_system.service;

import com.example.management_system.component.MyException;
import com.example.management_system.entity.Exam;
import com.example.management_system.repository.ExamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional
public class ExamService {
    @Autowired
    private ExamRepository er;

    public List<Exam> examList() {
        return er.examList();
    }

    public void deleteById(int eid){
        er.deleteById(eid);
    }


    public Exam findExamByTimeAndClassroom(LocalDateTime startTime, int classroom) {
//        return er.findByTimeAndClassroom(startTime, classroom);
       /* Exam exam=null;
        exam=findExamByTimeAndClassroom(startTime, classroom);
        return exam;*/
      /* if(findExamByTimeAndClassroom(startTime, classroom)!=null){
           return findExamByTimeAndClassroom(startTime, classroom);
       }*/
      //如果数据库中未找到与该段时间和教室相匹配的考试，则返回null
        Exam exam = null;



        try {
            exam = er.findByTimeAndClassroom(startTime, classroom);
        } catch (Exception e) {
            log.debug("{}", e);
        }
        return exam;
    }

    //保证所有考试不冲突
    public Exam addExam(Exam exam) {
        LocalDateTime startTime = exam.getStartTime();
        int classroom = exam.getClassroom();
        if (findExamByTimeAndClassroom(startTime, classroom) == null) {
            er.save(exam);
            return exam;
        }
        return null;
    }
}

