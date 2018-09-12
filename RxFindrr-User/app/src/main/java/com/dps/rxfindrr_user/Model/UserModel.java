package com.dps.rxfindrr_user.Model;

public class UserModel {

    private String Firstname;
    private String Middlename;
    private String lastname;
    private String dateofbirth;
    private String address;
    private String type;
    private String password;

    public UserModel() {

    }

    public UserModel(String firstname, String middlename, String lastname, String dateofbirth, String address, String type, String password) {
        Firstname = firstname;
        Middlename = middlename;
        this.lastname = lastname;
        this.dateofbirth = dateofbirth;
        this.address = address;
        this.type = type;
        this.password = password;
    }


    @Override
    public String toString() {
        return "UserModel{" +
                "Firstname='" + Firstname + '\'' +
                ", Middlename='" + Middlename + '\'' +
                ", lastname='" + lastname + '\'' +
                ", dateofbirth='" + dateofbirth + '\'' +
                ", address='" + address + '\'' +
                ", type='" + type + '\'' +
                ", password='" + password + '\'' +
                '}';
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

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
