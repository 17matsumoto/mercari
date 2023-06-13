package com.example.demo.repository;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Item;

@Repository
public class ItemCategoryRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * Itemオブジェクトを生成するローマッパー.
	 */
	private static final ResultSetExtractor<List<Item>> ITEM_RESULT_SET_EXTRACTOR = (rs) -> {
		//
		List<Item> itemList = new LinkedList<Item>();

		while (rs.next()) {

			Item item = new Item();
			item.setCount(rs.getInt("count"));

			itemList.add(item);
		}

		return itemList;
	};
	/**
	 * Itemオブジェクトを生成するローマッパー.
	 */
	private static final ResultSetExtractor<List<Item>> ITEMCOUNT_RESULT_SET_EXTRACTOR = (rs) -> {
		//
		List<Item> itemList = new LinkedList<Item>();

		while (rs.next()) {

			Item item = new Item();
			item.setId(rs.getInt("id"));
			item.setName(rs.getString("name"));
			item.setCount(rs.getInt("count"));

			itemList.add(item);
		}

		return itemList;
	};

	/**
	 * 大カテゴリーの商品数を検索.
	 * 
	 * @return
	 */
	public List<Item> getBigCategoryCount() throws DataAccessException {
		try {
			String sql = "select i.count as count from(select count(b.name)  as count from items i left join smal_category s on i.smal_category_id = s.id left join mediam_category m on m.id = s.mediam_category_id left join big_category b on m.big_category_id = b.id group by b.name) as i ;";
			List<Item> ItemCount = template.query(sql, ITEM_RESULT_SET_EXTRACTOR);
			return ItemCount;
		} catch (DataAccessException e) {
			throw e;
		}

	}

	/**
	 * 大カテゴリーの商品数を検索します
	 * 
	 * @param bigCategoryId
	 * @return
	 */
	public List<Item> getBigCategoryCount(Integer bigCategoryId) throws DataAccessException {
		try {
			String sql = "select i.count as count from(select count(b.name)  as count from items i left join smal_category s on i.smal_category_id = s.id left join mediam_category m on m.id = s.mediam_category_id left join big_category b on m.big_category_id = b.id where b.id = :bigCategoryId group by b.name) as i ;";
			SqlParameterSource param = new MapSqlParameterSource().addValue("bigCategoryId", bigCategoryId);
			List<Item> getMedCount = template.query(sql, param, ITEM_RESULT_SET_EXTRACTOR);

			return getMedCount;
		} catch (DataAccessException e) {
			throw e;
		}
	}

	/**
	 * 中カテゴリーの商品数を検索します
	 * 
	 * @param bigCategoryId
	 * @return
	 */
	public List<Item> getMedCategoryCount(Integer bigCategoryId) throws DataAccessException {
		try {

			String sql = "select i.count as count , i.name as name , i.id as id from(select count(m.id)  as count, m.name as name , m.id as id from items i left join smal_category s on i.smal_category_id = s.id left join mediam_category m on m.id = s.mediam_category_id left join big_category b on m.big_category_id = b.id  where big_category_id = :bigCategoryId group by m.id , m.name order by m.name) as i  ;";
			SqlParameterSource param = new MapSqlParameterSource().addValue("bigCategoryId", bigCategoryId);
			List<Item> getMedCount = template.query(sql, param, ITEMCOUNT_RESULT_SET_EXTRACTOR);

			return getMedCount;

		} catch (DataAccessException e) {
			throw e;
		}
	}

	/**
	 * 小カテゴリーの商品数を検索します
	 * 
	 * @param bigCategoryId
	 * @return
	 */
	public List<Item> getsmalCategoryCount(Integer medimaCategoryId)throws DataAccessException {
		try {
		String sql = "select i.count as count , i.name as name , i.id as id " + "from(select count(s.id)  as count , s.name as name , s.id as id "
				+ "from items i left join smal_category s on i.smal_category_id = s.id left join mediam_category m on m.id = s.mediam_category_id left join big_category b on m.big_category_id = b.id  "
				+ "where mediam_category_id =:medimaCategoryId  group by s.id, s.name order by s.name) as i;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("medimaCategoryId", medimaCategoryId);
		List<Item> getMedCount = template.query(sql, param, ITEMCOUNT_RESULT_SET_EXTRACTOR);

		return getMedCount;
		}catch(DataAccessException e) {
			throw e;
		}

	}

}