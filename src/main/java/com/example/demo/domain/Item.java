package com.example.demo.domain;

import java.util.List;

import lombok.Data;

/**
 * 商品関連エンティティー
 * 
 * @author matsumotoyuyya
 *
 */
@Data
public class Item {

	/**
	 * ID
	 */
	private Integer id;

	/**
	 * 商品名
	 */
	private String name;

	/**
	 * 商品状態
	 */
	private Integer condition;

	/**
	 * 小カテゴリーID
	 */
	private Integer smalCategoryId;

	/**
	 * ブランド
	 */
	private String brand;

	/**
	 * 価格
	 */
	private Double price;

	/**
	 * 配送料
	 */
	private Integer shipping;

	/**
	 * 商品説明
	 */
	private String description;

	/**
	 * 総個数
	 */
	private Integer count;
	
	private String nameAll;

	private Integer bigCount;

	/**
	 * 小カテゴリー
	 */
	private List<SmalCategory> smalCategoryList;

	public Item() {
	}

	public Item(Integer id, String name, Integer condition, Integer smalCategoryId, String brand, Double price,
			Integer shipping, String description, List<SmalCategory> smalCategoryList, Integer count) {
		super();
		this.id = id;
		this.name = name;
		this.condition = condition;
		this.smalCategoryId = smalCategoryId;
		this.brand = brand;
		this.price = price;
		this.shipping = shipping;
		this.description = description;
		this.smalCategoryList = smalCategoryList;
		this.count = count;
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

	public Integer getCondition() {
		return condition;
	}

	public void setCondition(Integer condition) {
		this.condition = condition;
	}

	public Integer getSmalCategoryId() {
		return smalCategoryId;
	}

	public void setSmalCategoryId(Integer smalCategoryId) {
		this.smalCategoryId = smalCategoryId;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getShipping() {
		return shipping;
	}

	public void setShipping(Integer shipping) {
		this.shipping = shipping;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<SmalCategory> getSmalCategoryList() {
		return smalCategoryList;
	}

	public void setSmalCategoryList(List<SmalCategory> smalCategoryList) {
		this.smalCategoryList = smalCategoryList;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getBigCount() {
		return bigCount;
	}

	public void setBigCount(Integer bigCount) {
		this.bigCount = bigCount;
	}

	public String getNameAll() {
		return nameAll;
	}

	public void setNameAll(String nameAll) {
		this.nameAll = nameAll;
	}
	
	

}
