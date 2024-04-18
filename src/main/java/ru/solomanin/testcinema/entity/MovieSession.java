package ru.solomanin.testcinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vladmihalcea.hibernate.type.array.IntArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "movie_sessions")
@TypeDefs({
        @TypeDef(name = "int-array",
        typeClass = IntArrayType.class)
})
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE,
            CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "movie_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Movie movie;

    @Column(name = "time")
    private String time;

    @Column(name = "seats")
    @Type(type = "int-array")
    private int[] seats;

    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "movieSession",
            cascade = CascadeType.ALL)
    private List<Ticket> tickets;



    public MovieSession() {
    }

    public MovieSession(Movie movie, String time,int[] seats) {
        this.movie = movie;
        this.time = time;
        this.seats = seats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int[] getSeats() {
        return seats;
    }

    public void setSeats(int[] seats) {
        this.seats = seats;
    }

//    public List<Ticket> getTickets() {
//        return tickets;
//    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "MovieSession{" +
                "id=" + id +
                ", movie=" + movie +
                ", time='" + time + '\'' +
                ", seats=" + Arrays.toString(seats) +
                '}';
    }

    public void addTicket(Ticket ticket) {

        if (tickets == null) {
            tickets = new ArrayList<>();
        }

        tickets.add(ticket);
        ticket.setMovieSession(this);
    }
}
