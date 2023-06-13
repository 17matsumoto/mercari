package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.Item;
import com.example.demo.servise.itemDetailServise;

/**
 * 商品詳細関連コントローラー.
 * 
 * @author matsumotoyuyya
 *
 */
@Controller
@RequestMapping("/itemDetail")
public class ShowItemDetailController {

	@Autowired
	private itemDetailServise itemDetailServise;

	/**
	 * 商品一覧画面に遷移.
	 * 
	 * @param model  モデル
	 * @param ItemId 商品Id
	 * @return 商品詳細画面
	 */
	@GetMapping("/showDetail")
	public String showItemDetail(Model model, Integer ItemId) {
		System.out.println("アイテムID " + ItemId);
		Item item = itemDetailServise.load(ItemId);
		model.addAttribute("item", item);
		System.out.println("item "  + item);
		return "/detail/detail.html";
	}

}
