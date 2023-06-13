package com.example.demo.servise;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.BigCategory;
import com.example.demo.domain.Item;
import com.example.demo.domain.MediamCategory;
import com.example.demo.domain.SmalCategory;
import com.example.demo.form.ItemForm;
import com.example.demo.repository.BigCategoryRepository;
import com.example.demo.repository.ItemCategoryRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.MediamCategoryRepository;
import com.example.demo.repository.SmalCategoryRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ShowItemServise {
	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private BigCategoryRepository bigCategoryRepository;

	@Autowired
	private MediamCategoryRepository mediamCategoryRepository;

	@Autowired
	private SmalCategoryRepository smalCategoryRepository;

	@Autowired
	private ItemCategoryRepository itemCategoryRepository;

	/**
	 * 商品全件検索.
	 * 
	 * @param page  ページ数
	 * @param brand ブランド
	 * @return 商品一覧
	 */
	public List<Item> findAll(Integer page, String brand) {
		List<Item> itemList = itemRepository.findAllPage(page, brand);
		return itemList;
	}

	/**
	 * Item総数を検索する.
	 * 
	 * @return Item数
	 */
	public List<Item> getItemCount(String brand) {
		List<Item> getCount = itemRepository.getCount(brand);
		return getCount;
	}

	/**
	 * 商品が検索された時の業務処理.
	 * 
	 * @param itemForm フォーム
	 * @param page     ページ数
	 * @param brand    ブランド
	 * @return 商品リスト
	 */
	public Optional<List<Item>> search(ItemForm itemForm, int page) {


		if (itemForm.getName() == null || itemForm.getName() == "") {
			itemForm.setName(null);
		}
		//TODO 空のブランドを検索できない,""をセットすると全てが狂う
		if (itemForm.getBrand() == null || itemForm.getBrand() == "") {
			itemForm.setBrand(null);
		}

		// nameAll,格カテゴリーを初期化
		String nameAll = null;
		String bigCategoryName = null;
		String mediamCategoryName = null;
		String smalCateogryName = null;
		// カテゴリー全入力時
		if (itemForm.getBigCategoryId() != null && itemForm.getMediamCategoryId() != null
				&& itemForm.getSmalCategoryId() != null) {

			BigCategory getBigCategory = bigCategoryRepository.load(itemForm.getBigCategoryId());
			bigCategoryName = getBigCategory.getName() + "/";

			MediamCategory getMediamCategory = mediamCategoryRepository.load(itemForm.getMediamCategoryId());
			mediamCategoryName = getMediamCategory.getName() + "/";

			SmalCategory getSmalCateogry = smalCategoryRepository.load(itemForm.getSmalCategoryId());
			smalCateogryName = getSmalCateogry.getName();

			nameAll = bigCategoryName + mediamCategoryName + smalCateogryName;
		}

		// 小カテゴリーのみnullの時
		if (itemForm.getBigCategoryId() != null && itemForm.getMediamCategoryId() != null
				&& itemForm.getSmalCategoryId() == null) {

			BigCategory getBigCategory = bigCategoryRepository.load(itemForm.getBigCategoryId());
			bigCategoryName = getBigCategory.getName() + "/";

			MediamCategory getMediamCategory = mediamCategoryRepository.load(itemForm.getMediamCategoryId());
			mediamCategoryName = getMediamCategory.getName() + "/";

			nameAll = bigCategoryName + mediamCategoryName;
		}

		// 中小カテゴリーがnullの時
		if (itemForm.getBigCategoryId() != null && itemForm.getMediamCategoryId() == null
				&& itemForm.getSmalCategoryId() == null) {

			BigCategory getBigCategory = bigCategoryRepository.load(itemForm.getBigCategoryId());
			bigCategoryName = getBigCategory.getName() + "/";

			nameAll = bigCategoryName;
		}

		// TODO 検索ボタンが押されなくても呼ばれている 必須修正
		// 検索ボタンが押された時
		Optional<List<Item>> itemListOptional = itemRepository.findByNameAndBrandLike(itemForm.getName(),
				itemForm.getBrand(), nameAll, page);

		if (itemListOptional.isPresent()) {
			List<Item> itemList = itemListOptional.get();

			Optional<List<Item>> getCount = itemRepository.getCount(itemForm.getName(), itemForm.getBrand(), nameAll);
			for (Item item : itemList) {
				item.setCount(getCount.get().size());
			}

			return Optional.of(itemList);
		} else {
			return Optional.empty();
		}
	}

	/**
	 * 大カテゴリーの商品数を検索.
	 * 
	 * @return 商品数
	 */
	public List<Item> getBigCategoryCount() {
		List<Item> item = itemCategoryRepository.getBigCategoryCount();
		return item;
	}

	/**
	 * 中カテゴリーの商品数を検索.
	 * 
	 * @param mediamCategoryId
	 * @return
	 */
	public MediamCategory getMediamCategory(Integer mediamCategoryId) {
		MediamCategory mediamCategory = mediamCategoryRepository.load(mediamCategoryId);
		return mediamCategory;

	}

}
