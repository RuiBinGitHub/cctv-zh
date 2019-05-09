package com.springboot.biz.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.springboot.biz.ItemInfoCBiz;
import com.springboot.dao.ItemInfoCDao;
import com.springboot.entity.ItemInfoC;

@Service
public class ItemInfoCBizImpl implements ItemInfoCBiz {

	@Resource
	private ItemInfoCDao itemInfoCDao;
	
	public void insertItemInfoC(ItemInfoC itemInfoC) {
		itemInfoCDao.insertItemInfoC(itemInfoC);
	}

	public void updateItemInfoC(ItemInfoC itemInfoC) {
		itemInfoCDao.updateItemInfoC(itemInfoC);
	}

	public void deleteItemInfoC(ItemInfoC itemInfoC) {
		itemInfoCDao.deleteItemInfoC(itemInfoC);
	}

	public ItemInfoC findInfoItemInfoC(Map<String, Object> map) {
		return itemInfoCDao.findInfoItemInfoC(map);
	}
}
