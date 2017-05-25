package com.example.bukagambarfrontend.POJO;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Provinces {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("provinces")
    @Expose
    private List<String> provinces = null;
    @SerializedName("message")
    @Expose
    private Object message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getProvinces() {
        return provinces;
    }

    public void setProvinces(List<String> provinces) {
        this.provinces = provinces;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

}