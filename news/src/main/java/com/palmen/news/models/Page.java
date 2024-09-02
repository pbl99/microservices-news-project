package com.palmen.news.models;

import java.util.ArrayList;
import java.util.List;

public class Page {
	private List<Item> items = new ArrayList<>();

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}
}
