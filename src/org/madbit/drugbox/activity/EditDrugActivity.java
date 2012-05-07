package org.madbit.drugbox.activity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.madbit.drugbox.R;
import org.madbit.drugbox.common.CommonValues;
import org.madbit.drugbox.dmf.DrugDAO;
import org.madbit.drugbox.entity.Drug;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EditDrugActivity extends Activity {	

	int drugID;	
	EditText name;
	Spinner type;
	EditText brand;
	EditText purchaseDate;
	EditText expireDate;
	EditText pathology;
	EditText administration;
	EditText minAge;
	Spinner category;
	
	String purchaseExtra;
	String expireExtra;
	
	int purchaseYear;
	int purchaseMonth;
	int purchaseDay;
	int expireYear;
	int expireMonth;
	int expireDay;
	
	SimpleDateFormat fmtDate = new SimpleDateFormat(CommonValues.DATE_PATTERN);
	Calendar purchaseCalendar = Calendar.getInstance();
	DatePickerDialog.OnDateSetListener purchaseDatePicker = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			purchaseCalendar.set(Calendar.YEAR, year);
			purchaseCalendar.set(Calendar.MONTH, monthOfYear);
			purchaseCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			updatePurchaseLabel();
		}
	};
	Calendar expireCalendar = Calendar.getInstance();
	DatePickerDialog.OnDateSetListener expireDatePicker = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			expireCalendar.set(Calendar.YEAR, year);
			expireCalendar.set(Calendar.MONTH, monthOfYear);
			expireCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			updateExpireLabel();
		}
	};
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_drug);
        name = (EditText) findViewById(R.id.name);
        type = (Spinner) findViewById(R.id.type);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.drug_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(adapter);
        
        brand = (EditText) findViewById(R.id.brand);
        purchaseDate = (EditText) findViewById(R.id.purchase);
        expireDate = (EditText) findViewById(R.id.expire);
        pathology = (EditText) findViewById(R.id.pathology);
        administration = (EditText) findViewById(R.id.administration);
        minAge = (EditText) findViewById(R.id.minAge);
        
        category = (Spinner) findViewById(R.id.category);        
        ArrayAdapter<CharSequence> adapterCategory = ArrayAdapter.createFromResource(this, R.array.drug_category, android.R.layout.simple_spinner_item);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapterCategory);        
        
        drugID = (Integer) this.getIntent().getSerializableExtra("did");
        String nameExtra = (String) this.getIntent().getSerializableExtra("name");
		int typeExtra = (Integer) this.getIntent().getSerializableExtra("type");
		String brandExtra = (String) this.getIntent().getSerializableExtra("brand");
		purchaseExtra = (String) this.getIntent().getSerializableExtra("purchase");
		expireExtra = (String) this.getIntent().getSerializableExtra("expire");
		String pathologyExtra = (String) this.getIntent().getSerializableExtra("pathology");
		String administrationExtra = (String) this.getIntent().getSerializableExtra("administration");
		int minAgeExtra = (Integer) this.getIntent().getSerializableExtra("minAge");
		int categoryExtra = (Integer) this.getIntent().getSerializableExtra("category");
		
		name.setText(nameExtra);
		type.setSelection(typeExtra);
		brand.setText(brandExtra);
		
		try {
			purchaseYear = fmtDate.parse(purchaseExtra).getYear() + 1900;
			purchaseMonth = fmtDate.parse(purchaseExtra).getMonth();
			purchaseDay = fmtDate.parse(purchaseExtra).getDay() - 1;
			expireYear = fmtDate.parse(expireExtra).getYear() + 1900;
			expireMonth = fmtDate.parse(expireExtra).getMonth();
			expireDay = fmtDate.parse(expireExtra).getDay() - 1;
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		purchaseDate.setText(purchaseExtra);		
		purchaseDate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {				
					new DatePickerDialog(
							EditDrugActivity.this,
							purchaseDatePicker,
							purchaseYear,
							purchaseMonth,
							purchaseDay).show();
			}
		});

		expireDate.setText(expireExtra);
		expireDate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				new DatePickerDialog(
						EditDrugActivity.this,
						expireDatePicker,
						expireYear,
						expireMonth,
						expireDay).show();
			}
		});
		
		pathology.setText(pathologyExtra);
		administration.setText(administrationExtra);
		minAge.setText(new Integer(minAgeExtra).toString());
		category.setSelection(categoryExtra);
    }
    
public void onSaveClick(View view) {
    	
    	Drug drug = new Drug();
    	if(name.getText().toString().length() != 0) {
    		drug.setDid(drugID);
	    	drug.setName(name.getText().toString());
	    	drug.setType(type.getSelectedItemPosition());
	    	drug.setBrand(brand.getText().toString());
	    	drug.setPurchaseDate(purchaseDate.getText().toString());
	    	drug.setExpireDate(expireDate.getText().toString());
	    		
	    	drug.setPathology(pathology.getText().toString());
	    	drug.setAdministration(administration.getText().toString());
	    	
	    	if(minAge.getText().toString().length() != 0)
	    		drug.setMinAge(Integer.parseInt(minAge.getText().toString()));
	    	else
	    		drug.setMinAge(CommonValues.MIN_AGE);
	    	drug.setCategory(category.getSelectedItemPosition());
	    	
	    	DrugDAO drugDao = new DrugDAO(this);
	    	drugDao.open();
	    	drugDao.updateDrug(drug);
	    	drugDao.close();
	    	
	    	// set message showed in HomeActivity
	    	Bundle bundle = new Bundle();    	
	    	bundle.putString("msg_updated",getString(R.string.msg_updated));
	    	startActivity(new Intent(this, DrugsListActivity.class).putExtras(bundle));
    	}
    	else {
    		Toast.makeText(this, "You need to insert the name",Toast.LENGTH_LONG).show();
    	}
    }

	private void updatePurchaseLabel() {
		purchaseDate.setText(fmtDate.format(purchaseCalendar.getTime()));
		purchaseYear = purchaseCalendar.getTime().getYear() + 1900;
		purchaseMonth = purchaseCalendar.getTime().getMonth();
		purchaseDay = purchaseCalendar.getTime().getDay() - 1;
	}
	private void updateExpireLabel() {
		expireDate.setText(fmtDate.format(expireCalendar.getTime()));
		expireYear = expireCalendar.getTime().getYear() + 1900;
		expireMonth = expireCalendar.getTime().getMonth();
		expireDay = expireCalendar.getTime().getDay() - 1;
	}
}
