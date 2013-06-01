package com.example.collject_android;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.collject_android.adapters.MyAdapter;

import com.example.collject_android.utils.Data;

public class MyListFragment extends ListFragment {
	
	private ArrayList<Data> datas;

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		FragmentActivity fa = this.getActivity();
		
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setAdapter();
	}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

	private void setAdapter() {
		this.setListAdapter(new MyAdapter(this.getActivity(), 0));
	}

	@Override
	public void onStart() {
		super.onStart();
	}
	
	
	
	
}