package com.example.banque;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
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
        
        final ListView list = (ListView)findViewById(R.id.listView1);
        
        bCharger.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(MainActivity.this, Charger.class);
            	startActivity(intent);
            }
        });
        
        bSauver.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(MainActivity.this, Sauver.class);
            	startActivity(intent);
            }
        });
        
        bAjouter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(MainActivity.this, Ajouter.class);
            	startActivity(intent);
            	
                String nom = intent.getStringExtra("nom");
                Double solde = intent.getDoubleExtra("solde",0);
                
                Compte compte = new Compte(nom,solde);               
            	
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.activity_main, lCompte);
        
        bEditer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(MainActivity.this, Editer.class);
            	startActivity(intent);
            }
        });
        
        bSupprimer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	Intent intent = new Intent(MainActivity.this, Supprimer.class);
            	startActivity(intent);
            }
        });
        

       String[] lCompte = {nom+" "+solde.toString()};

        
        
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }   
    
}
