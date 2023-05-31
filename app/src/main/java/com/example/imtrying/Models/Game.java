package com.example.imtrying.Models;

public class Game {

    private String name, description, type, year, time;

    public Game() {}

    public Game(String name, String description, String type, String year, String time) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.year = year;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
