package com.example.management_system.repository;

import com.example.management_system.entity.Invigilation;
import com.example.management_system.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvigilationRepository extends CustomizedRepository<Invigilation,Integer> {
    //通过教师查找监考信息
    @Query("select i from Invigilation i where i.teacher.id=:tid ")
    List<Invigilation> listByTeacher(@Param("tid") int tid);

   /* @Query("select u from User u where u.id=:id")
        //基于ID查找用户
    User findById(@Param("id") int id);*/

}
