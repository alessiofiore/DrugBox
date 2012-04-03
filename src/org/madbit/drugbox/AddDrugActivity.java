package org.madbit.drugbox;

import java.util.Date;

import org.madbit.drugbox.entity.Drug;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

public class AddDrugActivity extends Activity  {
	
	private EditText name;
	private Spinner type;
	private EditText brand;
	private DatePicker purchaseDate;
	private DatePicker expireDate;
	private EditText pathology;
	private EditText minAge;
	private CheckBox medicine;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_drug);
        name = (EditText) findViewById(R.id.name);
        type = (Spinner) findViewById(R.id.type);
        brand = (EditText) findViewById(R.id.brand);
        purchaseDate = (DatePicker) findViewById(R.id.purchase);
        expireDate = (DatePicker) findViewById(R.id.expire);
        pathology = (EditText) findViewById(R.id.pathology);
        minAge = (EditText) findViewById(R.id.minAge);
        medicine = (CheckBox) findViewById(R.id.medicine);        
    }
    
    public void onSaveClick(View view) {
    	
    	Drug drug = new Drug();
    	drug.setName(name.getText().toString());
    	drug.setType(type.toString());
    	drug.setBrand(brand.getText().toString());
    	
    	int day = purchaseDate.getDayOfMonth();
    	int month = purchaseDate.getMonth();
    	int year = purchaseDate.getYear();
    	Date date = new Date(year, month, day);
    	drug.setPurchaseDate(date);
    	
    	day = expireDate.getDayOfMonth();
    	month = expireDate.getMonth();
    	year = expireDate.getYear();
    	date = new Date(year, month, day);
    	drug.setExpireDate(date);
    	
    	
    }

}
