package com.example.demo.form;

/**
 * ログイン関連フォーム.
 * 
 * @author matsumotoyuyya
 */
public class LoginForm {

	/**
	 * メールアドレス
	 */
	private String mailAddress;

	/**
	 * パスワード
	 */
	private String password;

	public LoginForm() {
	}

	public LoginForm(String mailAddress, String password) {
		super();
		this.mailAddress = mailAddress;
		this.password = password;
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

}
