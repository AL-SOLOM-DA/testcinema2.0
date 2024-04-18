package ru.solomanin.testcinema.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import ru.solomanin.testcinema.entity.Movie;
import ru.solomanin.testcinema.entity.MovieSession;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Repository
public class MovieSessionDaoImpl implements MovieSessionDao {


    private EntityManager entityManager;

    @Autowired
    private MovieDao movieDao;

    private Logger logger = Logger.getLogger(MovieSessionDaoImpl.class.getName());

    @Value("${hall.capacity}")
    private int hallCapacity;

    @Autowired
    public MovieSessionDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<MovieSession> findAllSession() {
        Session currentSession = entityManager.unwrap(Session.class);

        Query<MovieSession> sessionQuery =
                currentSession.createQuery("from MovieSession order by time", MovieSession.class);

        List<MovieSession> sessionList = sessionQuery.getResultList();


        return sessionList;
    }


    public MovieSession findMovieSessionById(int sessionId) {

        Session currentSession = entityManager.unwrap(Session.class);

        MovieSession movieSession =
                currentSession.get(MovieSession.class, sessionId);

        return movieSession;
    }


    @Override
    public MovieSession saveMovieSession(MovieSession movieSession) {

        logger.info(movieSession.toString());
        Session currentSession = entityManager.unwrap(Session.class);

        Movie movie = movieDao.findMovieById(movieSession.getMovie().getId());
        if (movieSession.getSeats()==null) {
            int [] seatList = new int[hallCapacity];
            for (int i = 0; i < hallCapacity; i++) {
                seatList[i]=i+1;
            }

            movieSession.setSeats(seatList);
        }
        currentSession.saveOrUpdate(movieSession);
        movie.add(movieSession);
        return movieSession;
    }

    @Override
    public void delete(int id) {
        Session session = entityManager.unwrap(Session.class);
        MovieSession movieSession = findMovieSessionById(id);
        session.delete(movieSession);
    }


}
