package com.piterpan.sipr.Service;

import com.piterpan.sipr.Interface.UserInter;
import com.piterpan.sipr.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private UserInter userInter;

    @Autowired
    public UserService (UserInter userInter){
        this.userInter = userInter;
    }
    public List<User> findUserByRole(String role){
        return userInter.findUsersByRoleIsLike(role);
    }

    public User findByUsername(String username){
        return userInter.findUsersByUsername(username);
    }

//    public int findTopId(){
//        return userInter.findTopIdUser();
//    }
}
