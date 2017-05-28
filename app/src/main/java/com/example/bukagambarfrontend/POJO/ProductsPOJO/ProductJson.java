package com.example.bukagambarfrontend.POJO.ProductsPOJO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProductJson {

    @SerializedName("product")
    @Expose
    private Product product;
    @SerializedName("images")
    @Expose
    private String images;
    @SerializedName("force_insurance")
    @Expose
    private String forceInsurance;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public String getForceInsurance() {
        return forceInsurance;
    }

    public void setForceInsurance(String forceInsurance) {
        this.forceInsurance = forceInsurance;
    }

}