package edu.school21.cinema.controllers;

import edu.school21.cinema.models.Session;
import edu.school21.cinema.services.SessionService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public List<Session> searchSession(@RequestParam String filmName) {
        return sessionService.findByName(filmName);
    }

    @GetMapping(path = "/{sessionId}")
    public String viewSession(@PathVariable String sessionId) {
        return "";
    }

    @GetMapping(path = "/")
    public String viewSessions() {
        return "sessions";
    }


}
