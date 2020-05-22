package com.aisdigital;

import com.aisdigital.business.AuthorBusiness;
import com.aisdigital.business.MovieCategoryBusiness;
import com.aisdigital.enuns.HttpStatusCode;
import com.aisdigital.model.Author;
import com.aisdigital.model.MovieCategory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.junit.JUnitTestRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.Assert;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DesafioApplicationTests {

    @Autowired
    private AuthorBusiness authorBusiness;

    @Autowired
    private MovieCategoryBusiness movieCategoryBusiness;

    @Test
    void contextLoads() {
    }

    @Test
    void categoriaNull() throws Exception {
        MovieCategory category = new MovieCategory();
        ResponseEntity categoriaResponse = movieCategoryBusiness.save(category);
        Assertions.assertEquals(HttpStatusCode.BAD_REQUEST.getCod(), categoriaResponse.getStatusCode().value());
    }

    @Test
    void categoriaNoDescryption() throws Exception {
        MovieCategory category = new MovieCategory();
//        category.setId("CT2");
//        mockMvc.perform(post("/categories")
//                .contentType("application/json")
//                .content(objectMapper.writeValueAsString(category)))
//                .andExpect(status().isBadRequest());
    }

    @Test
    void categoriaNoId() throws Exception {
        MovieCategory category = new MovieCategory();
//        category.setDescryption("Infantil");
//        mockMvc.perform(post("/categories")
//                .contentType("application/json")
//                .content(objectMapper.writeValueAsString(category)))
//                .andExpect(status().isOk());
    }

    @Test
    void categoriaRemove() throws Exception {
        MovieCategory category = new MovieCategory();
        String fixId = "Infantil9999";
        category.setId(fixId);
        category.setDescryption("Infantil");
//        mockMvc.perform(post("/categories")
//                .contentType("application/json")
//                .content(objectMapper.writeValueAsString(category)))
//                .andExpect(status().isOk());
//
//        mockMvc.perform(get("/categories")
//                .contentType("application/json")
//                .param("categoryId", fixId))
//                .andExpect(status().isOk());
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/categories", fixId))
//                .andExpect(status().isOk());
//
//        mockMvc.perform(get("/categories")
//                .contentType("application/json")
//                .param("categoryId", fixId))
//                .andExpect(status().isBadRequest());

    }


    @Test
    void author() throws Exception {
        Author author = new Author();
//        mockMvc.perform(post("/authors")
//                .contentType("application/json")
//                .content(objectMapper.writeValueAsString(author)))
//                .andExpect(status().isBadRequest());
    }

    @Test
    void authorNoNane() throws Exception {
        Author author = new Author();
        author.setId("A1");
//        mockMvc.perform(post("/authors")
//                .contentType("application/json")
//                .content(objectMapper.writeValueAsString(author)))
//                .andExpect(status().isBadRequest());
    }

    @Test
    void authorNoId() throws Exception {
        Author author = new Author();
        author.setName("Humberto Silva");
//        mockMvc.perform(post("/authors")
//                .contentType("application/json")
//                .content(objectMapper.writeValueAsString(author)))
//                .andExpect(status().isOk());
    }

}
