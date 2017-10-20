package com.epicodus.doctorlookup.services;

import com.epicodus.doctorlookup.Constants;
import com.epicodus.doctorlookup.models.Doctor;

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
        Map<String, String> phones  = new HashMap<>();
        try {
            String jsonData = response.body().string();
            JSONObject doctorJson = new JSONObject(jsonData);
            JSONArray businessesJSON = doctorJson.getJSONArray("data");
            for (int i = 0; i < businessesJSON.length(); i++) {
                JSONObject doctorJSON = businessesJSON.getJSONObject(i);
                String uuid = doctorJSON.get("uid").toString();


                String firstName = doctorJSON.getJSONObject("profile").get("first_name").toString();
                String lastName = doctorJSON.getJSONObject("profile").get("last_name").toString();
                String title = doctorJSON.getJSONObject("profile").get("title").toString();;
              //  String phone = doctorJSON.optString("display_phone", "Phone not available");
               if(doctorJSON.getJSONArray("practices") != null ) {
                   boolean isWebsiteExists = false;
                   for(int j =0; j< doctorJSON.getJSONArray("practices").length(); j++) {
                       JSONObject practices = doctorJSON.getJSONArray("practices").getJSONObject(i);
                       if(!practices.isNull("website")) {
                           isWebsiteExists = true;
                           websites.add(practices.get("website").toString());
                       }

                   }

                   if(!isWebsiteExists && websites.size() ==0 ) {
                       websites.add("No Website Available");
                   }

                   for(int j =0; j< doctorJSON.getJSONArray("practices").length(); j++) {
                       JSONObject practices = doctorJSON.getJSONArray("practices").getJSONObject(i);
                       if(practices.getJSONArray("phones") != null) {
                           boolean isPhoneExists = false;

                           for(int k =0; k< practices.getJSONArray("phones").length(); k++) {

                                if(practices.getJSONArray("phones") != null ) {
                                    JSONObject phone = practices.getJSONArray("phones").getJSONObject(k);
                                    if (phone != null) {
                                        isPhoneExists = true;
                                        String phoneNumber = phone.get("number").toString();
                                        String type = phone.get("type").toString();
                                        phones.put(type, phoneNumber);
                                    }
                                }
                           }

                           if(!isPhoneExists && phones.size() ==0 ) {
                               phones.put("Phone#" ,"Phone# is not Available!");
                           }

                       }


                   }

               }

                Doctor doctor = new Doctor(uuid, firstName, lastName, title,
                         websites, phones);
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
