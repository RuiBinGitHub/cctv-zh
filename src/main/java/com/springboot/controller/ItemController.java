package com.springboot.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.biz.ItemBiz;
import com.springboot.biz.PipeBiz;
import com.springboot.entity.Item;
import com.springboot.entity.Pipe;
import com.springboot.entity.User;
import com.springboot.util.AppUtils;

@RestController
@RequestMapping(value = "/item")
public class ItemController {

	@Value(value = "${mypath}")
	private String path;

	@Resource
	private PipeBiz pipeBiz;
	@Resource
	private ItemBiz itemBiz;

	private Map<String, Object> map = null;

	@RequestMapping(value = "/delete")
	public boolean delete(int id) {
		User user = (User) AppUtils.findMap("user");
		map = AppUtils.getMap("id", id, "user", user);
		Item item = itemBiz.findInfoItem(map);
		if (StringUtils.isEmpty(item))
			return false;
		itemBiz.deleteItem(item);
		if (!StringUtils.isEmpty(item.getPath()))
			itemBiz.sortImage(item.getPipe());
		return true;
	}

	/** 删除图片 */
	@RequestMapping(value = "/deleteimage")
	public boolean deleteImage(int id) {
		User user = (User) AppUtils.findMap("user");
		map = AppUtils.getMap("id", id, "user", user);
		Item item = itemBiz.findInfoItem(map);
		if (StringUtils.isEmpty(item))
			return false;
		item.setPath("");
		item.setPicture("");
		itemBiz.updateItem(item);
		itemBiz.sortImage(item.getPipe());
		return true;
	}
	
	/** 移除图片 */
	@RequestMapping(value = "/removeimage")
	public boolean removeImage(int id) {
		User user = (User) AppUtils.findMap("user");
		map = AppUtils.getMap("id", id, "user", user);
		Pipe pipe = pipeBiz.findInfoPipe(map);
		if (StringUtils.isEmpty(pipe))
			return false;
		map = AppUtils.getMap("pipe", pipe, "path", "");
		List<Item> items = itemBiz.findListItem(map);
		for (Item item : items) {
			String ImagePath = path + item.getPath();
			File file = new File(ImagePath + ".png");
			file.delete();
			item.setPath("");
			item.setPicture("");
			itemBiz.updateItem(item);
		}
		return true;
	}
}
