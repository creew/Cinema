package edu.school21.cinema.controllers;

import edu.school21.cinema.models.entity.Session;
import edu.school21.cinema.services.SessionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/sessions")
@Validated
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping(path = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<Session> searchSession(@RequestParam String filmName) {
        List<Session> byName = sessionService.findByName(filmName);
        return byName;
    }

    @GetMapping(path = "/{sessionId}")
    public String viewSession(@PathVariable Integer sessionId, Model model) {
        model.addAttribute("session", sessionService.getSession(sessionId));
        return "session";
    }

    @GetMapping
    public String viewSessions() {
        return "sessions";
    }
}
