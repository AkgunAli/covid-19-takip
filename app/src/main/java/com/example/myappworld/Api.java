package com.example.myappworld;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface Api {


    String BASE_URL = "http://aliakgun.com/json/";

    @GET("haberler.php")
    Call<List<Hero>> getHeroes();
}
