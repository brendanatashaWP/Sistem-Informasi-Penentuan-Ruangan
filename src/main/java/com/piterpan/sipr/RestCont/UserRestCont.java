package com.piterpan.sipr.RestCont;

import com.piterpan.sipr.Exception.ResourceNotFoundException2;
import com.piterpan.sipr.Interface.UserInter;
import com.piterpan.sipr.Model.User;
import com.piterpan.sipr.Service.UserService;
import org.hibernate.annotations.Where;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.xml.ws.Response;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestCont {
    @Autowired
    UserInter userInter;
    @Autowired
    UserService userService;

    //Post new User
    @PostMapping("/post-user")
    public User addUser(@Valid @RequestBody User user) throws NoSuchAlgorithmException {
        user.setPassword(User.passwordEncoder(user.getPassword()));
        return userInter.save(user);
    }

    //Get All users biro 1
    @GetMapping("/get-user-biro1")
    public List<User> getAllUser() {
        return userService.findUserByRole("Biro 1");
    }

    //Get All users kaprodi
    @GetMapping("/get-user-kaprodi")
    public List<User> getAllUserKaprodi() {
        return userService.findUserByRole("KA Prodi");
    }

    //Get By id
    @GetMapping("/get-user/{id}")
    public User getUserById(@PathVariable(value = "id") Integer id){
        return userInter.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException2("User", "idUser", id));
    }

    //Update User
    @PutMapping("/put-user/{id}")
    public User updateUser(@PathVariable(value = "id") Integer id, @Valid @RequestBody User user) throws NoSuchAlgorithmException {
        User userLama = userInter.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException2("User", "idUser", id));
//        userLama.setIdUser(user.getIdUser());
        userLama.setNamaUser(user.getNamaUser());
        userLama.setPassword(User.passwordEncoder(user.getPassword()));
        userLama.setRole(user.getRole());
        userLama.setStatusUser(user.getStatusUser());
        userLama.setUsername(user.getUsername());
        User userBaru = userInter.save(userLama);
        return userBaru;
    }

    //Soft Delete User
    @PutMapping("/delete-user/{id}")
    public User deleteUser(@PathVariable(value = "id") Integer id, @Valid @RequestBody User user) throws NoSuchAlgorithmException {
        User user1 = userInter.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException2("User", "idUser", id));
//        user1.setIdUser(user.getIdUser());
        user1.setNamaUser(user.getNamaUser());
        user1.setPassword(User.passwordEncoder(user.getPassword()));
        user1.setRole(user.getRole());
        if (user.getStatusUser().equals("Active")){
            user1.setStatusUser("Non-Active");
        } else {
            user1.setStatusUser("Active");
        }

        user1.setUsername(user.getUsername());
        User userBaru = userInter.save(user1);
        return userBaru;
    }

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public String login (Model model, String err, String logout){
        if(err != null)
            model.addAttribute("error", "Your username and password is invalid!");

        if(logout != null)
            model.addAttribute("message", "You have been logged out succesfully");

        return "/production/login";
    }

    @GetMapping("/user/username")
    public ResponseEntity<com.piterpan.sipr.Model.Response<User>> findUserByUsername(@RequestParam String username){
        User user = userService.findByUsername(username);
        String message = user != null ? "Find user success" : "Find user failed";
        com.piterpan.sipr.Model.Response<User> response = new com.piterpan.sipr.Model.Response<>(message, user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
