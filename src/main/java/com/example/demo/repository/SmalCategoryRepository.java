package com.example.demo.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.SmalCategory;

@Repository
public class SmalCategoryRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<SmalCategory> SMALCATEGORY_ROW_MAPPER = new BeanPropertyRowMapper<>(
			SmalCategory.class);

	public SmalCategory load(Integer id) {
		String sql = "select id , name , name_all , mediam_category_id from smal_category where id =:id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		SmalCategory smalCategory = template.queryForObject(sql, param, SMALCATEGORY_ROW_MAPPER);
		return smalCategory;
	}

	/**
	 * 小カテゴリー全件検索
	 * @param page
	 * @return
	 */
	public List<SmalCategory> findBySmalCategoryAll(Integer page) {
		String sql = "SELECT id, name FROM smal_category OFFSET (:page * 30) limit 30;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("page", page);

		List<SmalCategory> smalCategoryList = template.query(sql, param, SMALCATEGORY_ROW_MAPPER);
		return smalCategoryList;
	}

	/**
	 * 小カテゴリー名前id検索.
	 * 
	 * @param bigCategoryName 大カテゴリ−名
	 * @return 検索結果
	 */
	public List<SmalCategory> findBySmalCategoryId(Integer mediamCategoryId) {
		String sql = "select id , name , mediam_category_id from smal_category where mediam_category_id = :mediamCategoryId order by name;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mediamCategoryId", mediamCategoryId);
		List<SmalCategory> smalCategoryNameList = template.query(sql, param, SMALCATEGORY_ROW_MAPPER);

		return smalCategoryNameList;
	}

	/**
	 * 小カテゴリーnameAllで検索.
	 * 
	 * @param nameAll
	 * @return 検索結果
	 */
	public SmalCategory findByNameAll(String nameAll) {
		String sql = "select id , name, mediam_category_id , name_all from smal_category where name_all = :nameAll;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("nameAll", nameAll);
		SmalCategory smalCategory = template.queryForObject(sql, param, SMALCATEGORY_ROW_MAPPER);

		return smalCategory;
	}

	/**
	 * 小カテゴリーを更新します.
	 * 
	 * @param employee 従業員情報
	 */
	public void updateSmalCategory(String smalCategoryName, Integer mediamCategoryId, Integer smalCategoryId,
			String nameAll) {

		String updateSql = "UPDATE smal_category SET name=:smalCategoryName , mediam_category_id =:mediamCategoryId, name_all =:nameAll  WHERE id=:smalCategoryId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("smalCategoryName", smalCategoryName)
				.addValue("mediamCategoryId", mediamCategoryId).addValue("nameAll", nameAll)
				.addValue("smalCategoryId", smalCategoryId);
		template.update(updateSql, param);
	}

	synchronized public SmalCategory insert(SmalCategory smalCategory) {

		SqlParameterSource param = new BeanPropertySqlParameterSource(smalCategory);
		// インサート処理
		if (smalCategory.getId() == null) {
			String insertOrder = "INSERT INTO smal_category(name , mediam_category_id , name_all ) "
					+ " VALUES(:name,:mediamCategoryId,:nameAll);";
			KeyHolder keyHolder = new GeneratedKeyHolder();
			String[] keyColumnNames = { "id" };
			template.update(insertOrder, param, keyHolder, keyColumnNames);
			smalCategory.setId(keyHolder.getKey().intValue());
			System.out.println(keyHolder.getKey() + "が割り当てられました");
		}
		return smalCategory;
	}

}
