package com.example.mymiwok.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.example.mymiwok.Adapters.CategoryFragmentPagerAdapter;
import com.example.mymiwok.R;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        ActionBar actionBar;
        actionBar = getSupportActionBar();

        // Define ColorDrawable object and parse color
        // using parseColor method
        // with color hash code as its parameter
        ColorDrawable colorDrawable
            = new ColorDrawable(Color.parseColor("#FF2F1D1A"));

        actionBar.setBackgroundDrawable(colorDrawable);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        // Create an adapter that knows which fragment should be shown on each page
        CategoryFragmentPagerAdapter adapter =
                new CategoryFragmentPagerAdapter(getSupportFragmentManager());

        // Set the adapter onto the view pager
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setupWithViewPager(viewPager);

//        TextView numbers = findViewById(R.id.numbers);
//        TextView phrases = findViewById(R.id.phrases);
//        TextView family = findViewById(R.id.family);
//        TextView colors = findViewById(R.id.colors);
//        numbers.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Opening the list of Numbers", Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(MainActivity.this, NumbersActivity.class);
//                startActivity(i);
//            }
//        });
//
//        phrases.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Opening the list of Phrases", Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(MainActivity.this, PhrasesActivity.class);
//                startActivity(i);
//            }
//        });
//
//        family.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Opening the list of names of Family members", Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(MainActivity.this, FamilyActivity.class);
//                startActivity(i);
//            }
//        });
//
//        colors.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Opening the list of Colors", Toast.LENGTH_SHORT).show();
//                Intent i = new Intent(MainActivity.this, ColorsActivity.class);
//                startActivity(i);
//            }
//        });
    }
}