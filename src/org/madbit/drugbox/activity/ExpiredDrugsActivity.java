package org.madbit.drugbox.activity;

import java.util.List;

import org.madbit.drugbox.R;
import org.madbit.drugbox.adapter.ExpiredDrugsAdapter;
import org.madbit.drugbox.dmf.DrugDAO;
import org.madbit.drugbox.entity.Drug;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class ExpiredDrugsActivity extends ListActivity  {
	
	boolean emptyList = false;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if(this.getIntent().getExtras() != null) {
        	if(this.getIntent().getExtras().getString("msg_deleted") != null)
        		Toast.makeText(this, this.getIntent().getExtras().getString("msg_deleted"),Toast.LENGTH_LONG).show();
        	else if(this.getIntent().getExtras().getString("msg_added") != null)
        		Toast.makeText(this, this.getIntent().getExtras().getString("msg_added"),Toast.LENGTH_LONG).show();
        	else if(this.getIntent().getExtras().getString("msg_updated") != null)
        		Toast.makeText(this, this.getIntent().getExtras().getString("msg_updated"),Toast.LENGTH_LONG).show();
		}
		
		DrugDAO drugDao = new DrugDAO(this);
		drugDao.open();
		List<Drug> drugs = drugDao.getExpiredDrugs();
		drugDao.close();
		
		if(drugs.size() == 0) {
			Drug d = new Drug();
			d.setName(getString(R.string.no_item));
			drugs.add(d);
			emptyList = true;
		}
	
		ExpiredDrugsAdapter drugAdapter = new ExpiredDrugsAdapter(ExpiredDrugsActivity.this, R.layout.expired_drugs, drugs);
		setListAdapter(drugAdapter);
		drugAdapter.notifyDataSetChanged();        
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		if(!emptyList) {
			Drug drug = (Drug) getListAdapter().getItem(position);		
			Intent drugDetailsActivity = new Intent(this, DrugDetailsActivity.class);
			drugDetailsActivity.putExtra("did", drug.getDid());
			drugDetailsActivity.putExtra("name", drug.getName());
			drugDetailsActivity.putExtra("type", drug.getType());
			drugDetailsActivity.putExtra("brand", drug.getBrand());
			drugDetailsActivity.putExtra("purchase", drug.getPurchaseDate());
			drugDetailsActivity.putExtra("expire", drug.getExpiryDate());
			drugDetailsActivity.putExtra("pathology", drug.getPathology());
			drugDetailsActivity.putExtra("minAge", drug.getMinAge());
			drugDetailsActivity.putExtra("category", drug.getCategory());
			
			this.startActivity(drugDetailsActivity);
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// return to the App's Home Activity
	    if ((keyCode == KeyEvent.KEYCODE_BACK)) {
	    	Intent intent = new Intent(this, HomeActivity.class);
	    	this.startActivity(intent);
	    }
	    return super.onKeyDown(keyCode, event);
	}
}
