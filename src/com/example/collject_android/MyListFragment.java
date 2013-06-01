package com.example.collject_android;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;
import com.example.collject_android.adapters.MyAdapter;

import com.example.collject_android.utils.Data;

public class MyListFragment extends ListFragment {
	
	private ArrayList<Data> datas;

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAdapter();
	}

	private void setAdapter() {
		this.setListAdapter(new MyAdapter(this.getActivity(), 0));
	}

	@Override
	public void onStart() {
		super.onStart();
	}
	
	
	
	
}