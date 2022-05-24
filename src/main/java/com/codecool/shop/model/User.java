package com.codecool.shop.model;

public class User extends BaseModel{
public String adress;
public String mail;
public String phone;


    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public User(String name) {
        super(name);
    }
}
