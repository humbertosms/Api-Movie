package com.aisdigital.controller;

import com.aisdigital.business.MovieBusiness;
import com.aisdigital.model.Movie;
import com.aisdigital.model.ResponseModelMsg;
import com.aisdigital.model.input.MovieVMInput;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "Lista todos Registros", notes = "Este endpoint lista todos os filmes")
    @GetMapping(produces = "application/json")
    public List<Movie> findAll() {
        return movieBusiness.findAll();
    }

    @ApiOperation(value = "Busca um Registro", notes = "Este endpoint busca filme por identificador")
    @GetMapping(value = "/{movieId}", produces = "application/json")
    public ResponseEntity<ResponseModelMsg> findById(@PathVariable String movieId) {
        return movieBusiness.findById(movieId);
    }

    @ApiOperation(value = "Salvar Registro", notes = "Este endpoint salva filme")
    @PostMapping(produces = "application/json")
    public ResponseEntity<ResponseModelMsg> save(@RequestBody MovieVMInput movie) {
        return movieBusiness.save(movie);
    }

    @ApiOperation(value = "Alterar Registro", notes = "Este endpoint altera um filme já existente")
    @PutMapping(produces = "application/json")
    public ResponseEntity<ResponseModelMsg> update(@RequestBody MovieVMInput movie) {
        return movieBusiness.update(movie);
    }

    @ApiOperation(value = "Remover Filme",notes = "Este endpoint remove filme")
    @DeleteMapping(value = "/{movieId}", produces = "application/json")
    public ResponseEntity<ResponseModelMsg> delete(@PathVariable String movieId) {
        return movieBusiness.deleteById(movieId);
    }
}
