package com.example.collject_android.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class GetAsyncTask extends AsyncTask<String, Void, JSONObject> {
	
	@Override
	protected JSONObject doInBackground(String... params) {
		if(params==null || params.length==0)
			throw new IllegalStateException("you should provide the server url");
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(params[0]);
		try {
			HttpResponse response=client.execute(get);
			return new JSONObject(EntityUtils.toString(response.getEntity()));
		} catch (Exception e){
			Log.e(getClass().getCanonicalName(), e.toString());
		}
		return null;
	}

}
