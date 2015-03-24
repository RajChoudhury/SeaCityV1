package com.example.rifaz.seacityv1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


public class StartMenu extends Activity {
    private ImageView location;
    private ImageView translate;
    private ImageView screenReader;
    private ImageView progress;
    private ImageView information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homescreen);
        location = (ImageView) findViewById(R.id.ic_location);
        translate = (ImageView) findViewById(R.id.ic_translate);
        screenReader = (ImageView) findViewById(R.id.ic_screen);
        progress = (ImageView) findViewById(R.id.ic_progress);
        information = (ImageView) findViewById(R.id.ic_information);

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LocationNFC.class);
                startActivity(intent);
            }
        });

        translate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),TranslateNFC.class);
                startActivity(intent);
            }
        });

        screenReader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ScreenReaderNFC.class);
                startActivity(intent);
            }
        });

        progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ProgressNFC.class);
                startActivity(intent);
            }
        });

        information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),InformationNFC.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
