package com.example.barhelper;

import java.io.Serializable;

public class Cocktail implements Serializable {

    int id;
    String title;
    boolean isFavourite;
    int photoId;
    String description;
    String ingredients;

    public Cocktail(int id, String title, boolean isFavourite, int photoId, String description, String ingredients) {
        this.id = id;
        this.title = title;
        this.isFavourite = isFavourite;
        this.photoId = photoId;
        this.description = description;
        this.ingredients = ingredients;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public Cocktail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }
}
