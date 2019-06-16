package com.example.management_system.service;

import com.example.management_system.component.MyException;
import com.example.management_system.component.Utils;
import com.example.management_system.entity.Exam;
import com.example.management_system.repository.ExamRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@Transactional
public class ExamService {
    @Autowired
    private ExamRepository er;

    boolean result;

    public List<Exam> examList() {
        return er.examList();
    }

    public void deleteById(int eid){
        er.deleteById(eid);
    }


    public Exam findExamByTimeAndClassroom(LocalDateTime startTime, int classroom) {
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
        if(exam.getStartTime().isAfter(exam.getOverTime())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "结束时间不能早于开始时间");
        }else{
            if (!isExamConflict(exam)) {
                er.save(exam);
            }else{
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "考试冲突");
            }
        }
        return exam;
    }

    /**
     * 判断考试是否冲突
     * @param exam
     * @return
     */
    public boolean isExamConflict(Exam exam){
        result = false;
        List<Exam> exams = er.examList();
        for (Exam e: exams) {
            if (Utils.isAgainst(exam.getStartTime(), exam.getOverTime(), e.getStartTime(), e.getOverTime())
            && e.getClassroom()==exam.getClassroom()) {
                result = true;
                break;
            }
        }
        return result;
    }
}

