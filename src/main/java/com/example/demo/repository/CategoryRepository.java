package com.example.demo.repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.BigCategory;
import com.example.demo.domain.MediamCategory;
import com.example.demo.domain.SmalCategory;

/**
 * 前カテゴリーを検索するリポジトリー.
 * 
 * @author matsumotoyuyya
 *
 */
@Repository
public class CategoryRepository {

	private static final ResultSetExtractor<List<SmalCategory>> SmalCategory_RESULT_SET_EXTRACTOR = (rs) -> {

		List<SmalCategory> smalCategoryList = new LinkedList<SmalCategory>();
		List<MediamCategory> mediamCategoryList = null;
		List<BigCategory> bigCategoryList = null;

		long beforSmalCategoryId = 0;
		long beforMediamCategoryid = 0;
		int nowSmalCategoryId = 0;
		while (rs.next()) {

			nowSmalCategoryId = rs.getInt("s_id");
			if (nowSmalCategoryId != beforSmalCategoryId) {
				SmalCategory smalCategory = new SmalCategory();
				smalCategory.setId(rs.getInt("s_id"));
				smalCategory.setName(rs.getString("s_name"));
				smalCategory.setMediamCategoryId(rs.getInt("s_mediam_category_id"));
				mediamCategoryList = new ArrayList<MediamCategory>();
				smalCategory.setMediamCategoryList(mediamCategoryList);
				smalCategoryList.add(smalCategory);

			}

			int nowMediamCategoryId = rs.getInt("m_id");

			if (beforMediamCategoryid != nowMediamCategoryId) {
				MediamCategory mediamCategory = new MediamCategory();
				mediamCategory.setId(rs.getInt("m_id"));
				mediamCategory.setName(rs.getString("m_name"));
				mediamCategory.setBigCategoryId(rs.getInt("m_big_category_id"));
				bigCategoryList = new ArrayList<BigCategory>();
				mediamCategory.setBigCategoryList(bigCategoryList);
				mediamCategoryList.add(mediamCategory);

			}

			if (rs.getInt("b_id") != 0) {
				BigCategory bigCategory = new BigCategory();
				bigCategory.setId(rs.getInt("b_id"));
				bigCategory.setName(rs.getString("b_name"));
				bigCategoryList.add(bigCategory);

			}

			// 現在のIDに変更
			beforSmalCategoryId = nowSmalCategoryId;
			beforMediamCategoryid = nowMediamCategoryId;

		}

		return smalCategoryList;

	};

	private static final ResultSetExtractor<List<MediamCategory>> MediamCategory_RESULT_SET_EXTRACTOR = (rs) -> {

		List<MediamCategory> mediamCategoryList = new LinkedList<MediamCategory>();
		List<BigCategory> bigCategoryList = null;

		long beforMediamCategoryid = 0;
		int nowMediamCategoryId = 0;
		while (rs.next()) {

			nowMediamCategoryId = rs.getInt("m_id");
			if (nowMediamCategoryId != beforMediamCategoryid) {
				MediamCategory mediamCategory = new MediamCategory();
				mediamCategory.setId(rs.getInt("m_id"));
				mediamCategory.setName(rs.getString("m_name"));
				mediamCategory.setBigCategoryId(rs.getInt("m_big_category_id"));
				bigCategoryList = new ArrayList<BigCategory>();
				mediamCategory.setBigCategoryList(bigCategoryList);
				mediamCategoryList.add(mediamCategory);
			}

			if (rs.getInt("b_id") != 0) {
				BigCategory bigCategory = new BigCategory();
				bigCategory.setId(rs.getInt("b_id"));
				bigCategory.setName(rs.getString("b_name"));
				bigCategoryList.add(bigCategory);

			}

			// 現在のIDに変更
			beforMediamCategoryid = nowMediamCategoryId;
			beforMediamCategoryid = nowMediamCategoryId;

		}

		return mediamCategoryList;

	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 中カテゴリーを検索する.
	 * 
	 * @param bigCategoryId 大カテゴリー
	 * @return 中カテゴリーリスト
	 */
	public List<MediamCategory> findByMediamCategory(Integer id) {
		String sql = 
				"select b.id b_id , b.name b_name,  m.id m_id, m.name m_name ,  m.big_category_id m_big_category_id\n"
						+ "from  big_category b\n" + "left join mediam_category m\n" + "on b.id = m.big_category_id\n"
						+ "where b.id = :id ;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);

		List<MediamCategory> mediamCategoryList = template.query(sql, param,MediamCategory_RESULT_SET_EXTRACTOR);
		return mediamCategoryList;
	}

	/**
	 * 小カテゴリーを検索する.
	 * 
	 * @param mediamCategoryId 中カテゴリー
	 * @return 小カテゴリーリスト
	 */
	public List<SmalCategory> findBySmalCategory(Integer mediamCategoryId) {
		String sql = String.format(
				"select s.id s_id , s.name s_name ,s.mediam_category_id s_mediam_category_id , m.id m_id , m.name m_name ,m.big_category_id m_big_category_id , b.id b_id , b.name b_name\n"
						+ "from mediam_category m \n" + "left join smal_category s\n"
						+ "on m.id = s.mediam_category_id\n" + "left join big_category b \n"
						+ "on b.id = m.big_category_id\n" + "where m.id = %d;",
				mediamCategoryId);
		List<SmalCategory> mediamCategoryList = template.query(sql, SmalCategory_RESULT_SET_EXTRACTOR);
		return mediamCategoryList;
	}

	/**
	 * 全カテゴリー名を検索する.
	 * 
	 * @param mediamCategoryId 中カテゴリー
	 * @return 小カテゴリーリスト
	 */
	public List<SmalCategory> findByCategory(Integer smalCategoryId) {
		String sql = String.format(
				"select s.id s_id , s.name s_name ,s.mediam_category_id s_mediam_category_id , m.id m_id , m.name m_name ,m.big_category_id m_big_category_id , b.id b_id , b.name b_name\n"
						+ "from mediam_category m \n" + "left join smal_category s\n"
						+ "on m.id = s.mediam_category_id\n" + "left join big_category b \n"
						+ "on b.id = m.big_category_id\n" + "where s.id = %d;",
				smalCategoryId);
		List<SmalCategory> smalCategoryList = template.query(sql, SmalCategory_RESULT_SET_EXTRACTOR);
		return smalCategoryList;
	}

	/**
	 * 全カテゴリーを名前検索する.
	 * 
	 * @param smalCategoryName  小カテゴリー
	 * @param mediamCategorName 中カテゴリー
	 * @param bigCategoryName   大カテゴリー
	 * @return 検索結果リスト
	 */
	public List<SmalCategory> findByCategoryName(String smalCategoryName, String mediamCategorName,
			String bigCategoryName) {
		String sql = String.format(
				"select s.id s_id , s.name s_name ,s.mediam_category_id s_mediam_category_id , m.id m_id , m.name m_name ,m.big_category_id m_big_category_id , b.id b_id , b.name b_name\n"
						+ "from mediam_category m \n" + "left join smal_category s\n"
						+ "on m.id = s.mediam_category_id\n" + "left join big_category b \n"
						+ "on b.id = m.big_category_id\n"
						+ "where s.name = :smalCategoryName and m.name = :mediamCategorName and b.name = :bigCategoryName;",
				smalCategoryName, mediamCategorName, bigCategoryName);
		SqlParameterSource param = new MapSqlParameterSource().addValue("smalCategoryName", smalCategoryName)
				.addValue("mediamCategorName", mediamCategorName).addValue("bigCategoryName", bigCategoryName);
		List<SmalCategory> allCategoryList = template.query(sql, param, SmalCategory_RESULT_SET_EXTRACTOR);
		if (allCategoryList.size() == 0) {
			return null;
		}
		return allCategoryList;
	}

}