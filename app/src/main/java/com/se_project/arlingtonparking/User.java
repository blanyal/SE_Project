package com.se_project.arlingtonparking;

public class User {
    private int id;
    private String username;
    private String password;
    private String lastn;
    private String firstn;
    private int role;
    private int uta_id;
    private int phone;
    private String email;
    private String address;
    private String city;
    private String state;
    private int zip;
    private String license;
    private String dob;
    private String permit;
    private String car;


    public User(int id, String username, String password, String lastn, String firstn, int role,
                int uta_id, int phone, String email, String address, String city,
                String state, int zip, String license, String dob, String permit, String car) {
        id = id;
        username = username;
        password = password;
        lastn = lastn;
        firstn = firstn;
        role = role;
        uta_id = uta_id;
        phone = phone;
        email = email;
        address = address;
        city = city;
        state = state;
        zip = zip;
        license = license;
        dob = dob;
        permit = permit;
        car = car;
    }

    public User(String username, String password, String lastn, String firstn, int role,
                int uta_id, int phone, String email, String address, String city,
                String state, int zip, String license, String dob, String permit, String car) {
        username = username;
        password = password;
        lastn = lastn;
        firstn = firstn;
        role = role;
        uta_id = uta_id;
        phone = phone;
        email = email;
        address = address;
        city = city;
        state = state;
        zip = zip;
        license = license;
        dob = dob;
        permit = permit;
        car = car;
    }

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
