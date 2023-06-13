package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.domain.BigCategory;
import com.example.demo.domain.Item;
import com.example.demo.domain.MediamCategory;
import com.example.demo.domain.SmalCategory;
import com.example.demo.servise.CategoryService;
import com.example.demo.servise.ItemEditServise;

/**
 * カテゴリー一覧画面を表示,検索するコントローラー.
 * 
 * @author matsumotoyuyya
 *
 */
@Controller
@RequestMapping("/category")
public class ShowCategorController {

	@Autowired
	private ItemEditServise itemEditServise;
	@Autowired
	private ShowItemListController itemListController;

	@Autowired
	private CategoryService categoryService;

	@GetMapping("")
	public String index(Model model) {
		// 大カテゴリーを検索表示
		List<BigCategory> bigCategoryListAll = itemListController.findByBigCategoryAndTotalNumber();
		model.addAttribute("bigCategoryList", bigCategoryListAll);
		return "/category/category.html";
	}

	@GetMapping("/showMediamCategory")
	public String showMediamCategory(Model model, Integer page) {
		List<MediamCategory> mediamCategoryList = new ArrayList<>();
		if (page == null || page == 0) {
			page = 1;
			mediamCategoryList = itemEditServise.findByMediamCategoryAll(page);
		} else {
			mediamCategoryList = itemEditServise.findByMediamCategoryAll(page);

		}
		model.addAttribute("mediamCategoryList", mediamCategoryList);
		model.addAttribute("page", page);

		return "/category/category.html";
	}

	/**
	 * 
	 * @param model
	 * @param page
	 * @return
	 */
	@GetMapping("/showSmalCategory")
	public String showSmalCategory(Model model, Integer page) {
		List<SmalCategory> smalCategoryList = new ArrayList<>();
		if (page == null || page == 0) {
			page = 1;
			smalCategoryList = itemEditServise.findBySmalCategoryAll(page);
		} else {
			smalCategoryList = itemEditServise.findBySmalCategoryAll(page);

		}
		model.addAttribute("smalCategoryList", smalCategoryList);
		model.addAttribute("page", page);

		return "/category/category.html";
	}

	/**
	 * プルダウン 中カテゴリー検索 .
	 * 
	 * @param bigCategory
	 * @return
	 */
	@PostMapping("/searchMediamCategory")
	@ResponseBody
	public List<Item> setMediamSelect(@RequestBody BigCategory bigCategory) {
		List<Item> getMedCount = categoryService.getMedCategoryCount(bigCategory);
		
		return getMedCount;
	}

	/**
	 * プルダウン 小カテゴリー検索 .
	 * 
	 * @param bigCategory
	 * @return
	 */
	@PostMapping("/searchSmalCategory")
	@ResponseBody
	public List<Item> setSmalSelect(@RequestBody MediamCategory mediamCategory) {
		List<Item> getSmalCount = categoryService.getSmalCategoryCount(mediamCategory);
		
		return getSmalCount;
	}

}
