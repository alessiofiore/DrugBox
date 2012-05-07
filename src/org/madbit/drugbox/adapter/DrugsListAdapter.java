package org.madbit.drugbox.adapter;

import java.util.List;

import org.madbit.drugbox.R;
import org.madbit.drugbox.entity.Drug;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class DrugsListAdapter extends ArrayAdapter<Drug> {

	private final Context context;
	private final List<Drug> drugs;
	private int resource;
	
	public DrugsListAdapter(Context context, int resource, List<Drug> drugs) {
		super(context, resource, resource, drugs);
		this.context = context;
		this.resource = resource;
		this.drugs = drugs;
	}

	@Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
		LinearLayout drugsView;
		Drug drug = getItem(position);
		
		if(convertView==null)
        {
            drugsView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi;
            vi = (LayoutInflater)getContext().getSystemService(inflater);
            vi.inflate(resource, drugsView, true);
        }
        else
        {
            drugsView = (LinearLayout) convertView;
        }
		ImageView icon = (ImageView)drugsView.findViewById(R.id.status_icon);
		
		icon.setImageResource(R.drawable.bullet_red);
        TextView drugName =(TextView)drugsView.findViewById(R.id.drugName);
        TextView drugType =(TextView)drugsView.findViewById(R.id.drugType);
 
        drugName.setText(drug.getName());        
        
        // fetch the relative String for the Type
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this.context, R.array.drug_types, android.R.layout.simple_spinner_item);
		String type = adapter.getItem(drug.getType()).toString();			
        drugType.setText(type);
 
        return drugsView;
    }
}
