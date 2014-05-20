package com.example.banque;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class Editer extends Activity{
	
	Button bEdit;
	RadioButton rbCredit;
	RadioButton rbDebit;
	EditText etSomme;
	double solde;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_editer);
        
        bEdit = (Button)findViewById(R.id.bEdit);
        rbCredit = (RadioButton)findViewById(R.id.radCredit);
        rbDebit = (RadioButton)findViewById(R.id.radDebit);
        final EditText etSomme = (EditText)findViewById(R.id.etSomme);
        
        Intent intent = getIntent();
        solde = intent.getDoubleExtra("solde", 0);
        
        bEdit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(rbCredit.isChecked()){
					solde = solde + Double.parseDouble(etSomme.getText().toString());
				}
				else{
					solde = solde - Double.parseDouble(etSomme.getText().toString());
				}
				
				Intent i =  new Intent();
				i.putExtra("solde", solde);
				
				setResult(2, i);
				
				finish();
				
			}
		});
        
    }

}
