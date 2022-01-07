package com.example.mymiwok.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mymiwok.Fragments.FamilyFragment;
import com.example.mymiwok.R;

public class FamilyActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new FamilyFragment())
                .commit();
    }

}