package com.example.demo.servise;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.domain.UserInfo;
import com.example.demo.repository.UserRepository;

import jakarta.transaction.Transactional;

/**
 * ユーザー関連サービス
 * 
 * @author matsumotoyuyya
 *
 */
@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * ユーザー情報をインサートします.
	 * 
	 * @param user ユーザー
	 */
	public void insert(UserInfo user) {
		String encodePassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodePassword);
		userRepository.insert(user);
	}

	/**
	 * メールアドレスから管理者情報を取得します.
	 * @param mailAddress
	 * @return
	 */
	public Optional<UserInfo> findByMail(String mailAddress) {
		Optional<UserInfo> user = userRepository.findByMail(mailAddress);
		return user;
	}

}
