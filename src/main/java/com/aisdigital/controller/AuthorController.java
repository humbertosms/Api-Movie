package com.aisdigital.controller;

import com.aisdigital.business.AuthorBusiness;
import com.aisdigital.model.Author;
import com.aisdigital.model.ResponseModelMsg;
import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "Lista todos Registros", notes = "Este endpoint lista todos os Autores")
    @GetMapping(produces = "application/json")
    public List<Author> findAll() {
        return authorBusiness.findAll();
    }

    @ApiOperation(value = "Buscar um Registro", notes = "Este endpoint busca Autor pelo identificador")
    @GetMapping(value = "/{authorId}", produces = "application/json")
    public ResponseEntity<ResponseModelMsg> findById(@PathVariable String authorId) {
        return authorBusiness.findById(authorId);
    }

    @ApiOperation(value = "Salvar Registro", notes = "Este endpoint salva Autor")
    @PostMapping(produces = "application/json")
    public ResponseEntity<ResponseModelMsg> save(@RequestBody Author author) {
        return authorBusiness.save(author);
    }

    @ApiOperation(value = "Alterar Registro", notes = "Este endpoint altera um Autor já existente")
    @PutMapping(produces = "application/json")
    public ResponseEntity<ResponseModelMsg> update(@RequestBody Author author) {
        return authorBusiness.update(author);
    }

    @ApiOperation(value = "Remover Registro", notes = "Este endpoint remove Autor")
    @DeleteMapping(value = "/{movieId}", produces = "application/json")
    public ResponseEntity<ResponseModelMsg> delete(@PathVariable String movieId) {
        return authorBusiness.deleteById(movieId);
    }
}
