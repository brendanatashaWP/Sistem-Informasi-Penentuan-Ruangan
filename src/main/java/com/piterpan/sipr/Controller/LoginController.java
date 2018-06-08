package com.piterpan.sipr.Controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.piterpan.sipr.Model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

@Controller
public class LoginController {

    private final static Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    private final static String base_url = "http://localhost:8090:/api/";

    private ObjectMapper objectMapper;
    private RestTemplate restTemplate;

    private LoginController() {
        objectMapper = new ObjectMapper();
        restTemplate = new RestTemplate();
    }

    @GetMapping(value="/")
    public String login(HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        if (user == null)
            return "redirect:/login";
        else {
            switch (user.getRole()){
                case "Admin":
                    return "redirect:/admin-biro1";
                case "Biro 1":
                    return "redirect:/biro1-dashboard";
                case "KA Prodi":
                    return "redirect:/kaprodi";
                default:
                    return "redirect:/error404";
            }
        }
    }

    @GetMapping("/login")
    public String loginNormal(HttpSession httpSession){
        User user = (User) httpSession.getAttribute("user");
        return user != null ? "redirect:/" : "/production/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<com.piterpan.sipr.Model.Response<User>> loginNormal (@RequestBody Map<String, String> params, HttpSession httpSession) throws NoSuchAlgorithmException {
        String url = "http://localhost:8090/api/" + "user/username";
        String username = params.get("username");
        String password = params.get("password");
        String message = null;
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(url)
                .queryParam("username", username);
        ResponseEntity<String > responseEntity = restTemplate.exchange(uriComponentsBuilder.toUriString(),
                HttpMethod.GET, null, String.class);
        com.piterpan.sipr.Model.Response<User> myResponse;
        User user = null;

        try {
            myResponse = objectMapper.readValue(responseEntity.getBody(), new TypeReference<com.piterpan.sipr.Model.Response<User>>(){

            });
            user = myResponse.getData();

            if (user == null)
                message = "Invalid username";
            else {
                password = User.passwordEncoder(password);
                if (password.equals(user.getPassword())){
                    message = "Login Success!";
                    httpSession.setAttribute("user", user);

                    LOGGER.info("User" + user.getUsername() + " logged in");
                } else {
                    message = "Invalid password";
                    System.out.println(params.get("password"));
                    System.out.println(password);
                    System.out.println(user.getPassword());
                    user = null;
                }
            }
        } catch (Exception e){
            message = "Internal server error";
            LOGGER.error(e.getMessage());
        } finally {
            myResponse = new com.piterpan.sipr.Model.Response<>(message, user);
        }
        return new ResponseEntity<>(myResponse, HttpStatus.OK);
    }

    @PostMapping("/logout")
    @ResponseBody
    public ResponseEntity<com.piterpan.sipr.Model.Response<Integer>> logout (HttpSession httpSession){
        String message;
        Integer data;
        User user = (User) httpSession.getAttribute("user");

        if (user != null) {
            //kalau logout user dari sessionnya dihapus
            httpSession.removeAttribute("user");
            message = "Logout success";
            data = 1;

            LOGGER.info("User " + user.getUsername() + " logged out");
        } else {
            message = "Logout failed";
            data = 0;
        }

        com.piterpan.sipr.Model.Response<Integer> response = new com.piterpan.sipr.Model.Response<>(message, data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
