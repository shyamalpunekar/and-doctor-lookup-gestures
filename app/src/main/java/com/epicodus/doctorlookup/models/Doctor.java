package com.epicodus.doctorlookup.models;

/**
 * Created by spuek on 10/19/17.
 */

public class Doctor {
    private String uuid;
    private String firstName;
    private String lastName;
    private String title;

    public Doctor(String uuid, String firstName, String lastName, String title, String website) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.website = website;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    private String website;


}
