package com.example.demo.servise;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Item;
import com.example.demo.repository.ItemRepository;

import jakarta.transaction.Transactional;

/**
 * 商品詳細画面関連サービス.
 * 
 * @author matsumotoyuyya
 *
 */
@Service
@Transactional
public class itemDetailServise {

	@Autowired
	private ItemRepository itemRepository;

	/**
	 * 商品詳細画面1件検索.
	 * @param ItemId 商品アイテム
	 * @return 検索商品
	 */
	public Item load(Integer ItemId) {

		Optional<Item> item = itemRepository.load(ItemId);

		return item.get();

	}

}
