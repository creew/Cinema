package edu.school21.cinema.services;

import edu.school21.cinema.models.entity.LoginInfo;
import edu.school21.cinema.repositories.LoginInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginInfoService {

    private final LoginInfoRepository loginInfoRepository;


    public LoginInfoService(LoginInfoRepository loginInfoRepository) {
        this.loginInfoRepository = loginInfoRepository;
    }

    public List<LoginInfo> findLoginInfoByUserId(Integer id) {
        return loginInfoRepository.findByUserId(id);
    }
}
