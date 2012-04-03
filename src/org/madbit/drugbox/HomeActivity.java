package org.madbit.drugbox;

import org.madbit.drugbox.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HomeActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
    }
    
    public void onClickAdd(View view) {
    	Intent intent = new Intent(this, AddDrugActivity.class);
    	this.startActivity(intent);
    }
}