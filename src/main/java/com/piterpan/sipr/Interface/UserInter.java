package com.piterpan.sipr.Interface;

import com.piterpan.sipr.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInter extends JpaRepository<User, Integer> {
    public List<User> findUsersByRoleIsLike(String role);

//    @Query("select u from user u where u.role = :role")
//    public List<User> findBiro1(@Param("role") String role);
}
