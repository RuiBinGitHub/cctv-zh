package com.springboot.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.biz.ItemInfoABiz;
import com.springboot.biz.ItemInfoBBiz;
import com.springboot.biz.ItemInfoCBiz;
import com.springboot.biz.ProjectBiz;
import com.springboot.entity.ItemInfoA;
import com.springboot.entity.ItemInfoB;
import com.springboot.entity.ItemInfoC;
import com.springboot.entity.Project;
import com.springboot.entity.User;
import com.springboot.util.AppUtils;

@RestController
@RequestMapping(value = "/iteminfo")
public class ItemInfoController {

	@Resource
	private ProjectBiz projectBiz;
	@Resource
	private ItemInfoABiz itemInfoABiz;
	@Resource
	private ItemInfoBBiz itemInfoBBiz;
	@Resource
	private ItemInfoCBiz itemInfoCBiz;

	private Map<String, Object> map = null;

	@RequestMapping(value = "/updateA")
	public ModelAndView updateA(ItemInfoA itemInfoA) {
		ModelAndView view = new ModelAndView("user/failure");
		if (StringUtils.isEmpty(itemInfoA.getProject()))
			return view;
		User user = (User) AppUtils.findMap("user");
		map = AppUtils.getMap("id", itemInfoA.getProject().getId(), "user", user);
		Project project = projectBiz.findInfoProject(map);
		if (StringUtils.isEmpty(project))
			return view;
		project.setInfoA(1);
		itemInfoABiz.updateItemInfoA(itemInfoA);
		projectBiz.updateProject(project);
		view.setViewName("user/success");
		return view;
	}

	@RequestMapping(value = "/updateB")
	public ModelAndView updateB(ItemInfoB itemInfoB) {
		ModelAndView view = new ModelAndView("user/failure");
		if (StringUtils.isEmpty(itemInfoB.getProject()))
			return view;
		User user = (User) AppUtils.findMap("user");
		map = AppUtils.getMap("id", itemInfoB.getProject().getId(), "user", user);
		Project project = projectBiz.findInfoProject(map);
		if (StringUtils.isEmpty(project))
			return view;
		project.setInfoB(1);
		itemInfoBBiz.updateItemInfoB(itemInfoB);
		projectBiz.updateProject(project);
		view.setViewName("user/success");
		return view;
	}

	@RequestMapping(value = "/updateC")
	public ModelAndView updatec(ItemInfoC itemInfoC) {
		ModelAndView view = new ModelAndView("user/failure");
		if (StringUtils.isEmpty(itemInfoC.getProject()))
			return view;
		User user = (User) AppUtils.findMap("user");
		map = AppUtils.getMap("id", itemInfoC.getProject().getId(), "user", user);
		Project project = projectBiz.findInfoProject(map);
		if (StringUtils.isEmpty(project))
			return view;
		project.setInfoC(1);
		itemInfoCBiz.updateItemInfoC(itemInfoC);
		projectBiz.updateProject(project);
		view.setViewName("user/success");
		return view;
	}

}
