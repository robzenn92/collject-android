package com.example.collject_android.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import android.graphics.Bitmap;

public class Data {
	String title;
	ArrayList<String> tags;
    Bitmap image;
	
	public Data() {
		title = "";
		this.tags = new ArrayList<String>();
	}

	public Data(String title, Collection<String> desc, Bitmap image) {
		this.title = title;
		this.tags = new ArrayList<String>();
		this.tags.addAll(desc);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Iterator<String> getTags() {
		return tags.listIterator();
	}

	public void setTags(Collection<String> tags) {
		this.tags.clear();
		this.tags.addAll(tags);
	}

	public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
	}
}
