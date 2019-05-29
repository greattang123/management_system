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
    public static final int UNALLOCATED=0;
    public static final int ALLOCATED=1;
    public static final int COMPLETED=2;
    //默认未分配
    private int state=0;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY,
            cascade = CascadeType.REMOVE)
    private Exam exam;
    @ManyToOne(fetch = FetchType.LAZY,
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

    public Invigilation(Exam exam) {
        this.exam = exam;
    }


}
