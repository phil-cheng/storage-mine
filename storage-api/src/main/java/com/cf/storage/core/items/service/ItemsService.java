package com.cf.storage.core.items.service;

import java.util.Map;

import com.cf.storage.core.items.po.Items;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageRowBounds;

public interface ItemsService {

	Integer addItem(Items items);

	Page queryItems(Map<String, Object> params, PageRowBounds rowBounds);

	Items selectOneByPrimayKey(String pid);

	Integer updateParentPrice(Items items,Double price);

	Integer delItems(Items items);

	Page queryItemsByImage(Map<String, Object> params, PageRowBounds rowBounds);
	
}
