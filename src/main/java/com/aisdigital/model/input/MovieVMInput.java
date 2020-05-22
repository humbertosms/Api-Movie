package com.aisdigital.model.input;

import io.swagger.annotations.ApiModelProperty;

public class MovieVMInput {
    @ApiModelProperty(example = "Mov999")
    private String id;
    @ApiModelProperty(example = "Filme de terror")
    private String name;
    @ApiModelProperty(example = "A1")
    private String idAuthor;
    @ApiModelProperty(example = "C1")
    private String idCategory;
    @ApiModelProperty(example = "20/12/2020")
    private String releaseDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(String idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
