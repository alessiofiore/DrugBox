package org.madbit.drugbox.dmf;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {
	
	public static final String TABLE_DRUG = "drug";	
	public static final String DRUG_ID = "_did";
	public static final String DRUG_NAME = "name";
	public static final String DRUG_TYPE = "type";
	public static final String DRUG_BRAND = "brand";
	public static final String DRUG_PURCHASE_DATE = "purchaseDate";
	public static final String DRUG_EXPIRING_DATE = "expireDate";
	public static final String DRUG_PATHOLOGY = "pathology";
	public static final String DRUG_MIN_AGE = "minAge";
	public static final String DRUG_CATEGORY = "category";

	private static final String DATABASE_NAME = "drugbox.db";
	private static final int DATABASE_VERSION = 1;
	
	// Database creation sql statement
		private static final String DRUG_TABLE_CREATE = "create table "
				+ TABLE_DRUG + "( " +
				DRUG_ID + " integer primary key autoincrement, " +
				DRUG_NAME + " text not null, " +
				DRUG_TYPE +	" text not null, " +
				DRUG_BRAND + " text, " +
				DRUG_PURCHASE_DATE + " text, " +
				DRUG_EXPIRING_DATE + " text, " +
				DRUG_PATHOLOGY + " text, " +
				DRUG_MIN_AGE + " integer, " +
				DRUG_CATEGORY + " text);";

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
		
	public DatabaseHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DRUG_TABLE_CREATE);
		Log.w(DatabaseHelper.class.getName(),"Database created ");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(DatabaseHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + DRUG_TABLE_CREATE);
		onCreate(db);

	}

}
