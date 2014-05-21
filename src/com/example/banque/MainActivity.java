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
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends Activity {
    Compte compte = new Compte();
    Button bAjouter,bEditer,bSupprimer;
    ListView list;
    String nom, list_solde, list_item, list_items;
    double solde, item_solde;
    ArrayList<String> values = new ArrayList<String>();  //a stocker en BDD a la place des compte peut etre?
    ArrayAdapter<String> adapter;
    int ItemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        final Button bAjouter = (Button) findViewById(R.id.bAjouter);
        final Button bEditer = (Button) findViewById(R.id.bEditer);
        final Button bSupprimer = (Button) findViewById(R.id.bSupprimer);
        final ListView list = (ListView)findViewById(R.id.listView1);
        
        
        // Charger dans la liste les données en base
        
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
				values.remove(ItemList);
				adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,values); 		
        		list.setAdapter(adapter);
				bEditer.setEnabled(false);
				bSupprimer.setEnabled(false);
				
				//mettre a jour la base pour suppression
			}
		});
        
        bEditer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, Editer.class);
				list_items =  adapter.getItem(ItemList);
				list_item = list_items.substring(0, list_items.length()-1);
				list_solde = list_item.substring(list_item.indexOf(": ")+1);
				item_solde = Double.parseDouble(list_solde);
				intent.putExtra("solde", item_solde);
				bEditer.setEnabled(false);
				bSupprimer.setEnabled(false);
				startActivityForResult(intent, 2);
			}
		});
        
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	final ListView list = (ListView)findViewById(R.id.listView1);
    	switch(requestCode){
    		case 1:{
    			nom = data.getStringExtra("nom");
        		solde = data.getDoubleExtra("solde", 0);
        		compte = new Compte(nom,solde); 		
        		values.add(compte.getName()+": "+compte.getSoldeString()+"€");   		
        		adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,values); 		
        		list.setAdapter(adapter);
        		
        		//mettre a jour la base pour ajout.
        		
    			break;
    		}
    		case 2:{
    			solde = data.getDoubleExtra("solde", 0);
        		String updateSolde = list_item.substring(0,list_item.length()-list_solde.length())+" "+solde+"€";
        		values.set(ItemList, updateSolde);
        		adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,values); 		
        		list.setAdapter(adapter);
        		
        		// mettre a jour la base pour changement du solde
        		
    			break;
    		}
    	}
    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }   
    
}
