package com.application.saia;

public class Visitor {
    public String id;
    public String name;
    public String description;
    public String phone;
    public String address;

    public Visitor(String id, String name, String description, String phone, String address) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.phone = phone;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}
