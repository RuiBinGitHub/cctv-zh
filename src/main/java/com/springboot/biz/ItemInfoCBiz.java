package com.springboot.biz;

import java.util.Map;

import com.springboot.entity.ItemInfoC;

public interface ItemInfoCBiz {

	public void insertItemInfoC(ItemInfoC itemInfoC);

	public void updateItemInfoC(ItemInfoC itemInfoC);

	public void deleteItemInfoC(ItemInfoC itemInfoC);

	public ItemInfoC findInfoItemInfoC(Map<String, Object> map);

}
