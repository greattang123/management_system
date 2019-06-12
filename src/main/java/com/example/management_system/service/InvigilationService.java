package com.example.management_system.service;

import com.example.management_system.entity.*;
import com.example.management_system.repository.InvigilationRepository;
import com.example.management_system.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.apache.bcel.generic.RET;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class InvigilationService {
    @Autowired
    private AdminService as;
    @Autowired
    private ExamService es;
    @Autowired
    private InvigilationRepository ir;
    @Autowired
    private UserService us;
    @Autowired
    private UserRepository ur;
    @Autowired
    private PasswordEncoder pe;
    //监考消息
    private String message;
    private String conflictMessage="";

    public void deleteByExam(int eid) {
        ir.deleteByExam(eid);
    }

    public Invigilation findById(int iid) {
        return ir.findById(iid);
    }

    public List<Invigilation> findByTeacher(int tid) {
      //控制台输出不友好1
        /*  log.debug("findByTeacher");
        ir.listByTeacher(tid).forEach(invigilation -> {
            log.debug(invigilation.getExam().getName());
        });*/
        return ir.listByTeacher(tid);
    }

    public List<Invigilation> findByExam(int eid) {
        return ir.listByExam(eid);
    }

    //查看所有监考信息，状态，分配结果
    public void checkAllInvigilationByTeacher(int tid) {
        List<Invigilation> invigilation = findByTeacher(tid);
        for (Invigilation i : invigilation) {
            //直接输出监考名称（监考信息）
            log.debug(i.getTitle());
            //输出监考完成状态
            log.debug(i.getState());
           /*每条监考信息只对应一个老师和考试，但一门考试还有其他监考老师
            ，故先查找该监考信息对应考试，再查找该考试对应的监考信息List，然后在每一个List里查找教师*/
            Exam exam = i.getExam();
            List<Invigilation> list = findByExam(exam.getId());
            for (Invigilation in : list) {
                log.debug(in.getTeacher().getName());
            }
        }
    }

    /**
     * @param
     */
   /* public void assign(Invigilation invigilation) {
        Exam exam = invigilation.getExam();
        LocalDateTime startTime = exam.getStartTime();
        int classroom = exam.getClassroom();
        //所有教师信息列表
        List<User> users = us.findAllUser();
        //可分派监考任务的教师列表，初始为空
        List<User> userAvailable = null;
        //bestUsers 监考次数为0的教师列表
        List<User> bestUsers = users.stream()
                .filter(u -> u.getFrequency() == 0)
                .collect(Collectors.toList());
        //secondUsers 监考次数全不为0的教师列表
        List<User> secondUsers = users.stream()
                .filter(u -> u.getFrequency() != 0)
                .collect(Collectors.toList());
        if (bestUsers != null) { //若监考次数为0，则直接分配监考任务
            User u = bestUsers.get(0);
            invigilation.setTeacher(u);
            u.setFrequency(1);
            us.updateInformation(u);
            invigilation.setState(invigilation.getState()+1);
        } else {
            for (User u : secondUsers) {//保证userAvailable没有监考次数为0，且合要求的教师
                List<Invigilation> invigilations = ir.listByTeacher(u.getId());
                for (Invigilation i : invigilations) {
                    if (i.getExam().getStartTime() != startTime || i.getExam().getClassroom() != classroom) {
                        userAvailable.add(u);
                    }
                }
            }
            int times = 50;
            //找出最少监考次数
            for (User u : userAvailable) {
                if (u.getFrequency() < times) {
                    times = u.getFrequency();
                }
            }
            for (User u : userAvailable) {
                if (u.getFrequency() == times) {
                    //若同一时间已安排其他考试，则发出冲突提示
                    List<Invigilation> invigilations = ir.listByTeacher(u.getId());
                    for (Invigilation i : invigilations) {
                        if (i.getExam().getStartTime() == startTime) {
                            log.debug("请注意，同一时间安排了两门考试");
                        }
                    }
                    //给监考次数最少的教师分配监考任务
                    invigilation.setTeacher(u);
                    u.setFrequency(++times);
                    us.updateInformation(u);
                    invigilation.setState(invigilation.getState()+1);
                }
            }
        }

    }*/
    public Conflict assign(InvigilationAdapter ia) {
        Exam exam = ia.getExam();
//        log.debug("{}", exam.getStartTime());
        es.addExam(exam);
        List<User> teachers = ia.getTeachers();
        //监考消息
        message = exam.getName() + "监考任务" + "\n开始时间：" +
                exam.getStartTime() +
                "\n结束时间：" +
                exam.getOverTime() +
                "\n监考地点：" +
                exam.getClassroom() +
                "\n监考教师：";
        conflictMessage="";
        if (teachers.size() != 0) {
            User teacherRandom = teachers.get(0);
            teachers.forEach(t -> {
                //分配监考并将对应监考教师监考次数加1
                Invigilation invigilation = new Invigilation();
                invigilation.setExam(exam);
                invigilation.setTitle(exam.getName() + "监考");
                invigilation.setTeacher(t);
                t.setFrequency(t.getFrequency() + 1);
                ir.saveAndFlush(invigilation);
                t.setPassword(pe.encode(t.getNumber()));
                ur.saveAndFlush(t);
                message += t.getName() + " ";

                if( as.isConflict(t, exam)){
                    conflictMessage+=(t.getName()+" ");
                }
            });
            message += "\n" + teacherRandom.getName() + "同志，您当前监考次数为：" + teacherRandom.getFrequency();
            log.debug(message);
        } else {
            Invigilation invigilation = new Invigilation();
            invigilation.setExam(exam);
            invigilation.setTitle(exam.getName() + "监考");
            ir.saveAndFlush(invigilation);
        }
      conflictMessage= conflictMessage!=null? conflictMessage+"时间冲突":null;
        ia.getExam().setId(exam.getId());
        Conflict conflict=new Conflict(ia,conflictMessage);
        return conflict;
    }

    public List<Invigilation> list() {
        List<Invigilation> list = new ArrayList<>();
        list = ir.list();
        list.forEach(i -> {
            i.setOvertime(isOvertime(i));
        });
        return list;
    }

    public List<InvigilationAdapter> listAdapter() {
        List<InvigilationAdapter> adapters = new ArrayList<>();
        List<Exam> exams = es.examList();
        for (Exam e : exams) {
            InvigilationAdapter adapter = new InvigilationAdapter();
            adapter.setExam(e);
            adapter.setTeachers(ir.getTeachersByExam(e.getId()));
            List<Invigilation> invigilations = ir.listByExam(e.getId());
//            log.debug("length:{}", invigilations.size());
            Invigilation invigilation = invigilations.get(0);
            adapter.setInvigilation(invigilation);
            adapters.add(adapter);
        }
        return adapters;
    }

    //修改监考信息
    public Conflict updateInformation(InvigilationAdapter ia) {
        Exam exam = ia.getExam();
        ir.deleteByExam(exam.getId());
//      es.deleteById(exam.getId());
       return assign(ia);

      /*  return Optional.ofNullable(ir.findById(invigilation.getId()))
                .or(() -> {
                    throw new ResponseStatusException(HttpStatus.FORBIDDEN, "无权限");
                })
                .map(a -> ir.saveAndFlush(invigilation))
                .get();*/
    }

    //回复消息
    public Invigilation feedBackMessage(Invigilation invigilation) {
        Invigilation i = ir.findById(invigilation.getId());
        i.setFeedBackMessage(invigilation.getFeedBackMessage());
        ir.saveAndFlush(i);
        return i;
    }

    public Boolean isOvertime(Invigilation invigilation) {
        if (invigilation.isOvertime() == true) return true;
        LocalDateTime nowTime = LocalDateTime.now();
        Exam e = invigilation.getExam();
        LocalDateTime examTime = LocalDateTime.of(e.getStartTime().getYear(),
                e.getStartTime().getMonthValue(),
                e.getStartTime().getDayOfMonth(),
                e.getStartTime().getHour(),
                e.getStartTime().getMinute());
        if (nowTime.until(examTime, ChronoUnit.HOURS) <= 1) {
            if ("请于考试开始一小时前回复".equals(invigilation.getFeedBackMessage())) {
                invigilation.setOvertime(true);
                ir.saveAndFlush(invigilation);
                return true;
            }
        }
        return false;
    }
}

