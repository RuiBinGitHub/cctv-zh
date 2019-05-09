package com.springboot.biz;

import java.util.List;
import java.util.Map;

import com.springboot.entity.Item;
import com.springboot.entity.Pipe;

public interface ItemBiz {

	public void insertItem(Item item);

	public void updateItem(Item item);

	public void deleteItem(Item item);

	public Item findInfoItem(Map<String, Object> map);

	public List<Item> findListItem(Map<String, Object> map);

	public List<Item> findListItem(Pipe pipe);

	public void sortImage(Pipe pipe);
}
