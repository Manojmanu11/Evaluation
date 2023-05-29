package com.example.evaluation;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class Response{



	@SerializedName("categories")
	private List<Categories> categories;

	public void setCategories(List<Categories> categories){
		this.categories = categories;
	}

	public List<Categories> getCategories(){
		return categories;
	}

	public String toString(){
		return
				"DataResponse{" +
						"categories = '" + categories + '\'' +
						"}";
	}
}