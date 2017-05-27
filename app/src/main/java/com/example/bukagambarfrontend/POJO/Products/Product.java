package com.example.bukagambarfrontend.POJO.Products;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("new")
    @Expose
    private String _new;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("negotiable")
    @Expose
    private String negotiable;
    @SerializedName("weight")
    @Expose
    private String weight;
    @SerializedName("stock")
    @Expose
    private String stock;
    @SerializedName("description_bb")
    @Expose
    private String descriptionBb;
    @SerializedName("free_shipping")
    @Expose
    private List<Integer> freeShipping = null;
    @SerializedName("product_detail_attributes")
    @Expose
    private ProductDetailAttributes productDetailAttributes;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNew() {
        return _new;
    }

    public void setNew(String _new) {
        this._new = _new;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNegotiable() {
        return negotiable;
    }

    public void setNegotiable(String negotiable) {
        this.negotiable = negotiable;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getDescriptionBb() {
        return descriptionBb;
    }

    public void setDescriptionBb(String descriptionBb) {
        this.descriptionBb = descriptionBb;
    }

    public List<Integer> getFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(List<Integer> freeShipping) {
        this.freeShipping = freeShipping;
    }

    public ProductDetailAttributes getProductDetailAttributes() {
        return productDetailAttributes;
    }

    public void setProductDetailAttributes(ProductDetailAttributes productDetailAttributes) {
        this.productDetailAttributes = productDetailAttributes;
    }

}