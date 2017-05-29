package com.example.bukagambarfrontend;

import com.example.bukagambarfrontend.POJO.CategorPOJO.CategoryAttributes;
import com.example.bukagambarfrontend.POJO.ImagePOJO.CompareResponse;
import com.example.bukagambarfrontend.POJO.ImagePOJO.ImageResponse;
import com.example.bukagambarfrontend.POJO.ProductResponsePOJO.ProductResponse;
import com.example.bukagambarfrontend.POJO.ProductsPOJO.ProductJson;
import com.example.bukagambarfrontend.POJO.ProvincesPOJO.Provinces;
import com.example.bukagambarfrontend.POJO.CategorPOJO.RootObject;
import com.squareup.okhttp.RequestBody;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.http.Part;
import retrofit.http.Path;
import retrofit.mime.TypedFile;
import rx.Observable;

/**
 * Created by WIN8 on 5/22/2017.
 */

public interface APIService {

    @GET("/categories.json")
    void getCategory(Callback<RootObject> cb);

    @GET("/address/provinces.json")
    void getProvinces(Callback<Provinces> cb);

    @Multipart
    @POST("/api")
    Observable<CompareResponse> compareFoto(@Part("file") TypedFile file, @Part("nama_p") String nama_p,
                                            @Part("cat_id") String cat_id);

    @Multipart
    @POST("/images.json")
    Observable<ImageResponse> uploadFoto(@Part("file") TypedFile file);

    @POST("/products.json")
    void uploadProduk(@Body ProductJson productJson, Callback<ProductResponse> cb);

    @GET("/categories/{id}/attributes.json")
    void getCategoryAttributes(@Path("id") String id, Callback<CategoryAttributes> cb);

}
