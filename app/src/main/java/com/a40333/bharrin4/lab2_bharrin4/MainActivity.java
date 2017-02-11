package com.a40333.bharrin4.lab2_bharrin4;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends Activity {


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList al = new ArrayList<String []>();
        //add teams to array

        al.add(new String[]{"fsu_sports_logo", "Florida State", "Feb 11"});
        al.add(new String[]{"bc_logo", "Boston College", "Feb 14"});
        al.add(new String[]{"nc_state", "North Carolina State", "Feb 18"});
        al.add(new String[]{"gtech_logo", "Georgia Tech", "Feb 26"});
        al.add(new String[]{"bc_logo", "Boston College", "March 1"});
        al.add(new String[]{"louisville", "Louisville", "March 4"});
        al.add(new String[]{"acc", "ACC Tournament", "March 7"});
        al.add(new String[]{"ncaa", "NCAA Tournament", "March 16"});


        ScheduleAdapter scheduleAdapter = new ScheduleAdapter(this, al);
        ListView scheduleListView = (ListView) findViewById(R.id.scheduleListView);
        scheduleListView.setAdapter(scheduleAdapter);

    }
}
