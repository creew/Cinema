package edu.school21.cinema.services;

import edu.school21.cinema.models.entity.Client;
import edu.school21.cinema.models.entity.LoginInfo;
import edu.school21.cinema.repositories.LoginInfoRepository;
import edu.school21.cinema.repositories.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class UserService {

    private final ClientRepository clientRepository;

    private final LoginInfoRepository loginInfoRepository;

    public UserService(ClientRepository clientRepository, LoginInfoRepository loginInfoRepository) {
        this.clientRepository = clientRepository;
        this.loginInfoRepository = loginInfoRepository;
    }

    @Transactional
    public Client findUserBySessionId(UUID sessionId, String ip) {
        Client client = clientRepository.findBySessionId(sessionId);
        if (client == null) {
            client = new Client();
            client.setSessionId(sessionId);
            client.setAvatar(null);
            clientRepository.save(client);
        }
        LoginInfo info = new LoginInfo();
        info.setUser(client);
        info.setLoginTime(LocalDateTime.now());
        info.setIp(ip);
        loginInfoRepository.save(info);
        return client;
    }
}
