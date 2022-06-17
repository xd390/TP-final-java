package nc.univ.edt.controller;

import nc.univ.edt.model.Cours;
import nc.univ.edt.model.Eleve;
import nc.univ.edt.model.Salle;
import nc.univ.edt.service.CoursService;
import nc.univ.edt.service.EleveService;
import nc.univ.edt.service.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ControllerApp {
    EleveService eleveService = new EleveService();
    SalleService salleService = new SalleService();
    CoursService coursService = new CoursService();

    @Autowired
    ApplicationContext applicationContext;


    @GetMapping(value="/")
    public String menu(){
        return "menu";
    }

    @GetMapping(value = "salle/{salle}")
    public String consultationSalleStatic(@RequestParam String salle){
        return salle;
    }

    /*
     * DEBUT PARTIE COURS
     *
     * */
    @GetMapping("/cours")
    public String consultationCours(Model model) {
        List<Cours> cours = coursService.getAll(applicationContext);
        model.addAttribute("cours", cours);
        return "consultationCours";
    }
    @GetMapping("/cours/suppression")
    public String suppressionCours(@RequestParam Long coursId) {
        coursService.delete(coursId, applicationContext);
        return "redirect:/cours";
    }
    @GetMapping("/cours/creation")
    public String creationCours(Model model){
        model.addAttribute("cours", new Cours());
        return "creationCours";
    }
    @PostMapping("/cours/creation")
    public String applyCreationCours(@ModelAttribute Cours cours){
        coursService.save(cours,applicationContext);
        return "redirect:/cours";
    }
    @GetMapping("/cours/modification")
    public String modifcationCours(@RequestParam Long coursId, Model model){
        model.addAttribute("cours",coursService.get(coursId,applicationContext));
        return "modificationCours";
    }
    @PostMapping("/cours/modification")
    public String modificationCours(@ModelAttribute Cours cours){
        coursService.update(cours, applicationContext);
        return "redirect:/cours";
    }
    /*
     * FIN PARTIE COURS
     *
     * */

    /*
     * DEBUT PARTIE ELEVE
     *
     * */

    @GetMapping("/eleve")
    public String consultationEleve(Model model){
        List<Eleve> eleves = eleveService.getAll(applicationContext);
        model.addAttribute("eleves",eleves);
        return "consultationEleve";
    }
    @GetMapping("/deleteEleve")
    public String suppressionEleve(@RequestParam Long eleveId){
        eleveService.delete(eleveId,applicationContext);
        return "redirect:/eleve";
    }
    @GetMapping("/eleve/modification")
    public String modificationEleve(@RequestParam Long eleveId, Model model){
        Eleve eleve = eleveService.get(eleveId,applicationContext);
        model.addAttribute("eleve",eleve);
        return "modificationEleve";
    }
    @PostMapping ("/eleve/modification")
    public String applyModificationEleve(@ModelAttribute Eleve eleve){
        eleveService.update(eleve, applicationContext);
        return "redirect:/eleve";
    }
    @GetMapping("/eleve/creation")
    public String createEleve(Model model){
        model.addAttribute("eleve", new Eleve());
        return "creationEleve";
    }
    @PostMapping("/eleve/creation")
    public String applyCreateEleve(@ModelAttribute Eleve eleve){
        eleveService.save(eleve,applicationContext);
        return "redirect:/eleve";
    }
    /*
    * FIN PARTIE ELEVE
    *
    * */

    /*
    * DEBUT PARTIE SALLE
    *
    * */

    @GetMapping("/salle")
    public String consultationSalle(Model model){
        List<Salle> salles = salleService.getAll(applicationContext);
        model.addAttribute("salles",salles);
        return "consultationSalle";
    }
    @DeleteMapping("/salle")
    public Map<String, Boolean> suppressionSalle(@PathVariable("id") Long salleId ){
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted",salleService.delete(salleId,applicationContext));
        return response;
    }
    @GetMapping("/salle/modification")
    public String modificationSalle(@PathVariable("id") Long salleId,Model model){
        model.addAttribute("salle",salleService.get(salleId,applicationContext));
        return "modificationSalle";
    }
    @PostMapping("/salle/modification")
    public String applyModificationSalle(@ModelAttribute Salle salle){
        salleService.update(salle, applicationContext);
        return "redirect:/salle";
    }
    @GetMapping("/salle/creation")
    public String creationSalle(){
        return "creationSalle";
    }
    @PostMapping("/salle/creation")
    public String applyCreationSalle(@ModelAttribute Salle salle) {
        salleService.save(salle,applicationContext);
        return "redirect:/salle";
    }

    /*
     * FIN PARTIE SALLE
     *
     * */
}
