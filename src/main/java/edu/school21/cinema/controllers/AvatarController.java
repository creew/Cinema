package edu.school21.cinema.controllers;

import edu.school21.cinema.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Controller
public class AvatarController {
    private final UserService userService;

    public AvatarController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/avatar/{userId}", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public void setAvatar(@CookieValue(name = "session-id") UUID sessionId, @PathVariable Integer userId, @RequestPart MultipartFile file) {
        userService.saveAvatar(sessionId, userId, file);
    }
}
