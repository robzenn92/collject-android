package com.example.collject_android;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.Menu;
import android.widget.ArrayAdapter;

import com.example.collject_android.adapters.MyAdapter;

public class MainActivity extends FragmentActivity {

	private ActionBar actionBar;
	FragmentTransaction ft;
	FragmentManager fm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.activity_main);

		fm = this.getSupportFragmentManager();

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.menu_dropdown,
				android.R.layout.simple_spinner_dropdown_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);

		actionBar.setListNavigationCallbacks(adapter,
				new ActionBar.OnNavigationListener() {

					@Override
					public boolean onNavigationItemSelected(int itemPosition,
							long itemId) {
						switch (itemPosition) {
						case 0: {
							ListFragment lf = (ListFragment) getSupportFragmentManager()
									.findFragmentById(R.id.main_fragment);
							lf.setListAdapter(new MyAdapter(MainActivity.this,0));
							break;
						}
						case 1: {
							ListFragment lf = (ListFragment) getSupportFragmentManager()
									.findFragmentById(R.id.main_fragment);
							lf.setListAdapter(new MyAdapter(MainActivity.this,0));
							break;
						}
						case 2: {
							ListFragment lf = (ListFragment) getSupportFragmentManager()
									.findFragmentById(R.id.main_fragment);
							lf.setListAdapter(new MyAdapter(MainActivity.this,0));
							break;
						}
						}
						return true;
					}
				});
		actionBar.setSelectedNavigationItem(0);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
