package com.example.taskmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LocaleController {

    @GetMapping("/change-language")
    public String changeLanguage(@RequestParam("lang") String lang) {
        return "redirect:/tasks";  // Redirige a la página de tareas después de cambiar el idioma
    }
}
