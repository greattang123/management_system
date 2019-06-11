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

    //通过ID查找监考信息
    @Query("select i from Invigilation i where i.id=:iid")
    Invigilation findById(@Param("iid")int iid);

    @Query("select i from Invigilation i where i.exam.id=:eid ")
    List<Invigilation> listByExam(@Param("eid") int eid);

    @Query(" from Invigilation ")
    List<Invigilation>list();

    @Query("select i.teacher from Invigilation i where i.exam.id=:eid ")
    List<User> getTeachersByExam(@Param("eid") int eid);

    //删除已分配的监考任务
    @Query("delete from Invigilation i where i.exam.id=:eid")
    void deleteByExam(@Param("eid")int eid);


}
