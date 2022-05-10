package edu.school21.cinema.controllers;

import edu.school21.cinema.models.ChatMessage;
import edu.school21.cinema.models.FileDescription;
import edu.school21.cinema.services.ChatMessageService;
import edu.school21.cinema.services.FileDescriptionSaver;
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

    private final FileDescriptionSaver fileDescriptionSaver;

    private final UserService userService;

    private final ChatMessageService chatMessageService;

    public FilmController(FileDescriptionSaver fileDescriptionSaver,
                          UserService userService,
                          ChatMessageService chatMessageService) {
        this.fileDescriptionSaver = fileDescriptionSaver;
        this.userService = userService;
        this.chatMessageService = chatMessageService;
    }

    @GetMapping("/images/{id}")
    public void getPanelFilms(@PathVariable UUID id, HttpServletResponse response) throws IOException {
        FileDescription image = fileDescriptionSaver.writeImage(id, response.getOutputStream());
        response.setContentType(image.getContentType());
    }

    @GetMapping("/films/{id}/chat")
    public String getChat(@PathVariable Integer id, @CookieValue(name = "user-id", required = false) UUID userId,
                          HttpServletRequest request, HttpServletResponse response, Model model) {
        System.out.println("user-id: " + id + ", remoteAddr: " + request.getRemoteAddr());
        if (userId == null) {
            userId = UUID.randomUUID();
        }
        Cookie cookie = new Cookie("user-id", userId.toString());
        cookie.setMaxAge(7 * 24 * 60 * 60);
        response.addCookie(cookie);
        model.addAttribute("user", userService.findUserById(userId, request.getRemoteAddr()));
        model.addAttribute("messages", chatMessageService.findMessagesByFilmId(id));
        model.addAttribute("filmId", id);
        return "chat";
    }

    @MessageMapping("/films/{id}/chat/messages")
    public ChatMessage f(@DestinationVariable Integer id, @Payload MessageStomp payload, @Header UUID userId) {
        return chatMessageService.saveMessage(id, payload, userId);
    }
}
