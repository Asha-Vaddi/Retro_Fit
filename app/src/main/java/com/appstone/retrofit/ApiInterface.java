package com.appstone.retrofit;

import java.util.HashMap;

import model.Result;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface ApiInterface {

  // @GET("everything/")
   // Call<String> getAllNews(@QueryMap HashMap<String, Object> queries);

   //@GET("top-headlines/")
    //Call<String> getAllSources(@QueryMap HashMap<String,Object> queries);

    @GET("everything/")
    Call<Result> getAllNews(@QueryMap HashMap<String, Object> queries);


    @GET("top-headlines/")
    Call<Result> getAllSources(@QueryMap HashMap<String,Object> queries);
}
