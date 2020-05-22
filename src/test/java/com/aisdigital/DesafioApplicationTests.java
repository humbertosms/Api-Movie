package com.aisdigital;

import com.aisdigital.business.AuthorBusiness;
import com.aisdigital.business.MovieBusiness;
import com.aisdigital.business.MovieCategoryBusiness;
import com.aisdigital.model.Author;
import com.aisdigital.model.Movie;
import com.aisdigital.model.MovieCategory;
import com.aisdigital.model.ResponseModelMsg;
import com.aisdigital.model.input.MovieVMInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class DesafioApplicationTests {

    @Autowired
    private AuthorBusiness authorBusiness;

    @Autowired
    private MovieCategoryBusiness movieCategoryBusiness;

    @Autowired
    private MovieBusiness movieBusiness;

    @Test
    void contextLoads() {
    }

    @Test
    void categoriaNull() throws Exception {
        MovieCategory category = new MovieCategory();
        ResponseEntity<ResponseModelMsg> categoriaResponse = movieCategoryBusiness.save(category);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), categoriaResponse.getStatusCode().value());
    }

    @Test
    void categoriaNoDescryption() throws Exception {
        MovieCategory category = new MovieCategory();
        category.setId("CT2");
        ResponseEntity<ResponseModelMsg> categoriaResponse = movieCategoryBusiness.save(category);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(), categoriaResponse.getStatusCode().value());
    }

    @Test
    void categoriaNoId() throws Exception {
        MovieCategory category = new MovieCategory();
        category.setDescryption("Infantil");
        ResponseEntity<ResponseModelMsg> categoriaResponse = movieCategoryBusiness.save(category);
        Assertions.assertEquals(HttpStatus.CREATED.value(), categoriaResponse.getStatusCode().value());
    }

    @Test
    void categoriaRemove() throws Exception {
        MovieCategory category = new MovieCategory();
        String fixId = "Infantil9999";
        category.setId(fixId);
        category.setDescryption("Infantil");

        ResponseEntity<ResponseModelMsg> categoriaResponse = movieCategoryBusiness.save(category);
        Assertions.assertEquals(HttpStatus.CREATED.value(), categoriaResponse.getStatusCode().value());

        categoriaResponse = movieCategoryBusiness.deleteById(fixId);
        Assertions.assertEquals(HttpStatus.OK.value(), categoriaResponse.getStatusCode().value());

        categoriaResponse = movieCategoryBusiness.findById(fixId);
        Assertions.assertEquals(HttpStatus.BAD_GATEWAY.value(), categoriaResponse.getStatusCode().value());
    }

    @Test
    void categoriaUpdate() throws Exception {
        MovieCategory category = new MovieCategory();
        String fixId = "Infantil9999";
        category.setId(fixId);
        category.setDescryption("Infantil");

        ResponseEntity<ResponseModelMsg> categoriaResponse = movieCategoryBusiness.save(category);
        Assertions.assertEquals(HttpStatus.CREATED.value(), categoriaResponse.getStatusCode().value());

        category.setDescryption("Horror");

        categoriaResponse = movieCategoryBusiness.update(category);
        Assertions.assertEquals(HttpStatus.OK.value(), categoriaResponse.getStatusCode().value());
        Assertions.assertEquals(category.getDescryption(), ((MovieCategory) categoriaResponse.getBody().getData()).getDescryption());
    }

    @Test
    void authorNull() throws Exception {
        Author author = new Author();
        ResponseEntity<ResponseModelMsg> autor = authorBusiness.save(author);
        Assertions.assertEquals(HttpStatus.BAD_GATEWAY.value(), autor.getStatusCode().value());
    }

    @Test
    void authorNoNane() throws Exception {
        Author author = new Author();
        author.setId("A1");
        ResponseEntity<ResponseModelMsg> autor = authorBusiness.save(author);
        Assertions.assertEquals(HttpStatus.BAD_GATEWAY.value(), autor.getStatusCode().value());
    }

    @Test
    void authorNoId() throws Exception {
        Author author = new Author();
        author.setName("Humberto Silva");
        ResponseEntity<ResponseModelMsg> autor = authorBusiness.save(author);
        Assertions.assertEquals(HttpStatus.OK.value(), autor.getStatusCode().value());
    }

    @Test
    void authorUpdate() throws Exception {
        Author author = new Author();
        author.setId("H9999");
        author.setName("Humberto Silva");
        ResponseEntity<ResponseModelMsg> autor = authorBusiness.save(author);
        Assertions.assertEquals(HttpStatus.CREATED.value(), autor.getStatusCode().value());

        author.setName("Silva Humberto");
        autor = authorBusiness.update(author);
        Assertions.assertEquals(HttpStatus.OK.value(), autor.getStatusCode().value());
        Assertions.assertEquals(author.getName(), ((Author) autor.getBody().getData()).getName());
    }

    @Test
    void authorDelete() throws Exception {
        Author author = new Author();
        author.setId("H9999");
        author.setName("Humberto Silva");
        ResponseEntity<ResponseModelMsg> autor = authorBusiness.save(author);
        Assertions.assertEquals(HttpStatus.CREATED.value(), autor.getStatusCode().value());

        autor = authorBusiness.deleteById("H9999");
        Assertions.assertEquals(HttpStatus.OK.value(), autor.getStatusCode().value());
    }

    @Test
    void movieEmpty() throws Exception {
        MovieVMInput movieVMInput = new MovieVMInput();
        ResponseEntity<ResponseModelMsg> movie = movieBusiness.save(movieVMInput);
        Assertions.assertEquals(HttpStatus.BAD_GATEWAY.value(), movie.getStatusCode().value());
    }

    @Test
    void movieNoId() throws Exception {
        MovieVMInput movieVMInput = new MovieVMInput();
        movieVMInput.setIdAuthor("A1");
        movieVMInput.setIdCategory("C1");
        movieVMInput.setName("A volta dos que não foram");
        movieVMInput.setReleaseDate("01/01/2001");
        ResponseEntity<ResponseModelMsg> movie = movieBusiness.save(movieVMInput);
        Assertions.assertEquals(HttpStatus.CREATED.value(), movie.getStatusCode().value());
    }

    @Test
    void movieNoValidAuthor() throws Exception {
        MovieVMInput movieVMInput = new MovieVMInput();
        movieVMInput.setIdAuthor("T01");
        movieVMInput.setIdCategory("C1");
        movieVMInput.setName("A volta dos que não foram");
        movieVMInput.setReleaseDate("01/01/2001");
        ResponseEntity<ResponseModelMsg> movie = movieBusiness.save(movieVMInput);
        Assertions.assertEquals(HttpStatus.BAD_GATEWAY.value(), movie.getStatusCode().value());
    }

    @Test
    void movieNoValidCategory() throws Exception {
        MovieVMInput movieVMInput = new MovieVMInput();
        movieVMInput.setIdAuthor("A1");
        movieVMInput.setIdCategory("T01");
        movieVMInput.setName("A volta dos que não foram");
        movieVMInput.setReleaseDate("01/01/2001");
        ResponseEntity<ResponseModelMsg> movie = movieBusiness.save(movieVMInput);
        Assertions.assertEquals(HttpStatus.BAD_GATEWAY.value(), movie.getStatusCode().value());
    }

    @Test
    void movieNoName() throws Exception {
        MovieVMInput movieVMInput = new MovieVMInput();
        movieVMInput.setIdAuthor("A1");
        movieVMInput.setIdCategory("C1");
        movieVMInput.setReleaseDate("01/01/2001");
        ResponseEntity<ResponseModelMsg> movie = movieBusiness.save(movieVMInput);
        Assertions.assertEquals(HttpStatus.BAD_GATEWAY.value(), movie.getStatusCode().value());
    }

    @Test
    void movieNoReleaseDate() throws Exception {
        MovieVMInput movieVMInput = new MovieVMInput();
        movieVMInput.setIdAuthor("A1");
        movieVMInput.setIdCategory("C1");
        movieVMInput.setName("A volta dos que não foram");
        ResponseEntity<ResponseModelMsg> movie = movieBusiness.save(movieVMInput);
        Assertions.assertEquals(HttpStatus.BAD_GATEWAY.value(), movie.getStatusCode().value());
    }

    @Test
    void movieDelete() throws Exception {
        MovieVMInput movieVMInput = new MovieVMInput();
        String fixId = "Mov99999";
        movieVMInput.setId(fixId);
        movieVMInput.setIdAuthor("A1");
        movieVMInput.setIdCategory("C1");
        movieVMInput.setName("A volta dos que não foram");
        movieVMInput.setReleaseDate("01/01/2001");
        ResponseEntity<ResponseModelMsg> movie = movieBusiness.save(movieVMInput);
        Assertions.assertEquals(HttpStatus.CREATED.value(), movie.getStatusCode().value());

        movie = movieBusiness.deleteById(fixId);
        Assertions.assertEquals(HttpStatus.OK.value(), movie.getStatusCode().value());
    }

    @Test
    void movieUpdate() throws Exception {
        MovieVMInput movieVMInput = new MovieVMInput();
        String fixId = "Mov99999";
        movieVMInput.setId(fixId);
        movieVMInput.setIdAuthor("A1");
        movieVMInput.setIdCategory("C1");
        movieVMInput.setName("A volta dos que não foram");
        movieVMInput.setReleaseDate("01/01/2001");

        ResponseEntity<ResponseModelMsg> movie = movieBusiness.save(movieVMInput);
        Assertions.assertEquals(HttpStatus.CREATED.value(), movie.getStatusCode().value());

        movieVMInput.setName("Poeira em alto mar");
        movie = movieBusiness.update(movieVMInput);
        Assertions.assertEquals(HttpStatus.OK.value(), movie.getStatusCode().value());
        Assertions.assertEquals(movieVMInput.getName(), ((Movie) movie.getBody().getData()).getName());
    }
}
