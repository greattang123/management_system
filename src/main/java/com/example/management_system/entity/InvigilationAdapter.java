package com.example.management_system.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InvigilationAdapter {
    private Exam exam;
    private List<User> teachers;

}
