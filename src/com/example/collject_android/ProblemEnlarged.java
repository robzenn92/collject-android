package com.example.collject_android;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.collject_android.utils.GetAsyncTask;
import com.example.collject_android.utils.GetAsyncTask.OnGet;
import com.example.collject_android.utils.Helper;
import com.example.collject_android.utils.Helper.InfoType;
import com.example.collject_android.utils.Helper.StuffType;
import com.example.collject_android.utils.User;
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
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		this.setContentView(R.layout.fragment_problem_enlarged);
		setProgressBarIndeterminateVisibility(true);
		pos = (Helper.StuffType) getIntent().getExtras().get("position");
		String username = (String) getIntent().getExtras().get("username");
		new GetAsyncTask(this).execute(Helper.serverGetRequestBuilder(
				pos, InfoType.General, getIntent()
						.getExtras().getInt(ID_PASSED_KEY)));

		mTitleIcon = (TextView) findViewById(R.id.enlarged_title_icon);
		Typeface font = Typeface.createFromAsset(getAssets(), "Entypo.ttf");
	    mTitleIcon.setTypeface(font);
	    mTitleIcon.setText(Html.fromHtml("&#128640;"));
		
	    mTitle = (TextView) findViewById(R.id.enlarged_title);
		mImageUser = (ImageView) findViewById(R.id.prog_user_image);
		
		// HOTFIX
		Bitmap bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(),R.drawable.medium);
		mImageUser.setImageBitmap(utils.getRoundedCornerBitmap(bitmap));
		mImageUser.setBackgroundResource(R.drawable.round_img_proj);

		
		mTitle = (TextView) findViewById(R.id.enlarged_title);
		mProblemDescription = (TextView) findViewById(R.id.enlarged_description);
		mLocation = (TextView) findViewById(R.id.enlarged_location);
		mSolution = (TextView) findViewById(R.id.enlarged_solution);
		mUser = (TextView) findViewById(R.id.enlarged_user);
		mUser.setText(username);
		Log.e("pimp username", username);
	}
	
	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	public void OnGetFinished(String json) {
		try {
			JSONObject obj = new JSONObject(json);
			mTitle.setText(obj.getString("title"));
			mProblemDescription.setText(obj.getString("description"));
			mLocation.setText(obj.getString("city"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setProgressBarVisibility(false);
	}
}
