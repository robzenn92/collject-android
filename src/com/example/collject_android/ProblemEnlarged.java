package com.example.collject_android;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.example.collject_android.utils.GetAsyncTask;
import com.example.collject_android.utils.GetAsyncTask.OnGet;
import com.example.collject_android.utils.Helper;
import com.example.collject_android.utils.Helper.InfoType;

public class ProblemEnlarged extends Activity implements OnGet {

	public static final String ID_PASSED_KEY = "id";
	
	private TextView mTitle;
	private TextView mProblemDescription;
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		this.setContentView(R.layout.fragment_problem_enlarged);
		setProgressBarIndeterminateVisibility(true);
		new GetAsyncTask(this).execute(Helper.serverGetRequestBuilder(
				Helper.StuffType.Problem, InfoType.General, getIntent()
						.getExtras().getInt(ID_PASSED_KEY)));
		mTitle = (TextView) findViewById(R.id.enlarged_title);
		mProblemDescription = (TextView) findViewById(R.id.enlarged_description);
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
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setProgressBarVisibility(false);
	}
}
