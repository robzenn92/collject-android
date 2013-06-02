package com.example.collject_android.adapters;

import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.collject_android.ProblemEnlarged;
import com.example.collject_android.R;
import com.example.collject_android.utils.Data;
import com.example.collject_android.utils.utils;

public class DataAdapter extends BaseAdapter {
	private List<Data> data;
	private Context ctx;
	
	public static final int MAX_TAGS_LEN = 40;
	
	public DataAdapter(Context ctx, List<Data> datas) {
		this.data=datas;
		this.ctx=ctx;
	}

	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		return data.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View rowView, ViewGroup parent) {
		if(rowView==null){
			LayoutInflater li = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView=li.inflate(R.layout.my_list_item,parent,false);
		}
		rowView.setId(data.get(position).getId());
		TextView mTitleTextView = (TextView) rowView
				.findViewById(R.id.prog_name);
		TextView mTagsTextView = (TextView) rowView
				.findViewById(R.id.prog_tags);
		ImageView mProfileImage = (ImageView) rowView
				.findViewById(R.id.prog_profile_image);

		// HOTFIX
		Bitmap bitmap = BitmapFactory.decodeResource(ctx.getResources(),R.drawable.medium);
		mProfileImage.setImageBitmap(utils.getRoundedCornerBitmap(bitmap));
		mProfileImage.setBackgroundResource(R.drawable.round_img_proj);
		
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
		rowView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(ctx, ProblemEnlarged.class);
				i.putExtra(ProblemEnlarged.ID_PASSED_KEY, v.getId());
				ctx.startActivity(i);
			}
		});
		return rowView;
	}
}
