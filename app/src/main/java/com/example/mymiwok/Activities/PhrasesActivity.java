package com.example.mymiwok.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.mymiwok.Fragments.PhrasesFragment;
import com.example.mymiwok.R;

public class PhrasesActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_main);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, new PhrasesFragment())
                .commit();

    }


}