package com.example.banque;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Ajouter extends Activity{
	private static final int REQUEST_CODE = 1;
	String nom="";
	double solde = 0;
	Button bAjout;
	EditText EtNom;
	EditText EtSolde;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ajouter);
        
        final Button bAjout = (Button) findViewById(R.id.bAjout);
    	final EditText EtNom = (EditText) findViewById(R.id.etNom);
    	final EditText EtSolde = (EditText) findViewById(R.id.etSolde);
    	
    	
    	bAjout.setOnClickListener(new View.OnClickListener(){

			@Override
			public void onClick(View v) {
				nom = EtNom.getText().toString();
				solde = Double.parseDouble(EtSolde.getText().toString());
				Intent intent =  new Intent();
				intent.putExtra("nom", nom);
				intent.putExtra("solde", solde);
				
				setResult(1,intent);
				finish();
				
			}
    	});
        
    }

}