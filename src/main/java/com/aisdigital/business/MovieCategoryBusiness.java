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

    public ResponseEntity findById(String categoryId) {
        MovieCategory movieCategory = baseRepository.getMovieCategories().stream().filter(a -> a.getId().equals(categoryId)).findAny().orElse(null);
        if (movieCategory != null) {
            return new ResponseEntity<MovieCategory>(movieCategory, HttpStatus.OK);
        }
        return new ResponseEntity(new ResponseModelMsg("Registro não encontrado.",02), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<MovieCategory> save(MovieCategory movieCategory) {
        try {
            if (movieCategory.getId() == null || movieCategory.getId().isEmpty()) {
                movieCategory.setId("C" + baseRepository.getMovieCategories().size() + 1);
            }

            if (movieCategory.getDescryption() == null || movieCategory.getDescryption().isEmpty()) {
                return new ResponseEntity(new ResponseModelMsg("Descrição da categoria deve ser preechido",03), HttpStatus.BAD_REQUEST);
            }
            baseRepository.getMovieCategories().add(movieCategory);
            return new ResponseEntity(movieCategory, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ResponseModelMsg> deleteById(String categoryId) {
        ResponseEntity categoria = findById(categoryId);
        if (categoria.getStatusCode() == HttpStatus.OK) {
            baseRepository.getMovieCategories().remove(categoria.getBody());
            return new ResponseEntity(new ResponseModelMsg("Registro excluido com sucesso.",01), HttpStatus.OK);
        }else
            return new ResponseEntity(new ResponseModelMsg("Registro não encontrado.",02), HttpStatus.BAD_REQUEST);
    }
}
