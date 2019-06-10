package com.example.management_system.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class InvigilationAdapter {
    private Exam exam;
    private List<User> teachers;
    private Invigilation invigilation;
}
