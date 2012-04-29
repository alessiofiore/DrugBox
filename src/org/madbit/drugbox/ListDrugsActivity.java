package org.madbit.drugbox;

import java.util.List;

import org.madbit.drugbox.adapter.DrugAdapter;
import org.madbit.drugbox.dmf.DrugDAO;
import org.madbit.drugbox.entity.Drug;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListDrugsActivity extends ListActivity  {
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if(this.getIntent().getExtras() != null)
        	Toast.makeText(this, this.getIntent().getExtras().getString("msg_deleted"),Toast.LENGTH_LONG).show();
		
		DrugDAO drugDao = new DrugDAO(this);
		drugDao.open();
		List<Drug> drugs = drugDao.getAllDrugs();
		drugDao.close();
		
		
//			 setContentView(R.layout.list_drugs);
//			 TextView message = (TextView) findViewById(R.id.message);
//			 message.setText(getString(R.string.no_item));
		
		
			DrugAdapter drugAdapter = new DrugAdapter(ListDrugsActivity.this, R.layout.list_drugs, drugs);
			setListAdapter(drugAdapter);
			drugAdapter.notifyDataSetChanged();
		
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		Drug drug = (Drug) getListAdapter().getItem(position);		
		Intent drugDetailsActivity = new Intent(this, DrugDetailsActivity.class);
		drugDetailsActivity.putExtra("did", drug.getDid());
		drugDetailsActivity.putExtra("name", drug.getName());
		drugDetailsActivity.putExtra("type", drug.getType());
		drugDetailsActivity.putExtra("brand", drug.getBrand());
		drugDetailsActivity.putExtra("purchase", drug.getPurchaseDate());
		drugDetailsActivity.putExtra("expire", drug.getExpireDate());
		drugDetailsActivity.putExtra("pathology", drug.getPathology());
		drugDetailsActivity.putExtra("minAge", drug.getMinAge());
		drugDetailsActivity.putExtra("category", drug.getCategory());
		
		this.startActivity(drugDetailsActivity);
	}	
}
