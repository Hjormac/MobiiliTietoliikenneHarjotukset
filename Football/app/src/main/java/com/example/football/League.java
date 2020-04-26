package com.example.football;

public class League {

    private String name;
    private String id;

    public League(String n , String i) {
        this.name = n;
        this.id = i;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
