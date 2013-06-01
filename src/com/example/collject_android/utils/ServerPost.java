package com.example.collject_android.utils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.example.collject_android.interfaces.OnTaskFinished;

import android.os.AsyncTask;
import android.provider.SyncStateContract.Constants;
import android.util.Log;

public class ServerPost<Progress> extends AsyncTask<String, Progress, Boolean> {
	
	JSONObject json;
	String serverURL;
	OnTaskFinished caller;
	
	public ServerPost(OnTaskFinished caller) {
		this.caller = caller;
	}
	
	@Override
	protected Boolean doInBackground(String... params) {
		
			ArrayList<NameValuePair> values = new ArrayList<NameValuePair>(params.length);
			
			for (int i = 0; i < params.length; i++) {

				values.add(new BasicNameValuePair("token", params[i]));
			}
			HttpClient client = new DefaultHttpClient();
			HttpPost post = new HttpPost(serverURL);
			
			try {
				post.setEntity(new UrlEncodedFormEntity(values));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			
			json = null;
			String jsonString;
			try {
				jsonString = EntityUtils.toString(client.execute(post).getEntity());
				json = new JSONObject(jsonString);
			} catch (Exception e) {
				Log.e(caller.getClass().toString(), e.toString());
				e.printStackTrace();
				return false;
			}
			return true;
	}


}
