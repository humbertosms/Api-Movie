package com.aisdigital.controller;

import com.aisdigital.business.MovieCategoryBusiness;
import com.aisdigital.model.MovieCategory;
import com.aisdigital.model.ResponseModelMsg;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "Listar todos Registros", notes = "Este endpoint lista todas as Categoria")
    @GetMapping(produces = "application/json")
    public List<MovieCategory> findAll() {
        return movieCategoryBusiness.findAll();
    }

    @ApiOperation(value = "Buscar um Registro", notes = "Este endpoint busca Categoria pelo identificador")
    @GetMapping(value = "/{categoryId}", produces = "application/json")
    public ResponseEntity<ResponseModelMsg> findById(@PathVariable String categoryId) {
        return movieCategoryBusiness.findById(categoryId);
    }

    @ApiOperation(value = "Salvar Registro", notes = "Este endpoint salva Categoria")
    @PostMapping( produces = "application/json")
    public ResponseEntity<ResponseModelMsg> save(@RequestBody MovieCategory movieCategory) {
        return movieCategoryBusiness.save(movieCategory);
    }

    @ApiOperation(value = "Alterar Registro", notes = "Este endpoint altera uma Categoria já existente")
    @PutMapping( produces = "application/json")
    public ResponseEntity<ResponseModelMsg> update(@RequestBody MovieCategory movieCategory) {
        return movieCategoryBusiness.update(movieCategory);
    }

    @ApiOperation(value = "Remover Registro", notes = "Este endpoint remove Categoria")
    @DeleteMapping(value = "/{categoryId}", produces = "application/json")
    public ResponseEntity<ResponseModelMsg> delete(@PathVariable String categoryId) {
        return movieCategoryBusiness.deleteById(categoryId);
    }

}
