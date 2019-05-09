package com.springboot.biz.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.springboot.biz.ItemInfoBBiz;
import com.springboot.dao.ItemInfoBDao;
import com.springboot.entity.ItemInfoB;

@Service
public class ItemInfoBBizImpl implements ItemInfoBBiz {

	@Resource
	private ItemInfoBDao itemInfoBDao;

	public void insertItemInfoB(ItemInfoB itemInfoB) {
		itemInfoBDao.insertItemInfoB(itemInfoB);
	}

	public void updateItemInfoB(ItemInfoB itemInfoB) {
		itemInfoBDao.updateItemInfoB(itemInfoB);
	}

	public void deleteItemInfoB(ItemInfoB itemInfoB) {
		itemInfoBDao.deleteItemInfoB(itemInfoB);
	}

	public ItemInfoB findInfoItemInfoB(Map<String, Object> map) {
		return itemInfoBDao.findInfoItemInfoB(map);
	}

}
