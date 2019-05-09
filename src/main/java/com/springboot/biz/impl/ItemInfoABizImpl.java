package com.springboot.biz.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.springboot.biz.ItemInfoABiz;
import com.springboot.dao.ItemInfoADao;
import com.springboot.entity.ItemInfoA;

@Service
public class ItemInfoABizImpl implements ItemInfoABiz {

	@Resource
	private ItemInfoADao itemInfoADao;

	public void insertItemInfoA(ItemInfoA itemInfoA) {
		itemInfoADao.insertItemInfoA(itemInfoA);
	}

	public void updateItemInfoA(ItemInfoA itemInfoA) {
		itemInfoADao.updateItemInfoA(itemInfoA);
	}

	public void deleteItemInfoA(ItemInfoA itemInfoA) {
		itemInfoADao.deleteItemInfoA(itemInfoA);
	}

	public ItemInfoA findInfoItemInfoA(Map<String, Object> map) {
		return itemInfoADao.findInfoItemInfoA(map);
	}

}
