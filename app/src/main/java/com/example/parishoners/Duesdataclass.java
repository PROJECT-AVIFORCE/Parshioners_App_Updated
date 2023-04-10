package com.example.parishoners;

public class Duesdataclass {
    String Title,Description,Date,Time;

    public Duesdataclass() {

    }

    public Duesdataclass(String title, String description, String date, String time) {
        this.Title = title;
        this.Description = description;
        this.Date = date;
        this.Time = time;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        this.Time = time;
    }
}
