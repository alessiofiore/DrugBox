package org.madbit.drugbox.dmf;

import java.util.ArrayList;
import java.util.List;

import org.madbit.drugbox.entity.Drug;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DrugDAO {

	private SQLiteDatabase database;
	private DatabaseHelper dbHelper;
	private String[] drugColumns = {DatabaseHelper.DRUG_ID, DatabaseHelper.DRUG_NAME, DatabaseHelper.DRUG_TYPE, DatabaseHelper.DRUG_BRAND, DatabaseHelper.DRUG_PURCHASE_DATE, 
			DatabaseHelper.DRUG_EXPIRY_DATE, DatabaseHelper.DRUG_PATHOLOGY, DatabaseHelper.DRUG_ADMINISTRATION, DatabaseHelper.DRUG_MIN_AGE, DatabaseHelper.DRUG_CATEGORY};
	
	public DrugDAO(Context context) {
		dbHelper = new DatabaseHelper(context);
	}
	
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}
	
	public long addDrug(Drug drug) {
		ContentValues drugValues = new ContentValues();
		drugValues.put(DatabaseHelper.DRUG_NAME, drug.getName());
		drugValues.put(DatabaseHelper.DRUG_TYPE, drug.getType());
		drugValues.put(DatabaseHelper.DRUG_BRAND, drug.getBrand());
		drugValues.put(DatabaseHelper.DRUG_PURCHASE_DATE, drug.getPurchaseDate());
		drugValues.put(DatabaseHelper.DRUG_EXPIRY_DATE, drug.getExpiryDate());
		drugValues.put(DatabaseHelper.DRUG_PATHOLOGY, drug.getPathology());
		drugValues.put(DatabaseHelper.DRUG_ADMINISTRATION, drug.getAdministration());
		drugValues.put(DatabaseHelper.DRUG_MIN_AGE, drug.getMinAge());
		drugValues.put(DatabaseHelper.DRUG_CATEGORY, drug.getCategory());
		long drugId = database.insert(DatabaseHelper.TABLE_DRUG, null, drugValues);
		return drugId;
	}
	
	public List<Drug> getAllDrugs() {
		List<Drug> drugs = new ArrayList<Drug>();
		Cursor cursor = database.query(DatabaseHelper.TABLE_DRUG, drugColumns, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Drug drug = cursorToDrug(cursor);
			drugs.add(drug);
			cursor.moveToNext();
		}
		cursor.close();
		return drugs;
	}
	
	public void deleteDrug(int drugId) {
		database.delete(DatabaseHelper.TABLE_DRUG, DatabaseHelper.DRUG_ID + "=?", new String [] {new Integer(drugId).toString()});
	}
	
	public void updateDrug(Drug drug) {
		ContentValues drugValues = new ContentValues();
		drugValues.put(DatabaseHelper.DRUG_NAME, drug.getName());
		drugValues.put(DatabaseHelper.DRUG_TYPE, drug.getType());
		drugValues.put(DatabaseHelper.DRUG_BRAND, drug.getBrand());
		drugValues.put(DatabaseHelper.DRUG_PURCHASE_DATE, drug.getPurchaseDate());
		drugValues.put(DatabaseHelper.DRUG_EXPIRY_DATE, drug.getExpiryDate());
		drugValues.put(DatabaseHelper.DRUG_PATHOLOGY, drug.getPathology());
		drugValues.put(DatabaseHelper.DRUG_ADMINISTRATION, drug.getAdministration());
		drugValues.put(DatabaseHelper.DRUG_MIN_AGE, drug.getMinAge());
		drugValues.put(DatabaseHelper.DRUG_CATEGORY, drug.getCategory());
		database.update(DatabaseHelper.TABLE_DRUG, drugValues, DatabaseHelper.DRUG_ID + "=?", new String [] {new Integer(drug.getDid()).toString()});		
	}
	
	public int getExpiredDrugsCount() {
		String sql = "SELECT count(" + DatabaseHelper.DRUG_ID + ") FROM " + DatabaseHelper.TABLE_DRUG + " WHERE date(" + DatabaseHelper.DRUG_EXPIRY_DATE + ") < date('now')";
		Cursor cursor = database.rawQuery(sql, null);
		cursor.moveToFirst();
		return cursor.getInt(0);
	}
	
	public List<Drug> getExpiredDrugs() {
		List<Drug> drugs = new ArrayList<Drug>();
		String sql = "SELECT * FROM " + DatabaseHelper.TABLE_DRUG + " WHERE date(" + DatabaseHelper.DRUG_EXPIRY_DATE + ") < date('now')";
		Cursor cursor = database.rawQuery(sql, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Drug drug = cursorToDrug(cursor);
			drugs.add(drug);
			cursor.moveToNext();
		}
		cursor.close();
		return drugs;
	}
	
	private Drug cursorToDrug(Cursor cursor) {
		Drug drug = new Drug();
		drug.setDid(cursor.getInt(0));
		drug.setName(cursor.getString(1));
		drug.setType(cursor.getInt(2));
		drug.setBrand(cursor.getString(3));
		drug.setPurchaseDate(cursor.getString(4));
		drug.setExpiryDate(cursor.getString(5));
		drug.setPathology(cursor.getString(6));
		drug.setAdministration(cursor.getString(7));
		drug.setMinAge(cursor.getInt(8));
		drug.setCategory(cursor.getInt(9));
		return drug;
	}
}
