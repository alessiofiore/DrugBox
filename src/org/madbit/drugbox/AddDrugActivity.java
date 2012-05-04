package org.madbit.drugbox;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.madbit.drugbox.dmf.DrugDAO;
import org.madbit.drugbox.entity.Drug;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddDrugActivity extends Activity {
	
	private EditText name;
	private Spinner type;
	private EditText brand;
	private DatePicker purchaseDate;
	private DatePicker expireDate;
	private EditText pathology;
	private EditText minAge;
	private Spinner category;
	
	final int MIN_AGE = 16;
	final String DATE_PATTERN = "yyyy/MM/dd";
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_drug);
        name = (EditText) findViewById(R.id.name);
        type = (Spinner) findViewById(R.id.type);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.drug_types, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(adapter);
        
        brand = (EditText) findViewById(R.id.brand);
        purchaseDate = (DatePicker) findViewById(R.id.purchase);
        expireDate = (DatePicker) findViewById(R.id.expire);
        pathology = (EditText) findViewById(R.id.pathology);
        minAge = (EditText) findViewById(R.id.minAge);
        
        category = (Spinner) findViewById(R.id.category);        
        ArrayAdapter<CharSequence> adapterCategory = ArrayAdapter.createFromResource(this, R.array.drug_category, android.R.layout.simple_spinner_item);
        adapterCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(adapterCategory);
    }
    
    public void onSaveClick(View view) {
    	
    	Drug drug = new Drug();
    	if(name.getText().toString().length() != 0) {
	    	drug.setName(name.getText().toString());
	    	drug.setType(type.getSelectedItemPosition());
	    	drug.setBrand(brand.getText().toString());
	    	
	    	// formatting date	    	
	    	SimpleDateFormat fmtDate = new SimpleDateFormat(DATE_PATTERN);
	    	
	    	Date purchase = new Date(purchaseDate.getYear() - 1900, purchaseDate.getMonth(), purchaseDate.getDayOfMonth());	    	
	    	String formattedDate = fmtDate.format(purchase);
	    	drug.setPurchaseDate(formattedDate);
	    	
	    	Date expire = new Date(expireDate.getYear() - 1900, expireDate.getMonth(), expireDate.getDayOfMonth());
	    	formattedDate = fmtDate.format(expire);
	    	drug.setExpireDate(formattedDate);
	    	
	    	drug.setPathology(pathology.getText().toString());
	    	if(minAge.getText().toString().length() != 0)
	    		drug.setMinAge(Integer.parseInt(minAge.getText().toString()));
	    	else
	    		drug.setMinAge(MIN_AGE);
	    	drug.setCategory(category.getSelectedItemPosition());
	    	
	    	DrugDAO drugDao = new DrugDAO(this);
	    	drugDao.open();
	    	drugDao.addDrug(drug);
	    	drugDao.close();
	    	
	    	// set message showed in HomeActivity
	    	Bundle bundle = new Bundle();    	
	    	bundle.putString("msg_added",getString(R.string.msg_added));
	    	startActivity(new Intent(this, ListDrugsActivity.class).putExtras(bundle));
    	}
    	else {
    		Toast.makeText(this, "You need to insert the name",Toast.LENGTH_LONG).show();
    	}
    }
}
