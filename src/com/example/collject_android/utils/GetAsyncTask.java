package com.example.collject_android.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class GetAsyncTask extends AsyncTask<String, Void, String> {
	public interface OnGet{
		public void OnGetFinished(String json);
	}
	
	private OnGet listener;
	
	public GetAsyncTask(OnGet listener) {
		super();
		this.listener = listener;
	}
	@Override
	protected String doInBackground(String... params) {
		if (params == null || params.length == 0)
			throw new IllegalStateException("you should provide the server url");
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(params[0]);
		try {
			if (!isCancelled()) {
				HttpResponse resp = client.execute(get);
				if (!isCancelled())
					return EntityUtils.toString(resp.getEntity());
			}
		} catch (Exception e) {
			Log.e(getClass().getCanonicalName(), e.toString());
		}
		return null;
	}
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		Log.e(listener.getClass().toString(), result);
		listener.OnGetFinished(result);
	}
	

}
