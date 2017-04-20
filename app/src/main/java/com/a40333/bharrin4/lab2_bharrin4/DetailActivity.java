package com.a40333.bharrin4.lab2_bharrin4;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by User on 2/15/2017.
 */

public class DetailActivity extends AppCompatActivity {

    static final int REQUEST_TAKE_PHOTO = 1;
    File photoFile = null;
    String mCurrentPhotoPath;
    String photoName;
    long team_id;

    @Override

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("ND Athletics");

        final Team teamInfo = (Team) getIntent().getSerializableExtra("team");
        Game gameInfo = (Game) getIntent().getSerializableExtra("game");

        TextView gameTime = (TextView) findViewById(R.id.gameTime);
        gameTime.setText(gameInfo.getGameTime());

        TextView gameLocation = (TextView) findViewById(R.id.gameLocation);
        gameLocation.setText(gameInfo.getGameLocation());

        ImageView opposingLogo = (ImageView) findViewById(R.id.opposingLogo);
        String mDrawableName = teamInfo.getOpposingLogo();
        Log.d("logo", mDrawableName);
        int resID = getResources().getIdentifier(mDrawableName, "drawable", getPackageName());
        opposingLogo.setImageResource(resID);

        TextView opposingName = (TextView) findViewById(R.id.opposingName);
        opposingName.setText(teamInfo.getOpposingName());

        TextView opposingMascot = (TextView) findViewById(R.id.opposingMascot);
        opposingMascot.setText(teamInfo.getOpposingMascot());

        TextView opposingRec = (TextView) findViewById(R.id.opposingRec);
        opposingRec.setText(teamInfo.getOpposingRec());

        TextView scoreID = (TextView) findViewById(R.id.scoreID);
        scoreID.setText(gameInfo.getScoreID());

        TextView finalString = (TextView) findViewById(R.id.finalString);
        finalString.setText(gameInfo.getFinalString());

        TextView ndName = (TextView) findViewById(R.id.ndName);
        ndName.setText(teamInfo.getNdName());

        TextView ndMascot = (TextView) findViewById(R.id.ndMascot);
        ndMascot.setText(teamInfo.getNdMascot());

        TextView ndRec = (TextView) findViewById(R.id.ndRec);
        ndRec.setText(teamInfo.getNdRec());

        ImageView ndLogo = (ImageView) findViewById(R.id.ndLogo);
        String mDrawableName2 = teamInfo.getNdLogo();
        int resID2 = getResources().getIdentifier(mDrawableName2, "drawable", getPackageName());
        ndLogo.setImageResource(resID2);

        final Button cameraButton = (Button) findViewById(R.id.cameraButton);
        cameraButton.setText("Photo Gallery");
        Intent i = getIntent();
        team_id = (i.getExtras().getLong("team_id"));

        cameraButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mIntent = new Intent(DetailActivity.this, GalleryActivity.class);
                mIntent.putExtra("team_id", team_id);
                System.out.println("team_id = " + team_id);
                startActivity(mIntent);
            }
        });
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int res_id = item.getItemId();

        if (res_id == R.id.share) {
            //code for sharing the schedule
            Intent shareIntent = new Intent();
            shareIntent.setAction(android.content.Intent.ACTION_SEND);
            shareIntent.setType("plain/text");
            shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "BasketBall Matches");
            //shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, gameSchedule());
            startActivity(android.content.Intent.createChooser(shareIntent, "Share via"));
        }
        return true;
    }
};

