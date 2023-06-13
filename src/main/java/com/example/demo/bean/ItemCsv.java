package com.example.demo.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@JsonPropertyOrder({ "ID", "価格", "カテゴリー", "ブランド", "コンディション" })
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ItemCsv {

	@JsonProperty("ID")
	private Integer id;

	@JsonProperty("価格")
	private Double price;

	@JsonProperty("カテゴリー")
	private String nameAll;

	@JsonProperty("ブランド")
	private String brand;

	@JsonProperty("コンディション")
	private Integer condition;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getNameAll() {
		return nameAll;
	}

	public void setNameAll(String nameAll) {
		this.nameAll = nameAll;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Integer getCondition() {
		return condition;
	}

	public void setCondition(Integer condition) {
		this.condition = condition;
	}
	
	public ItemCsv() {
		// TODO Auto-generated constructor stub
	}

	public ItemCsv(Integer id, Double price, String nameAll, String brand, Integer condition) {
		super();
		this.id = id;
		this.price = price;
		this.nameAll = nameAll;
		this.brand = brand;
		this.condition = condition;
	}
	
	

}
