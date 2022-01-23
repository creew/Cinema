package edu.school21.cinema.services;

import edu.school21.cinema.models.Film;
import edu.school21.cinema.repositories.FilmRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class FilmService {

    private final FilmRepository filmRepository;

    public FilmService(FilmRepository filmRepository) {
        this.filmRepository = filmRepository;
    }


    public List<Film> getFilms() {
        return filmRepository.findAll();
    }

    public void save(String title, int yearOfRelease, String restrictions,
                     String description, MultipartFile poster) {
        Film film = new Film();
        film.setTitle(title);
        film.setYearOfRelease(yearOfRelease);
        film.setDescription(description);
        film.setRestrictions(restrictions);
        film.setPoster(poster.getOriginalFilename());

        filmRepository.save(film);
    }
}
