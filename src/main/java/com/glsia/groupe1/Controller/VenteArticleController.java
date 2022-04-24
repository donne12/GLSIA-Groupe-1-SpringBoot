package com.glsia.groupe1.Controller;

import com.glsia.groupe1.models.Article;
import com.glsia.groupe1.models.Vente;
import com.glsia.groupe1.models.VenteArticle;
import com.glsia.groupe1.service.ArticleService;
import com.glsia.groupe1.service.VenteArticleService;
import com.glsia.groupe1.service.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/venteArticle")
public class VenteArticleController {
    @Autowired
    private VenteArticleService venteArticleService;
    @Autowired
    private VenteService venteService;
    @Autowired
    private ArticleService articleService;

    @GetMapping("/index")
    public String afficherVenteArticle(Model model)
    {
        model.addAttribute("listVentes", venteArticleService.showAll());
        return "ventes/showVente";
    }

    @GetMapping("/create/{id}")
    public String AfficherFormulaire(@PathVariable("id") int id,Model model)
    {
        model.addAttribute("ListArticles",articleService.showAll());
        model.addAttribute("Vente", venteService.find(id));
        return "ventes/formVente";
    }

    @PostMapping("/save")
    public String saveVenteArticle(VenteArticle venteArticle)
    {

        int article_key = venteArticle.getArticleId();
        Article article = articleService.find(article_key);
        article.setQteStok(article.getQteStok() - venteArticle.getQuantite());
        articleService.save(article);
        venteArticleService.save(venteArticle);
        return "redirect:/venteArticle/index";
    }

    @GetMapping("/edit/{id}")
    public String formEditVente(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("unVente", venteArticleService.find(id));
        return "ventes/formEditVente";
    }

    @PostMapping("/edit")
    public String editVente(@ModelAttribute("vente") VenteArticle venteArticle){
        venteArticleService.save(venteArticle);
        return "redirect:/venteArticle/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteVentes(@PathVariable("id") int id){
        VenteArticle venteArticle = venteArticleService.find(id);
        int article_key = venteArticle.getArticleId();
        Article article = articleService.find(article_key);
        article.setQteStok(article.getQteStok() + venteArticle.getQuantite());
        articleService.save(article);
        venteArticleService.delete(id);
        return "redirect:/vente/index";
    }

}
