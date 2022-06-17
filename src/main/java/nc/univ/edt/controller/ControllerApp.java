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

    @GetMapping("cours")
    public String consultationCours(Model model) {
        List<Cours> cours = coursService.getAll(applicationContext);
        model.addAttribute("cours", cours);
        return "view/cours/consultationCours";
    }
    @GetMapping("/cours/creation")
    public String CreationCours(){
        return "creationCours";
    }
    @PostMapping("/cours/creation")
    public Cours creationCours(@RequestBody Cours cours){
        return coursService.save(cours,applicationContext);
    }

    @GetMapping("/eleve")
    public String consultationEleve(Model model){
        List<Eleve> eleves = eleveService.getAll(applicationContext);
        model.addAttribute("eleves",eleves);
        return "consultationEleve";
    }
    @GetMapping("/eleve/creation")
    public String createEleve(Model model){
        model.addAttribute("eleve", new Eleve());
        return "creationEleve";
    }
    @PostMapping("/eleve/creation")
    public String creationEleve(@RequestBody Eleve eleve){
        eleveService.save(eleve,applicationContext);
        return "consultationEleve";
    }

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
    @GetMapping("/salle/creation")
    public String creationSalle(){
        return "creationSalle";
    }
    @PostMapping("/salle/creation")
    public Salle creationSalle(@RequestBody Salle salle) {
        return salleService.save(salle,applicationContext);
    }

}
