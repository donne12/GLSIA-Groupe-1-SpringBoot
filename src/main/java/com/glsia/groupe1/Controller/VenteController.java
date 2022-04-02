package com.glsia.groupe1.Controller;

import com.glsia.groupe1.models.Article;
import com.glsia.groupe1.models.Vente;
import com.glsia.groupe1.service.ArticleService;
import com.glsia.groupe1.service.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/vente")
public class VenteController {

    @Autowired
    private VenteService venteService;
    @Autowired
    private ArticleService articleService;

    @GetMapping("/index")
    public String afficherVente(Model model)
    {
        model.addAttribute("listVente", venteService.showAll());
        return "vente/showApprov";
    }

    @GetMapping("/create")
    public String AfficherFormulaire(Model model)
    {

        model.addAttribute("ListVente", venteService.showAll());
        return "vente/formApprov";
    }

    @PostMapping("/save")
    public String saveApprov(Vente vente)
    {
        vente.setDateVente(LocalDate.now());
        venteService.save(vente);
        int article_key = vente.getArticleId();
        Article article = articleService.find(article_key);
        article.setQteStok(article.getQteStok() - vente.getQuantite());
        articleService.save(article);
        return "redirect:/vente/index";
    }

    @GetMapping("/edit/{id}")
    public String formEditVente(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("unVente", venteService.find(id));
        return "vente/formEditVente";
    }

    @PostMapping("/edit")
    public String editVente(@ModelAttribute("vente") Vente vente){
        venteService.save(vente);
        return "redirect:/vente/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteVente(@PathVariable("id") int id){
        Vente vente = venteService.find(id);
        int article_key = vente.getArticleId();
        Article article = articleService.find(article_key);
        article.setQteStok(article.getQteStok() + vente.getQuantite());
        venteService.delete(id);
        articleService.save(article);
        return "redirect:/vente/index";
    }


}
