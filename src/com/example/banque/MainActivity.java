package com.example.banque;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import 	android.content.Intent;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final Button bCharger = (Button) findViewById(R.id.bCharger);
        final Button bSauver = (Button) findViewById(R.id.bSauver);
        final Button bAjouter = (Button) findViewById(R.id.bAjouter);
        final Button bEditer = (Button) findViewById(R.id.bEditer);
        final Button bSupprimer = (Button) findViewById(R.id.bSupprimer);
        
        bCharger.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(MainActivity.this, Charger.class);
            	startActivity(intent);
            }
        });
        
        bSauver.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            }
        });
        
        bAjouter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            }
        });
        
        bEditer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            }
        });
        
        bSupprimer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
            }
        });
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }   
    
}
