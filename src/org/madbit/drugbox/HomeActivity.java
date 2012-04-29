package org.madbit.drugbox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        if(this.getIntent().getExtras() != null)
        	Toast.makeText(this, this.getIntent().getExtras().getString("msg_added"),Toast.LENGTH_LONG).show();
    }
    
    public void onClickAdd(View view) {
    	Intent intent = new Intent(this, AddDrugActivity.class);
    	this.startActivity(intent);
    }
    
    public void onListAdd(View view) {
    	Intent intent = new Intent(this, ListDrugsActivity.class);
    	this.startActivity(intent);
    }
}