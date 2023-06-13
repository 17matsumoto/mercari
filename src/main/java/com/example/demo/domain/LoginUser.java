package com.example.demo.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class LoginUser extends User {

	private static final long serialVersionUID = 1L;

	private final UserInfo userInfo;

	/**
	 * 通常のユーザ情報に加え、認可用ロールを設定する.
	 * 
	 * @param user          ユーザ情報(名前)
	 * @param authorityList 権限情報が入ったリスト
	 */
	public LoginUser(UserInfo userInfo, Collection<GrantedAuthority> authorityList) {
		super(userInfo.getMailAddress(), userInfo.getPassword(), authorityList);
		this.userInfo = userInfo;
	}

	/**
	 * ユーザ情報を返します.
	 * 
	 * @return ユーザ情報
	 */
	public UserInfo getUserInfo() {
		return userInfo;
	}

}
