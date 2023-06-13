package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.BigCategory;

@Repository
public class BigCategoryRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	/**
	 * BigCategoryオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<BigCategory> BIGCATEGORY_ROW_MAPPER = new BeanPropertyRowMapper<>(BigCategory.class);

	/**
	 * 全件検索を行います.
	 * 
	 * @return 商品情報リスト
	 */
	public List<BigCategory> findByBigCategoryAll() {
		String sql = "SELECT id,name FROM big_category order by name;";
		List<BigCategory> bigCategoryList = template.query(sql, BIGCATEGORY_ROW_MAPPER);
		return bigCategoryList;
	}

	/**
	 * 大カテゴリー名前検索.
	 * @param bigCategoryName 大カテゴリ−名
	 * @return 検索結果
	 */
	public Optional<List<BigCategory>> findByBigCategoryName(String bigCategoryName) {
		String sql = "select id , name from big_category where name = :bigCategoryName;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("bigCategoryName", bigCategoryName);
		List<BigCategory> bigCategoryNameList = template.query(sql, param, BIGCATEGORY_ROW_MAPPER);
		if (bigCategoryNameList.isEmpty()) {
			return Optional.empty();
		}

		return Optional.of(bigCategoryNameList);
	}
	
	public BigCategory load(Integer id) {
	 String sql = "select id , name from big_category where id = :id ;";
	 SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
	 BigCategory bigCategory = template.queryForObject(sql, param, BIGCATEGORY_ROW_MAPPER);
	 return bigCategory;
	}
}
