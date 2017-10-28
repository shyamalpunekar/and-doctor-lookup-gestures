package com.epicodus.doctorlookup.models;

import org.parceler.Parcel;

import java.util.List;
import java.util.Map;

/**
 * Created by spuek on 10/19/17.
 */


@Parcel
public class Doctor {
    private String uuid;
    private String firstName;
    private String lastName;
    private String title;
    private List<String> websites;
    private Map<String, String> phones;
    private List<Practice> practices;
    private boolean accepts_new_patients;

    public Doctor(){}

    public Doctor(String uuid, String firstName, String lastName, String title, List<String> websites, Map<String, String> phones, List<Practice> practices, boolean accepts_new_patients) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.websites = websites;
        this.phones = phones;
        this.practices = practices;
        this.accepts_new_patients = accepts_new_patients;

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


    public Map<String, String> getPhones() {
        return phones;
    }

    public void setPhones(Map<String, String> phone) {
        this.phones = phone;
    }


    public List<String> getWebsites() {
        return websites;
    }

    public void setWebsites(List<String> websites) {
        this.websites = websites;
    }

    public List<Practice> getPractices() {
        return practices;
    }

    public void setPractices(List<Practice> practices) {
        this.practices = practices;
    }

    public boolean isAccepts_new_patients() {
        return accepts_new_patients;
    }

    public void setAccepts_new_patients(boolean accepts_new_patients) {
        this.accepts_new_patients = accepts_new_patients;
    }
}
