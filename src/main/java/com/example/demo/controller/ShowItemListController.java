package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.domain.BigCategory;
import com.example.demo.domain.Item;
import com.example.demo.form.ItemForm;
import com.example.demo.servise.ItemEditServise;
import com.example.demo.servise.ShowItemServise;

import jakarta.servlet.http.HttpSession;

/**
 * 商品一覧画面,検索関連コントローラー.
 * 
 * @author matsumotoyuyya
 *
 */
@Controller
@RequestMapping("/item")
public class ShowItemListController {

	@Autowired
	private ShowItemServise itemServise;

	@Autowired
	private ItemEditServise itemEditServise;

	@Autowired
	private HttpSession session;

	/**
	 * 初回商品一覧画面に遷移 全件表示時.
	 * 
	 * @param model    モデル
	 * @param page     ページ数
	 * @param itemForm フォーム
	 * @return 商品一覧画面
	 */
	@GetMapping("/top")
	public String showItemList(Model model, Integer page, ItemForm itemForm) {
		List<Item> itemList = new ArrayList<>();

		// ページ数の指定が無い場合は1ページ目を表示させる
		if (page == null || page == 0) {
			page = 1;

			itemList = itemServise.findAll(page, itemForm.getBrand());

		} else {
			itemList = itemServise.findAll(page, itemForm.getBrand());

		}
		
		

		int count = 0;
		List<Item> getItemCount = itemServise.getItemCount(itemForm.getBrand());
		count = getItemCount.get(0).getCount();

		int totaltNumberOfPages = (count - 1) / 30 + 1;

		List<BigCategory> bigCategoryList = findByBigCategoryAndTotalNumber();

		model.addAttribute("page", page);
		model.addAttribute("totaltNumberOfPages", totaltNumberOfPages);
		model.addAttribute("bigCategoryList", bigCategoryList);
		model.addAttribute("itemList", itemList);
		session.setAttribute("itemForm", new ItemForm());

		return "/list/item_list.html";
	}

	/**
	 * 商品検索orページ遷移をします。 全件検索以外は全てこのメソッド.
	 * 
	 * @param model    モデル
	 * @param page     ページ数
	 * @param itemForm フォーム
	 * @return 商品一覧画面
	 */
	@GetMapping("/serchItem")
	public String serchItem(Model model, Integer page, ItemForm itemForm) {
		Optional<List<Item>> itemList = Optional.empty();

		// 何も検索されずにページ変更を行なった時
		if (itemForm.getName() == null && itemForm.getBigCategoryId() == null && itemForm.getMediamCategoryId() == null
				&& itemForm.getSmalCategoryId() == null && itemForm.getBrand() == null) {
			itemForm = (ItemForm) session.getAttribute("itemForm");

		}

		// ページ数の指定が無い場合は1ページ目を表示させる
		if (page == null || page == 0) {
			page = 1;
			itemList = itemServise.search(itemForm, page);

		} else {
			itemList = itemServise.search(itemForm, page);

		}

		List<BigCategory> bigCategoryList = findByBigCategoryAndTotalNumber();

		if (itemList.isPresent() && !itemList.get().isEmpty()) { // nullでなく、要素数が0でない場合にのみ実行
			int totaltNumberOfPages = (itemList.get().get(0).getCount() - 1) / 30 + 1;
			model.addAttribute("bigCategoryList", bigCategoryList);
			model.addAttribute("page", page);
			model.addAttribute("totaltNumberOfPages", totaltNumberOfPages);
			model.addAttribute("itemList", itemList.get());
			session.setAttribute("itemForm", itemForm);
			return "list/item_list";
		} else {
			model.addAttribute("message", "なんで？");
			return showItemList(model, page, itemForm);
		}

	}

	/**
	 * 大カテゴリーとその商品数を取得します.
	 * 
	 * @return 大カテゴリーリスト
	 */
	List<BigCategory> findByBigCategoryAndTotalNumber() {
		List<BigCategory> bigCategoryList = itemEditServise.findByBigCategoryAll();
		List<Item> totaltNumberOfBigCategory = itemServise.getBigCategoryCount();
		int i = 0;
		// TODO bigオブジェクに無理やり詰め込んでいるので変更に弱くなる 名前順で上からsetしているので今後よう対応する
		for (BigCategory bigCategory : bigCategoryList) {
			bigCategory.setCount(totaltNumberOfBigCategory.get(i).getCount());
			i++;
		}
		return bigCategoryList;
	}

}
