package com.example.androidapplication.Api;


import com.example.androidapplication.model.PhotosResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("photos")
    Call<ArrayList<PhotosResponse>> getPhotos();

}
