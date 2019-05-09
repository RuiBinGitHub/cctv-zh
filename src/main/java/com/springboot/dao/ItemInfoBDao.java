package com.springboot.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.springboot.entity.ItemInfoB;

@Mapper
public interface ItemInfoBDao {

	public void insertItemInfoB(ItemInfoB itemInfoB);

	public void updateItemInfoB(ItemInfoB itemInfoB);

	public void deleteItemInfoB(ItemInfoB itemInfoB);

	public ItemInfoB findInfoItemInfoB(Map<String, Object> map);

}
