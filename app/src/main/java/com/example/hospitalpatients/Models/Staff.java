package com.example.hospitalpatients.Models;

public class Staff {
    public String id, doctor;

    public Staff(){}

    public Staff(String id, String doctor) {
        this.id = id;
        this.doctor = doctor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
}
