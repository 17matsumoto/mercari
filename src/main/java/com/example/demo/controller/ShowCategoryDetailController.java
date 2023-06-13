package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.BigCategory;
import com.example.demo.domain.Item;
import com.example.demo.domain.MediamCategory;
import com.example.demo.servise.CategoryService;

/**
 * カテゴリー詳細関連コントローラー.
 * 
 * @author matsumotoyuyya
 *
 */
@Controller
@RequestMapping("/categoryDetail")
public class ShowCategoryDetailController {

	@Autowired
	private CategoryService categoryService;
	
	
	/**
	 * 大カテゴリーの詳細画面に遷移します.
	 * 
	 * @param model         モデル
	 * @param bigCategoryId 大カテゴリーID
	 * @return カテゴリー詳細画面
	 */
	@GetMapping("/showBigDetail")
	public String showBigDetail(Model model, Integer bigCategoryId) {
		Integer count = 0;
		BigCategory bigCategory = categoryService.findByBigCategory(bigCategoryId);
		List<Item> getMedCount = categoryService.getBigCategoryCount(bigCategory);
		for (Item medCount : getMedCount) {
			count = medCount.getCount();
		}
		model.addAttribute("count", count);
		model.addAttribute("bigCategory", bigCategory);
		return "/detail/categoryDetail.html";
	}
	/**
	 * 中カテゴリーの詳細画面に遷移します.
	 * 
	 * @param model         モデル
	 * @param bigCategoryId 中カテゴリーID
	 * @return カテゴリー詳細画面
	 */
	@GetMapping("/showMediamDetail")
	public String showMediamDetail(Model model, Integer mediamCategoryId) {
		MediamCategory mediamCategory = categoryService.findByMediamCategory(mediamCategoryId);
		model.addAttribute("mediamCategory", mediamCategory);
		return "/detail/categoryDetail.html";
	}

}
