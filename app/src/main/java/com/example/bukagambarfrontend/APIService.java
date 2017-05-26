package com.example.bukagambarfrontend;

import com.example.bukagambarfrontend.POJO.Provinces;
import com.example.bukagambarfrontend.POJO.RootObject;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.mime.TypedFile;

/**
 * Created by WIN8 on 5/22/2017.
 */

public interface APIService {

    @GET("/categories.json")
    void getCategory(Callback<RootObject> cb);

    @GET("/address/provinces.json")
    void getProvinces(Callback<Provinces> cb);

   // @Field("nama_p") String nama_p, @Field("cat_id") String cat_id,

    @Multipart
    @POST("/upload.php")
    void uploadFoto(@Part("file") TypedFile file, Callback<Response> cb);
}
