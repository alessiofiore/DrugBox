package org.madbit.drugbox.activity;

import org.madbit.drugbox.R;
import org.madbit.drugbox.dmf.DrugDAO;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class DrugDetailsActivity extends Activity {
	
	private int drugId;
	
	String nameExtra;
	int typeExtra;
	String brandExtra;
	String purchaseExtra;
	String expireExtra;
	String pathologyExtra;
	String administrationExtra;
	int minAgeExtra;
	int categoryExtra;

	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drug_details);
		
		drugId = (Integer) this.getIntent().getSerializableExtra("did");
		
		TextView name = (TextView) findViewById(R.id.name);
		TextView type = (TextView) findViewById(R.id.type);
		TextView brand = (TextView) findViewById(R.id.brand);
		TextView purchase = (TextView) findViewById(R.id.purchase);
		TextView expire = (TextView) findViewById(R.id.expire);
		TextView pathology = (TextView) findViewById(R.id.pathology);
		TextView administration = (TextView) findViewById(R.id.administration);
		TextView minAge = (TextView) findViewById(R.id.minAge);
		TextView category = (TextView) findViewById(R.id.category);
		
		nameExtra = (String) this.getIntent().getSerializableExtra("name");
		typeExtra = (Integer) this.getIntent().getSerializableExtra("type");
		brandExtra = (String) this.getIntent().getSerializableExtra("brand");
		purchaseExtra = (String) this.getIntent().getSerializableExtra("purchase");
		expireExtra = (String) this.getIntent().getSerializableExtra("expire");
		pathologyExtra = (String) this.getIntent().getSerializableExtra("pathology");
		administrationExtra = (String) this.getIntent().getSerializableExtra("administration");
		minAgeExtra = (Integer) this.getIntent().getSerializableExtra("minAge");
		categoryExtra = (Integer) this.getIntent().getSerializableExtra("category");
		
		name.setText(nameExtra);
		
		// fetch the relative String for the type
        ArrayAdapter<CharSequence> adapterType = ArrayAdapter.createFromResource(this, R.array.drug_types, android.R.layout.simple_spinner_item);
		String typeString = adapterType.getItem(typeExtra).toString();			
        type.setText(typeString);
        
		brand.setText(brandExtra);
		purchase.setText(purchaseExtra);
		expire.setText(expireExtra);
		pathology.setText(pathologyExtra);
		administration.setText(administrationExtra);
		minAge.setText(new Integer(minAgeExtra).toString());
		
		// fetch the relative String for the category
        ArrayAdapter<CharSequence> adapterCategory = ArrayAdapter.createFromResource(this, R.array.drug_category, android.R.layout.simple_spinner_item);
		String categoryString = adapterCategory.getItem(categoryExtra).toString();			
		category.setText(categoryString);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.drugdetails_option_menu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Bundle bundle = new Bundle();
		
		// Handle item selection
	    switch (item.getItemId()) {
	        case R.id.edit_drug:	        	    	
		    	bundle.putInt("did", drugId);
		    	bundle.putString("name", nameExtra);
		    	bundle.putInt("type", typeExtra);
		    	bundle.putString("brand", brandExtra);
		    	bundle.putString("purchase", purchaseExtra);
		    	bundle.putString("expire", expireExtra);
		    	bundle.putString("pathology", pathologyExtra);
		    	bundle.putString("administration", administrationExtra);
		    	bundle.putInt("minAge", minAgeExtra);
		    	bundle.putInt("category", categoryExtra);
		    	startActivity(new Intent(this, EditDrugActivity.class).putExtras(bundle));
	            return true;
	        case R.id.delete_drug:
	        	DrugDAO drugDao = new DrugDAO(this);
		    	drugDao.open();
		    	drugDao.deleteDrug(drugId);
		    	drugDao.close();
		    	
		    	// set message showed in ListDrugsActivity
		    	bundle = new Bundle();    	
		    	bundle.putString("msg_deleted",getString(R.string.msg_deleted));
		    	startActivity(new Intent(this, DrugsListActivity.class).putExtras(bundle));
		    			    	
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
