package com.epicodus.doctorlookup.models;

import org.parceler.Parcel;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by spuek on 10/20/17.
 */
@Parcel
public class Practice {
    private String website;
    private Map<String, String> phones;
    private Map<String, String> visitAddress;

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Map<String, String> getPhones() {
        return phones;
    }

    public void setPhones(Map<String, String> phones) {
        this.phones = phones;
    }

    public Map<String, String> getVisitAddress() {
        return visitAddress;
    }

    public void setVisitAddress(Map<String, String> visitAddress) {
        this.visitAddress = visitAddress;
    }
}
