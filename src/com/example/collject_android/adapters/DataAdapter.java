package com.example.collject_android.adapters;

import java.util.Iterator;
import java.util.List;

import com.example.collject_android.ProblemEnlarged;
import com.example.collject_android.R;
import com.example.collject_android.utils.Data;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
		TextView mTitleTextView = (TextView) rowView
				.findViewById(R.id.prog_name);
		TextView mTagsTextView = (TextView) rowView
				.findViewById(R.id.prog_tags);
		ImageView mProfileImage = (ImageView) rowView
				.findViewById(R.id.prog_profile_image);

		// HOTFIX
		
		Bitmap bitmap = BitmapFactory.decodeResource(ctx.getResources(),R.drawable.medium);
		mProfileImage.setImageBitmap(getRoundedCornerBitmap(bitmap));
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
				ctx.startActivity(i);
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