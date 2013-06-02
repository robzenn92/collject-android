package com.example.collject_android;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.collject_android.utils.GetAsyncTask;
import com.example.collject_android.utils.GetAsyncTask.OnGet;
import com.example.collject_android.utils.Helper;
import com.example.collject_android.utils.Helper.InfoType;
import com.example.collject_android.utils.Helper.StuffType;
import com.example.collject_android.utils.utils;

public class ProblemEnlarged extends Activity implements OnGet {

	public static final String ID_PASSED_KEY = "id";
	Helper.StuffType pos;

	private TextView mTitle, mTitleIcon;
	private ImageView mImageUser;
	private TextView mProblemDescription;
	private TextView mLocation;
	private TextView mSolution;
	private TextView mUser;
	private TextView mTags;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		setProgressBarIndeterminateVisibility(true);
		pos = (Helper.StuffType) getIntent().getExtras().get("position");
		String username = (String) getIntent().getExtras().get("username");
		String img = (String) getIntent().getExtras().get("image");
		String tags = (String) getIntent().getExtras().get("tags");
		new GetAsyncTask(this).execute(Helper
				.serverGetRequestBuilder(pos, InfoType.General, getIntent()
						.getExtras().getInt(ID_PASSED_KEY)));

		if (pos == StuffType.Solution) {
			this.setContentView(R.layout.activity_solution);

			mTitle = (TextView) findViewById(R.id.enlarged_title);
			mImageUser = (ImageView) findViewById(R.id.problem_user_image);

			mProblemDescription = (TextView) findViewById(R.id.solution_description);

			mUser = (TextView) findViewById(R.id.problem_user);
			mUser.setText(username);

		} else {
			this.setContentView(R.layout.fragment_problem_enlarged);

			mTitleIcon = (TextView) findViewById(R.id.enlarged_title_icon);
			Typeface font = Typeface.createFromAsset(getAssets(), "Entypo.ttf");
			mTitleIcon.setTypeface(font);
			mTitleIcon.setText(Html.fromHtml("&#128640;"));

			mTitle = (TextView) findViewById(R.id.enlarged_title);
			mImageUser = (ImageView) findViewById(R.id.prog_user_image);

			mTitle = (TextView) findViewById(R.id.enlarged_title);
			mProblemDescription = (TextView) findViewById(R.id.enlarged_description);
			mLocation = (TextView) findViewById(R.id.enlarged_location);
			mSolution = (TextView) findViewById(R.id.enlarged_solution);
			mUser = (TextView) findViewById(R.id.enlarged_user);
			mUser.setText(username);
			
			mSolution.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					new GetAsyncTask(ProblemEnlarged.this).execute(Helper
							.serverGetRequestBuilder(pos, InfoType.Solution, getIntent()
									.getExtras().getInt(ID_PASSED_KEY)));
				}
			});
			
			mTags = (TextView) findViewById(R.id.enlarged_tags);
			mTags.setText(tags);
		}

		Bitmap bitmap;
		if (img != null && !img.equals("")) {
			byte[] ds = Base64.decode(img.substring(img.indexOf("base64,")+7), Base64.DEFAULT);
			bitmap = BitmapFactory.decodeByteArray(ds, 0, ds.length);
		} else {
			bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.medium);
		}
		mImageUser.setImageBitmap(utils.getRoundedCornerBitmap(bitmap));
		mImageUser.setBackgroundResource(R.drawable.round_img_proj);
		mImageUser.setAdjustViewBounds(true);

	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	public void OnGetFinished(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			if (pos != StuffType.Solution) {
				mTitle.setText(obj.getString("title"));
				mLocation.setText(obj.getString("city"));
			}
			mProblemDescription.setText(obj.getString("description"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setProgressBarVisibility(false);
	}
}
