package com.example.parishoners;

import com.google.firebase.Timestamp;
import com.google.protobuf.StringValue;

public class announcementclass {
    String titles,des;
    Timestamp timestamp;

    public announcementclass() {
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }


    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
