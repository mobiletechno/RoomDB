package com.gtihub.MobileTechno.KingOfUniverse.zoomDB.View.Activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.viewpager.widget.ViewPager;

import android.app.DatePickerDialog;
import android.content.Context;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.gtihub.MobileTechno.KingOfUniverse.zoomDB.Model.Adapters.ViewPagerAdapter;
import com.gtihub.MobileTechno.KingOfUniverse.zoomDB.R;
/**
 * Created by A.Rajkumar on 18/08/2020.
 */

public class MainActivity extends AppCompatActivity {
    public static Context ctx;

    public static String mydate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPagerAdapter viewPagerAdapter =
                new ViewPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(viewPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }
}