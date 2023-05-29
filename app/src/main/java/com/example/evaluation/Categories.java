package com.example.evaluation;

import com.google.gson.annotations.SerializedName;

public class Categories {

	@SerializedName("strCategory")
	private String strCategory;

	@SerializedName("strCategoryDescription")
	private String strCategoryDescription;

	@SerializedName("idCategory")
	private String idCategory;

	public Categories(String strCategory, String strCategoryDescription, String idCategory, String strCategoryThumb) {
		this.strCategory = strCategory;
		this.strCategoryDescription = strCategoryDescription;
		this.idCategory = idCategory;
		this.strCategoryThumb = strCategoryThumb;
	}

	@SerializedName("strCategoryThumb")
	private String strCategoryThumb;

	public void setStrCategory(String strCategory){
		this.strCategory = strCategory;
	}

	public String getStrCategory(){
		return strCategory;
	}

	public void setStrCategoryDescription(String strCategoryDescription){
		this.strCategoryDescription = strCategoryDescription;
	}

	public String getStrCategoryDescription(){
		return strCategoryDescription;
	}

	public void setIdCategory(String idCategory){
		this.idCategory = idCategory;
	}

	public String getIdCategory(){
		return idCategory;
	}

	public void setStrCategoryThumb(String strCategoryThumb){
		this.strCategoryThumb = strCategoryThumb;
	}

	public String getStrCategoryThumb(){
		return strCategoryThumb;
	}
}