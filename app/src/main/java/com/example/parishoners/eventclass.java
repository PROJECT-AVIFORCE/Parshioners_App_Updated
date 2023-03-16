package com.example.parishoners;

public class eventclass {
    String Title,Description,Date,Time;

    public eventclass() {
    }

    public eventclass(String title, String description, String date, String time) {
        Title = title;
        Description = description;
        Date = date;
        Time = time;
    }

    public String getTitle() {
        return this.Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return this.Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDate() {
        return this.Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return this.Time;
    }

    public void setTime(String time) {
        Time = time;
    }
}
