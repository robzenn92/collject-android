package com.example.collject_android;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;

import com.example.collject_android.adapters.DataAdapter;
import com.example.collject_android.utils.Data;
import com.example.collject_android.utils.GetAsyncTask;
import com.example.collject_android.utils.GetAsyncTask.OnGet;
import com.example.collject_android.utils.Helper;
import com.example.collject_android.utils.Helper.StuffType;
import com.example.collject_android.utils.User;

public class MainActivity extends FragmentActivity implements
		ActionBar.OnNavigationListener, OnGet {

	private ActionBar mActionBar;
	private DataAdapter mListAdapter;
	private ArrayList<Data> datas;
	private GetAsyncTask active;
	Helper.StuffType pos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		this.setContentView(R.layout.activity_main);

		mActionBar = getActionBar();

		setNavigation();
		datas = new ArrayList<Data>();
		mListAdapter = new DataAdapter(this, datas);
		ListFragment lf = (ListFragment) getSupportFragmentManager()
				.findFragmentById(R.id.main_fragment);
		lf.setListAdapter(mListAdapter);
	}

	private void setNavigation() {
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.menu_dropdown,
				android.R.layout.simple_spinner_dropdown_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

		mActionBar.setListNavigationCallbacks(adapter, this);
		mActionBar.setSelectedNavigationItem(0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public ArrayList<Data> getDatas() {
		return datas;
	}

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		if (active != null)
			active.cancel(true);
		active = new GetAsyncTask(this);
		setProgressBarIndeterminateVisibility(true);
		switch (itemPosition) {
		case 0: {
			active.execute(Helper.serverGetRequestBuilder(
					Helper.StuffType.Problem, Helper.InfoType.List, null));
			pos = Helper.StuffType.Problem;
			break;
		}
		case 1: {
			active.execute(Helper.serverGetRequestBuilder(
					Helper.StuffType.Solution, Helper.InfoType.List, null));
			pos = Helper.StuffType.Solution;
			break;
		}
		case 2: {
			active.execute(Helper.serverGetRequestBuilder(
					Helper.StuffType.Project, Helper.InfoType.List, null));
			pos = Helper.StuffType.Project;
			break;
		}
		}
		return true;
	}

	public Helper.StuffType getPosition() {
		return pos;
	}

	@Override
	public void OnGetFinished(String json) {
		JSONArray jsonarr;
		setProgressBarIndeterminateVisibility(false);
		datas.clear();
		mListAdapter.notifyDataSetChanged();
		try {
			jsonarr = new JSONArray(json);
			new AsyncTask<JSONArray, JSONObject, Void>() {

				@Override
				protected Void doInBackground(JSONArray... params) {
					for (int i = 0; i < params[0].length(); i++) {

						try {
							JSONObject jobj = params[0].getJSONObject(i);
							publishProgress(jobj);
						} catch (JSONException e) {
							Log.e(getClass().getCanonicalName(), e.toString());
						}

					}
					return null;
				}

				@Override
				protected void onProgressUpdate(JSONObject... params) {
					Data d = new Data();
					try {
						d.setId(params[0].getInt("id"));
						d.setTitle(params[0].getString("title"));
						JSONObject tmpJson;
						String tmp = "";
						if (pos == StuffType.Problem) {
							tmp = params[0].getString("owner");
						} else if (pos == StuffType.Project) {
							tmp = params[0].getString("user");
						}
						tmpJson = new JSONObject(tmp);
						JSONArray jarr = new JSONArray(
								tmpJson.getString("skills"));
						int len = jarr.length();
						String[] skill = new String[len];
						for (int i = 0; i < len; i++) {
							skill[i] = jarr.getString(i);
						}
						User nw = new User(Integer.parseInt(tmpJson
								.getString("id")), tmpJson.getString("img"),
								tmpJson.getString("username"),
								tmpJson.getString("email"), skill);
						d.setUser(nw);
						Log.e("jsongay", d.getUser().getName());
						datas.add(d);
						mListAdapter.notifyDataSetChanged();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						Log.e(getClass().getCanonicalName(), e.toString());
					}

				}

			}.execute(jsonarr);
		} catch (Exception e) {
			Log.e(getClass().getCanonicalName(), e.toString());
		}

	}
}
