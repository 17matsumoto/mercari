
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
import com.example.demo.domain.Item;
import com.example.demo.domain.LoginUser;
import com.example.demo.form.ItemForm;
import com.example.demo.servise.ItemEditServise;
import com.example.demo.servise.itemDetailServise;

/**
 * 商品を編集するコントローラー.
 * 
 * @author matsumotoyuyya
 *
 */
@Controller
@RequestMapping("/")
public class ItemEditController {

	@Autowired
	private itemDetailServise itemDetailServise;

	@Autowired
	private ItemEditServise itemEditServise;

	@Autowired
	private ShowItemListController itemListController;

	/**
	 * 商品編集画面に遷移します.
	 * 
	 * @param model     モデル
	 * @param itemForm  フォーム
	 * @param itemId    商品ID
	 * @param loginUser ログインユーザー
	 * @return 編集画面
	 */
	@GetMapping("/edit")
	public String showEdit(Model model, ItemForm itemForm, Integer itemId,
			@AuthenticationPrincipal LoginUser loginUser) {
		Item item = itemDetailServise.load(itemId);

		// 大カテゴリー検索
		List<BigCategory> bigCategoryListAll = itemListController.findByBigCategoryAndTotalNumber();
		model.addAttribute("bigCategoryList", bigCategoryListAll);

		model.addAttribute("item", item);
		model.addAttribute("itemform", itemForm);
		return "/edit/edit.html";
	}

	/**
	 * 商品の編集をします.
	 * 
	 * @param model    モデル
	 * @param itemform フォーム
	 * @return 商品一覧画面
	 */
	@PostMapping("/updateItem")
	public String updateItem(@Validated ItemForm itemForm, BindingResult bindingResult, Model model,
			@AuthenticationPrincipal LoginUser loginUser) {
		if (bindingResult.hasErrors()) {
			return showEdit(model, itemForm, itemForm.getId(), loginUser);
		}

		itemForm.setSmalCategoryId(itemForm.getSmalCategoryId());
		itemEditServise.updateItem(itemForm);

		return "redirect:/item/top";
	}
}
