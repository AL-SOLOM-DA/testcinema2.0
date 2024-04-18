package ru.solomanin.testcinema.service;

import ru.solomanin.testcinema.entity.Movie;

import java.util.List;

public interface MovieService {

    Movie saveMovie(Movie movie);

    List<Movie> findAllMovies();

    Movie findMovieById(int id);

    Movie findMovieByName(String title);

    void deleteMovie(int id);
}
