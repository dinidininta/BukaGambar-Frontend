package com.example.bukagambarfrontend.POJO;

import java.util.ArrayList;

/**
 * Created by WIN8 on 5/22/2017.
 */

public class RootObject {
    private String status;

    public String getStatus() { return this.status; }

    public void setStatus(String status) { this.status = status; }

    private ArrayList<Category> categories;

    public ArrayList<Category> getCategories() { return this.categories; }

    public void setCategories(ArrayList<Category> categories) { this.categories = categories; }

    private Object message;

    public Object getMessage() { return this.message; }

    public void setMessage(Object message) { this.message = message; }
}
