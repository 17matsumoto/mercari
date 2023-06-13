package com.example.demo.domain;

/**
 * ユーザドメイン.
 * 
 * @author matsumotoyuyya
 *
 */
public class UserInfo {

	/**
	 * ID
	 */
	private Integer id;

	/**
	 * ユーザー名
	 */
	private String name;

	/**
	 * メールアドレス
	 */
	private String mailAddress;

	/**
	 * パスワード
	 */
	private String password;

	/**
	 * 管理者権限
	 */
	private Integer authority;

	public UserInfo() {
	}

	public UserInfo(Integer id, String name, String mailAddress, String password, Integer authority) {
		super();
		this.id = id;
		this.name = name;
		this.mailAddress = mailAddress;
		this.password = password;
		this.authority = authority;
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

	public String getMailAddress() {
		return mailAddress;
	}

	public void setMailAddress(String mailAddress) {
		this.mailAddress = mailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getAuthority() {
		return authority;
	}

	public void setAuthority(Integer authority) {
		this.authority = authority;
	}

}
