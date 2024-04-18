package ru.solomanin.testcinema.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.solomanin.testcinema.entity.Movie;
import ru.solomanin.testcinema.exception.NotFoundException;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class MovieDaoImpl implements MovieDao{

    private EntityManager entityManager;
    private Logger logger = Logger.getLogger(MovieDaoImpl.class.getName());

    @Autowired
    public MovieDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Movie saveMovie(Movie movie) {
        Session currentSession = entityManager.unwrap(Session.class);
        currentSession.saveOrUpdate(movie);
        return movie;
    }

    @Override
    public List<Movie> findAllMovies() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Movie> movieQuery = currentSession.createQuery("from Movie order by id", Movie.class);
        List<Movie> movies = movieQuery.getResultList();

        return movies;
    }

    @Override
    public Movie findMovieById(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Movie movie = currentSession.get(Movie.class, id);

        return movie;
    }
    @Override
    public Movie findMovieByName(String title){
        return findAllMovies().stream()
                .filter(movie -> movie.getTitle().equals(title))
                .findFirst()
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void deleteMovie(int id) {
        Session currentSession = entityManager.unwrap(Session.class);
        Movie movie = findMovieById(id);
        currentSession.delete(movie);
    }
}
