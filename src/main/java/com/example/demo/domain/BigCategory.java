package com.example.demo.domain;

import lombok.Data;

@Data
public class BigCategory {

	/**
	 * ID
	 */
	private Integer id;

	/**
	 * 大カテゴリー名
	 */
	private String name;

	
	private Integer count;
	
	
	public BigCategory() {
	}

	public BigCategory(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	

}
