package com.example.demo.servise;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.BigCategory;
import com.example.demo.domain.MediamCategory;
import com.example.demo.domain.SmalCategory;
import com.example.demo.form.ItemForm;
import com.example.demo.repository.BigCategoryRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.MediamCategoryRepository;
import com.example.demo.repository.SmalCategoryRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ItemAddServise {

	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private BigCategoryRepository bigCategoryRepository;
	
	@Autowired
	private MediamCategoryRepository mediamCategoryRepository;
	
	@Autowired
	private SmalCategoryRepository smalCategoryRepository;
	
	/**
	 * 商品を登録します.
	 * @param itemform
	 */
	public void insertItem(ItemForm itemform ) {

		 itemRepository.insert(itemform); 
	}
	
	
	
	/**
	 * 大カテゴリーを検索します.
	 * @param bigCategoryName
	 * @return
	 */
	public List<BigCategory>findByBigCategory(String bigCategoryName){
		Optional<List<BigCategory>> bigCategory = bigCategoryRepository.findByBigCategoryName(bigCategoryName);
	
		return bigCategory.get();
	}
	
//	/**
//	 * 中カテゴリーを検索します.
//	 * @param mediamCategoryName
//	 * @param bigCategoryId
//	 * @return
//	 */
//	public MediamCategory findByMediamCategory(String mediamCategoryName , Integer bigCategoryId) {
//		MediamCategory mediamCategory = mediamCategoryRepository.findByBigCategoryIdAndName(mediamCategoryName, bigCategoryId);
//		return mediamCategory;
//	}
	
	/**
	 * 中カテゴリーを登録します.
	 * @param MediamCategoryName
	 * @param bigCategoryId
	 * @return
	 */
	public MediamCategory insertMediamCategory(String MediamCategoryName, Integer bigCategoryId) {
		MediamCategory mediamCategory = new MediamCategory();
		mediamCategory.setName(MediamCategoryName);
		mediamCategory.setBigCategoryId(bigCategoryId);
		
		MediamCategory getInsertMediamCategory = mediamCategoryRepository.insert(mediamCategory);
		return getInsertMediamCategory;
	}

//	/**
//	 * 小カテゴリーを検索します.
//	 * @param mediamCategoryName
//	 * @param bigCategoryId
//	 * @return
//	 */
//	public SmalCategory findBySmalCategory(String smalCategoryName , Integer mediamCategoryId) {
//		SmalCategory smalCategory = smalCategoryRepository.findByMediamCategoryIdAndName(smalCategoryName, mediamCategoryId);
//		return smalCategory;
//	}
	
	public SmalCategory insertsmalCategory(String smalCategoryName, Integer mediamCategoryId , String nameAll) {
		SmalCategory smalCategory = new SmalCategory();
		smalCategory.setName(smalCategoryName);
		smalCategory.setMediamCategoryId(mediamCategoryId);
		smalCategory.setNameAll(nameAll);
		
		SmalCategory getInsertSmalCategory = smalCategoryRepository.insert(smalCategory);
		return getInsertSmalCategory;
	}
	
}
