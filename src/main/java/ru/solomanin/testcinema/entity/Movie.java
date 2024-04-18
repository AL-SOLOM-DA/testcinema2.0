package ru.solomanin.testcinema.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "movie_title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    private int releaseYear;

    @OneToMany(fetch = FetchType.LAZY,
                mappedBy = "movie",
                cascade = CascadeType.ALL)
    List<MovieSession> movieSessions;

    public Movie() {
    }

    public Movie(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

//    public List<MovieSession> getMovieSessions() {
//        return movieSessions;
//    }

    public void setMovieSessions(List<MovieSession> movieSessions) {
        this.movieSessions = movieSessions;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", releaseYear=" + releaseYear +
                '}';
    }

    public void add(MovieSession movieSession) {

        if (movieSessions == null) {
            movieSessions = new ArrayList<>();
        }

        movieSessions.add(movieSession);
        movieSession.setMovie(this);
    }
}
