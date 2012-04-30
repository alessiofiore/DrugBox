package org.madbit.drugbox;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
import android.widget.TextView;
import android.widget.Toast;

public class EditDrugActivity extends Activity {	

	final int MIN_AGE = 16;
	
	private int drugID;
	
	private EditText name;
	private Spinner type;
	private EditText brand;
	private TextView purchaseDate;
	private TextView expireDate;
	private EditText pathology;
	private EditText minAge;
	private Spinner category;
	
	SimpleDateFormat fmtDate = new SimpleDateFormat("yyyy/MM/dd");
	Calendar date = Calendar.getInstance();
	DatePickerDialog.OnDateSetListener d=new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			date.set(Calendar.YEAR, year);
			date.set(Calendar.MONTH, monthOfYear);
			date.set(Calendar.DAY_OF_MONTH, dayOfMonth);
			updateLabel();
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
        purchaseDate = (TextView) findViewById(R.id.purchase);
        expireDate = (TextView) findViewById(R.id.expire);
        pathology = (EditText) findViewById(R.id.pathology);
        minAge = (EditText) findViewById(R.id.minAge);
        
        category = (Spinner) findViewById(R.id.category);        
        ArrayAdapter<CharSequence> adapterCategory = ArrayAdapter.createFromResource(this, R.array.drug_category, android.R.layout.simple_spinner_item);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapterCategory);        
        
        drugID = (Integer) this.getIntent().getSerializableExtra("did");
        String nameExtra = (String) this.getIntent().getSerializableExtra("name");
		int typeExtra = (Integer) this.getIntent().getSerializableExtra("type");
		String brandExtra = (String) this.getIntent().getSerializableExtra("brand");
		String purchaseExtra = (String) this.getIntent().getSerializableExtra("purchase");
		String expireExtra = (String) this.getIntent().getSerializableExtra("expire");
		String pathologyExtra = (String) this.getIntent().getSerializableExtra("pathology");
		int minAgeExtra = (Integer) this.getIntent().getSerializableExtra("minAge");
		int categoryExtra = (Integer) this.getIntent().getSerializableExtra("category");
		
		name.setText(nameExtra);
		type.setSelection(typeExtra);
		brand.setText(brandExtra);
		purchaseDate.setText(purchaseExtra);
		purchaseDate.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				new DatePickerDialog(
						EditDrugActivity.this,
						d,
						date.get(Calendar.YEAR),
						date.get(Calendar.MONTH),
						date.get(Calendar.DAY_OF_MONTH)).show();
			}
		});

		expireDate.setText(expireExtra);
		pathology.setText(pathologyExtra);
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
	    	
//	    	int day = purchaseDate.getDayOfMonth();
//	    	int month = purchaseDate.getMonth();
//	    	int year = purchaseDate.getYear();
//	    	String purchase = new String(year + "/" + month + "/" + day);
//	    	drug.setPurchaseDate(purchase);
//	    	
//	    	day = expireDate.getDayOfMonth();
//	    	month = expireDate.getMonth();
//	    	year = expireDate.getYear();
//	    	String expire = new String(year + "/" + month + "/" + day);
//	    	drug.setExpireDate(expire);
	    	
	    	drug.setPathology(pathology.getText().toString());
	    	if(minAge.getText().toString().length() != 0)
	    		drug.setMinAge(Integer.parseInt(minAge.getText().toString()));
	    	else
	    		drug.setMinAge(MIN_AGE);
	    	drug.setCategory(category.getSelectedItemPosition());
	    	
	    	DrugDAO drugDao = new DrugDAO(this);
	    	drugDao.open();
	    	drugDao.updateDrug(drug);
	    	drugDao.close();
	    	
	    	// set message showed in HomeActivity
	    	Bundle bundle = new Bundle();    	
	    	bundle.putString("msg_updated",getString(R.string.msg_updated));
	    	startActivity(new Intent(this, ListDrugsActivity.class).putExtras(bundle));
    	}
    	else {
    		Toast.makeText(this, "You need to insert the name",Toast.LENGTH_LONG).show();
    	}
    }

	private void updateLabel() {
		purchaseDate.setText(fmtDate.format(date.getTime()));
	}

}
