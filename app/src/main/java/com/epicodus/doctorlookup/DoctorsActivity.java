package com.epicodus.doctorlookup;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DoctorsActivity extends AppCompatActivity {

    @Bind(R.id.locationTextView)
    TextView mLocationTextView;
    @Bind(R.id.listView)
    ListView mListView;


    private String[] doctors = new String[] {"Jay Bell", "Dan Bell", "Amy Bell", "Bel Kis",
            "Angela Bell", "Lori Day", "Julianne", "Mary Balson", "Ashley", "Troy", "Kandi",
            "Pamela John", "Anup's Belur", "Nella Bello", "Belinda Bells"};

    private String[] information = new String[] {"Specializes in your and your family's health",
            "Specializes in physical therapy",
            "Specializes in the care of the female reproductive system",
            "Specializes in imaging via X-rays and ultrasound.", "Specializes in vision and " +
            "prescribing glasses and contact lenses",
            "Specializes in physical therapy",
            "Specializes in managing pain and anesthesia in surgeries",
            "Specializes in physical therapy.",
            "Physical therapist assistants are skilled health care providers ",
            " An occupational therapist is a person who has graduated from an entry-level " +
                    "occupational therapy program accredited by the Accreditation Council for " +
                    "Occupational Therapy Education (ACOTE) or predecessor organizations",
            "Description is unavailable", "Specializes in managing pain and anesthesia in " +
            "surgeries", "Specializes in physical therapy.", "Specializes in teeth and oral health",
            "An occupational therapist is a person who has graduated from an entry-level" +
                    " occupational therapy program accredited by the Accreditation Council for" +
                    " Occupational Therapy Education (ACOTE) or predecessor organizations" };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);

        ButterKnife.bind(this);
//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, doctors);

        MyDoctorsArrayAdapter adapter = new MyDoctorsArrayAdapter(this, android.R.layout.simple_list_item_1, doctors, information);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String restaurant = ((TextView)view).getText().toString();
                Toast.makeText(DoctorsActivity.this, restaurant, Toast.LENGTH_LONG).show();
            }
        });

        Intent intent = getIntent();
        String location= intent.getStringExtra("location");

        mLocationTextView.setText("Here are all the doctors near: " + location);

    }
}
