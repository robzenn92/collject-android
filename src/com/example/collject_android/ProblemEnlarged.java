package com.example.collject_android;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProblemEnlarged extends Fragment {

	public static ProblemEnlarged newInstance() {
		ProblemEnlarged f = new ProblemEnlarged();
        return f;
    }
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_problem_enlarged, container, false);
        return v;
    }
}
