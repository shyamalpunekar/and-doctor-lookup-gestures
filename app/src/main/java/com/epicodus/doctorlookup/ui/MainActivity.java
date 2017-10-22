package com.epicodus.doctorlookup.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epicodus.doctorlookup.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{



    @Bind(R.id.findDoctorsButton)
    Button mFindDoctorsButton;
    @Bind(R.id.locationEditText)
    EditText mLocationEditText;
    @Bind(R.id.appNameTextView)
    TextView mAppNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        //ad font
        Typeface ostrichFont = Typeface.createFromAsset(getAssets(), "fonts/ostrich-regular.ttf");
        mAppNameTextView.setTypeface(ostrichFont);

        mFindDoctorsButton.setOnClickListener(this);
    }

        @Override
        public void onClick(View v) {

            if (v == mFindDoctorsButton) {

                String name = mLocationEditText.getText().toString();

                if (name.equals("") || name == null ){

                    AlertDialog.Builder dlgAlert = new AlertDialog.Builder(this);
                    dlgAlert.setMessage("Please enter valid Name");
                    dlgAlert.create().show();

                }
                else {
                    Intent intent = new Intent(MainActivity.this, DoctorsActivity.class);
                    intent.putExtra("name", name);

                    startActivity(intent);
                }

            }
        }


    }

