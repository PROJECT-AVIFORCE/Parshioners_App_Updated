package com.example.parishoners;

import com.google.firebase.Timestamp;

public class announcementitemclass {
    String title,des;
    Timestamp t;

    public Timestamp getT() {
        return t;
    }

    public void setT(Timestamp t) {
        this.t = t;
    }

    public announcementitemclass(String title, String des, Timestamp t ) {
        this.title = title;
        this.des= des;
        this.t = t;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getdescription() {
        return des;
    }

    public void setdescription(String des) {
        this.des = des;
    }


    public announcementitemclass(){

    }

}
