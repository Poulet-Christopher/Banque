package com.example.banque;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {
	
	ListView lv;
	Button ajouter, editer, historique, supprimer, viderBase;
	Compte compte =  new Compte();
	String nom;
	double solde;
	CompteBDD compteBDD;
	ArrayList<String> values = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    int selectItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lv = (ListView)findViewById(R.id.listView1);
		ajouter = (Button)findViewById(R.id.bMainAjouter);
		editer = (Button)findViewById(R.id.bMainEditer);
		historique = (Button)findViewById(R.id.bMainHisto);
		supprimer = (Button)findViewById(R.id.bMainSuppr);
		viderBase = (Button)findViewById(R.id.bMainVider);
		
		compteBDD = new CompteBDD(this);      
        compteBDD.open();
        
        values = compteBDD.getComptes();       
        if(!values.isEmpty()){
        	adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,values); 		
    		lv.setAdapter(adapter);
        }
		
		ajouter.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent iAjouter = new Intent(MainActivity.this, Ajouter.class);
				startActivityForResult(iAjouter, 1);
			}
		});
		
		viderBase.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				compteBDD.removeAll();
				values.clear();
				adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,values); 		
	    		lv.setAdapter(adapter);
			}
		});
		
		lv.setOnItemClickListener(new OnItemClickListener(){
	       	 @Override 
	       	 public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3)
	       	 { 
	       		 selectItem = position;
	       		 editer.setEnabled(true);
	       		 historique.setEnabled(true);
	       		 supprimer.setEnabled(true);
	       	 }
		});
		
		supprimer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				compteBDD.removeBanqueWithID(getItemIdForBDD(values.get(selectItem)));
				values = compteBDD.getComptes();
				adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,values); 		
	    		lv.setAdapter(adapter);
			}
		});
		
		editer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent iEditer = new Intent(MainActivity.this, Editer.class);
				
				String Item = values.get(selectItem);
				Item = Item.substring(0, Item.length()-1);
				Item = Item.substring(Item.indexOf(" : ")+3);
				solde = Double.parseDouble(Item);
				
				iEditer.putExtra("solde",solde);
				startActivityForResult(iEditer,2);
				editer.setEnabled(false);
				supprimer.setEnabled(false);
				historique.setEnabled(false);
				
				
			}
		});

	}
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	switch(requestCode){
    		case 1:{
    			nom = data.getStringExtra("nom");
    			solde = data.getDoubleExtra("solde", 0);
    			compte = new Compte(nom,solde);
    			compteBDD.insertCompte(compte);
    			compte.setId(compteBDD.getLastID());
    			
    			values = compteBDD.getComptes();       
    	        if(!values.isEmpty()){
    	        	adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,values); 		
    	    		lv.setAdapter(adapter);
    	        }
    			break;
    		}
    		case 2:{
    			solde = data.getDoubleExtra("solde", 0);
    			compteBDD.updateCompte(getItemIdForBDD(values.get(selectItem)), solde);
    			values = compteBDD.getComptes();       
    		    if(!values.isEmpty()){
    		    	adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,values); 		
    		    	lv.setAdapter(adapter);
    		    }
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

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public int getItemIdForBDD(String Item){
		Item = values.get(selectItem);
		int IdBDD = Integer.parseInt(Item.substring(0, Item.indexOf("||")));
		return IdBDD;
	}

}
