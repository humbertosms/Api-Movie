package com.aisdigital.controller;

import com.aisdigital.business.MovieCategoryBusiness;
import com.aisdigital.model.MovieCategory;
import com.aisdigital.model.ResponseModelMsg;
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
    public List<MovieCategory> findAll() {
        return movieCategoryBusiness.findAll();
    }

    @GetMapping(value = "/{categoryId}", produces = "application/json")
    public ResponseEntity<ResponseModelMsg> findById(@PathVariable String categoryId) {
        return movieCategoryBusiness.findById(categoryId);
    }

    @PostMapping( produces = "application/json")
    public ResponseEntity<ResponseModelMsg> save(@RequestBody MovieCategory movieCategory) {
        return movieCategoryBusiness.save(movieCategory);
    }

    @PutMapping( produces = "application/json")
    public ResponseEntity<ResponseModelMsg> update(@RequestBody MovieCategory movieCategory) {
        return movieCategoryBusiness.update(movieCategory);
    }

    @DeleteMapping(value = "/{categoryId}", produces = "application/json")
    public ResponseEntity<ResponseModelMsg> delete(@PathVariable String categoryId) {
        return movieCategoryBusiness.deleteById(categoryId);
    }

}
