package org.madbit.drugbox.adapter;

import java.util.List;

import org.madbit.drugbox.R;
import org.madbit.drugbox.entity.Drug;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ExpiredDrugsAdapter extends ArrayAdapter<Drug> {

	private final Context context;
	private final List<Drug> drugs;
	private int resource;
	
	public ExpiredDrugsAdapter(Context context, int resource, List<Drug> drugs) {
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
        TextView drugName =(TextView)drugsView.findViewById(R.id.name);
        TextView drugType =(TextView)drugsView.findViewById(R.id.expireDate);
 
        drugName.setText(drug.getName());
        String expirationDate = String.format(context.getText(R.string.expirationDate).toString(), drug.getExpireDate());
        drugType.setText(expirationDate);
 
        return drugsView;
    }
}
