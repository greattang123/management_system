package com.example.management_system.information_test;

import com.example.management_system.entity.Exam;
import com.example.management_system.service.ExamService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ExamTest {
    @Autowired
    private ExamService es;

    @Test
    public void findExamTest() {
        int classroom = 303;
        LocalDateTime startTime = LocalDateTime.parse("2019-06-09T14:00");
        Exam exam = es.findExamByTimeAndClassroom(startTime, classroom);
        if (exam != null)
            log.debug("{}", exam.getClass().getName());
        log.debug("{}", exam);
    }
}
