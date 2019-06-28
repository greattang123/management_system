package com.example.management_system.repository;

import com.example.management_system.entity.Course;
import com.example.management_system.entity.CourseSchedule;
import com.example.management_system.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseScheduleRepository extends CustomizedRepository<CourseSchedule, Integer>{
    //基于教师ID查找其所授全部课程
    @Query("select cs.course from CourseSchedule  cs  where cs.teacher.id=:tid")
    List<Course> coursesFindByTeacher(@Param("tid") int tid);
}
