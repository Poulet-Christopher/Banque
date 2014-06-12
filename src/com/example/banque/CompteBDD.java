package com.example.banque;

import java.util.ArrayList;

import android.content.*;
import android.database.*;
import android.database.sqlite.*;
import android.widget.Toast;

public class CompteBDD {

	private static final int VERSION_BDD = 1;
	private static final String NOM_BDD = "Compte.db";

	private static final String TABLE_COMPTE = "table_compte";
	private static final String COL_ID = "ID";
	private static final int NUM_COL_ID = 0;
	private static final String COL_NOM = "compte";
	private static final int NUM_COL_NOM = 1;
	private static final String COL_SOLDE = "solde";
	private static final int NUM_COL_SOLDE = 2;

	private SQLiteDatabase bdd;

	private BaseCompte BaseCompte;

	public CompteBDD(Context context){
		// création de la BDD et de sa table
		BaseCompte = new BaseCompte(context, NOM_BDD, null, VERSION_BDD);
	}

	public void open(){
		// ouverture de la BDD en écriture
		bdd = BaseCompte.getWritableDatabase();
	}

	public void close(){
		// fermeture de l'accès à la BDD
		bdd.close();
	}

	public SQLiteDatabase getBDD(){
		return bdd;
	}

	public ArrayList<String> getComptes(){
		Cursor c = bdd.query(TABLE_COMPTE,new String[] {COL_ID, COL_NOM, COL_SOLDE}, null, null, null, null, null);
		c.moveToFirst();
		ArrayList<String> values = new ArrayList<String>();
		for(int i = 0; i < c.getCount(); i++){
			String id, nom, solde;
			id = c.getString(NUM_COL_ID);
			nom = c.getString(NUM_COL_NOM);
			solde = c.getString(NUM_COL_SOLDE);
			Compte compte = new Compte(nom, Double.parseDouble(solde));
			compte.setId(Integer.parseInt(id));
			values.add(id+"||"+nom+" : "+solde+" €");
			c.moveToNext();
		}
		c.close();
		return values;
	}

	public long insertCompte(Compte compte){
		// Création d'un compte
		ContentValues values = new ContentValues();
		// ajout d'une valeur associé à une clé (nom de la colonne associé à la valeur que l'on saisie)
		values.put(COL_NOM, compte.getName());
		values.put(COL_SOLDE, compte.getSoldeString());
		// insertion de l'objet dans la BDD via le contentValues
		return bdd.insert(TABLE_COMPTE, null, values);
	}

	public int updateCompte(int id, double solde){
		// MAJ d'un compte
		ContentValues values = new ContentValues();
		values.put(COL_SOLDE, String.valueOf(solde));
		return bdd.update(TABLE_COMPTE, values, COL_ID + " = "+id, null);
	}

	public int removeBanqueWithID(int id){
		// suppression d'un compte de la BDD grâce à l'ID
		return bdd.delete(TABLE_COMPTE, COL_ID + " = "+id, null);
	}

	public int removeAll(){
		return bdd.delete(TABLE_COMPTE, null, null);
	}
	
	public int getLastID(){
		int id;
		Cursor c = bdd.query(TABLE_COMPTE,new String[] {COL_ID}, null, null, null, null, null);
		c.moveToLast();
		return id = c.getInt(NUM_COL_ID);
	}
}
