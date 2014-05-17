package com.example.banque;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Ajouter extends Activity{

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
				String nom = EtNom.getText().toString();
				double solde = Double.parseDouble(EtSolde.getText().toString());
				
				Intent intent = new Intent(Ajouter.this,MainActivity.class);
				intent.putExtra("nom",nom);
				intent.putExtra("solde", solde);
				startActivity(intent);
				
			}
    		
    	});
        
    }

}