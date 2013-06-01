package com.example.collject_android.adapters;

import java.util.ArrayList;
import java.util.Iterator;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.collject_android.ProblemEnlarged;
import com.example.collject_android.R;
import com.example.collject_android.utils.Data;

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
		if (test == 1) {
			data = new ArrayList<Data>();
			ArrayList<String> tst = new ArrayList<String>(6);
			tst.add("#android");
			tst.add("#javammerda");
			tst.add("#daleks");
			tst.add("#bruciare");
			tst.add("#alberi");
			tst.add("#ios");
			data.add(new Data("blek", tst, null));
			data.add(new Data("GINO", tst, null));
			data.add(new Data("MY", tst, null));
			data.add(new Data("TOPO", tst, null));
			data.add(new Data("MOAR", tst, null));
			data.add(new Data("DALEKS", tst, null));
		}
		this.addAll(data);
//		this.notifyDataSetChanged();
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
		TextView mTitleTextView = (TextView) rowView
				.findViewById(R.id.prog_name);
		TextView mTagsTextView = (TextView) rowView
				.findViewById(R.id.prog_tags);
		ImageView mProfileImage = (ImageView) rowView
				.findViewById(R.id.prog_profile_image);

		// HOTFIX
		
		Bitmap bitmap = BitmapFactory.decodeResource(getContext().getResources(),R.drawable.medium);
		bitmap = getRoundedCornerBitmap(bitmap);
		mProfileImage.setImageBitmap(bitmap);
		
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
				/*
				FragmentManager fm = ((FragmentActivity) context).getSupportFragmentManager();
				FragmentTransaction ft = fm.beginTransaction();
				ListFragment lf = (ListFragment) fm.findFragmentById(R.id.problem_fragment);
				lf.setListAdapter(new MyAdapter(context, 1));
				*/
				Intent i = new Intent(context, ProblemEnlarged.class);
				context.startActivity(i);
			}
		});
		return rowView;
	}

	private Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
		Bitmap inpBitmap = bitmap;
	    int width = 0;
	    int height = 0;
	    width = inpBitmap.getWidth();
	    height = inpBitmap.getHeight();

	    if (width <= height) {
	        height = width;
	    } else {
	        width = height;
	    }

	    Bitmap output = Bitmap.createBitmap(width, height, Config.ARGB_8888);
	    Canvas canvas = new Canvas(output);

	    final Paint paint = new Paint();
	    final Rect rect = new Rect(0, 0, width, height);
	    RectF rectF = new RectF(rect);
	    
	    // Ritaglio
	    float roundPx = width / 2;
	    int color = 0xff424242;
	    paint.setAntiAlias(true);
	    canvas.drawARGB(0, 0, 0, 0);
	    paint.setColor(color);
	    canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
	    
	    paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
	    canvas.drawBitmap(inpBitmap, rect, rect, paint);
	    
	    // Bordino
//	    roundPx = width / 2 + 1;
//	    color = 0xff56710;
//	    paint.setAntiAlias(true);
//	    canvas.drawARGB(0, 0, 0, 0);
//	    paint.setColor(color);
//	    canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
//
//	    paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
//	    canvas.drawBitmap(inpBitmap, rect, rect, paint);

	    return output;
	}

}
