package com.example.customerdemo.dto;

public class CustomerDto {

    private Long id;
    private String name;
    private String surname;
    private String Location;

    public CustomerDto() {
    }

    public CustomerDto(Long id, String name, String surname, String location) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        Location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}
