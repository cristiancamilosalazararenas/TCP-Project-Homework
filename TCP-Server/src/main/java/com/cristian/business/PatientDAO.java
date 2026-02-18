package com.cristian.business;

public class PatientDAO {

    private String idNumber;
    private String name;
    private String lastname;
    private int age;
    private String email;
    private String gender;
    private String city;
    private String country;
    private PatientCSVWriter patientCSVWriter;

    public PatientDAO(String idNumber, String name, String lastname, int age, String email, String gender, String city, String country, PatientCSVWriter patientCSVWriter) {
        this.idNumber = idNumber;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.gender = gender;
        this.city = city;
        this.country = country;
        this.patientCSVWriter = patientCSVWriter;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public PatientCSVWriter getPatientCSVWriter() {
        return patientCSVWriter;
    }

    public void setPatientCSVWriter(PatientCSVWriter patientCSVWriter) {
        this.patientCSVWriter = patientCSVWriter;
    }

    public String register(){
        StringBuilder sb = new StringBuilder();
        sb.append(this.idNumber).append(",").append(this.name).append(",")
                .append(this.lastname).append(",").append(this.age).append(",")
                .append(this.email).append(",").append(this.gender).append(",")
                .append(this.city).append(",").append(this.country);
        return patientCSVWriter.writeFasta(sb.toString());
    }
}
