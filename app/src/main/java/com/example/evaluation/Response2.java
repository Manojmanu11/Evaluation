package com.example.evaluation;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response2{

	@SerializedName("meals")
	private List<MealsItem> meals;

	public void setMeals(List<MealsItem> meals){
		this.meals = meals;
	}

	public List<MealsItem> getMeals(){
		return meals;
	}

	@Override
	public String toString() {
		return "Response2{" +
				"meals=" + meals +
				'}';
	}
}