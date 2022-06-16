package nc.univ.edt.controller;

import org.springframework.web.bind.annotation.*;

@org.springframework.stereotype.Controller
public class ControllerApp {

    @RequestMapping(value = "consultation/salle", method = {RequestMethod.GET})
    @ResponseBody
    public String consultationSalle(@RequestParam String salle){
        return salle;
    }

    @GetMapping("form")
    @ResponseBody
    public String salleForm(){
        String html = "<html>"+
                "<body>" +
                "<form method = 'post' action = '/hello'>" +
                "<input type = 'text' name = 'name' />" +
                "<input type = 'submit' name = 'Salle!' />" +
                "</form>" +
                "</body>" +
                "</html>";
        return html;
    }


}
