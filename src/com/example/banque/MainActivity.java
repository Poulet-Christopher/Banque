package com.example.banque;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends Activity {
    Compte compte = new Compte();
    Button bCharger,bSauver,bAjouter,bEditer,bSupprimer;
    ListView list;
    String nom;
    double solde;
    ArrayList<String> values = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    int ItemList;

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
            	startActivityForResult(intent, 1);
            }
        });
        
        list.setOnItemClickListener(new OnItemClickListener(){
        	 @Override 
        	 public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
        	 { 
        	        bEditer.setEnabled(true);
        	        bSupprimer.setEnabled(true);
                	ItemList = position;
        	 }
        });
        
        bSupprimer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				adapter.remove(adapter.getItem(ItemList));
				adapter.notifyDataSetChanged();
				bEditer.setEnabled(false);
				bSupprimer.setEnabled(false);
			}
		});
        
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	final ListView list = (ListView)findViewById(R.id.listView1);
    	if(requestCode==1){
    		nom = data.getStringExtra("nom");
    		solde = data.getDoubleExtra("solde", 0);
    		compte = new Compte(nom,solde); 		
    		values.add(compte.getName()+": "+compte.getSoldeString()+"€");   		
    		adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,values); 		
    		list.setAdapter(adapter);
    	}
    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }   
    
}
