package edu.school21.cinema.controllers;

import edu.school21.cinema.services.FilmService;
import edu.school21.cinema.services.HallService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final HallService hallService;

    private final FilmService filmService;

    public AdminController(HallService hallService, FilmService filmService) {
        this.hallService = hallService;
        this.filmService = filmService;
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
        return "adminHalls";
    }

    @PostMapping(value = "/panel/films/add", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String addFilm(@RequestParam String title,
                          @RequestParam(name = "year_of_release", required = false) Integer yearOfRelease,
                          @RequestParam String restrictions,
                          @RequestParam String description,
                          @RequestPart(required = false) MultipartFile poster) {
        filmService.save(title, yearOfRelease, restrictions, description, poster);
        return "redirect:/admin/panel/films";
    }

    @GetMapping("/panel/films")
    public String getPanelFilms(Model model) {
        model.addAttribute("films", filmService.getFilms());
        return "adminFilms";
    }

    @GetMapping("/panel/sessions")
    public void getPanelSessions() {

    }


}
