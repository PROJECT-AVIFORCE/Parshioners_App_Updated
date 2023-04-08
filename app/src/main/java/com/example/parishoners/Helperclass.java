package com.example.parishoners;

public class Helperclass {

    String email, name , dob , gender , phone ;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Helperclass(String email, String name, String dob, String gender, String phone) {
        this.email = email;
        this.name = name;
        this.dob = dob;
        this.gender = gender;
        this.phone = phone;
    }

    public Helperclass() {
    }
}
