package com.example.hospitalpatients.Models;

public class Patient {
    public String cardnumber, patient, hospitalization, doctor, location, age, diagnosis;

    public Patient(){}
    public Patient(String cardnumber, String patient, String hospitalization, String doctor, String location, String age, String diagnosis) {
        this.cardnumber = cardnumber;
        this.patient = patient;
        this.hospitalization = hospitalization;
        this.doctor = doctor;
        this.location = location;
        this.age = age;
        this.diagnosis = diagnosis;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getHospitalization() {
        return hospitalization;
    }

    public void setHospitalization(String hospitalization) {
        this.hospitalization = hospitalization;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }
}
