package com.example.andre.recyclerviewsqlite;

/**
 * Created by andre on 23-Jun-17.
 */

public class Player {
    private String name,position;
    private int id;

    public Player(String name, String position, int id) {
        this.name = name;
        this.position = position;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
