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

import com.example.demo.domain.MediamCategory;

@Repository
public class MediamCategoryRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<MediamCategory> MEDIAMCATEGORY_ROW_MAPPER = new BeanPropertyRowMapper<>(
			MediamCategory.class);

	public MediamCategory load(Integer id) {
		String sql = "select id , name , big_category_id from mediam_category where id = :id ;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		MediamCategory mediamCategory = template.queryForObject(sql, param, MEDIAMCATEGORY_ROW_MAPPER);
		return mediamCategory;
	}

	public List<MediamCategory> findByMediamCategoryAll(Integer page) {
		System.out.println(page + "ページ数");
		String sql = "SELECT id , name FROM mediam_category  OFFSET (:page * 30) limit 30;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("page", page - 1);
		List<MediamCategory> mediamCategoryList = template.query(sql,param, MEDIAMCATEGORY_ROW_MAPPER);
		return mediamCategoryList;
	}

	/**
	 * 中カテゴリー名前id検索.
	 * 
	 * @param bigCategoryName 大カテゴリ−名
	 * @return 検索結果
	 */
	public List<MediamCategory> findByMediamCategoryId(Integer bigCategoryId) {
		System.out.println(bigCategoryId + "ここは");
		String sql = "select id , name , big_category_id from mediam_category where  big_category_id = :bigCategoryId order by name ;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("bigCategoryId", bigCategoryId);
		List<MediamCategory> mediamCategoryNameList = template.query(sql, param, MEDIAMCATEGORY_ROW_MAPPER);
		if (mediamCategoryNameList.size() == 0) {
			return null;
		}

		return mediamCategoryNameList;
	}

	/**
	 * 中カテゴリー情報を更新します.
	 * 
	 * @param mediamCategoryName
	 * @param bigCategoryId
	 * @param mediamCategoryId
	 */
	public void updateMediamCategory(String mediamCategoryName, Integer bigCategoryId, Integer mediamCategoryId) {

		String updateSql = "UPDATE mediam_category SET name=:mediamCategoryName , big_category_id =:bigCategoryId  WHERE id=:mediamCategoryId";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mediamCategoryName", mediamCategoryName)
				.addValue("bigCategoryId", bigCategoryId).addValue("mediamCategoryId", mediamCategoryId);
		template.update(updateSql, param);
	}

	/**
	 * 中カテゴリーを登録します.
	 * 
	 * @param mediamCategory
	 * @return
	 */
	synchronized public MediamCategory insert(MediamCategory mediamCategory) {

		SqlParameterSource param = new BeanPropertySqlParameterSource(mediamCategory);
		// インサート処理
		if (mediamCategory.getId() == null) {
			String insertOrder = "INSERT INTO mediam_category(name , big_category_id ) "
					+ " VALUES(:name,:bigCategoryId);";
			KeyHolder keyHolder = new GeneratedKeyHolder();
			String[] keyColumnNames = { "id" };
			template.update(insertOrder, param, keyHolder, keyColumnNames);
			mediamCategory.setId(keyHolder.getKey().intValue());
			System.out.println(keyHolder.getKey() + "が割り当てられました");
		}
		return mediamCategory;
	}

}
