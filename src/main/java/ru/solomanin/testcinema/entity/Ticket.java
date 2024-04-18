package ru.solomanin.testcinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vladmihalcea.hibernate.type.array.IntArrayType;
import com.vladmihalcea.hibernate.type.array.StringArrayType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "tickets")
@TypeDefs({
        @TypeDef(name = "int-array",
                typeClass = IntArrayType.class),

        @TypeDef(name = "string-array",
                typeClass = StringArrayType.class)
})
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int ticketNum;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE,
                    CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "movie_session_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private MovieSession movieSession;

    @Column(name = "seat")
    private int seat;



    public Ticket() {
    }

    public int getTicketNum() {
        return ticketNum;
    }

    public void setTicketNum(int ticketNum) {
        this.ticketNum = ticketNum;
    }

    public MovieSession getMovieSession() {
        return movieSession;
    }

    public void setMovieSession(MovieSession movieSession) {
        this.movieSession = movieSession;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketNum=" + ticketNum +
                ", movieSession=" + movieSession +
                ", seat=" + seat +
                '}';
    }
}

