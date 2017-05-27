
package com.example.bukagambarfrontend.POJO.ProductResponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Specs {

    @SerializedName("brand")
    @Expose
    private Object brand;
    @SerializedName("bahan")
    @Expose
    private Object bahan;
    @SerializedName("ukuran")
    @Expose
    private Object ukuran;

    public Object getBrand() {
        return brand;
    }

    public void setBrand(Object brand) {
        this.brand = brand;
    }

    public Object getBahan() {
        return bahan;
    }

    public void setBahan(Object bahan) {
        this.bahan = bahan;
    }

    public Object getUkuran() {
        return ukuran;
    }

    public void setUkuran(Object ukuran) {
        this.ukuran = ukuran;
    }

}