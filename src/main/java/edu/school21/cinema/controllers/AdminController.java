package edu.school21.cinema.controllers;

import edu.school21.cinema.services.HallService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final HallService hallService;

    public AdminController(HallService hallService) {
        this.hallService = hallService;
    }

    @PostMapping("/panel/halls/add")
    public String addHall(@RequestParam("id") Integer id,
                          @RequestParam("seats") Integer seats) {
        hallService.save(id, seats);
        return "redirect:/admin/panel/halls";
    }

    @GetMapping("/panel/halls")
    public String getPanelHalls(Model model) {
        model.addAttribute("halls", hallService.getHalls());
        return "halls";
    }

    @GetMapping("/panel/films")
    public String getPanelFilms(Model model) {
        model.addAttribute("persons", Collections.emptyList());
        return "films";
    }

    @GetMapping("/panel/sessions")
    public void getPanelSessions() {

    }


}
