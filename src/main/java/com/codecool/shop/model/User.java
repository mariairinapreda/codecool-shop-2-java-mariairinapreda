package com.codecool.shop.model;

public class User extends BaseModel{

    private String name;
    private String address;
    private String email;
    private String phone;
    private String password;
    private String city;
    private String state;
    private String zipCode;
    private UserStatus userStatus;

    public User(String name, String email, String password, UserStatus userStatus) {
        super(name);
        this.email = email;
        this.password = password;
        this.userStatus = userStatus;
    }

    public User(String name, String address, String email, String phone, String password, String city, String state, String zipCode) {
        super(name);
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }
}
