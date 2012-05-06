package org.madbit.drugbox.activity;

import org.madbit.drugbox.R;
import org.madbit.drugbox.service.ExpiredDrugService;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;

public class HomeActivity extends Activity {
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Intent startServiceIntent = new Intent(this, ExpiredDrugService.class);
        this.startService(startServiceIntent);
    }
    
    @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
    	// return to the Android home screen (launcher)
	    if ((keyCode == KeyEvent.KEYCODE_BACK)) {
	    	Intent i = new Intent(Intent.ACTION_MAIN);
	    	i.addCategory(Intent.CATEGORY_HOME);
	    	startActivity(i);
	    }
	    return super.onKeyDown(keyCode, event);
	}
    
    public void onClickAdd(View view) {
    	Intent intent = new Intent(this, AddDrugActivity.class);
    	this.startActivity(intent);
    }
    
    public void onListAdd(View view) {
    	Intent intent = new Intent(this, DrugsListActivity.class);
    	this.startActivity(intent);
    }
}