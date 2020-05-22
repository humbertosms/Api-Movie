package com.aisdigital.repository;

import com.aisdigital.model.Author;
import com.aisdigital.model.Movie;
import com.aisdigital.model.MovieCategory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BaseRepository {
    private List<Movie> movies;
    private List<Author> authors;
    private List<MovieCategory> movieCategories;

    public BaseRepository() {
        if (movies == null && authors ==null && movieCategories==null) {
            setFirstData();
        }
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public List<MovieCategory> getMovieCategories() {
        return movieCategories;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void setMovieCategories(List<MovieCategory> movieCategories) {
        this.movieCategories = movieCategories;
    }

    private void setFirstData() {
        this.movies = new ArrayList<>();
        this.authors = new ArrayList<>();
        this.movieCategories = new ArrayList<>();

        Author georgeLucas = new Author();
        georgeLucas.setId("A1");
        georgeLucas.setName("George Lucas");

        authors.add(georgeLucas);

        Author jossWhedon = new Author();
        jossWhedon.setId("A2");
        jossWhedon.setName("Joss Whedon");

        authors.add(jossWhedon);

        MovieCategory acao = new MovieCategory();
        acao.setId("C2");
        acao.setDescryption("Ação");

        movieCategories.add(acao);

        MovieCategory ficcao = new MovieCategory();
        ficcao.setId("C1");
        ficcao.setDescryption("Ficção Cientifica");

        movieCategories.add(ficcao);

        Movie gerraEstrelas = new Movie();
        gerraEstrelas.setId("M1");
        gerraEstrelas.setAuthor(georgeLucas);
        gerraEstrelas.setCategory(ficcao);
        gerraEstrelas.setName("Guerra nas Estrelas");
        gerraEstrelas.setReleaseDate("18/08/1977");

        movies.add(gerraEstrelas);

        Movie ultron = new Movie();
        ultron.setId("M2");
        ultron.setAuthor(jossWhedon);
        ultron.setCategory(acao);
        ultron.setName("Vingadores: Era de Ultron");
        ultron.setReleaseDate("23/04/2015");

        movies.add(ultron);

    }
}
