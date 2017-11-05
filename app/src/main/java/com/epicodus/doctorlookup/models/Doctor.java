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
    private String imageUrl;
    private List<String> websites;
    private Map<String, String> phones;
    private List<Practice> practices;
    private boolean accepts_new_patients;
    private List<Specialty> specialties;
    private String pushId;
    String index;


    public Doctor(){}

    public Doctor(String uuid, String firstName, String lastName, String title, List<String> websites, Map<String, String> phones, String imageUrl, List<Practice> practices, boolean accepts_new_patients,
                  List<Specialty> specialties) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.websites = websites;
        this.phones = phones;
        this.practices = practices;
        this.accepts_new_patients = accepts_new_patients;
        this.specialties = specialties;
        this.imageUrl = imageUrl;
        this.index = "not_specified";

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

    public List<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(List<Specialty> specialties) {
        this.specialties = specialties;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}
