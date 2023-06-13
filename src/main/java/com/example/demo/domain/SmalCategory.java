package com.example.demo.domain;

import java.util.List;

public class SmalCategory {

	/**
	 * ID
	 */
	private Integer id;

	/**
	 * 大カテゴリー名
	 */
	private String name;

	/**
	 * 中カテゴリーID
	 */
	private Integer mediamCategoryId;

	/**
	 * 全ての階層名
	 */
	private String nameAll;

	private Integer smalCount;

	/**
	 * 中カテゴリーリスト
	 */
	private List<MediamCategory> mediamCategoryList;

	public SmalCategory() {
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

	public Integer getMediamCategoryId() {
		return mediamCategoryId;
	}

	public void setMediamCategoryId(Integer mediamCategoryId) {
		this.mediamCategoryId = mediamCategoryId;
	}

	public String getNameAll() {
		return nameAll;
	}

	public void setNameAll(String nameAll) {
		this.nameAll = nameAll;
	}

	public List<MediamCategory> getMediamCategoryList() {
		return mediamCategoryList;
	}

	public void setMediamCategoryList(List<MediamCategory> mediamCategoryList) {
		this.mediamCategoryList = mediamCategoryList;
	}

	public SmalCategory(Integer id, String name, Integer mediamCategoryId, String nameAll,
			List<MediamCategory> mediamCategoryList) {
		super();
		this.id = id;
		this.name = name;
		this.mediamCategoryId = mediamCategoryId;
		this.nameAll = nameAll;
		this.mediamCategoryList = mediamCategoryList;
	}

	public Integer getSmalCount() {
		return smalCount;
	}

	public void setSmalCount(Integer smalCount) {
		this.smalCount = smalCount;
	}

}
