package ru.solomanin.testcinema.service;

import ru.solomanin.testcinema.entity.MovieSession;

import java.util.List;

public interface MovieSessionService {

    List<MovieSession> findAllSession();

    MovieSession findMovieSessionById(int sessionId);

    MovieSession saveMovieSession(MovieSession session);

    void delete(int id);

    /*MovieSession updateMovieSession(int sessionId, MovieSession session);


    void deleteMovieSessionById(int sessionId);*/



}
