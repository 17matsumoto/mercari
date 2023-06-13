package com.example.demo.form;

import java.util.List;

import com.example.demo.domain.SmalCategory;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ItemForm {

	/**
	 * ID
	 */
	private Integer id;

	/**
	 * 商品名
	 */
	@NotBlank(message = "商品名を入力してください")
	private String name;

	/**
	 * 商品状態
	 */
	@NotNull(message = "商品状態を入力してください")
	private Integer condition;

	/**
	 * ブランド
	 */
	@NotBlank(message = "ブランドを入力してください")
	private String brand;

	/**
	 * 価格
	 */
	@NotNull(message = "価格を入力してください")
	private Double price;

	/**
	 * 配送料
	 */
	private Integer shipping;

	/**
	 * 商品説明
	 */
	@NotBlank(message = "商品説明を入力してください")
	private String description;

	/**
	 * 大カテゴリー名
	 */
	@NotNull(message = "カテゴリーを選択してください")
	private Integer bigCategoryId;

	/**
	 * 中カテゴリー名
	 */
	@NotNull(message = "カテゴリーを選択してください")
	private Integer mediamCategoryId;

	/**
	 * 小カテゴリー名
	 */
	@NotNull(message = "カテゴリーを選択してください")
	private Integer smalCategoryId;

	/**
	 * 小カテゴリー
	 */
	private List<SmalCategory> smalCategoryList;

	public ItemForm() {
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

	public Integer getBigCategoryId() {
		return bigCategoryId;
	}

	public void setBigCategoryId(Integer bigCategoryId) {
		this.bigCategoryId = bigCategoryId;
	}

	public Integer getMediamCategoryId() {
		return mediamCategoryId;
	}

	public void setMediamCategoryId(Integer mediamCategoryId) {
		this.mediamCategoryId = mediamCategoryId;
	}

	public Integer getSmalCategoryId() {
		return smalCategoryId;
	}

	public void setSmalCategoryId(Integer smalCategoryId) {
		this.smalCategoryId = smalCategoryId;
	}

	public List<SmalCategory> getSmalCategoryList() {
		return smalCategoryList;
	}

	public void setSmalCategoryList(List<SmalCategory> smalCategoryList) {
		this.smalCategoryList = smalCategoryList;
	}

	public ItemForm(Integer id, @NotBlank(message = "商品名を入力してください") String name,
			@NotNull(message = "商品状態を入力してください") Integer condition, @NotBlank(message = "ブランドを入力してください") String brand,
			@NotNull(message = "価格を入力してください") Double price, Integer shipping,
			@NotBlank(message = "商品説明を入力してください") String description,
			@NotBlank(message = "カテゴリーを入力してください") Integer bigCategoryId,
			@NotBlank(message = "カテゴリーを入力してください") Integer mediamCategoryId,
			@NotBlank(message = "カテゴリーを入力してください") Integer smalCategoryId, List<SmalCategory> smalCategoryList) {
		super();
		this.id = id;
		this.name = name;
		this.condition = condition;
		this.brand = brand;
		this.price = price;
		this.shipping = shipping;
		this.description = description;
		this.bigCategoryId = bigCategoryId;
		this.mediamCategoryId = mediamCategoryId;
		this.smalCategoryId = smalCategoryId;
		this.smalCategoryList = smalCategoryList;
	}



}
