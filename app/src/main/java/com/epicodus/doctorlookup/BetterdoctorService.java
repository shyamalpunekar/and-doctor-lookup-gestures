package com.epicodus.doctorlookup;

import com.epicodus.doctorlookup.models.Doctor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

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

        try {
            String jsonData = response.body().string();
            JSONObject doctorJson = new JSONObject(jsonData);
            JSONArray businessesJSON = doctorJson.getJSONArray("businesses");
            for (int i = 0; i < businessesJSON.length(); i++) {
                JSONObject doctorJSON = businessesJSON.getJSONObject(i);
                String uuid = doctorJSON.getString("uuid");
                String firstName = doctorJSON.getString("firstName");
                String lastName = doctorJSON.getString("lastName");
                String title = doctorJSON.getString("title");
                String phone = doctorJSON.optString("display_phone", "Phone not available");
                String website = doctorJSON.getString("url");
                double rating = doctorJSON.getDouble("rating");

//                String imageUrl = restaurantJSON.getString("image_url");
//
//                double latitude = (double) restaurantJSON.getJSONObject("coordinates").getDouble("latitude");
//
//                double longitude = (double) restaurantJSON.getJSONObject("coordinates").getDouble("longitude");

//                ArrayList<String> address = new ArrayList<>();
//                JSONArray addressJSON = restaurantJSON.getJSONObject("name")
//                        .getJSONArray("display_address");
//                for (int y = 0; y < addressJSON.length(); y++) {
//                    address.add(addressJSON.get(y).toString());
//                }

//                ArrayList<String> categories = new ArrayList<>();
//                JSONArray categoriesJSON = restaurantJSON.getJSONArray("categories");
////
//                for (int y = 0; y < categoriesJSON.length(); y++) {
//                    categories.add(categoriesJSON.getJSONObject(y).getString("title"));
//                }
                Doctor doctor = new Doctor(uuid, firstName, lastName, title,
                         website);
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
