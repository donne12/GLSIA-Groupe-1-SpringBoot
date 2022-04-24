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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("/vente")
public class VenteController {

    @Autowired
    private VenteService venteService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private VenteArticleService venteArticleService;

    @GetMapping("/index")
    public String afficherVente(Model model)
    {
        model.addAttribute("listVente", venteService.showActive());
        return "vente/showVente";
    }

    @GetMapping("/create")
    public String AfficherFormulaire()
    {
        return "redirect:/vente/save";
    }

    @GetMapping("/save")
    public String saveApprov()
    {
        Vente vente = new Vente();
        vente.setDateVente(LocalDate.now());
        vente.setCloture(false);
        venteService.save(vente);
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
        List<VenteArticle> ventes  = new ArrayList<>();
        ventes =  venteArticleService.showByVenteId(id);
        try {
            for (VenteArticle vente :  ventes ) {
                venteArticleService.delete(vente.getId());
                System.out.println(vente.getId());
            }
            venteService.delete(id);
        }catch (Exception e){

        }

        return "redirect:/vente/index";
    }

    @GetMapping("/cloture/{id}")
    public String clotureVente(@PathVariable("id") int id){
        Vente vente = venteService.find(id);
        vente.setCloture(true);
        venteService.save(vente);
        return "redirect:/vente/index";
    }

    @GetMapping("/see/{id}")
    public String see(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("listVentes", venteArticleService.showByVenteId(id));
        return "ventes/see";
    }




}
