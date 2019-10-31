package com.example.recyclerview;

/**
 * @author Ouyang Yu
 * @date 2019/10/26 12:52
 */
public class Fruit {
	String name;
	int imageID;

	public Fruit(String name, int imageID) {
		this.name = name;
		this.imageID = imageID;
	}

	public String getName() {
		return name;
	}

	public int getImageID() {
		return imageID;
	}
}
