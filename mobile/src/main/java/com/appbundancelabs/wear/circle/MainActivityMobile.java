package com.appbundancelabs.wear.circle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import android.content.Intent;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.Date;

import android.os.Process;

import android.widget.Button;

import android.view.View;
import android.view.View.OnClickListener;

import android.util.Log;

public class MainActivityMobile extends AppCompatActivity {

   private int iClkTime = 1; // Count of button click
   TextView tvLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_mobile);

        // Get handle of button
        Button dispatchBtn = (Button) findViewById(R.id.dispatchBtn);
        dispatchBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivityMobile.this, Page2Activity.class);
                startActivity(intent);
            }
        });

        //Button btn = (Button) findViewById(R.id.button3);

        // To exit / kill application
        Button btnClose = (Button) findViewById(R.id.button2);
        btnClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Process.killProcess(Process.myPid());
            }
        });

        final String prefixPrompt = "This is No. ";
        final String suffixPrompt = " time(s) that button is clicked";

        /*
        btn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("ProgTraceInfo", prefixPrompt + (iClkTime) + suffixPrompt);
                iClkTime++;
            }
        });
        */

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        Parse.initialize(this, "XzXTBKrA1rwteFCQShZ5kUGbPRhJTdwWDFEEeTlH", "3omDxQRtfqRMPW3VcEJv9u7tXovH2mtBKtT5mshH");
        ParseInstallation.getCurrentInstallation().saveInBackground();

        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("appName", "Circle");
        testObject.saveInBackground();


        // Save smartBin data to Parse
        /*
        ParseObject smartBin = new ParseObject("SmartBin");
        smartBin.put("date", "27-08-2015");
        smartBin.put("time", "15:45:25");
        smartBin.put("smartBin_Id", "smartBin-01");
        smartBin.put("location", "KLCC");
        smartBin.put("weight", "5.2");
        smartBin.saveInBackground();
        */

        // Retrive Data from Parse
        ParseQuery<ParseObject> query = ParseQuery.getQuery("SmartBin");
        query.getInBackground("5iMDDR2KYH", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject parseObject, ParseException e) {
                if (e == null){
                    // parseObject contains all the data
                    String objectId   = parseObject.getObjectId();
                    Date updatedAt    = parseObject.getUpdatedAt();
                    Date createdAt    = parseObject.getCreatedAt();
                    String smartBinId = parseObject.getString("smartBin_Id");
                    String location   = parseObject.getString("location");
                    double weight     = parseObject.getDouble("weight");
                    // Show weight value  at UI
                    TextView location_parse = (TextView) findViewById(R.id.tvLocation);
                    location_parse.setText(location);

                } else {
                    // something went wrong here
                }
            }
        });


        // To track statistics around application opens
        ParseAnalytics.trackAppOpenedInBackground(getIntent());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity_mobile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
