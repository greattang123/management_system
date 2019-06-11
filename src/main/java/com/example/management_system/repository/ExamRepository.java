package com.example.management_system.repository;

import com.example.management_system.entity.Exam;
import com.example.management_system.entity.User;
import org.apache.tomcat.jni.Local;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExamRepository extends CustomizedRepository<Exam, Integer> {
    //所有考试信息
    @Query("from Exam ")
    List<Exam> examList();

    @Query("select e from Exam e where e.id=:id")
    Exam findById(@Param("id") int id);

    //基于考试编号查找考试
    @Query("select e from Exam e where e.number=:number")
    Exam findByNumber(@Param("number") int number);

    //基于考试名查找考试
    @Query("select e from Exam e where e.name=:name")
    List<Exam> findByName(@Param("name") String name);

    //基于考试时间和教室查找考试
    @Query("select e from Exam e where e.startTime=:startTime and " +
            "e.classroom=:classroom")
    Exam findByTimeAndClassroom(@Param("startTime") LocalDateTime startTime,
                                @Param("classroom") int classroom);

    @Modifying
    @Query("delete from Exam e where e.id=:eid")
    void deleteById(@Param("eid")int eid);
}

