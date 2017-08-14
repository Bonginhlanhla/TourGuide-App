package com.example.admin.tourguide_app;

/**
 * Created by Admin on 8/2/2017.
 */

public class ImageUpload {

    private String name,description, url;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public ImageUpload(String name, String description, String url) {
        this.name = name;
        this.description = description;
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public ImageUpload()
    {

    }
}
