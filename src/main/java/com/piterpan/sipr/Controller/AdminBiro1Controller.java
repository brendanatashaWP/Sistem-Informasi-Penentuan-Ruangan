package com.piterpan.sipr.Controller;

import com.piterpan.sipr.RestCont.UserRestCont;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminBiro1Controller {

//    private final static Logger logger = LoggerFactory.getLogger(AdminBiro1Controller.class);

    UserRestCont userRestCont;


    @RequestMapping(value = "/admin-biro1")
    public String adminBiro1(){
        return "production/admin-biro1";
    }

    @RequestMapping(value = "/admin-kaprodi")
    public String adminKaprodi(){
        return "production/admin-kaprodi";
    }
}
