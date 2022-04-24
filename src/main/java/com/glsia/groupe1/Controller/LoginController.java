package com.glsia.groupe1.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@Controller
public class LoginController {

    @RequestMapping("/*")
    @RolesAllowed("USER")
    public String getUser(){
        return "home/index";
    }

    @RequestMapping("/admin")
    @RolesAllowed("ADMIN")
    public  String getAdmin(){
        return "redirect:/home/index";
    }
}
