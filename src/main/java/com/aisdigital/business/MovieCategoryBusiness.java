package com.aisdigital.business;

import com.aisdigital.model.MovieCategory;
import com.aisdigital.model.ResponseModelMsg;
import com.aisdigital.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MovieCategoryBusiness {

    @Autowired
    BaseRepository baseRepository;


    public List<MovieCategory> findAll() {
        return baseRepository.getMovieCategories();
    }

    public ResponseEntity<ResponseModelMsg> findById(String categoryId) {
        MovieCategory movieCategory = baseRepository.getMovieCategories().stream().filter(a -> a.getId().equals(categoryId)).findAny().orElse(null);
        if (movieCategory != null) {
            return new ResponseEntity<>(new ResponseModelMsg("Registro encontrado.", 2, movieCategory), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseModelMsg("Registro não encontrado.", 2), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ResponseModelMsg> save(MovieCategory movieCategory) {
        try {
            if (movieCategory.getId() == null || movieCategory.getId().isEmpty()) {
                movieCategory.setId("C" + (baseRepository.getMovieCategories().size() + 1));
            }

            if (movieCategory.getDescryption() == null || movieCategory.getDescryption().isEmpty()) {
                return new ResponseEntity<>(new ResponseModelMsg("Descrição da categoria deve ser preechido", 03), HttpStatus.BAD_REQUEST);
            }
            baseRepository.getMovieCategories().add(movieCategory);
            return new ResponseEntity<>(new ResponseModelMsg("Registro salvo com sucesso.", 2, movieCategory), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseModelMsg("Erro no Servidor:" + e.getMessage(), 2), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ResponseModelMsg> update(MovieCategory movieCategory) {
        try {
            if (movieCategory.getId() == null || movieCategory.getId().isEmpty()) {
                return new ResponseEntity<>(new ResponseModelMsg("Id da categoria deve ser preechido", 03), HttpStatus.BAD_REQUEST);
            }

            if (HttpStatus.OK != findById(movieCategory.getId()).getStatusCode()) {
                return new ResponseEntity<>(new ResponseModelMsg("Id da categoria deve ser preechido", 03), HttpStatus.BAD_REQUEST);
            }

            if (movieCategory.getDescryption() == null || movieCategory.getDescryption().isEmpty()) {
                return new ResponseEntity<>(new ResponseModelMsg("Descrição da categoria deve ser preechido", 03), HttpStatus.BAD_REQUEST);
            }
            baseRepository.getMovieCategories().add(movieCategory);
            return new ResponseEntity<>(new ResponseModelMsg("Registro salvo com sucesso.", 3, movieCategory), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseModelMsg("Erro no Servidor: " + e.getMessage(), 3), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ResponseModelMsg> deleteById(String categoryId) {
        ResponseEntity<ResponseModelMsg> categoria = findById(categoryId);
        if (categoria.getStatusCode() == HttpStatus.OK) {
            MovieCategory category = (MovieCategory) categoria.getBody().getData();
            baseRepository.getMovieCategories().remove(category);
            return new ResponseEntity<>(new ResponseModelMsg("Registro excluido com sucesso.", 01), HttpStatus.OK);
        } else
            return new ResponseEntity<>(new ResponseModelMsg("Registro não encontrado.", 02), HttpStatus.BAD_REQUEST);
    }
}
