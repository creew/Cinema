package edu.school21.cinema.services;

import edu.school21.cinema.models.entity.Hall;
import edu.school21.cinema.repositories.HallRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HallService {

    private final HallRepository hallRepository;

    public HallService(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    public List<Hall> getHalls() {
        return hallRepository.findAll();
    }

    public void save(Integer id, Integer seats) {
        Hall hall = new Hall();
        hall.setId(id);
        hall.setNumberOfSeats(seats);
        hallRepository.save(hall);
    }
}
