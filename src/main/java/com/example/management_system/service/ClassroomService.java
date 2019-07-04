package com.example.management_system.service;

import com.example.management_system.entity.Classroom;
import com.example.management_system.repository.ClassroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ClassroomService {
    @Autowired
    private ClassroomRepository cr;
    public List<Classroom> getClassrooms(){
        return cr.allClassroom();
    }
}
