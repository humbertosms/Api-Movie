package com.aisdigital.business;

import com.aisdigital.model.Author;
import com.aisdigital.model.ResponseModelMsg;
import com.aisdigital.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AuthorBusiness {

    BaseRepository baseRepository;

    @Autowired
    public void setBaseRepository(BaseRepository baseRepository) {
        this.baseRepository = baseRepository;
    }

    public List<Author> findAll() {
        return baseRepository.getAuthors();
    }

    public ResponseEntity<ResponseModelMsg> findById(String idAuthor) {
        Author author = baseRepository.getAuthors().stream().filter(a -> a.getId().equals(idAuthor)).findAny().orElse(null);
        if (author != null) {
            return new ResponseEntity<>(new ResponseModelMsg("Registro encontrado.", 5, author), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseModelMsg("Registro não encontrado.", 5), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<ResponseModelMsg> save(Author author) {
        try {
            if (author.getId() == null || author.getId().isEmpty()) {
                author.setId("A" + (baseRepository.getAuthors().size() + 1));
            } else {

                if (HttpStatus.OK == findById(author.getId()).getStatusCode()) {
                    return new ResponseEntity<>(new ResponseModelMsg("Id do autor já foi utilizado", 5), HttpStatus.BAD_REQUEST);
                }
            }
            if (author.getName() == null || author.getName().isEmpty()) {
                return new ResponseEntity<>(new ResponseModelMsg("Nome do autor deve ser preechido", 5), HttpStatus.BAD_REQUEST);
            }

            baseRepository.getAuthors().add(author);
            return new ResponseEntity<>(new ResponseModelMsg("Registro salvo com sucesso.", 5, author), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseModelMsg("Erro no Servidor: " + e.getMessage(), 99), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ResponseModelMsg> update(Author author) {
        try {
            if (author.getId() == null || author.getId().isEmpty()) {
                return new ResponseEntity<>(new ResponseModelMsg("Id do Autor deve ser preechido.", 5), HttpStatus.BAD_REQUEST);
            }

            if (HttpStatus.OK != findById(author.getId()).getStatusCode()) {
                return new ResponseEntity<>(new ResponseModelMsg("Id do Autor deve existir.", 5), HttpStatus.BAD_REQUEST);
            }

            if (author.getName() == null || author.getName().isEmpty()) {
                return new ResponseEntity<>(new ResponseModelMsg("Nome do Autor deve ser preechido.", 5), HttpStatus.BAD_REQUEST);
            }

            baseRepository.getAuthors().remove(author);
            baseRepository.getAuthors().add(author);
            return new ResponseEntity<>(new ResponseModelMsg("Registro salvo com sucesso.", 5, author), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseModelMsg("Erro no Servidor:"+ e.getMessage(), 99), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<ResponseModelMsg> deleteById(String idAuthor) {
        ResponseEntity<ResponseModelMsg> autor = findById(idAuthor);
        if (autor.getStatusCode() == HttpStatus.OK) {
            ResponseModelMsg removeRespAuthor = autor.getBody();
            Author removeAuthor = (Author) removeRespAuthor.getData();
            baseRepository.getAuthors().remove(removeAuthor);
            return new ResponseEntity<>(new ResponseModelMsg("Registro excluido com sucesso.", 1), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ResponseModelMsg("Registro não encontrado.", 2), HttpStatus.BAD_REQUEST);
    }
}
