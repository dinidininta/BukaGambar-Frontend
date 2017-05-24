package com.example.bukagambarfrontend;

import com.example.bukagambarfrontend.POJO.Provinces;
import com.example.bukagambarfrontend.POJO.RootObject;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by WIN8 on 5/22/2017.
 */

public interface APIService {

    @GET("/categories.json")
    void getCategory(Callback<RootObject> cb);

    @GET("/address/provinces.json")
    void getProvinces(Callback<Provinces> cb);
}
