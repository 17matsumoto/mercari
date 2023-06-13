package com.example.demo.domain;


public class Original {

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
	private Integer conditionId;

	/**
	 * カテゴリー名
	 */
	private String categoryName;

	/**
	 * ブランド
	 */
	private String brand;

	/**
	 * 価格
	 */
	private double price;

	/**
	 * 配送料
	 */
	private Integer shipping;

	/**
	 * 商品説明
	 */
	private String description;

	public Original() {
	}

	public Original(Integer id, String name, Integer condition, String category_name, String brand, double price,
			Integer shipping, String description) {
		super();
		this.id = id;
		this.name = name;
		this.conditionId = condition;
		this.categoryName = category_name;
		this.brand = brand;
		this.price = price;
		this.shipping = shipping;
		this.description = description;
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

	public Integer getConditionId() {
		return conditionId;
	}

	public void setConditionId(Integer conditionId) {
		this.conditionId = conditionId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
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

	
}
