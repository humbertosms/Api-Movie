package com.aisdigital.controller;

import com.aisdigital.business.MovieCategoryBusiness;
import com.aisdigital.model.Movie;
import com.aisdigital.model.MovieCategory;
import com.aisdigital.model.ResponseModelMsg;
import com.aisdigital.model.input.MovieVMInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/categories")
@RestController
public class MovieCategoryController {

    private MovieCategoryBusiness movieCategoryBusiness;

    @Autowired
    public void setMovieCategoryBusiness(MovieCategoryBusiness movieCategoryBusiness) {
        this.movieCategoryBusiness = movieCategoryBusiness;
    }

    @GetMapping(produces = "application/json")
    List<MovieCategory> findAll() {
        return movieCategoryBusiness.findAll();
    }

    @GetMapping(value = "/{categoryId}", produces = "application/json")
    ResponseEntity<MovieCategory> findById(@PathVariable String categoryId) {
        return movieCategoryBusiness.findById(categoryId);
    }

    @PostMapping( produces = "application/json")
    ResponseEntity<MovieCategory> save(@RequestBody MovieCategory movieCategory) {
        return movieCategoryBusiness.save(movieCategory);
    }

    @DeleteMapping(value = "/{categoryId}", produces = "application/json")
    ResponseEntity<ResponseModelMsg> delete(@PathVariable String categoryId) {
        return movieCategoryBusiness.deleteById(categoryId);
    }


}
