package com.piterpan.sipr.Controller;

import com.piterpan.sipr.Model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class AdminKaprodiController {

    @RequestMapping("/kaprodi")
    public String kaprodi(HttpSession httpSession, ModelMap modelMap){
        User user = (User) httpSession.getAttribute("user");
        if (user == null)
            return "redirect:/login";
        else if (!user.getRole().equals("KA Prodi"))
            return "redirect:/";
        else {
            modelMap.addAttribute("idUser", user.getIdUser());
            modelMap.addAttribute("username", user.getUsername());
            modelMap.addAttribute("namaUser", user.getNamaUser());
            return "/production/kaprodi";
        }
    }


}
