package com.epicodus.doctorlookup.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.doctorlookup.Constants;
import com.epicodus.doctorlookup.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    //private SharedPreferences mSharedPreferences;
    //private SharedPreferences.Editor mEditor;

    private DatabaseReference mSearchedNameReference;

    @Bind(R.id.findDoctorsButton)
    Button mFindDoctorsButton;
    @Bind(R.id.locationEditText)
    EditText mLocationEditText;
    @Bind(R.id.appNameTextView)
    TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mSearchedNameReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_NAME);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        //ad font
        Typeface ostrichFont = Typeface.createFromAsset(getAssets(), "fonts/ostrich-regular.ttf");
        mAppNameTextView.setTypeface(ostrichFont);

       // mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
       // mEditor = mSharedPreferences.edit();

        mFindDoctorsButton.setOnClickListener(this);
    }

        @Override
        public void onClick(View v) {

            if (v == mFindDoctorsButton) {

                String name = mLocationEditText.getText().toString();

                //
                saveNameToFirebase(name);
                //add sharedPreference
                if(!(name).equals("")) {
                  //  addToSharedPreferences(name);
                }

                if (name.equals("") || name == null ){

                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
                    dlgAlert.setMessage("Please enter valid Name");
                    dlgAlert.create().show();
                    return;

                }
                else {
                    Intent intent = new Intent(MainActivity.this, DoctorsActivity.class);
                    intent.putExtra("name", name);

                    startActivity(intent);
                }

            }
        }

    public void saveNameToFirebase(String name) {
        mSearchedNameReference.push().setValue(name);
    }

//    private void addToSharedPreferences(String name) {
//        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, name).apply();
//    }


    }

