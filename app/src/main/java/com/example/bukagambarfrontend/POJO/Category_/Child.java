package com.example.bukagambarfrontend.POJO.Category_;

import java.util.ArrayList;

/**
 * Created by WIN8 on 5/22/2017.
 */

public class Child {

    private int id;

    public int getId() { return this.id; }

    public void setId(int id) { this.id = id; }

    private String name;

    public String getName() { return this.name; }

    public void setName(String name) { this.name = name; }

    private String url;

    public String getUrl() { return this.url; }

    public void setUrl(String url) { this.url = url; }

    private ArrayList<Child2> children;

    public ArrayList<Child2> getChildren() { return this.children; }

    public void setChildren(ArrayList<Child2> children) { this.children = children; }
}
