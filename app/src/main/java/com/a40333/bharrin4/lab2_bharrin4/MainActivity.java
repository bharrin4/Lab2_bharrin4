package com.a40333.bharrin4.lab2_bharrin4;

//import android.support.v7.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ND Athletics");

        MyCsvFileReader myCsvFileReader = new MyCsvFileReader(getApplicationContext());
        String mDrawableName = "schedule";
        int resID = getResources().getIdentifier(mDrawableName , "raw", getPackageName());
        Log.d("tag", "resID="+resID);
        final ArrayList <Team> al = myCsvFileReader.readCsvFile(resID);



        ScheduleAdapter scheduleAdapter = new ScheduleAdapter(getApplicationContext(), al);
        ListView scheduleListView = (ListView) findViewById(R.id.scheduleListView);
        scheduleListView.setAdapter(scheduleAdapter);

        AdapterView.OnItemClickListener clickListener = new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent mIntent = new Intent(MainActivity.this, DetailActivity.class);
                mIntent.putExtra("team", al.get(position));
                startActivity(mIntent);
            }
        };
        // this will automatically attach the listener to each item of the listview.
        scheduleListView.setOnItemClickListener (clickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();

        if (res_id == R.id.share) {
            /*code for sharing the schedule
            Intent shareIntent = new Intent();
            shareIntent.setAction(android.content.Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra("android.content.Intent.EXTRA_SUBJECT", "BasketBall Matches");
            shareIntent.putExtra("android.content.Intent.EXTRA_TEXT", gameSchedule());
            startActivity(Intent.createChooser(shareIntent), "Share via"); */
        }

        else if (res_id == R.id.sync) {
            // Snackbar with Try Again action
        }

        else if (res_id == R.id.settings) {
            // Floating Contextual Menu with options

        }
        return true;
    }

}
