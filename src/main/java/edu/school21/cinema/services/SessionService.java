package edu.school21.cinema.services;

import edu.school21.cinema.models.Film;
import edu.school21.cinema.models.Hall;
import edu.school21.cinema.models.Session;
import edu.school21.cinema.repositories.FilmRepository;
import edu.school21.cinema.repositories.HallRepository;
import edu.school21.cinema.repositories.SessionRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class SessionService {
    private final SessionRepository sessionRepository;

    private final FilmRepository filmRepository;

    private final HallRepository hallRepository;

    public SessionService(SessionRepository sessionRepository, FilmRepository filmRepository, HallRepository hallRepository) {
        this.sessionRepository = sessionRepository;
        this.filmRepository = filmRepository;
        this.hallRepository = hallRepository;
    }

    public List<Session> getSessions() {
        return sessionRepository.findAll();
    }

    public Session save(Integer hallId, Integer filmId, Long cost, Instant date) {
        Hall hall = hallRepository.findById(hallId);
        if (hall == null) {
            return null;
        }
        Film film = filmRepository.findById(filmId);
        if (film == null) {
            return null;
        }
        Session session = new Session();
        session.setDate(date);
        session.setTicketCost(cost);
        session.setFilm(film);
        session.setHall(hall);
        sessionRepository.save(session);
        return session;
    }

    public List<Session> findByName(String filmName) {
        return sessionRepository.findByName(filmName);
    }
}
