package com.piterpan.sipr.Controller;

import com.piterpan.sipr.Model.User;
import com.piterpan.sipr.RestCont.UserRestCont;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AdminBiro1Controller {

    private final static Logger logger = LoggerFactory.getLogger(AdminBiro1Controller.class);

    UserRestCont userRestCont;


    @RequestMapping(value = "/admin-biro1")
    public String adminBiro1(HttpSession httpSession, ModelMap modelMap){
        User user = (User) httpSession.getAttribute("user");
        if (user == null)
            return "redirect:/login";
        else if (!user.getRole().equals("Admin"))
            return "redirect:/";
        else {
            modelMap.addAttribute("idUser", user.getIdUser());
            modelMap.addAttribute("username", user.getUsername());
            return "production/admin-biro1";
        }
    }

    @RequestMapping(value = "/admin-kaprodi")
    public String adminKaprodi(HttpSession httpSession, ModelMap modelMap){
        User user = (User) httpSession.getAttribute("user");
        if (user == null)
            return "redirect:/login";
        else if (!user.getRole().equals("Admin"))
            return "redirect:/";
        else {
            modelMap.addAttribute("idUser", user.getIdUser());
            modelMap.addAttribute("username", user.getUsername());
            return "production/admin-kaprodi";
        }
    }
}
