package com.glsia.groupe1.Controller;

import com.glsia.groupe1.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private ArticleService articleService;

    @GetMapping("/index")
    public String afficherProduit()
    {
        return "home/index";
    }
}
