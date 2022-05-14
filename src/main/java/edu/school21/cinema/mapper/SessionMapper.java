package edu.school21.cinema.mapper;

import edu.school21.cinema.models.dto.FilmDto;
import edu.school21.cinema.models.dto.SessionDto;
import edu.school21.cinema.models.entity.FileDescription;
import edu.school21.cinema.models.entity.Film;
import edu.school21.cinema.models.entity.Session;
import edu.school21.cinema.services.PathConstructorService;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring")
public abstract class SessionMapper {

    @Autowired
    protected PathConstructorService pathConstructorService;

    public abstract SessionDto sessionToSessionDto(Session session);

    @Mappings({
            @Mapping(target = "posterFilename", ignore = true),
            @Mapping(target = "posterPath", ignore = true),
            @Mapping(target = "posterContentType", ignore = true)
    })
    public abstract FilmDto filmToFilmDto(Film film);

    @BeforeMapping
    public void processPoster(Film film, @MappingTarget FilmDto filmDto) {
        FileDescription poster = film.getPoster();
        if (poster != null) {
            filmDto.setPosterPath(pathConstructorService.constructPathString(poster.getId()));
            filmDto.setPosterFilename(poster.getFilename());
            filmDto.setPosterContentType(poster.getContentType());
        }
    }


}
