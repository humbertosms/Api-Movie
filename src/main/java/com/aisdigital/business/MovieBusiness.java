package com.aisdigital.business;

import com.aisdigital.model.Author;
import com.aisdigital.model.Movie;
import com.aisdigital.model.MovieCategory;
import com.aisdigital.model.ResponseModelMsg;
import com.aisdigital.model.input.MovieVMInput;
import com.aisdigital.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieBusiness {

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

    public ResponseEntity<ResponseModelMsg> findById(String movieId) {
        Movie movie = baseRepository.getMovies().stream().filter(a -> a.getId().equals(movieId)).findFirst().orElse(null);
        if (movie != null) {
            return new ResponseEntity<>(new ResponseModelMsg("Registro encontrado.", 1, movie), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseModelMsg("Registro não encontrado.", 1), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ResponseModelMsg> save(MovieVMInput movie) {
        List<String> msgRetorno = new ArrayList<>();
        Movie filmeSave = new Movie();
        try {
            if (movie.getId() == null || movie.getId().isEmpty())
                movie.setId("M" + (baseRepository.getMovies().size() + 1));
            else {
                if (findById(movie.getId()).getStatusCode() == HttpStatus.OK) {
                    msgRetorno.add("Id do filme já foi utilizado!");
                }
            }

            validateMovie(movie, msgRetorno);

            if (!msgRetorno.isEmpty()) {
                return new ResponseEntity<>(new ResponseModelMsg(msgRetorno, 1), HttpStatus.BAD_REQUEST);
            }

            fillData(movie, filmeSave);

            baseRepository.getMovies().add(filmeSave);
            return new ResponseEntity<>(new ResponseModelMsg("Registro salvo com sucesso.", 1, filmeSave), HttpStatus.CREATED);
        } catch (Exception e) {
            msgRetorno.add("Erro no servidor: " + e.getMessage());
            return new ResponseEntity<>(new ResponseModelMsg(msgRetorno, 1), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private void fillData(MovieVMInput movie, Movie filmeSave) {
        filmeSave.setId(movie.getId());
        filmeSave.setName(movie.getName());
        filmeSave.setReleaseDate(movie.getReleaseDate());
        filmeSave.setCategory((MovieCategory) categoryBusiness.findById(movie.getIdCategory()).getBody().getData());
        filmeSave.setAuthor((Author) authorBusiness.findById(movie.getIdAuthor()).getBody().getData());
    }

    public ResponseEntity<ResponseModelMsg> update(MovieVMInput movie) {
        List<String> msgRetorno = new ArrayList<>();
        Movie filmeSave = new Movie();
        try {
            if (movie.getId() == null || movie.getId().isEmpty()) {
                msgRetorno.add("Id do filme deve ser preechido.");
            }
            validateMovie(movie, msgRetorno);

            if (!msgRetorno.isEmpty()) {
                return new ResponseEntity<>(new ResponseModelMsg(msgRetorno, 1), HttpStatus.BAD_REQUEST);
            }

            fillData(movie, filmeSave);

            baseRepository.getMovies().remove(filmeSave);
            baseRepository.getMovies().add(filmeSave);
            return new ResponseEntity<>(new ResponseModelMsg("Resgistro salvo com sucesso.", 1, filmeSave), HttpStatus.OK);
        } catch (Exception e) {
            msgRetorno.add("Erro no servidor: " + e.getMessage());
            return new ResponseEntity<>(new ResponseModelMsg(msgRetorno, 1), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    private void validateMovie(MovieVMInput movie, List<String> msgRetorno) {
        if (movie.getName() == null || movie.getName().isEmpty()) {
            msgRetorno.add("Nome do filme deve ser preenchido!");
        }

        if ((movie.getIdAuthor() == null) || movie.getIdAuthor().isEmpty()) {
            msgRetorno.add("Autor do filme deve ser preenchido!");
        }

        if (movie.getIdCategory() == null || movie.getIdCategory().isEmpty()) {
            msgRetorno.add("Categoria do filme deve ser preenchida!");
        }

        if (movie.getReleaseDate() == null || movie.getReleaseDate().isEmpty()) {
            msgRetorno.add("Data de Lançamento do filme deve ser preenchida!");
        }

        if (HttpStatus.OK != categoryBusiness.findById(movie.getIdCategory()).getStatusCode()) {
            msgRetorno.add("Categoria do filme Informada Incorretamente!");
        }

        if (HttpStatus.OK != authorBusiness.findById(movie.getIdAuthor()).getStatusCode()) {
            msgRetorno.add("Autor do filme Informado Incorretamente!");
        }
    }

    public ResponseEntity<ResponseModelMsg> deleteById(String movieId) {
        ResponseEntity<ResponseModelMsg> deleteItem = findById(movieId);
        if (deleteItem.getStatusCode() == HttpStatus.OK) {
            baseRepository.getMovies().remove(deleteItem.getBody().getData());
            return new ResponseEntity<>(new ResponseModelMsg("Registro excluido com sucesso.", 1), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseModelMsg("Registro não encontrado.", 1), HttpStatus.BAD_REQUEST);
    }
}
