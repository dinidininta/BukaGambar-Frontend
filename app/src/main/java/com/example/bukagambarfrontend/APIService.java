package com.example.bukagambarfrontend;

import com.example.bukagambarfrontend.POJO.ImagePOJO.ImageResponse;
import com.example.bukagambarfrontend.POJO.ProductResponsePOJO.ProductResponse;
import com.example.bukagambarfrontend.POJO.ProductsPOJO.ProductJson;
import com.example.bukagambarfrontend.POJO.ProvincesPOJO.Provinces;
import com.example.bukagambarfrontend.POJO.CategorPOJO.RootObject;

import retrofit.Callback;
import retrofit.http.Body;
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
    @POST("/images.json")
    void uploadFoto(@Part("file") TypedFile file, Callback<ImageResponse> cb);

    @POST("/products.json")
    void uploadProduk(@Body ProductJson productJson, Callback<ProductResponse> cb);
}
