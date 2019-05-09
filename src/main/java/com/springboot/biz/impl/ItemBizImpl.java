package com.springboot.biz.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.springboot.biz.ItemBiz;
import com.springboot.dao.ItemDao;
import com.springboot.entity.Item;
import com.springboot.entity.Pipe;
import com.springboot.util.AppUtils;

@Service
public class ItemBizImpl implements ItemBiz {

	@Resource
	private ItemDao itemDao;

	private Map<String, Object> map = null;

	public void insertItem(Item item) {
		itemDao.insertItem(item);
	}

	public void updateItem(Item item) {
		itemDao.updateItem(item);
	}

	public void deleteItem(Item item) {
		itemDao.deleteItem(item);
	}

	public Item findInfoItem(Map<String, Object> map) {
		return itemDao.findInfoItem(map);
	}

	public List<Item> findListItem(Map<String, Object> map) {
		return itemDao.findListItem(map);
	}

	public List<Item> findListItem(Pipe pipe) {
		map = AppUtils.getMap("pipe", pipe);
		return itemDao.findListItem(map);
	}

	public void sortImage(Pipe pipe) {
		map = AppUtils.getMap("pipe", pipe, "path", "");
		List<Item> items = this.findListItem(map);
		for (int i = 0; items != null && i < items.size(); i++) {
			items.get(i).setPicture(i + 1 + "");
			this.updateItem(items.get(i));
		}
	}
}
