package com.example.collject_android.adapters;

import java.util.ArrayList;
import java.util.Iterator;

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
    public static final int MAX_TAGS_LEN = 40;
	
    //TEST
	public MyAdapter(Context context, int test) {
		super(context, R.layout.my_list_item);
		this.context = context;
		if (test == 0) {
			data = new ArrayList<Data>();
			ArrayList<String> tst = new ArrayList<String>(6);
			tst.add("#bruciare");
			tst.add("#alberi");
			tst.add("#ios");
			tst.add("#android");
			tst.add("#javammerda");
			tst.add("#daleks");
			data.add(new Data("blek", tst, null));
			data.add(new Data("MOAR", tst, null));
			data.add(new Data("DALEKS", tst, null));
			data.add(new Data("GINO", tst, null));
			data.add(new Data("MY", tst, null));
			data.add(new Data("TOPO", tst, null));
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
		
		Iterator<String> iter = data.get(position).getTags();
		String tmp = "";
		String asd;
		
		while (iter.hasNext()) {
			asd = iter.next();
			if ((tmp.length()+asd.length()+2)>=MAX_TAGS_LEN) break;
			if (tmp.length()>0) tmp = tmp + ", " + asd;
			else tmp = asd;
		}
		mTagsTextView.setText(tmp);
		
		return rowView;
	}

}
