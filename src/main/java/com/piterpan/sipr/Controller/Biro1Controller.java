package com.piterpan.sipr.Controller;

import com.piterpan.sipr.Interface.UserInter;
import com.piterpan.sipr.Model.Matakuliah;
import com.piterpan.sipr.Model.Ruang;
import com.piterpan.sipr.Model.User;
import com.piterpan.sipr.RestCont.RuangRestCont;
import com.piterpan.sipr.RestCont.UserRestCont;
import com.piterpan.sipr.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class Biro1Controller {
    @Autowired
    UserRestCont userRestCont;
    @Autowired
    RuangRestCont ruangRestCont;
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
        Ruang ruang = (Ruang) httpSession.getAttribute("ruang");
        if (user == null)
            return "redirect:/login";
        else if (!user.getRole().equals("Biro 1"))
            return "redirect:/";
        else {
            modelMap.addAttribute("userList", userRestCont.getAllUserKaprodi());
//            modelMap.addAttribute("ruang", ruangRestCont.getKapasitas(ruang.getNamaRuang()));
            modelMap.addAttribute("ruangList", ruangRestCont.getActiveRuang());
//            modelMap.addAttribute("ruang", ruangRestCont.getKapasitas());
            modelMap.addAttribute("idUser", user.getIdUser());
            modelMap.addAttribute("username", user.getUsername());
            modelMap.addAttribute("namaUser", user.getNamaUser());
//            modelMap.addAttribute("namaRuang", ruang.getNamaRuang());
//            modelMap.addAttribute("idRuang", ruang.getIdRuang());
            return "/production/biro1-setting-jadwal";
        }
    }

    @RequestMapping("/biro1-setting-ruangan")
    public String biro1SettingRuangan(HttpSession httpSession, ModelMap modelMap){
        User user = (User) httpSession.getAttribute("user");
        Ruang ruang = (Ruang) httpSession.getAttribute("ruang");
        if (user == null)
            return "redirect:/login";
        else if (!user.getRole().equals("Biro 1"))
            return "redirect:/";
        else {
            modelMap.addAttribute("idUser", user.getIdUser());
            modelMap.addAttribute("username", user.getUsername());
            modelMap.addAttribute("namaUser", user.getNamaUser());
//            modelMap.addAttribute("idRuang", ruang.getIdRuang());
            return "/production/biro1-setting-ruangan";
        }
    }
}
