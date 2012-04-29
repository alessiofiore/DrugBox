package org.madbit.drugbox;

import org.madbit.drugbox.dmf.DrugDAO;
import org.madbit.drugbox.entity.Drug;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddDrugActivity extends Activity  {
	
	private EditText name;
	private Spinner type;
	private EditText brand;
	private DatePicker purchaseDate;
	private DatePicker expireDate;
	private EditText pathology;
	private EditText minAge;
	private Spinner category;
	
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
	    	drug.setType(type.getSelectedItem().toString());
	    	drug.setBrand(brand.getText().toString());
	    	
	    	int day = purchaseDate.getDayOfMonth();
	    	int month = purchaseDate.getMonth();
	    	int year = purchaseDate.getYear();
	    	String purchase = new String(year + "/" + month + "/" + day);
	    	drug.setPurchaseDate(purchase);
	    	
	    	day = expireDate.getDayOfMonth();
	    	month = expireDate.getMonth();
	    	year = expireDate.getYear();
	    	String expire = new String(year + "/" + month + "/" + day);
	    	drug.setExpireDate(expire);
	    	
	    	drug.setPathology(pathology.getText().toString());
	    	if(minAge.getText().toString().length() != 0)
	    		drug.setMinAge(Integer.parseInt(minAge.getText().toString()));
	    	drug.setCategory(category.getSelectedItem().toString());
	    	
	    	DrugDAO drugDao = new DrugDAO(this);
	    	drugDao.open();
	    	drugDao.addDrug(drug);
	    	drugDao.close();
	    	
	    	// set message showed in HomeActivity
	    	Bundle bundle = new Bundle();    	
	    	bundle.putString("msg_added",getString(R.string.msg_added));
	    	startActivity(new Intent(this, HomeActivity.class).putExtras(bundle));
    	}
    	else {
    		Toast.makeText(this, "You need to insert the name",Toast.LENGTH_LONG).show();
    	}
    }
}
