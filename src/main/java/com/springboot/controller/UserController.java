package com.springboot.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.biz.CompanyBiz;
import com.springboot.biz.UserBiz;
import com.springboot.entity.Company;
import com.springboot.entity.User;
import com.springboot.util.AppUtils;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Resource
	private UserBiz userBiz;
	@Resource
	private CompanyBiz companyBiz;

	private Map<String, Object> map = null;

	@RequestMapping(value = "/logon")
	public ModelAndView logon(User user, String companyName) {
		ModelAndView view = new ModelAndView("user/logon");
		if (user == null || companyName == null)
			return view;
		map = AppUtils.getMap("name", companyName);
		Company company = companyBiz.findInfoCompany(map);
		if (company == null) {
			view.addObject("tips", "*公司名称不正确，请重新输入！");
			view.addObject("companyName", companyName);
			view.addObject("user", user);
			return view;
		}
		map = AppUtils.getMap("username", user.getUsername());
		if (userBiz.findInfoUser(map) != null) {
			view.addObject("tips", "*登录账号已存在，请重新输入！");
			view.addObject("companyName", companyName);
			view.addObject("user", user);
			return view;
		}
		user.setRole("Role2");
		user.setDate(AppUtils.getDate(null));
		user.setCompany(company);
		userBiz.insertUser(user);
		view.setViewName("redirect:/user/complete");
		return view;
	}

	@RequestMapping(value = "/login")
	public ModelAndView login(HttpServletRequest request, String username, String password) {
		try {
			ModelAndView view = new ModelAndView("redirect:/project/showlist");
			if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
				view.setViewName("user/login");
				view.addObject("username", username);
				view.addObject("password", password);
				return view;
			}
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			SecurityUtils.getSubject().login(token);
			SavedRequest location = WebUtils.getSavedRequest(request);
			if (!StringUtils.isEmpty(location)) {
				String path = location.getRequestUrl();
				path = path.substring(5, path.length());
				view.setViewName("redirect:" + path);
			}
			return view;
		} catch (IncorrectCredentialsException e) {
			ModelAndView view = new ModelAndView("user/login");
			view.addObject("tips", "*账号密码不正确，请重新输入！");
			view.addObject("username", username);
			view.addObject("password", password);
			return view;
		}
	}

	/** 退出登录 */
	@RequestMapping(value = "leave")
	public ModelAndView leave() {
		ModelAndView view = new ModelAndView();
		view.setViewName("redirect:/user/login");
		SecurityUtils.getSubject().logout();
		AppUtils.removeSession("user");
		return view;
	}

}
