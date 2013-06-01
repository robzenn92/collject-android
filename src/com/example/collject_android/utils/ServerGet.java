package com.example.collject_android.utils;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import com.example.collject_android.interfaces.OnTaskFinished;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class ServerGet<Progress> extends AsyncTask <String, Progress, Boolean> {
	
	Context context;
	OnTaskFinished caller;
	JSONObject json;
	
	/**
	 * class to make get requests from the server on the execute you should pass the url
	 * for the request the <Progress> can be set to <Void>
	 * @param caller the caller of the get
	 * @param context the context where the request is started
	 */
	public ServerGet(OnTaskFinished caller, Context context) {
		this.caller =  caller;
		this.context = context;
	}

	@Override
	protected Boolean doInBackground(String... params) {
		
		if (params.length != 1) {
			return false;
		}

		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(params[0]);
		
		json = null;
		String jsonString;
		
		try {
			jsonString = EntityUtils.toString(client.execute(get).getEntity());
			json = new JSONObject(jsonString);
		} catch (Exception e) {
			Log.e(caller.getClass().toString(), e.toString());
			return false;
		}
		return true;
	}

	@Override
	protected void onPostExecute(Boolean result) {
		super.onPostExecute(result);
		if (result) {
			caller.onTaskFinished(json);
		} else {
			caller.onTaskFinished(null);
		}
	}
}
