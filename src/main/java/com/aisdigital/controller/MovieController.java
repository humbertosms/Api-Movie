package com.aisdigital.controller;

import com.aisdigital.business.MovieBusiness;
import com.aisdigital.model.Movie;
import com.aisdigital.model.ResponseModelMsg;
import com.aisdigital.model.input.MovieVMInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/movies")
@RestController
public class MovieController {

    private MovieBusiness movieBusiness;

    @Autowired
    public void setMovieBusiness(MovieBusiness movieBusiness) {
        this.movieBusiness = movieBusiness;
    }

    @GetMapping(produces = "application/json")
    List<Movie> findAll() {
        return movieBusiness.findAll();
    }

    @GetMapping(value = "/{movieId}", produces = "application/json")
    ResponseEntity<Movie> findById(@PathVariable String movieId) {
        return movieBusiness.findById(movieId);
    }

    @PostMapping( produces = "application/json")
    ResponseEntity<Movie> save(@RequestBody MovieVMInput movie) {
        return movieBusiness.save(movie);
    }

    @DeleteMapping(value = "/{movieId}", produces = "application/json")
    ResponseEntity<ResponseModelMsg> delete(@PathVariable String movieId) {
        return movieBusiness.deleteById(movieId);
    }
}
