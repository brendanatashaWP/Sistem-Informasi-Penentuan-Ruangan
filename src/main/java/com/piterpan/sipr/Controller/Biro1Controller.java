package com.piterpan.sipr.Controller;

import com.piterpan.sipr.Model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class Biro1Controller {

    @RequestMapping("/biro1-dashboard")
    public String biro1Dashboard(HttpSession httpSession, ModelMap modelMap){
        User user = (User) httpSession.getAttribute("user");
        if (user == null)
            return "redirect:/login";
        else if (!user.getRole().equals("Biro 1"))
            return "redirect:/";
        else {
            modelMap.addAttribute("idUser", user.getIdUser());
            modelMap.addAttribute("username", user.getUsername());
            modelMap.addAttribute("namaUser", user.getNamaUser());
            return "/production/biro1-dashboard";
        }
    }

    @RequestMapping("/biro1-setting-jadwal")
    public String biro1SettingJadwal(HttpSession httpSession, ModelMap modelMap){
        User user = (User) httpSession.getAttribute("user");
        if (user == null)
            return "redirect:/login";
        else if (!user.getRole().equals("Biro 1"))
            return "redirect:/";
        else {
            modelMap.addAttribute("idUser", user.getIdUser());
            modelMap.addAttribute("username", user.getUsername());
            modelMap.addAttribute("namaUser", user.getNamaUser());
            return "/production/biro1-setting-jadwal";
        }
    }

    @RequestMapping("/biro1-setting-ruangan")
    public String biro1SettingRuangan(HttpSession httpSession, ModelMap modelMap){
        User user = (User) httpSession.getAttribute("user");
        if (user == null)
            return "redirect:/login";
        else if (!user.getRole().equals("Biro 1"))
            return "redirect:/";
        else {
            modelMap.addAttribute("idUser", user.getIdUser());
            modelMap.addAttribute("username", user.getUsername());
            modelMap.addAttribute("namaUser", user.getNamaUser());
            return "/production/biro1-setting-ruangan";
        }
    }
}
