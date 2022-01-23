package edu.school21.cinema.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "session")
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name="hall_id")
    private Hall hall;

    @ManyToOne
    @JoinColumn(name="film_id")
    private Film film;

    @Column(name = "ticket_cost")
    private Long ticketCost;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Long getTicketCost() {
        return ticketCost;
    }

    public void setTicketCost(Long ticketCost) {
        this.ticketCost = ticketCost;
    }
}
