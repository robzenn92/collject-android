package com.example.collject_android.utils;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class PostAsyncTask extends AsyncTask<String, Void, JSONObject> {
	
	private ArrayList<NameValuePair> params;

	public PostAsyncTask(ArrayList<NameValuePair> params) {
		super();
		this.params = params;
	}

	@Override
	protected JSONObject doInBackground(String... arg0) {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(arg0[0]);
		try {
			post.setEntity(new UrlEncodedFormEntity(params));
			HttpResponse resp=client.execute(post);
			return new JSONObject(EntityUtils.toString(resp.getEntity()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e(getClass().getCanonicalName(), e.toString());
		}
		return null;
	}

}
