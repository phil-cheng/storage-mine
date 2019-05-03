package com.cf.storage.core.items.service;

import java.util.Map;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.cf.storage.core.items.dao.ItemsMapper;
import com.cf.storage.core.items.po.Items;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageRowBounds;

@Service
public class ItemserviceImpl implements ItemsService {
   
	@Resource
	ItemsMapper itemsMapper;

	@Override
	public Integer addItem(Items items) {
		return itemsMapper.insertSelective(items);
	}

	@Override
	public Page queryItems(Map<String, Object> params, PageRowBounds rowBounds) {
		return itemsMapper.queryItems(params,rowBounds);
	}

	@Override
	public Items selectOneByPrimayKey(String pid) {
		return itemsMapper.selectByPrimaryKey(pid);
	}

	@Override
	public Integer updateParentPrice(Items items, Double price) {
		Items parentItem = itemsMapper.selectByPrimaryKey(items.getPid());
		if(!"".equals(parentItem.getPid()) && !"000000".equals(parentItem.getPid())){
			// 经费累加
			Items updatedItem = new Items();
			updatedItem.setId(parentItem.getId());
			updatedItem.setTotalPrice(parentItem.getTotalPrice() + price);
			int a = itemsMapper.updateByPrimaryKeySelective(updatedItem);
			if(a > 0){
				// 往上递归修改
				this.updateParentPrice(parentItem,price);
			}
		}
		return 1;
	}

	@Override
	public Integer delItems(Items items) {
		return itemsMapper.deleteItemsRecursion(items);
	}

	@Override
	public Page queryItemsByImage(Map<String, Object> params, PageRowBounds rowBounds) {
		return itemsMapper.queryItemsByImage(params,rowBounds);
	}


}
