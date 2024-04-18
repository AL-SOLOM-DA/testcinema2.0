package ru.solomanin.testcinema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.solomanin.testcinema.dao.MovieDao;
import ru.solomanin.testcinema.entity.Movie;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService{

    private MovieDao movieDao;

    @Autowired
    public MovieServiceImpl(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    @Override
    @Transactional
    public Movie saveMovie(Movie movie) {
        return movieDao.saveMovie(movie);
    }

    @Override
    @Transactional
    public List<Movie> findAllMovies() {
        return movieDao.findAllMovies();
    }

    @Override
    @Transactional
    public Movie findMovieById(int id) {
        return movieDao.findMovieById(id);
    }

    @Override
    @Transactional
    public Movie findMovieByName(String title) {
        return movieDao.findMovieByName(title);
    }

    @Override
    @Transactional
    public void deleteMovie(int id) {
        movieDao.deleteMovie(id);
    }
}
