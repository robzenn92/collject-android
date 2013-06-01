package com.example.collject_android.adapters;

import java.util.ArrayList;

import android.content.Context;
import com.example.collject_android.utils.Data;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.collject_android.R;

public class MyAdapter extends ArrayAdapter<Data> {

	private Context context;
	private ArrayList<Data> data;
	
	public MyAdapter(Context context, int test) {
		super(context, R.layout.my_list_item);
		this.context = context;
		if (test == 0) {
			data = new ArrayList<Data>();
			data.add(new Data("blek", "blek", null));
			data.add(new Data("MOAR", "PIMP", null));
			data.add(new Data("DALEKS", "EXTERMINATE", null));
			data.add(new Data("NIGGA", "STOLE", null));
			data.add(new Data("MY", "BIKE", null));
			data.add(new Data("TOPO", "GIGIO", null));
		}
		this.addAll(data);
		this.notifyDataSetChanged();
	}

	public MyAdapter(Context context, Data[] data) {
		super(context, R.layout.my_list_item, data);
		this.context = context;
		this.data = new ArrayList<Data>();
		for (int i = 0; i < data.length; i++) {
			this.data.add(data[i]);
		}
	}
	
	public MyAdapter(Context context, ArrayList<Data> data) {
		super(context, R.layout.my_list_item, data);
		this.context = context;
		this.data = data;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(R.layout.my_list_item, parent, false);
		TextView mTitleTextView = (TextView) rowView.findViewById(R.id.prog_name);
		TextView mTagsTextView = (TextView) rowView.findViewById(R.id.prog_tags);
		ImageView mProfileImage = (ImageView) rowView.findViewById(R.id.prog_profile_image);
		mTitleTextView.setText(data.get(position).getTitle());
		mTagsTextView.setText(data.get(position).getTags());
		
		return rowView;
	}

}
