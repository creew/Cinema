package edu.school21.cinema.services;

import edu.school21.cinema.models.entity.FileDescription;
import edu.school21.cinema.models.entity.Film;
import edu.school21.cinema.repositories.FilmRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class FilmService {

    private final FilmRepository filmRepository;

    private final FileDescriptionService fileDescriptionService;

    public FilmService(FilmRepository filmRepository, FileDescriptionService fileDescriptionService) {
        this.filmRepository = filmRepository;
        this.fileDescriptionService = fileDescriptionService;
    }


    public List<Film> getFilms() {
        return filmRepository.findAll();
    }

    public void save(String title, Integer yearOfRelease, String restrictions,
                     String description, MultipartFile poster) {
        Film film = new Film();
        film.setTitle(title);
        film.setYearOfRelease(yearOfRelease);
        film.setDescription(description);
        film.setRestrictions(restrictions);
        if (poster != null) {
            try {
                FileDescription file = fileDescriptionService.saveFile(poster.getOriginalFilename(),
                        poster.getContentType(), poster.getInputStream());
                film.setPoster(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            film.setPoster(fileDescriptionService.findById(UUID.fromString("11111111-1111-1111-1111-111111111111")));
        }
        filmRepository.save(film);
    }
}
