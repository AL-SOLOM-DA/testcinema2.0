package ru.solomanin.testcinema.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.solomanin.testcinema.entity.Movie;
import ru.solomanin.testcinema.service.MovieService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    /*@GetMapping("/movie/{title}")
    public Movie findMovieByTitle(@PathVariable("title") String title){
        return movieService.findMovieByName(title);
    }*/

    @GetMapping("/movie")
    public List<Movie> findAllMovies(){
        return movieService.findAllMovies();
    }

    @PostMapping("/movie")
    public Movie createMovie(@RequestBody Movie movie){
        movieService.saveMovie(movie);
        return movie;
    }

    @GetMapping("/movie/{id}")
    public Movie findMovieById(@PathVariable("id") int id){
        return movieService.findMovieById(id);
    }

    @PutMapping("/movie")
    public Movie updateMovie(@RequestBody Movie movie){
        movieService.saveMovie(movie);
        return movie;
    }

    @DeleteMapping("/movie/{id}")
    public void deleteMovie(@PathVariable("id") int id){
        movieService.deleteMovie(id);
    }
}
