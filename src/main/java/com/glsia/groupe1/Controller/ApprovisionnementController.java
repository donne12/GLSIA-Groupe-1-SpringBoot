package com.glsia.groupe1.Controller;

import com.glsia.groupe1.models.Approvisionnement;
import com.glsia.groupe1.models.Article;
import com.glsia.groupe1.service.ApprovisionnementService;
import com.glsia.groupe1.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@Controller
@RequestMapping("/approv")
public class ApprovisionnementController {
    @Autowired
    private ApprovisionnementService approvisionnementService;
    @Autowired
    private ArticleService articleService;

    @GetMapping("/index")
    public String afficherApprovisionnement(Model model)
    {
        model.addAttribute("listApprov", approvisionnementService.showAll());
        return "approv/showApprov";
    }

    @GetMapping("/create")
    public String AfficherFormulaire(Model model)
    {

        model.addAttribute("ListApprov", approvisionnementService.showAll());
        return "approv/formApprov";
    }

    @PostMapping("/save")
    public String saveApprov(Approvisionnement approvisionnement)
    {
        approvisionnement.setDateApprov(LocalDate.now());
        approvisionnementService.save(approvisionnement);
        int article_key = approvisionnement.getArticleId();
        Article article = articleService.find(article_key);
        article.setQteStok(article.getQteStok() + approvisionnement.getQuantite());
        articleService.save(article);
        return "redirect:/approv/index";
    }


    @GetMapping("/edit/{id}")
    public String formEditProduit(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("unApprov", approvisionnementService.find(id));
        return "approv/formEditApprov";
    }

    @PostMapping("/edit")
    public String editProduit(@ModelAttribute("approvisionnement") Approvisionnement approvisionnement){
        approvisionnementService.save(approvisionnement);
        return "redirect:/approv/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduit(@PathVariable("id") int id){
        Approvisionnement approvisionnement = approvisionnementService.find(id);
        int article_key = approvisionnement.getArticleId();
        Article article = articleService.find(article_key);
        article.setQteStok(article.getQteStok() - approvisionnement.getQuantite());
        approvisionnementService.delete(id);
        articleService.save(article);
        return "redirect:/approv/index";
    }



}
