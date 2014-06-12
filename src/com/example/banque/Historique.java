package com.example.banque;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.os.Build;

public class Historique extends Activity {
	
	ListView lv;
	Button retour, vider;
	CompteBDD compteBDD;
	ArrayList<String> values = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    int id;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_historique);

		lv = (ListView)findViewById(R.id.listView1);
		retour = (Button)findViewById(R.id.bRetour);
		vider = (Button)findViewById(R.id.bVider);
		
		compteBDD = new CompteBDD(this);      
        compteBDD.open();
		
		Intent iHisto = getIntent();
		id = iHisto.getIntExtra("id", 0);
		
		values = compteBDD.getOperations(id);       
        if(!values.isEmpty()){
        	adapter = new ArrayAdapter<String>(Historique.this,android.R.layout.simple_list_item_1,values); 		
    		lv.setAdapter(adapter);
        }
        
        retour.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
        
        vider.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				compteBDD.removeOperation(id);
				values.clear();
				adapter = new ArrayAdapter<String>(Historique.this,android.R.layout.simple_list_item_1,values); 		
	    		lv.setAdapter(adapter);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.historique, menu);
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


}
