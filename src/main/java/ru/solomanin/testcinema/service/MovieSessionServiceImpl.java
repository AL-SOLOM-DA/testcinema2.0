package ru.solomanin.testcinema.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.solomanin.testcinema.dao.MovieSessionDao;
import ru.solomanin.testcinema.entity.MovieSession;

import java.util.List;

@Service
public class MovieSessionServiceImpl implements MovieSessionService{

    private MovieSessionDao sessionDao;

    @Autowired
    public MovieSessionServiceImpl(MovieSessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }

    @Override
    @Transactional
    public List<MovieSession> findAllSession() {
        return sessionDao.findAllSession();
    }

    @Override
    @Transactional
    public MovieSession findMovieSessionById(int sessionId) {
        return sessionDao.findMovieSessionById(sessionId);
    }

    @Override
    @Transactional
    public MovieSession saveMovieSession(MovieSession session) {
        return sessionDao.saveMovieSession(session);
    }

    @Override
    @Transactional
    public void delete(int id) {
        sessionDao.delete(id);
    }

    /* @Override
    public MovieSession updateMovieSession(int sessionId, MovieSession session) {
        return sessionDao.updateMovieSession(sessionId, session);
    }



    @Override
    public void deleteMovieSessionById(int sessionId) {
        sessionDao.deleteMovieSessionById(sessionId);
    }*/
}
