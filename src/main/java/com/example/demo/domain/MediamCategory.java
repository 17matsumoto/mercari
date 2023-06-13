package com.example.demo.domain;

import java.util.List;

import lombok.Data;

@Data
public class MediamCategory {

	/**
	 * ID
	 */
	private Integer id;

	/**
	 * 大カテゴリー名
	 */
	private String name;

	/**
	 * 大カテゴリーID
	 */
	private Integer bigCategoryId;

	private Integer medCount;
	/**
	 * 大カテゴリーリスト.
	 */
	private List<BigCategory> bigCategoryList;

	public MediamCategory() {
	}

	public MediamCategory(Integer id, String name, Integer bigCategoryId, List<BigCategory> bigCategoryList) {
		super();
		this.id = id;
		this.name = name;
		this.bigCategoryId = bigCategoryId;
		this.bigCategoryList = bigCategoryList;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getBigCategoryId() {
		return bigCategoryId;
	}

	public void setBigCategoryId(Integer bigCategoryId) {
		this.bigCategoryId = bigCategoryId;
	}

	public List<BigCategory> getBigCategoryList() {
		return bigCategoryList;
	}

	public void setBigCategoryList(List<BigCategory> bigCategoryList) {
		this.bigCategoryList = bigCategoryList;
	}

	public Integer getMedCount() {
		return medCount;
	}

	public void setMedCount(Integer medCount) {
		this.medCount = medCount;
	}

}
