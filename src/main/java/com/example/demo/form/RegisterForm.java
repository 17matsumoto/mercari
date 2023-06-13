package com.example.demo.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
 * ユーザー登録関連フォーム.
 * 
 * @author matsumotoyuyya
 *
 */
public class RegisterForm {

	/** ユーザー名 */
	@NotBlank(message = "名前を入力して下しい")
	private String name;

	/** メールアドレス **/
	@Pattern(regexp = "^([a-zA-Z0-9])+([a-zA-Z0-9\\._-])*@([a-zA-Z0-9_-])+([a-zA-Z0-9\\._-]+)$", message = "正しいメールアドレスを入力して下さい")
	private String mailAddress;

	/** パスワード */
	@Pattern(regexp = "^(?:(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])|(?=.*\\d)(?=.*[^A-Za-z0-9])(?=.*[a-z])|(?=.*[^A-Za-z0-9])(?=.*[A-Z])(?=.*[a-z])|(?=.*\\d)(?=.*[A-Z])(?=.*[^A-Za-z0-9]))(?!.*(.)\\1{2,})[A-Za-z0-9!~<>,;:_=?*+#.”&§%°()\\|\\[\\]\\-\\$\\^\\@\\/]{8,16}$", message = "パスワードはa-z,A-Z,記号,数字それぞれ1つずつ含めた8文字以上16文字以内で入力して下さい")
	private String password;

	public RegisterForm() {
	}

	public RegisterForm(String name, String mailAddress, String password) {
		super();
		this.name = name;
		this.mailAddress = mailAddress;
		this.password = password;
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

}
