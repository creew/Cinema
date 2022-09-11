package edu.school21.cinema.controllers;

import edu.school21.cinema.models.entity.ChatMessage;
import edu.school21.cinema.models.entity.Client;
import edu.school21.cinema.services.ChatMessageService;
import edu.school21.cinema.services.FileDescriptionService;
import edu.school21.cinema.services.LoginInfoService;
import edu.school21.cinema.services.UserService;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Controller
public class FilmController {

    private final FileDescriptionService fileDescriptionService;

    private final UserService userService;

    private final ChatMessageService chatMessageService;

    private final LoginInfoService loginInfoService;

    public FilmController(FileDescriptionService fileDescriptionService,
                          UserService userService,
                          ChatMessageService chatMessageService, LoginInfoService loginInfoService) {
        this.fileDescriptionService = fileDescriptionService;
        this.userService = userService;
        this.chatMessageService = chatMessageService;
        this.loginInfoService = loginInfoService;
    }

    @GetMapping("/images/{id}")
    public void getPanelFilms(@PathVariable UUID id, HttpServletResponse response) throws IOException {
        String contentType = fileDescriptionService.writeImage(id, response.getOutputStream());
        response.setContentType(contentType);
    }

    @GetMapping("/films/{id}/chat")
    public String getChat(@PathVariable Integer id, @CookieValue(name = "session-id", required = false) UUID sessionId,
                          HttpServletRequest request, HttpServletResponse response, Model model) {
        System.out.println("session-id: " + id + ", remoteAddr: " + request.getRemoteAddr());
        if (sessionId == null) {
            sessionId = UUID.randomUUID();
        }
        Cookie cookie = new Cookie("session-id", sessionId.toString());
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);
        Client user = userService.findUserBySessionId(sessionId, request.getRemoteAddr());
        model.addAttribute("user", user);
        model.addAttribute("messages", chatMessageService.findMessagesByFilmId(id));
        model.addAttribute("logins", loginInfoService.findLoginInfoByUserId(user.getId()));
        model.addAttribute("filmId", id);
        return "chat";
    }

    @MessageMapping("/films/{id}/chat/messages")
    public ChatMessage f(@DestinationVariable Integer id, @Payload MessageStomp payload, @Header UUID sessionId) {
        return chatMessageService.saveMessage(id, payload, sessionId);
    }
}
