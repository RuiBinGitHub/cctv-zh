package com.springboot.biz;

import java.util.Map;

import com.springboot.entity.ItemInfoA;

public interface ItemInfoABiz {

	public void insertItemInfoA(ItemInfoA itemInfoA);

	public void updateItemInfoA(ItemInfoA itemInfoA);

	public void deleteItemInfoA(ItemInfoA itemInfoA);

	public ItemInfoA findInfoItemInfoA(Map<String, Object> map);

}
