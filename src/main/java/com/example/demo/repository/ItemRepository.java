package com.example.demo.repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.Item;
import com.example.demo.domain.SmalCategory;
import com.example.demo.form.ItemForm;

/**
 * アイテム関連リポジトリー.
 * 
 * @author matsumotoyuyya
 *
 */
@Repository
public class ItemRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * Itemオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<Item> ITEM_ROW_MAPPER = new BeanPropertyRowMapper<>(Item.class);

	/**
	 * Itemオブジェクトを生成するローマッパー.
	 */
	private static final ResultSetExtractor<List<Item>> ITEM_RESULT_SET_EXTRACTOR = (rs) -> {
		// 記事一覧が入るarticleListを生成
		List<Item> itemList = new LinkedList<Item>();
		List<SmalCategory> smalCategoryList = null;

		// 前の行の記事IDを退避しておく変数
		long beforeItemId = 0;

		while (rs.next()) {
			// 現在検索されている記事IDを退避
			int nowItemId = rs.getInt("id");

			// 現在の記事IDと前の記事IDが違う場合はArticleオブジェクトを生成
			if (nowItemId != beforeItemId) {
				Item item = new Item();
				item.setId(nowItemId);
				item.setName(rs.getString("name"));
				item.setCondition(rs.getInt("condition"));
				item.setSmalCategoryId(rs.getInt("smal_category_id"));
				item.setBrand(rs.getString("brand"));
				item.setPrice(rs.getDouble("price"));
				item.setShipping(rs.getInt("shipping"));
				item.setDescription(rs.getString("description"));
				item.setNameAll(rs.getString("s_name_all"));

				// 空のコメントリストを作成しArticleオブジェクトにセットしておく
				smalCategoryList = new ArrayList<SmalCategory>();
				item.setSmalCategoryList(smalCategoryList);
				// コメントがセットされていない状態のArticleオブジェクトをarticleListオブジェクトにadd
				itemList.add(item);
			}

			// 記事だけあってコメントがない場合はCommentオブジェクトは作らない
			if (rs.getInt("s_id") != 0) {
				SmalCategory smalCategory = new SmalCategory();
				smalCategory.setId(rs.getInt("s_id"));
				smalCategory.setName(rs.getString("s_name"));
				smalCategory.setMediamCategoryId(rs.getInt("s_mediam_category_id"));
				// コメントをarticleオブジェクト内にセットされているcommentListに直接addしている(参照型なのでこのようなことができる)
				smalCategoryList.add(smalCategory);
			}

			// 現在の記事IDを前の記事IDを入れる退避IDに格納
			beforeItemId = nowItemId;
		}
		return itemList;
	};

	/**
	 * Item総数を検索する.
	 * 
	 * @return Item数
	 */
	public List<Item> getCount(String brand) throws DataAccessException {
		try {
			if (brand == null) {
				String sql = "select count(id) from items;";
				List<Item> getCount = template.query(sql, ITEM_ROW_MAPPER);
				return getCount;
			} else {
				String sql = "select count(id) from items where brand =:brand;";
				SqlParameterSource param = new MapSqlParameterSource().addValue("brand", brand);
				List<Item> getCount = template.query(sql, param, ITEM_ROW_MAPPER);
				return getCount;
			}
		} catch (DataAccessException e) {
			throw e;
		}
	}

	
	/**
	 * 全件検索を行います.
	 * 
	 * @return 商品情報リスト
	 */
	public List<Item> findAll()  {
			String sql = 
						"SELECT  i.id,i.name, i.condition, i. smal_category_id, i.brand, i.price, i.shipping, i.description ,s.id s_id , s.name  s_name, s.mediam_category_id s_mediam_category_id,s.name_all s_name_all \n"
								+ "from items \n" + "as i left join smal_category s \n"
								+ "on i.smal_category_id = s.id order by i.id  ;";

				List<Item> itemList = template.query(sql, ITEM_RESULT_SET_EXTRACTOR);
				return itemList;
	}
	/**
	 * 全件検索を行います.
	 * 
	 * @return 商品情報リスト
	 */
	public List<Item> findAllPage(int page, String brand) throws DataAccessException {
		try {
			if (brand == null) {
				String sql = String.format(
						"SELECT  i.id,i.name, i.condition, i. smal_category_id, i.brand, i.price, i.shipping, i.description ,s.id s_id , s.name  s_name, s.mediam_category_id s_mediam_category_id,s.name_all s_name_all \n"
								+ "from items \n" + "as i left join smal_category s \n"
								+ "on i.smal_category_id = s.id order by i.name limit 30 OFFSET (%d * 30) ;",
						page - 1);

				List<Item> itemList = template.query(sql, ITEM_RESULT_SET_EXTRACTOR);
				return itemList;
			} else {
				String sql = "SELECT  i.id, i.name, i.condition, i. smal_category_id, i.brand, i.price, i.shipping, i.description ,s.id s_id , s.name  s_name, s.mediam_category_id s_mediam_category_id,s.name_all s_name_all \n"
						+ "from items \n" + "as i left join smal_category s \n"
						+ "on i.smal_category_id = s.id where i.brand = :brand order by i.name  OFFSET (:page * 30) limit 30;";
				SqlParameterSource param = new MapSqlParameterSource().addValue("page", page - 1).addValue("brand",
						brand);

				List<Item> itemList = template.query(sql, param, ITEM_RESULT_SET_EXTRACTOR);
				return itemList;
			}
		} catch (DataAccessException e) {
			// TODO: handle exception
			System.out.println("DB接続に失敗しました");
			throw e;
		}
	}

	/**
	 * 検索ボタンが押された時の曖昧検索.
	 *
	 * @param name    商品名
	 * @param brand   商品ブランド
	 * @param nameAll カテゴリー名
	 * @param page    ページ数
	 * @return Optionalで検索結果を返す
	 */
	public Optional<List<Item>> findByNameAndBrandLike(String name, String brand, String nameAll, Integer page)
			throws DataAccessException {
		try {
			String sql = "SELECT i.id, i.name, i.condition, i.smal_category_id, i.brand, i.price, i.shipping, i.description ,\n"
					+ "s.id s_id , s.name s_name, s.mediam_category_id s_mediam_category_id, s.name_all s_name_all \n"
					+ "FROM items\n"
					+ "AS i LEFT JOIN smal_category s ON i.smal_category_id = s.id  WHERE i.name LIKE :name OR i.brand LIKE :brand OR s.name_all LIKE :nameAll ORDER BY i.name OFFSET (:page * 30) LIMIT 30;";
			SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%")
					.addValue("brand", brand != null ? "%" + brand + "%" : null).addValue("nameAll", nameAll + "%")
					.addValue("page", page - 1);

			List<Item> itemList = template.query(sql, param, ITEM_RESULT_SET_EXTRACTOR);
			if (itemList.isEmpty()) {
				return Optional.empty();
			}
			return Optional.of(itemList);
		} catch (DataAccessException e) {
			throw e;
			// TODO: handle exception
		}
	}

	/**
	 * 検索時の総数を検索します.
	 * 
	 * @param name    商品名
	 * @param brand   ブランド
	 * @param nameAll カテゴリー
	 * @param page    ページ数
	 * @return 商品数
	 */
	public Optional<List<Item>> getCount(String name, String brand, String nameAll) throws DataAccessException {
		try {
			String sql = "SELECT i.id, i.name, i.condition, i. smal_category_id, i.brand, i.price, i.shipping, i.description ,\n"
					+ "s.id s_id , s.name  s_name, s.mediam_category_id s_mediam_category_id,s.name_all s_name_all \n"
					+ "from items\n"
					+ "as i left join smal_category s  on i.smal_category_id = s.id  where i.name like :name or i.brand like :brand or s.name_all like :nameAll order by i.name;";
			// + "where s.name_all like :nameAll;";
			SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%")
					.addValue("brand", "%" + brand + "%").addValue("nameAll", nameAll + "%");

			List<Item> itemList = template.query(sql, param, ITEM_RESULT_SET_EXTRACTOR);
			if (itemList.isEmpty()) {
				return Optional.empty();
			}
			return Optional.of(itemList);
		} catch (DataAccessException e) {
			throw e;
			// TODO: handle exception
		}
	}

	/**
	 * 主キー検索を行います.
	 * 
	 * @param ItemId 検索したい主キーの値
	 * @return 検索された商品情報
	 */
	public Optional<Item> load(Integer ItemId) throws DataAccessException {
		try {
			String sql = "SELECT i.id, i.name, i.condition, i. smal_category_id, i.brand, i.price, i.shipping, i.description ,s.id s_id , s.name  s_name, s.mediam_category_id s_mediam_category_id,s.name_all s_name_all \n"
					+ " FROM items i  left join smal_category s  on i.smal_category_id = s.id \n"
					+ " where i.id = :ItemId ;";

			SqlParameterSource param = new MapSqlParameterSource().addValue("ItemId", ItemId);
			List<Item> item = template.query(sql, param, ITEM_RESULT_SET_EXTRACTOR);
			if (item.isEmpty()) {
				return Optional.empty();
			}

			return Optional.of(item.get(0));

		} catch (DataAccessException e) {
			throw e;
			// TODO: handle exception
		}
	}

	/**
	 * 商品を更新します.
	 * 
	 * @param employee 従業員情報
	 */
	public void updateItem(ItemForm itemForm) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(itemForm);

		String updateSql = "UPDATE items SET name=:name , price =:price , brand = :brand , condition =:condition,smal_category_id =:smalCategoryId ,description =:description  WHERE id=:id";
		template.update(updateSql, param);
	}

	/**
	 * 商品を登録します.
	 * 
	 * @param 商品フォーム
	 */
	public void insert(ItemForm itemForm) {

		SqlParameterSource param = new BeanPropertySqlParameterSource(itemForm);
		// インサート処理
		String insertOrder = "INSERT INTO items( name, smal_category_id , price, brand,condition,description) "
				+ " VALUES(:name,:smalCategoryId,:price,:brand,:condition,:description);";
		template.update(insertOrder, param);
	}

}
