package com.epam.mentoring.controllers;

/**
 * Created by Aleksandr_Ruzanov on 03.03.2017.
 */
public class RequestEmployee {

    private long id;

    private String email;

    private String name;

    public RequestEmployee() {
    }

    public RequestEmployee(long id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
