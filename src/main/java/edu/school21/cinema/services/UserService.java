package edu.school21.cinema.services;

import edu.school21.cinema.models.entity.Client;
import edu.school21.cinema.models.entity.FileDescription;
import edu.school21.cinema.models.entity.LoginInfo;
import edu.school21.cinema.repositories.ClientRepository;
import edu.school21.cinema.repositories.LoginInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {

    private final ClientRepository clientRepository;

    private final LoginInfoRepository loginInfoRepository;

    private final FileDescriptionService fileDescriptionService;

    public UserService(ClientRepository clientRepository, LoginInfoRepository loginInfoRepository, FileDescriptionService fileDescriptionService) {
        this.clientRepository = clientRepository;
        this.loginInfoRepository = loginInfoRepository;
        this.fileDescriptionService = fileDescriptionService;
    }

    @Transactional
    public Client findUserBySessionId(UUID sessionId, String ip) {
        Client client = clientRepository.findBySessionId(sessionId);
        if (client == null) {
            client = new Client();
            client.setSessionId(sessionId);
            client.setAvatar(fileDescriptionService.findById(UUID.fromString("33333333-3333-3333-3333-333333333333")));
            clientRepository.save(client);
        }
        LoginInfo info = new LoginInfo();
        info.setUser(client);
        info.setLoginTime(LocalDateTime.now());
        info.setIp(ip);
        loginInfoRepository.save(info);
        return client;
    }

    public void saveAvatar(UUID sessionId, Integer userId, MultipartFile file) {
        Client client = clientRepository.findById(userId);
        if (client == null) {
            throw new IllegalArgumentException("No such user found: " + userId);
        }
        if (!client.getSessionId().equals(sessionId)) {
            throw new IllegalArgumentException("Trying to set avatar for another user: " + userId);
        }
        try {
            FileDescription fileDescription = fileDescriptionService.saveFile(file.getName(), file.getContentType(), file.getInputStream());
            client.setAvatar(fileDescription);
            clientRepository.update(client);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
