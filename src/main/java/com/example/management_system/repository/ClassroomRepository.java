package com.example.management_system.repository;

import com.example.management_system.entity.Classroom;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClassroomRepository extends CustomizedRepository<Classroom,Integer> {

    @Query("select c from Classroom c order by c.name")
    List<Classroom> allClassroom();
}
