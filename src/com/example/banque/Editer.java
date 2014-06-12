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
import android.widget.RadioButton;
import android.os.Build;

public class Editer extends Activity {
	
	RadioButton crediter, debiter;
	EditText etMontant;
	Button editer;
	double solde, montant;
	String operation;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editer);
		
		crediter = (RadioButton)findViewById(R.id.rCredit);
		debiter = (RadioButton)findViewById(R.id.rDebit);
		etMontant = (EditText)findViewById(R.id.etMontant);
		editer = (Button)findViewById(R.id.bEditer);
		
		Intent iEditer = getIntent();
		solde = iEditer.getDoubleExtra("solde", 0);
		
		editer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				montant = Double.parseDouble(etMontant.getText().toString());
				if(crediter.isChecked()){
					solde = solde + montant;
					operation = "+"+montant;
				}
				else
				{
					solde = solde - montant;
					operation = "-"+montant;
				}
				
				Intent intent = new Intent();
				intent.putExtra("solde", solde);
				intent.putExtra("op", operation);
				setResult(2, intent);
				finish();
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.editer, menu);
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
