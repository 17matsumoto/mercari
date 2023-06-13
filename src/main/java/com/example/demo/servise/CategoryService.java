package com.example.demo.servise;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.BigCategory;
import com.example.demo.domain.Item;
import com.example.demo.domain.MediamCategory;
import com.example.demo.domain.SmalCategory;
import com.example.demo.repository.BigCategoryRepository;
import com.example.demo.repository.ItemCategoryRepository;
import com.example.demo.repository.MediamCategoryRepository;
import com.example.demo.repository.SmalCategoryRepository;

import jakarta.transaction.Transactional;

/**
 * カテゴリー関連サービス
 * 
 * @author matsumotoyuyya
 *
 */
@Service
@Transactional
public class CategoryService {
	
	@Autowired
	private BigCategoryRepository bigCategoryRepository;

	@Autowired
	private MediamCategoryRepository mediamCategoryRepository;

	
	@Autowired
	private SmalCategoryRepository smalCategoryRepository;
	
	@Autowired
	private ItemCategoryRepository itemCategoryRepository;

	
	
	/**
	 * プルダウンの中カテゴリーを検索します.
	 * @param bigCategory 大カテゴリー　
	 * @return
	 */
	public List<MediamCategory> findByMediamCategory(BigCategory bigCategory) {
		List<MediamCategory> mediamCategoryList = mediamCategoryRepository.findByMediamCategoryId(bigCategory.getId());
		return mediamCategoryList;
	}
	
	/**
	 * 大カテゴリーの商品数を検索します.
	 * @param bigCategory 大カテゴリー　
	 * @return
	 */
	public List<Item> getBigCategoryCount(BigCategory bigCategory) {
		List<Item> medCount = itemCategoryRepository.getBigCategoryCount(bigCategory.getId());
		return medCount;
	}
	
	/**
	 * プルダウンの中カテゴリーを検索します.
	 * @param bigCategory 大カテゴリー　
	 * @return
	 */
	public List<Item> getMedCategoryCount(BigCategory bigCategory) {
		List<Item> medCount = itemCategoryRepository.getMedCategoryCount(bigCategory.getId());
		return medCount;
	}
	
	/**
	 * 小カテゴリーを検索します.
	 * 
	 * @param mediamCategoryId 中カテゴリー
	 * @return 小カテゴリリスト
	 */
	public List<SmalCategory> findBySmalCategory(MediamCategory mediamCategory) {
		List<SmalCategory> smalCategoryList = smalCategoryRepository.findBySmalCategoryId(mediamCategory.getId());
		return smalCategoryList;
	}
	
	/**
	 * プルダウンの中カテゴリーを検索します.
	 * @param bigCategory 大カテゴリー　
	 * @return
	 */
	public List<Item> getSmalCategoryCount(MediamCategory mediamCategory) {
		List<Item> smalCount = itemCategoryRepository.getsmalCategoryCount(mediamCategory.getId());
		return smalCount;
	}

	
	/**
	 * 大カテゴリー主キー検索.
	 * @param bigCategoryId
	 * @return
	 */
	public BigCategory findByBigCategory(Integer bigCategoryId) {
		BigCategory  bigCategory = bigCategoryRepository.load(bigCategoryId);
		return bigCategory;
	}
	
	
	/**
	 * 中カテゴリー主キー検索.
	 * @param mediamCateogryId
	 * @return
	 */
	public MediamCategory findByMediamCategory(Integer mediamCateogryId) {
	MediamCategory mediamCategory = mediamCategoryRepository.load(mediamCateogryId);
	return mediamCategory;
		
	}
	
}
