package com.epicodus.doctorlookup;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();

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

        mFindDoctorsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                String location = mLocationEditText.getText().toString();
                Log.d(TAG, location);

//                Toast.makeText(MainActivity.this, "Hello!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, DoctorsActivity.class);
                intent.putExtra("location", location);
                startActivity(intent);

            }


        });


    }
}
