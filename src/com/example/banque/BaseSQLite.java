package com.example.banque;

import android.content.*;
import android.database.sqlite.*;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

public class BaseSQLite extends SQLiteOpenHelper {

		private static final String TABLE_BANQUE = "table_banque";
		private static final String KEY_ID = "ID";
		private static final String KEY_NOM = "compte";
		
		private static final String CREATE_BDD = "CREATE TABLE " + TABLE_BANQUE
				+ " ("
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ KEY_NOM + " TEXT NOT NULL);";
		
		public BaseSQLite(Context context, String name, CursorFactory factory, int version){
			super(context, name, factory, version);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db){
			// création de la table à partir de la requête écrite dans la variable CREATE_BDD
			db.execSQL(CREATE_BDD);
		}	
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
			// faire ce que l'on veut
			// suppression de la table
			db.execSQL("DROP TABLE " + TABLE_BANQUE + ";");
			onCreate(db);
		}
		
}
