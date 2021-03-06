package edu.school21.cinema.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "hall")
public class Hall {

    @Id
    private Integer id;

    @Column(name = "number_of_seats")
    private Integer numberOfSeats;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }
}
