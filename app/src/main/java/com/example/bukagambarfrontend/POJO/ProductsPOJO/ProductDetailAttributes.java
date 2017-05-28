package com.example.bukagambarfrontend.POJO.ProductsPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductDetailAttributes {

    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("bahan")
    @Expose
    private String bahan;
    @SerializedName("referer")
    @Expose
    private String referer;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBahan() {
        return bahan;
    }

    public void setBahan(String bahan) {
        this.bahan = bahan;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

}