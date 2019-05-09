package com.springboot.biz;

import java.util.Map;

import com.springboot.entity.ItemInfoB;

public interface ItemInfoBBiz {

	public void insertItemInfoB(ItemInfoB itemInfoB);

	public void updateItemInfoB(ItemInfoB itemInfoB);

	public void deleteItemInfoB(ItemInfoB itemInfoB);

	public ItemInfoB findInfoItemInfoB(Map<String, Object> map);
}
