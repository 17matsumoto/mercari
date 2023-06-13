package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Original;

@Repository
public class OriginalRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * Originalオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<Original> ORIGINAL_ROW_MAPPER = new BeanPropertyRowMapper<>(Original.class);

	/**
	 * 全件検索を行います.
	 * 
	 * @return 商品情報リスト
	 */
	public List<Original> findAll() {
		String sql = "SELECT id,name,condition_id,category_name,brand,price,shipping,description FROM original order by price desc;";
		List<Original> itemList = template.query(sql, ORIGINAL_ROW_MAPPER);
		return itemList;
	}

}
