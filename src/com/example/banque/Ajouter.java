package com.example.banque;

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
import android.widget.Button;
import android.widget.EditText;
import android.os.Build;

public class Ajouter extends Activity {
	
	EditText etnom, etsolde;
	String nom;
	double solde;
	Button ajouter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ajouter);

		etnom = (EditText)findViewById(R.id.etAjoutNom);
		etsolde = (EditText)findViewById(R.id.etAjoutSolde);
		ajouter = (Button)findViewById(R.id.bAjouter);
		
		ajouter.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				nom =  etnom.getText().toString();
				solde = Double.parseDouble(etsolde.getText().toString());
				
				intent.putExtra("nom", nom);
				intent.putExtra("solde", solde);
				
				setResult(1,intent);
				finish();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ajouter, menu);
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
