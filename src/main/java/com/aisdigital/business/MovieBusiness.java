package com.aisdigital.business;

import com.aisdigital.model.Author;
import com.aisdigital.model.Movie;
import com.aisdigital.model.MovieCategory;
import com.aisdigital.model.input.MovieVMInput;
import com.aisdigital.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieBusiness  {

    private AuthorBusiness authorBusiness;

    private MovieCategoryBusiness categoryBusiness;

    private BaseRepository baseRepository;

    @Autowired
    public void setBaseRepository(BaseRepository baseRepository) {
        this.baseRepository = baseRepository;
    }

    @Autowired
    public void setAuthorBusiness(AuthorBusiness authorBusiness) {
        this.authorBusiness = authorBusiness;
    }

    @Autowired
    public void setCategoryBusiness(MovieCategoryBusiness categoryBusiness) {
        this.categoryBusiness = categoryBusiness;
    }

    public List<Movie> findAll() {
        return baseRepository.getMovies();
    }

    public ResponseEntity findById(String movieId) {
        Movie movie = baseRepository.getMovies().stream().filter(a -> a.getId().equals(movieId)).findFirst().orElse(null);
        if (movie != null) {
            return new ResponseEntity<Movie>(movie, HttpStatus.OK);
        }
        return new ResponseEntity(null, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity save(MovieVMInput movie) {
        List<String> msgRetorno = new ArrayList<>();
        Movie filmeSave = new Movie();
        try {
            if (movie.getId() == null || movie.getId().isEmpty()) {
                movie.setId("M" + baseRepository.getMovies().size() + 1);
            }
            filmeSave.setId(movie.getId());

            if (movie.getName() == null || movie.getName().isEmpty()) {
                msgRetorno.add("Nome do filme deve ser preenchido!");
            }
            filmeSave.setName(movie.getName());

            if (movie.getIdAuthor() == null || movie.getIdAuthor().isEmpty()) {
                msgRetorno.add("Autor do filme deve ser preenchido!");
            } else {
                ResponseEntity author = authorBusiness.findById(movie.getIdAuthor());
                if (author.getStatusCode() != HttpStatus.OK) {
                    msgRetorno.add("Autor do filme Informado Incorretamente!");
                } else {
                    filmeSave.setAuthor((Author) author.getBody());
                }
            }

            if (movie.getIdCategory() == null || movie.getIdCategory().isEmpty()) {
                msgRetorno.add("Categoria do filme deve ser preenchida!");
            } else {
                ResponseEntity category = categoryBusiness.findById(movie.getIdCategory());
                if (category.getStatusCode() != HttpStatus.OK) {
                    msgRetorno.add("Categoria do filme Informada Incorretamente!");
                } else {
                    filmeSave.setCategory((MovieCategory) category.getBody());
                }
            }

            if (movie.getReleaseDate() == null || movie.getReleaseDate().isEmpty()) {
                msgRetorno.add("Data de Lançamento do filme deve ser preenchida!");
            } else {
                filmeSave.setReleaseDate(movie.getReleaseDate());
            }

            if (msgRetorno.size() > 0) {
                return new ResponseEntity(msgRetorno, HttpStatus.BAD_REQUEST);
            }

            baseRepository.getMovies().add(filmeSave);
        } catch (Exception e) {
            msgRetorno.add(e.getMessage());
            return new ResponseEntity(msgRetorno, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(filmeSave, HttpStatus.OK);

    }

    public ResponseEntity deleteById(String movieId) {
        ResponseEntity deleteItem = findById(movieId);
        if (deleteItem.getStatusCode() == HttpStatus.OK) {
            baseRepository.getMovies().remove(deleteItem.getBody());
            return new ResponseEntity("Registro excluido com sucesso.", HttpStatus.OK);
        }
        return new ResponseEntity("Registro não encontrado.", HttpStatus.BAD_REQUEST);
    }
}
