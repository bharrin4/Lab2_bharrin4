package com.a40333.bharrin4.lab2_bharrin4;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

/**
 * Created by User on 2/15/2017.
 */

public class DetailActivity extends Activity {

    @Override

    public void onCreate (Bundle bundle) {

        super.onCreate(bundle);
        setContentView(R.layout.activity_detail);

        String[] stringInfo = getIntent().getStringArrayExtra("team");

        TextView gameTime = (TextView) findViewById(R.id.gameTime);
        gameTime.setText(stringInfo[0]);

        TextView gameLocation = (TextView) findViewById(R.id.gameLocation);
        gameLocation.setText(stringInfo[1]);

        ImageView opposingLogo = (ImageView) findViewById(R.id.opposingLogo);
        String mDrawableName = stringInfo[2];
        int resID = getResources().getIdentifier(mDrawableName , "drawable",  getPackageName());
        opposingLogo.setImageResource(resID);

        TextView opposingName = (TextView) findViewById(R.id.opposingName);
        opposingName.setText(stringInfo[3]);

        TextView opposingMascot = (TextView) findViewById(R.id.opposingMascot);
        opposingMascot.setText(stringInfo[4]);

        TextView opposingRec = (TextView) findViewById(R.id.opposingRec);
        opposingRec.setText(stringInfo[5]);

        TextView scoreID = (TextView) findViewById(R.id.scoreID);
        scoreID.setText(stringInfo[6]);

        TextView finalString = (TextView) findViewById(R.id.finalString);
        finalString.setText(stringInfo[7]);

        TextView ndName = (TextView) findViewById(R.id.ndName);
        ndName.setText(stringInfo[8]);

        TextView ndMascot = (TextView) findViewById(R.id.ndMascot);
        ndMascot.setText(stringInfo[9]);

        TextView ndRec = (TextView) findViewById(R.id.ndRec);
        ndRec.setText(stringInfo[10]);

        ImageView ndLogo = (ImageView) findViewById(R.id.ndLogo);
        String mDrawableName2 = stringInfo[11];
        int resID2 = getResources().getIdentifier(mDrawableName2 , "drawable",  getPackageName());
        ndLogo.setImageResource(resID2);

        Button cameraButton = (Button) findViewById(R.id.cameraButton);
        cameraButton.setText(stringInfo[12]);
        cameraButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(cameraIntent);
            }
        });
    }

}

