package com.example.management_system.time;

import com.example.management_system.entity.Exam;
import com.example.management_system.service.ExamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

import static java.lang.Math.log;

@Component
@Slf4j
public class Timer {
    @Autowired
    private ExamService es;
    @Scheduled(cron = "0/2 * * * * ?")
    public void test(){
        LocalDate nowTime=LocalDate.now();
        List<Exam> exams=es.examList();
        for(Exam e:exams){
                LocalDate examTime = LocalDate.of(e.getStartTime().getYear(),
                        e.getStartTime().getMonthValue(),
                        e.getStartTime().getDayOfMonth());
                if(nowTime.until(examTime, ChronoUnit.DAYS)==1){
                    log.debug("{}", "hi");
                }
        }
    }
}
