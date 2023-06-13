package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.BigCategory;
import com.example.demo.domain.LoginUser;
import com.example.demo.form.ItemForm;
import com.example.demo.servise.ItemAddServise;

@Controller
@RequestMapping("/itemAdd")
public class ItemAddController {

	@Autowired
	private ItemAddServise itemAddServise;
	@Autowired
	private ShowItemListController itemListController;

	/**
	 * 商品追加画面を表示します.
	 * @param model モデル
	 * @param itemForm　フォーム
	 * @param loginUser　ログインユーザー
	 * @return 商品追加画面
	 */
	@GetMapping("/index")
	public String showAdd(Model model, ItemForm itemForm, @AuthenticationPrincipal LoginUser loginUser) {

		List<BigCategory> bigCategoryListAll = itemListController.findByBigCategoryAndTotalNumber();
		model.addAttribute("bigCategoryList", bigCategoryListAll);

		return "/add/add.html";
	}

	/**
	 * 商品をインサートし、商品一覧画面に遷移します.
	 * 
	 * @param model モデル
	 * @param itemForm　フォーム
	 * @param bindingResult 
	 * @return　商品一覧画面
	 */
	@PostMapping("/add")
	public String addItem(@Validated ItemForm itemForm, BindingResult bindingResult, Model model,
			@AuthenticationPrincipal LoginUser loginUser) {
		if (bindingResult.hasErrors()) {
			return showAdd(model, itemForm, loginUser);
		}
		System.out.println("小カテゴリー" + itemForm.getSmalCategoryId());
		itemForm.setSmalCategoryId(itemForm.getSmalCategoryId());

		// 商品を登録
		itemAddServise.insertItem(itemForm);

		return "redirect:/item/top";
	}

}
