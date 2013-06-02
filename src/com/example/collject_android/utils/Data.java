package com.example.collject_android.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import android.graphics.Bitmap;

public class Data {
	Integer id;
	String title;
	String tags;
    Bitmap image;
    User user;
	
	public Data() {
		title = "";
		this.tags = "";
	}
	
	public Data( int id, String title) {
		this.id=id;
		this.title = title;
		this.tags = "";
	}

	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	public String getTags(){
		return tags;
	}


	public Bitmap getImage() {
		return image;
	}

	public void setImage(Bitmap image) {
		this.image = image;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void addTags(String tags) {
		this.tags +=tags+" ";
	}
}
