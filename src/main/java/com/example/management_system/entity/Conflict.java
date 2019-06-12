package com.example.management_system.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Conflict {
    private InvigilationAdapter invigilation;
    private String conflictMessage;
}
