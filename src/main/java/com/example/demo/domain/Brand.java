package com.example.demo.domain;

/**
 * 商品ブランドドメイン.
 * 
 * @author matsumotoyuyya
 *
 */
public class Brand {

	/**
	 * ブランドId
	 */
	private Integer brand_id;

	/**
	 * ブランド名
	 */
	private String name;

	public Brand() {
	}

	public Brand(Integer brand_id, String name) {
		super();
		this.brand_id = brand_id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Brand [brand_id=" + brand_id + ", name=" + name + "]";
	}

	public Integer getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(Integer brand_id) {
		this.brand_id = brand_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
