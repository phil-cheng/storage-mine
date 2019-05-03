package com.cf.storage.core.items.dao;


import java.util.Map;

import com.cf.storage.core.items.po.Items;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageRowBounds;

import tk.mybatis.mapper.common.Mapper;

public interface ItemsMapper extends Mapper<Items>{

	Page queryItems(Map<String, Object> params, PageRowBounds rowBounds);

	Integer deleteItemsRecursion(Items items);

	Page queryItemsByImage(Map<String, Object> params, PageRowBounds rowBounds);

}