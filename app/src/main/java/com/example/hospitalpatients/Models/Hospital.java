package com.example.hospitalpatients.Models;

public class Hospital {
    public String locroom, corpnum, department, room;

    public Hospital() {}

    public Hospital(String locroom, String corpnum, String department, String room) {
        this.locroom = locroom;
        this.corpnum = corpnum;
        this.department = department;
        this.room = room;
    }

    public String getLocroom() {
        return locroom;
    }

    public void setLocroom(String locroom) {
        this.locroom = locroom;
    }

    public String getCorpnum() {
        return corpnum;
    }

    public void setCorpnum(String corpnum) {
        this.corpnum = corpnum;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }
}
