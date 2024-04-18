package ru.solomanin.testcinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.solomanin.testcinema.entity.MovieSession;
import ru.solomanin.testcinema.service.MovieSessionService;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class MovieSessionController {

    private Logger logger = Logger.getLogger(MovieSessionController.class.getName());

    private MovieSessionService sessionService;

    @Autowired
    public MovieSessionController(MovieSessionService sessionService) {
        this.sessionService = sessionService;
    }

    @GetMapping("/session")
    public List<MovieSession> findAllMovieSession() {
        return sessionService.findAllSession();
    }

    @GetMapping("/session/{id}")
    public MovieSession findMovieSessionById(@PathVariable("id") int id) {
        return sessionService.findMovieSessionById(id);
    }

    @PostMapping("/session")
    public MovieSession createSession(@RequestBody MovieSession movieSession) {

        sessionService.saveMovieSession(movieSession);

        return movieSession;
    }

    @PutMapping("/session")
    public MovieSession updateSession(@RequestBody MovieSession movieSession) {

        sessionService.saveMovieSession(movieSession);

        return movieSession;
    }


    @DeleteMapping("/session/{id}")
    public String deleteSession(@PathVariable int id) {
        sessionService.delete(id);
        return "Сеанс удален!";
    }
}
