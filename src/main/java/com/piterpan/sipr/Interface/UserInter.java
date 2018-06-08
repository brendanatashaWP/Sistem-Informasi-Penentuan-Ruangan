package com.piterpan.sipr.Interface;

import com.piterpan.sipr.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInter extends JpaRepository<User, Integer> {
    public List<User> findUsersByRoleIsLike(String role);
    public User findUsersByUsername(String username);
}
