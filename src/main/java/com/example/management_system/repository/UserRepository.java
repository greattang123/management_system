package com.example.management_system.repository;

import com.example.management_system.entity.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CustomizedRepository<User, Integer> {
    @Query("select u from User u where u.id=:id")
   //基于ID查找用户
    User findById(@Param("id") int id);
    @Query("select u from User u where u.number=:number")
    //基于工号查找教师
    User findByNumber(@Param("number")String number);
    //获取所有用户列表
    @Query("select u from User u where u.name is not null ")
    List<User> findAllUser();

    /*@Query("select u from User u where u.frequency=:min(frequency)")
    User findLeast();*/
}
