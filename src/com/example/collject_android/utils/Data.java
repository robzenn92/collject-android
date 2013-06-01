package com.example.collject_android.utils;

import android.graphics.Bitmap;

public class Data {
	String title;
	String tags;
    Bitmap image;
	
	public Data() {
		title = "";
		tags = "";
	}

	public Data(String title, String desc, Bitmap image) {
		this.title = title;
		this.tags = desc;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
	}
}
