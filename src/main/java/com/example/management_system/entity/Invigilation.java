package com.example.management_system.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Invigilation {
    public static final String UNALLOCATED="未分配";
    public static final String ALLOCATED="已分配";
    public static final String COMPLETED="已完成";
    //默认未分配
    private String  state="未完成";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    @ManyToOne(
            cascade = CascadeType.REMOVE)
    private Exam exam;
    @ManyToOne(
            cascade = CascadeType.REMOVE)
    private User teacher;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            updatable = false,
            insertable = false)
    private LocalDateTime insertTime;
    @Column(columnDefinition = "DATETIME NOT NULL " +
            "DEFAULT CURRENT_TIMESTAMP ON UPDATE " +
            "CURRENT_TIMESTAMP",
            updatable = false, insertable = false)
    private LocalDateTime updateTime;
    //所需监考人数，默认为2
    private int needPersons=2;
    private int allocatedPersons=0;
    private String feedBackMessage="请于考试开始一小时前回复";
    private boolean isOvertime=false;
    //被监考班级
    private String clazz;
    public Invigilation(Exam exam) {
        this.exam = exam;
    }

    public Invigilation( Exam exam, int needPersons,User teacher) {
        this.exam = exam;
        this.needPersons = needPersons;
        this.title=exam.getName()+"考试";
        this.teacher=teacher;
    }
}
