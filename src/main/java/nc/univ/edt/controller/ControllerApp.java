package nc.univ.edt.controller;

import nc.univ.edt.model.Eleve;
import nc.univ.edt.service.EleveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ControllerApp {
    EleveService eleveService = new EleveService();

    @Autowired
    ApplicationContext applicationContext;

    @RequestMapping(value = "consultation/salle", method = {RequestMethod.GET})
    public String consultationSalle(@RequestParam String salle, Model model){
        String salleName = "A10";
        model.addAttribute("salle", salleName);
        return salle;
    }

    @GetMapping(value = "consultation/salle/{salle}")
    public String consultationSalleStatic(@RequestParam String salle){
        return salle;
    }

    @GetMapping("form")
    public String salleForm(){
        return "form";
    }

    @GetMapping("cours/consultation")
    public String list(Model model){
        List<String> salles = new ArrayList<String>();
        salles.add("A5");
        salles.add("B10");
        salles.add("C3");
        model.addAttribute("salles", salles);
        return "view/cours/consultationCours";
    }

    @GetMapping("/eleve")
    public String consultationEleve(Model model){
        List<Eleve> eleves = eleveService.getAll(applicationContext);
        model.addAttribute("eleves",eleves);
        return "consultationEleve";
    }


}
