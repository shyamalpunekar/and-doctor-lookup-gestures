package com.epicodus.doctorlookup.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.epicodus.doctorlookup.Constants;
import com.epicodus.doctorlookup.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class DoctorsActivity extends AppCompatActivity {

    public static final String TAG = DoctorsActivity.class.getSimpleName();

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;


    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                addToSharedPreferences(query);
                //getDoctors(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    // add addToSharedPreferences to write data
        private void addToSharedPreferences(String name) {
        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, name).apply();
    }


//
//    private void getDoctors(String name) {
//        final BetterdoctorService betterdoctorService = new BetterdoctorService();
//
//        betterdoctorService.findRDoctors(name, new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.w("MyTag", "requestFailed");
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//
//                try {
//
//                    mDoctors = betterdoctorService.processResults(response);
//
//                    DoctorsActivity.this.runOnUiThread(new Runnable() {
//
//                        @Override
//                        public void run() {
//
//
//                            mAdapter = new DoctorListAdapter(getApplicationContext(), mDoctors);
//                            mRecyclerView.setAdapter(mAdapter);
//                            RecyclerView.LayoutManager layoutManager =
//                                    new LinearLayoutManager(DoctorsActivity.this);
//                            mRecyclerView.setLayoutManager(layoutManager);
//                            mRecyclerView.setHasFixedSize(true);
//
//
//                        }
//
//                    });
//
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//
//
//                }
//            }
//
//        });
//
//    }

}
