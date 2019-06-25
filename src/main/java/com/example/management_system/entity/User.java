package com.example.management_system.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
/**
 * 用户有三种：教师，管理员/专业主任,
 */
public class User {
    public static final int USER_AUTHORITY = 1;
    public static final int ADMIN_AUTHORITY = 2;
    public static final int SUPER_ADMIN_AUTHORITY = 3;
    //未声明时默认为1(教师用户权限)
    private int authority = 1;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //姓名

    private String name;
    //职称(可更新)
    private String title;
    //简介(可更新)
    @Column(columnDefinition = "TEXT")
    private String intro;
    //手机号(唯一,可更新)
    @Column(unique = true)
    private String phoneNumber;

    //工号(唯一)
    @Column(unique = true)
    private String number;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    //禁止password序列化
    private String password;
    //监考次数，初始化时为0
    private int frequency=0;
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            updatable = false,
            insertable = false)
    private LocalDateTime insertTime;

    @OneToMany
    private List<CourseSchedule> cs;

    public User(int id) {
        this.id = id;
    }

    public User(String name, String number, String password) {
        this.name = name;
        this.number = number;
        this.password = password;
    }

    public User(String name, String number) {

        this.name = name;
        this.number = number;
    }

    public User(String number) {
        this.number = number;
    }
}
