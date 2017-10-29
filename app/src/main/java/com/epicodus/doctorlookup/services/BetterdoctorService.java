package com.epicodus.doctorlookup.services;

import com.epicodus.doctorlookup.Constants;
import com.epicodus.doctorlookup.models.Doctor;
import com.epicodus.doctorlookup.models.Practice;
import com.epicodus.doctorlookup.models.Specialty;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by spuek on 10/19/17.
 */

public class BetterdoctorService {

    public static void findRDoctors(String name, Callback callback) {

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.DOCTOR_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.DOCTOR_NAME_QUERY_PARAMETER, name);
        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)

                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);


    }


    public ArrayList<Doctor> processResults(Response response) {
        ArrayList<Doctor> doctors = new ArrayList<>();
        Set<String> websites = new HashSet<>();
        Map<String, String> phones = null;
        boolean accepts_new_patients =false;

        List<Practice> practices = new ArrayList<>();
        try {
            String jsonData = response.body().string();
            JSONObject doctorJson = new JSONObject(jsonData);
            List<Specialty> specialties = new ArrayList<>();
            JSONArray businessesJSON = doctorJson.getJSONArray("data");
            for (int i = 0; i < businessesJSON.length(); i++) {
                JSONObject doctorJSON = businessesJSON.getJSONObject(i);
                String uuid = doctorJSON.get("uid").toString();


                String firstName = doctorJSON.getJSONObject("profile").get("first_name").toString();
                String lastName = doctorJSON.getJSONObject("profile").get("last_name").toString();
                String title = doctorJSON.getJSONObject("profile").get("title").toString();;

                String ratings = doctorJSON.getJSONObject("ratings").toString();;
//                if(doctorJSON.getJSONObject("ratings").getJSONArray() {
//
//                }
               if(doctorJSON.getJSONArray("practices") != null ) {
                   boolean isWebsiteExists = false;
                   boolean isVisitAddress = false;
                   for(int j =0; j< doctorJSON.getJSONArray("practices").length(); j++) {
                    Practice individualPractice = new Practice();
                       JSONObject practice = doctorJSON.getJSONArray("practices").getJSONObject(j);

                       if(!practice.isNull("website")) {
                           isWebsiteExists = true;
                           websites.add(practice.get("website").toString());
                           individualPractice.setWebsite(practice.get("website").toString());

                       }
                       else {
                           individualPractice.setWebsite("Website No Available");
                       }

                       if(!practice.isNull("accepts_new_patients")) {
                           if(practice.getBoolean("accepts_new_patients"))
                           accepts_new_patients = true;
                       }
                       else {
                           individualPractice.setWebsite("Website No Available");
                       }

                       if(practice.getJSONArray("phones") != null) {
                           boolean isPhoneExists = false;
                           phones  = new HashMap<>();
                           for(int k =0; k< practice.getJSONArray("phones").length(); k++) {

                               if(practice.getJSONArray("phones") != null ) {
                                   JSONObject phone = practice.getJSONArray("phones").getJSONObject(k);
                                   if (phone != null) {
                                       isPhoneExists = true;
                                       String phoneNumber = phone.get("number").toString();
                                       String type = phone.get("type").toString();
                                       phones.put(type, phoneNumber);
                                       individualPractice.setPhones(phones);
                                   }
                               }
                           }

                           if(!isPhoneExists && phones.size() ==0 ) {
                               phones.put("Phone#" ,"Phone# is not Available!");
                           }

                       }

                       if(!practice.isNull("visit_address")) {
                           Map<String, String> visitAddress = new HashMap<>();
                           isVisitAddress = true;
                           visitAddress.put("City: ",practice.getJSONObject("visit_address").get("city").toString());
                           visitAddress.put("State: ",practice.getJSONObject("visit_address").get("state").toString());
                           visitAddress.put("Street: ",practice.getJSONObject("visit_address").get("street").toString());
//                           visitAddress.put("Street2: ",practice.getJSONObject("visit_address").get("street2").toString());
                           visitAddress.put("Zip: ",practice.getJSONObject("visit_address").get("zip").toString());

                           individualPractice.setVisitAddress(visitAddress);
                           practices.add(individualPractice);

                       }
                       else {
                           Map<String, String> visitAddress = new HashMap<>();
                           visitAddress.put("Visit Address", "Not Avilable");
                           individualPractice.setVisitAddress(visitAddress);
                       }

                   }

                   if(!isWebsiteExists && websites.size() ==0 ) {
                       websites.add("No Website Available");
                   }
               }

                if(doctorJSON.getJSONArray("specialties") != null ) {


                    for (int j = 0; j < doctorJSON.getJSONArray("specialties").length(); j++) {
                        Specialty individualSpecialty = new Specialty();
                        JSONObject specialty = doctorJSON.getJSONArray("specialties").getJSONObject(j);

                        if (!specialty.isNull("name")) {
                            individualSpecialty.setName(specialty.get("name").toString());

                        } else {
                            individualSpecialty.setName("Name Not Available");
                        }

                        if (!specialty.isNull("actors")) {
                            individualSpecialty.setActors(specialty.get("actors").toString());

                        } else {
                            individualSpecialty.setActors("Actors Not Available");
                        }

                        if (!specialty.isNull("category")) {
                            individualSpecialty.setCategory(specialty.get("category").toString());

                        } else {
                            individualSpecialty.setCategory("category Not Available");
                        }

                        specialties.add(individualSpecialty);

                    }
                }
List<String>  websitesList = new ArrayList<>(websites);
                Doctor doctor = new Doctor(uuid, firstName, lastName, title,
                websitesList, phones, practices,accepts_new_patients, specialties);
                doctors.add(doctor);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        return doctors;
    }
}
