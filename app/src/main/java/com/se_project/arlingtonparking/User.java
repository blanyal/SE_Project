package com.se_project.arlingtonparking;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class User {
    @PrimaryKey
    @NonNull
    public String username;

    @ColumnInfo(name = "password")
    public String password;

    @ColumnInfo(name = "lastn")
    public String lastn;

    @ColumnInfo(name = "firstn")
    public String firstn;

    @ColumnInfo(name = "role")
    public int role;

    @ColumnInfo(name = "uta_id")
    public int uta_id;

    @ColumnInfo(name = "phone")
    public int phone;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "address")
    public String address;

    @ColumnInfo(name = "city")
    public String city;

    @ColumnInfo(name = "state")
    public String state;

    @ColumnInfo(name = "zip")
    public int zip;

    @ColumnInfo(name = "license")
    public String license;

    @ColumnInfo(name = "dob")
    public String dob;

    @ColumnInfo(name = "permit")
    public String permit;

    @ColumnInfo(name = "car")
    public String car;


    public User(String username, String password, String lastn, String firstn, int role,
                int uta_id, int phone, String email, String address, String city,
                String state, int zip, String license, String dob, String permit, String car) {
        this.username = username;
        this.password = password;
        this.lastn = lastn;
        this.firstn = firstn;
        this.role = role;
        this.uta_id = uta_id;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.license = license;
        this.dob = dob;
        this.permit = permit;
        this.car = car;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastn() {
        return lastn;
    }

    public void setLastn(String lastn) {
        this.lastn = lastn;
    }

    public String getFirstn() {
        return firstn;
    }

    public void setFirstn(String firstn) {
        this.firstn = firstn;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getUta_id() {
        return uta_id;
    }

    public void setUta_id(int uta_id) {
        this.uta_id = uta_id;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPermit() {
        return permit;
    }

    public void setPermit(String permit) {
        this.permit = permit;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }
}
