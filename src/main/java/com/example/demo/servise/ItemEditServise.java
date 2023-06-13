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
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.MediamCategoryRepository;
import com.example.demo.repository.SmalCategoryRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ItemEditServise {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private BigCategoryRepository bigCategoryRepository;

	@Autowired
	private MediamCategoryRepository mediamCategoryRepository;

	@Autowired
	private SmalCategoryRepository smalCategoryRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	public List<BigCategory> findByBigCategoryAll() {
		List<BigCategory> bigCategory = bigCategoryRepository.findByBigCategoryAll();
		return bigCategory;
	}

	public List<MediamCategory> findByMediamCategoryAll(Integer page) {
		List<MediamCategory> MediamCategoryList = mediamCategoryRepository.findByMediamCategoryAll(page);
		return MediamCategoryList;
	}

	// 中カテゴリー更新
	public void updateMediamCategory(String mediamCategoryName, Integer bigCategoryId, Integer mediamCategoryId) {
		mediamCategoryRepository.updateMediamCategory(mediamCategoryName, bigCategoryId, mediamCategoryId);
	}

	// 小カテゴリー更新
	public void updateSmalCategory(String smalCategoryName, Integer mediamCategoryId, Integer smalCategoryId,
			String nameAll) {
		smalCategoryRepository.updateSmalCategory(smalCategoryName, mediamCategoryId, smalCategoryId, nameAll);
	}

	// 商品更新
	public void updateItem(ItemForm itemForm) {
		itemRepository.updateItem(itemForm);
	}

	public List<SmalCategory> findBySmalCategoryAll(Integer page) {
		List<SmalCategory> smalCategoryList = smalCategoryRepository.findBySmalCategoryAll(page);
		return smalCategoryList;
	}

	public List<BigCategory> findByBigCategoryName(String bigCategoryName) {
	Optional<List<BigCategory>>	bigCategory = bigCategoryRepository.findByBigCategoryName(bigCategoryName);
		return bigCategory.get();
	}

//	public MediamCategory findByBigCategoryIdAndName(String mediamCategoryName, Integer bigCategoryId) {
//		MediamCategory mediamCategory = mediamCategoryRepository.findByBigCategoryIdAndName(mediamCategoryName,
//				bigCategoryId);
//		return mediamCategory;
//	}

	public SmalCategory findBySmalCategoryId(Integer smalCategoryId) {
		SmalCategory smalCategory = smalCategoryRepository.load(smalCategoryId);
		return smalCategory;
	}

	public List<SmalCategory> findByCategory(Integer smalCategoryId) {
		List<SmalCategory> category = categoryRepository.findByCategory(smalCategoryId);
		return category;
	}

}
