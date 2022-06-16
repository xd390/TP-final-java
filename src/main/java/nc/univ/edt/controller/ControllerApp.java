package nc.univ.edt.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
public class ControllerApp {

    @RequestMapping(value = "consultation/salle", method = {RequestMethod.GET})
    @ResponseBody
    public String consultationSalle(@RequestParam String salle, Model model){
        String salleName = "A10";
        model.addAttribute("salle", salleName);
        return salle;
    }

    @GetMapping(value = "consultation/salle/{salle}")
    @ResponseBody
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


}
