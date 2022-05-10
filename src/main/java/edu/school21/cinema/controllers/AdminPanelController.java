package edu.school21.cinema.controllers;

import edu.school21.cinema.services.FilmService;
import edu.school21.cinema.services.HallService;
import edu.school21.cinema.services.SessionService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/admin/panel")
@Validated
public class AdminPanelController {

    private final HallService hallService;

    private final FilmService filmService;

    private final SessionService sessionService;

    public AdminPanelController(HallService hallService,
                                FilmService filmService,
                                SessionService sessionService) {
        this.hallService = hallService;
        this.filmService = filmService;
        this.sessionService = sessionService;
    }

    @PostMapping("/halls")
    public String addHall(@RequestParam("id") Integer id,
                          @RequestParam("seats") Integer seats) {
        hallService.save(id, seats);
        return "redirect:/admin/panel/halls";
    }

    @GetMapping("/halls")
    public String getPanelHalls(Model model) {
        model.addAttribute("halls", hallService.getHalls());
        return "adminHalls";
    }

    @PostMapping(value = "/films", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public String addFilm(@RequestParam(name = "title") @Size(min = 1) String title,
                          @RequestParam(name = "year_of_release", required = false) Integer yearOfRelease,
                          @RequestParam String restrictions,
                          @RequestParam String description,
                          @RequestPart(required = false) MultipartFile poster) {
        filmService.save(title, yearOfRelease, restrictions, description, poster);
        return "redirect:/admin/panel/films";
    }

    @GetMapping("/films")
    public String getPanelFilms(Model model) {
        model.addAttribute("films", filmService.getFilms());
        return "adminFilms";
    }

    @PostMapping(value = "/sessions")
    public String addSession(@RequestParam(name = "film") Integer filmId,
                          @RequestParam(name = "hall") Integer hallId,
                          @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") LocalDateTime date,
                          @RequestParam Long cost) {
        sessionService.save(hallId, filmId, cost, date);
        return "redirect:/admin/panel/sessions";
    }

    @GetMapping("/sessions")
    public String getPanelSessions(Model model) {
        model.addAttribute("films", filmService.getFilms());
        model.addAttribute("halls", hallService.getHalls());
        model.addAttribute("sessions", sessionService.getSessions());
        return "adminSessions";
    }
}
