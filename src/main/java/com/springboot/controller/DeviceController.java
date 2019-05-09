package com.springboot.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.biz.DeviceBiz;
import com.springboot.entity.Device;
import com.springboot.entity.User;
import com.springboot.util.AppUtils;

@RestController
@RequestMapping(value = "/device")
public class DeviceController {

	@Resource
	private DeviceBiz deviceBiz;

	private Map<String, Object> map = null;

	@RequestMapping(value = "/insert")
	public ModelAndView insert(Device device) {
		ModelAndView view = new ModelAndView("user/failure");
		if (StringUtils.isEmpty(device.getName()))
			return view;
		User user = (User) AppUtils.findMap("user");
		device.setDate(AppUtils.getDate(null));
		device.setCompany(user.getCompany());
		deviceBiz.insertDevice(device);
		view.setViewName("redirect:/success");
		return view;
	}

	@RequestMapping(value = "/update")
	public ModelAndView update(Device device) {
		ModelAndView view = new ModelAndView("user/failure");
		User user = (User) AppUtils.findMap("user");
		map = AppUtils.getMap("id", device.getId(), "company", user.getCompany());
		if (deviceBiz.findInfoDevice(map) == null)
			return view;
		device.setCompany(user.getCompany());
		deviceBiz.updateDevice(device);
		view.setViewName("redirect:/success");
		return view;
	}

	@RequestMapping(value = "/delete")
	public boolean delete(int id) {
		User user = (User) AppUtils.findMap("user");
		map = AppUtils.getMap("id", id, "company", user.getCompany());
		Device device = deviceBiz.findInfoDevice(map);
		if (!StringUtils.isEmpty(device))
			deviceBiz.deleteDevice(device);
		return true;
	}

	@RequestMapping(value = "/updateview")
	public ModelAndView updateview(int id) {
		ModelAndView view = new ModelAndView("user/failure");
		User user = (User) AppUtils.findMap("user");
		map = AppUtils.getMap("id", id, "company", user.getCompany());
		Device device = deviceBiz.findInfoDevice(map);
		if (StringUtils.isEmpty(device))
			return view;
		view.setViewName("device/update");
		view.addObject("device", device);
		return view;
	}

	@RequestMapping(value = "/showlist")
	public ModelAndView showlist(String name, @RequestParam(defaultValue = "1") int page) {
		ModelAndView view = new ModelAndView("device/showlist");
		User user = (User) AppUtils.findMap("user");
		map = AppUtils.getMap("company", user.getCompany());
		if (!StringUtils.isEmpty(name))
			map.put("name", name);
		int cont = deviceBiz.getCountPage(map, 15);
		page = page > cont ? cont : page;
		map.put("page", page);
		List<Device> devices = deviceBiz.findListDevice(map);
		view.addObject("devices", devices);
		view.addObject("page", page);
		view.addObject("cont", cont);
		return view;
	}

}
