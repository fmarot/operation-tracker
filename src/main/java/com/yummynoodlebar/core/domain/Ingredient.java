package com.yummynoodlebar.core.domain;

import lombok.Data;

@Data
public class Ingredient {
	private String name;
	private String description;

	public Ingredient(String name, String description) {
		this.name = name;
		this.description = description;
	}

}
