package com.epicodus.doctorlookup.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.epicodus.doctorlookup.R;
import com.epicodus.doctorlookup.adapters.DoctorListAdapter;
import com.epicodus.doctorlookup.adapters.MyDoctorsArrayAdapter;
import com.epicodus.doctorlookup.models.Doctor;
import com.epicodus.doctorlookup.services.BetterdoctorService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
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
    public List<Doctor> mDoctors = new ArrayList<>();

   // private SharedPreferences mSharedPreferences;
   // private String mRecentAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);

        ButterKnife.bind(this);
        MyDoctorsArrayAdapter adapter = new MyDoctorsArrayAdapter(this, android.R.layout.simple_list_item_1, mDoctors);
        mListView.setAdapter(adapter);

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String doctor = ((TextView) view).getText().toString();

            }
        });

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
//        if (mRecentAddress != null) {
//            getDoctors(mRecentAddress);
//        }

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        getDoctors(name);


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
