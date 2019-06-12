package com.example.management_system.time;

import com.example.management_system.entity.Exam;
import com.example.management_system.entity.Invigilation;
import com.example.management_system.service.ExamService;
import com.example.management_system.service.InvigilationService;
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
    @Autowired
    private InvigilationService is;
    private String message;
    @Scheduled(cron = "0/10 * * * * ?")
    public void test(){
        LocalDate nowTime=LocalDate.now();
        List<Exam> exams=es.examList();
//        List<Invigilation>list=is.list();
        for(Exam e:exams){
            message = e.getName() + "监考任务" + "\n开始时间：" +
                    e.getStartTime() +
                    "\n结束时间：" +
                   e.getOverTime() +
                    "\n监考地点：" +
                    e.getClassroom() +
                    "\n监考教师：";
            List<Invigilation>invigilations=is.findByExam(e.getId());
            LocalDate examTime = LocalDate.of(e.getStartTime().getYear(),
                    e.getStartTime().getMonthValue(),
                   e.getStartTime().getDayOfMonth());
            if(nowTime.until(examTime, ChronoUnit.DAYS)==1){
               invigilations.forEach(t->{
                   message+=t.getTeacher().getName()+" ";
               });
               log.debug(message);
            }
        }
       /* for(Invigilation i:list){
            Exam exam=i.getExam();
            message = exam.getName() + "监考任务" + "\n开始时间：" +
                    exam.getStartTime() +
                    "\n结束时间：" +
                    exam.getOverTime() +
                    "\n监考地点：" +
                    exam.getClassroom() +
                    "\n监考教师：";
                LocalDate examTime = LocalDate.of(exam.getStartTime().getYear(),
                       exam.getStartTime().getMonthValue(),
                        exam.getStartTime().getDayOfMonth());
                if(nowTime.until(examTime, ChronoUnit.DAYS)==1){
                    message+=i.getTeacher().getName()+" ";
                    log.debug("{}",
                            i.getExam().getName()+"监考任务"+"\n开始时间：" +
                            i.getExam().getStartTime()+ "\n结束时间：" +
                            i.getExam().getOverTime()+"\n监考地点：" +
                            i.getExam().getClassroom()+"\n监考教师："+
                            i.getTeacher().getName());

                }
        }*/
    }
}
