package com.aisdigital.model;

import io.swagger.annotations.ApiModelProperty;
import org.springframework.lang.NonNull;

import java.util.Objects;

public class MovieCategory {
    @ApiModelProperty(example = "C666")
    private String id;
    @ApiModelProperty(example = "Terror" , required = true)
    @NonNull
    private String descryption;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescryption() {
        return descryption;
    }

    public void setDescryption(String descryption) {
        this.descryption = descryption;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieCategory category = (MovieCategory) o;
        return id.equals(category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
