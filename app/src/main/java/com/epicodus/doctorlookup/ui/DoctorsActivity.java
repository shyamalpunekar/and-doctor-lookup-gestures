package com.epicodus.doctorlookup.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.epicodus.doctorlookup.adapters.MyDoctorsArrayAdapter;
import com.epicodus.doctorlookup.R;
import com.epicodus.doctorlookup.adapters.DoctorListAdapter;
import com.epicodus.doctorlookup.models.Doctor;
import com.epicodus.doctorlookup.services.BetterdoctorService;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Response;

public class DoctorsActivity extends AppCompatActivity {

    public static final String TAG = DoctorsActivity.class.getSimpleName();

    @Bind(R.id.nameTextView)
    TextView mNameTextView;
    @Bind(R.id.listView)
    ListView mListView;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private DoctorListAdapter mAdapter;
    public ArrayList<Doctor> mDoctors = new ArrayList<>();

    private String[] doctors = new String[]{"Jay Bell", "Dan Bell", "Amy Bell", "Bel Kis",
            "Angela Bell", "Lori Day", "Julianne", "Mary Balson", "Ashley", "Troy", "Kandi",
            "Pamela John", "Anup's Belur", "Nella Bello", "Belinda Bells"};

    private String[] information = new String[]{"Specializes in your and your family's health",
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
                    " Occupational Therapy Education (ACOTE) or predecessor organizations"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);

        ButterKnife.bind(this);
//        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, doctors);

        MyDoctorsArrayAdapter adapter = new MyDoctorsArrayAdapter(this, android.R.layout.simple_list_item_1, mDoctors, information);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String doctor = ((TextView) view).getText().toString();

            }
        });

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");


        getDoctors(name);
        if(mDoctors.size() ==0) {


        }

    }

    private void getDoctors(String name) {
        final BetterdoctorService betterdoctorService = new BetterdoctorService();

            betterdoctorService.findRDoctors(name, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.w("MyTag", "requestFailed");
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                    try {
                            Log.v(TAG, response.body().string());
                            mDoctors = betterdoctorService.processResults(response);
                            DoctorsActivity.this.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mAdapter = new DoctorListAdapter(getApplicationContext(), mDoctors);
                                mRecyclerView.setAdapter(mAdapter);
                                RecyclerView.LayoutManager layoutManager =
                                        new LinearLayoutManager(DoctorsActivity.this);
                                mRecyclerView.setLayoutManager(layoutManager);
                                mRecyclerView.setHasFixedSize(true);

                            }
                        });

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            });
        }

}
