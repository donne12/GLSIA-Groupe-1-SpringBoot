package com.glsia.groupe1.Controller;

import com.glsia.groupe1.models.Article;
import com.glsia.groupe1.service.ArticleService;
import com.glsia.groupe1.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private CategorieService categorieService;

    @GetMapping("/index")
    public String afficherProduit(Model model)
    {
        model.addAttribute("listArticle", articleService.showAll());
        return "article/showProduct";
    }

    @GetMapping("/create")
    public String AfficherFormulaire(Model model)
    {

        model.addAttribute("ListCategorie", categorieService.showAll());
        return "article/formProduit";
    }

    @PostMapping("/save")
    public String save(Article article)
    {
        article.setQteStok(0);
        article.setDateCreation(LocalDate.now());

        articleService.save(article);
        return "redirect:/article/index";
    }

    @GetMapping("/edit/{id}")
    public String formEditProduit(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("unArticle", articleService.find(id));
        model.addAttribute("ListCategorie", categorieService.showAll());
        return "article/formEditProduit";
    }

    @PostMapping("/edit")
    public String editProduit(@ModelAttribute("article") Article article){
        articleService.save(article);
        return "redirect:/article/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduit(@PathVariable("id") int id){
        articleService.delete(id);
        return "redirect:/article/index";
    }

    @PostMapping("/search")
    public String searchApp(String libelle, Model model)
    {
        if(libelle != ""){
            model.addAttribute("listArticle", articleService.search(libelle));
        }else {
            model.addAttribute("listArticle", articleService.showAll());
        }

        return "article/showProduct";
    }

}
