package com.dps.rxfindrr_user.Model;

public class UserModel {


    private String username;
    private String Firstname;
    private String Middlename;
    private String lastname;
    private String address;
    private String password;


    @Override
    public String toString() {
        return "UserModel{" +
                "Firstname='" + Firstname + '\'' +
                ", Middlename='" + Middlename + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                '}';
    }


    public UserModel(String firstname, String middlename, String lastname, String address, String password, String username) {
        Firstname = firstname;
        Middlename = middlename;
        this.lastname = lastname;
        this.address = address;
        this.password = password;
        this.username = username;
    }


    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getMiddlename() {
        return Middlename;
    }

    public void setMiddlename(String middlename) {
        Middlename = middlename;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public UserModel() {

    }


}
