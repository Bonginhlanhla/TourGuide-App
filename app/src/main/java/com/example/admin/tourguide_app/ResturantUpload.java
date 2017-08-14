package com.example.admin.tourguide_app;

/**
 * Created by Admin on 8/3/2017.
 */

public class ResturantUpload {

    private String restName , restDescrp, restUrl;

    public ResturantUpload(String restName, String restDescrp, String restUrl) {
        this.restName = restName;
        this.restDescrp = restDescrp;
        this.restUrl = restUrl;
    }

    public String getRestName() {
        return restName;
    }

    public String getRestDescrp() {
        return restDescrp;
    }

    public String getRestUrl() {
        return restUrl;
    }
    public ResturantUpload()
    {

    }
}
