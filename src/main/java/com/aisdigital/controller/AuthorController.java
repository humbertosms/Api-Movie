package com.aisdigital.controller;

import com.aisdigital.business.AuthorBusiness;
import com.aisdigital.model.Author;
import com.aisdigital.model.ResponseModelMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/authors")
@RestController
public class AuthorController {
    private AuthorBusiness authorBusiness;

    @Autowired
    public void setAuthorBusiness(AuthorBusiness authorBusiness) {
        this.authorBusiness = authorBusiness;
    }

    @GetMapping(produces = "application/json")
    public List<Author> findAll() {
        return authorBusiness.findAll();
    }

    @GetMapping(value = "/{authorId}", produces = "application/json")
    public ResponseEntity<ResponseModelMsg> findById(@PathVariable String authorId) {
        return authorBusiness.findById(authorId);
    }

    @PostMapping(produces = "application/json")
    public ResponseEntity<ResponseModelMsg> save(@RequestBody Author author) {
        return authorBusiness.save(author);
    }

    @PutMapping(produces = "application/json")
    public ResponseEntity<ResponseModelMsg> update(@RequestBody Author author) {
        return authorBusiness.update(author);
    }

    @DeleteMapping(value = "/{movieId}", produces = "application/json")
    public ResponseEntity<ResponseModelMsg> delete(@PathVariable String movieId) {
        return authorBusiness.deleteById(movieId);
    }
}
