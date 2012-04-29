package org.madbit.drugbox;

import org.madbit.drugbox.dmf.DrugDAO;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class DrugDetailsActivity extends Activity {
	
	private int drugId;

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
		TextView minAge = (TextView) findViewById(R.id.minAge);
		TextView category = (TextView) findViewById(R.id.category);
		
		String nameExtra = (String) this.getIntent().getSerializableExtra("name");
		String typeExtra = (String) this.getIntent().getSerializableExtra("type");
		String brandExtra = (String) this.getIntent().getSerializableExtra("brand");
		String purchaseExtra = (String) this.getIntent().getSerializableExtra("purchase");
		String expireExtra = (String) this.getIntent().getSerializableExtra("expire");
		String pathologyExtra = (String) this.getIntent().getSerializableExtra("pathology");
		Integer minAgeExtra = (Integer) this.getIntent().getSerializableExtra("minAge");
		String categoryExtra = (String) this.getIntent().getSerializableExtra("category");
		
		name.setText(nameExtra);
		type.setText(typeExtra);
		brand.setText(brandExtra);
		purchase.setText(purchaseExtra);
		expire.setText(expireExtra);
		pathology.setText(pathologyExtra);
		//minAge.setText(minAgeExtra);
		category.setText(categoryExtra);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.drugdetails_option_menu, menu);
	    return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.edit_drug:
	            
	            return true;
	        case R.id.delete_drug:
	        	DrugDAO drugDao = new DrugDAO(this);
		    	drugDao.open();
		    	drugDao.deleteDrug(drugId);
		    	drugDao.close();
		    	
		    	// set message showed in ListDrugsActivity
		    	Bundle bundle = new Bundle();    	
		    	bundle.putString("msg_deleted",getString(R.string.msg_deleted));
		    	startActivity(new Intent(this, ListDrugsActivity.class).putExtras(bundle));
		    			    	
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
}
