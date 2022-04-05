package com.glsia.groupe1.Controller;

import com.glsia.groupe1.models.Categorie;
import com.glsia.groupe1.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categorie")
public class CategorieController {
    @Autowired
    private CategorieService categorieService;

    @GetMapping("/index")
    public String afficherProduit(Model model)
    {
        model.addAttribute("listCategorie", categorieService.showAll());
        return "categorie/showCategorie";
    }

    @GetMapping("/create")
    public String AfficherFormulaire(Model model)
    {
        return "categorie/formCategorie";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("categorie") Categorie categorie)
    {
        categorieService.save(categorie);
        return "redirect:/categorie/index";
    }


    @GetMapping("/edit/{id}")
    public String formEditProduit(@PathVariable("id") int id, Model model)
    {
        model.addAttribute("ListCategorie", categorieService.find(id));
        return "categorie/formEditCategorie";
    }

    @PostMapping("/edit")
    public String editProduit(@ModelAttribute("categorie") Categorie categorie){
        categorieService.save(categorie);
        return "redirect:/categorie/index";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduit(@PathVariable("id") int id){
        categorieService.delete(id);
        return "redirect:/categorie/index";
    }



}
