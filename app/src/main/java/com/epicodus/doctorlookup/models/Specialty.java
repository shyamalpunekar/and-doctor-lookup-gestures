package com.epicodus.doctorlookup.models;

import org.parceler.Parcel;

/**
 * Created by spunek on 10/28/17.
 */


@Parcel

public class Specialty {

    private String name;
    private String description;
    private String category;
    private String actors;


    public Specialty() {
    }


    public Specialty(String name, String description, String category, String actors) {
        this.name = name;
        this.description = description;
        this.category = category;
        this.actors = actors;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }
}
