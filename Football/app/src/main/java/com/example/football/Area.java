package com.example.football;

public class Area {

    private String id;
    private String name;

    public Area(String i, String n) {
        this.id = i;
        this.name = n;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
