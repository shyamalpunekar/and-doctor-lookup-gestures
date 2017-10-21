package com.epicodus.doctorlookup.models;

import java.util.List;
import java.util.Map;
import java.util.Set;
import org.parceler.Parcel;

/**
 * Created by spuek on 10/19/17.
 */


@Parcel
public class Doctor {
    private String uuid;
    private String firstName;
    private String lastName;
    private String title;
    private Set<String> websites;
    private Map<String, String> phones;
    private List<Practice> practices;

    public Doctor(){}

    public Doctor(String uuid, String firstName, String lastName, String title, Set<String> websites, Map<String, String> phones, List<Practice> practices) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.websites = websites;
        this.phones = phones;
        this.practices = practices;

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


    public Set<String> getWebsites() {
        return websites;
    }

    public void setWebsites(Set<String> websites) {
        this.websites = websites;
    }

    public List<Practice> getPractices() {
        return practices;
    }

    public void setPractices(List<Practice> practices) {
        this.practices = practices;
    }
}
