package com.example.banque;

import java.util.ArrayList;

import android.content.*;
import android.database.*;
import android.database.sqlite.*;

public class BanqueBDD {
	
	private static final int VERSION_BDD = 1;
	private static final String NOM_BDD = "Banque.db";
	
	private static final String TABLE_BANQUE = "table_banque";
	private static final String COL_ID = "ID";
	private static final int NUM_COL_ID = 0;
	private static final String COL_NOM = "compte";
	private static final int NUM_COL_NOM = 1;
	
	private SQLiteDatabase bdd;
	
	private BaseSQLite BaseSQLite;
	
	public BanqueBDD(Context context){
		// création de la BDD et de sa table
		BaseSQLite = new BaseSQLite(context, NOM_BDD, null, VERSION_BDD);
	}
	
	public void open(){
		// ouverture de la BDD en écriture
		bdd = BaseSQLite.getWritableDatabase();
	}
	
	public void close(){
		// fermeture de l'accès à la BDD
		bdd.close();
	}
	
	public SQLiteDatabase getBDD(){
		return bdd;
	}
	
	public ArrayList<String> getComptes(){
		Cursor c = bdd.query(TABLE_BANQUE,new String[] {COL_NOM}, null, null, null, null, null);
		c.moveToFirst();
		ArrayList<String> values = new ArrayList<String>();
		for(int i = 0; i < c.getCount(); i++){
			values.add(c.getString(c.getColumnIndex(COL_NOM)));
			c.moveToNext();
		}
		c.close();
		return values;
	}
	
	public long insertCompte(String compte){
		// Création d'un compte
		ContentValues values = new ContentValues();
		// ajout d'une valeur associé à une clé (nom de la colonne associé à la valeur que l'on saisie)
		values.put(COL_NOM, compte);
		// insertion de l'objet dans la BDD via le contentValues
		return bdd.insert(TABLE_BANQUE, null, values);
	}
	
	public int updateCompte(int id, String compte){
		// MAJ d'un compte
		ContentValues values = new ContentValues();
		values.put(COL_NOM, compte);
		return bdd.update(TABLE_BANQUE, values, COL_ID + " = "+id, null);
	}
	
	public int removeBanqueWithID(int id){
		// suppression d'un compte de la BDD grâce à l'ID
		return bdd.delete(TABLE_BANQUE, COL_ID + " = "+id, null);
	}
	
	public int removeAll(){
		return bdd.delete(TABLE_BANQUE, null, null);
	}
}
