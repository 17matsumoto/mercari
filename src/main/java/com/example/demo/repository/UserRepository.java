package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.UserInfo;

/**
 * ユーザー関連リポジトリー.
 * 
 * @author matsumotoyuyya
 *
 */
@Repository
public class UserRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * Userオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<UserInfo> USER_ROW_MAPPER = new BeanPropertyRowMapper<>(UserInfo.class);

	public void insert(UserInfo user) {
		String sql = "insert into users (name , mail_address , password , authority) values(:name, :mailAddress, :password, :authority); ";
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		template.update(sql, param);
	}

	/**
	 * メールアドレスが存在するかを検索します.
	 * 
	 * @param mailAddress メールアドレス
	 * @return ユーザー
	 */
	public Optional<UserInfo> findByMail(String mailAddress) {
		String sql = "select id, name, mail_address, password ,authority from users where mail_address = :mailAddress";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", mailAddress);
		List<UserInfo> userList = template.query(sql, param, USER_ROW_MAPPER);

		if (userList.isEmpty()) {
			return Optional.empty();
		}

		return Optional.of(userList.get(0));
	}

}
