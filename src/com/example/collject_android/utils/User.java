package com.example.collject_android.utils;

import java.util.ArrayList;

public class User {
	int id;
	String img;
	String mail;
	String name;
	ArrayList<String> skill;

	public User(int id, String img, String name, String mail, String[] skill) {
		super();
		this.id = id;
		this.img = img;
		this.mail = mail;
		this.name = name;
		this.skill = new ArrayList<String>();
		for (int i = 0; i < skill.length; i++) {
			this.skill.add(skill[i]);
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public ArrayList<String> getSkill() {
		return skill;
	}

	public void setSkill(ArrayList<String> skill) {
		this.skill = skill;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
