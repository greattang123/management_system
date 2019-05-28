package com.example.management_system.repository;

import com.example.management_system.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CustomizedRepository<User, Integer> {
    @Query("select u from User u where u.id=:id")
    User findById(@Param("id") int id);
    @Query("select u from User u where u.number=:number")
    User findByNumber(@Param("number")String number);
}
