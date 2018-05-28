package com.piterpan.sipr.Interface;

import com.piterpan.sipr.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInter extends JpaRepository<User, Integer> {
}
