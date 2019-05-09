package com.springboot.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.springboot.biz.ItemBiz;
import com.springboot.biz.PipeBiz;
import com.springboot.biz.ProjectBiz;
import com.springboot.entity.Item;
import com.springboot.entity.Pipe;
import com.springboot.entity.Project;
import com.springboot.entity.User;
import com.springboot.util.AppUtils;

@RestController
@RequestMapping(value = "/pipe")
public class PipeController {

	@Value("${mypath}")
	private String path;
	
	@Resource
	private ProjectBiz projectBiz;
	@Resource
	private PipeBiz pipeBiz;
	@Resource
	private ItemBiz itemBiz;

	private Map<String, Object> map = null;

	@RequestMapping(value = "/makepipe")
	private ModelAndView makepipe(int id, @RequestParam(defaultValue = "1") int no) {
		ModelAndView view = new ModelAndView("user/faliure");
		User user = (User) AppUtils.findMap("user");
		map = AppUtils.getMap("id", id, "user", user);
		Project project = projectBiz.findInfoProject(map);
		if (StringUtils.isEmpty(project))
			return view;
		map = AppUtils.getMap("project", project);
		int size = pipeBiz.getCount(map);
		no = no > size ? size : no;
		map = AppUtils.getMap("project", project, "no", no);
		Pipe pipe = pipeBiz.findInfoPipe(map);
		if (!StringUtils.isEmpty(pipe))
			pipe.setItems(itemBiz.findListItem(pipe));
		view.setViewName("pipe/makepipe");
		view.addObject("project", project);
		view.addObject("pipe", pipe);
		view.addObject("size", size);
		return view;
	}

	@RequestMapping(value = "/showpipe")
	private ModelAndView showpipe(int id, @RequestParam(defaultValue = "1") int no) {
		ModelAndView view = new ModelAndView("user/faliure");
		map = AppUtils.getMap("id", id);
		Project project = projectBiz.findInfoProject(map);
		if (StringUtils.isEmpty(project))
			return view;
		map = AppUtils.getMap("project", project);
		int size = pipeBiz.getCount(map);
		no = no > size ? size : no;
		map = AppUtils.getMap("project", project, "no", no);
		Pipe pipe = pipeBiz.findInfoPipe(map);
		if (!StringUtils.isEmpty(pipe))
			pipe.setItems(itemBiz.findListItem(pipe));
		view.setViewName("pipe/showpipe");
		view.addObject("project", project);
		view.addObject("pipe", pipe);
		view.addObject("size", size);
		return view;
	}

	@RequestMapping(value = "/insert")
	public ModelAndView insert(int id, @RequestParam(defaultValue = "1") int no) {
		ModelAndView view = new ModelAndView("user/faliure");
		User user = (User) AppUtils.findMap("user");
		map = AppUtils.getMap("id", id, "user", user);
		Project project = projectBiz.findInfoProject(map);
		if (StringUtils.isEmpty(project))
			return view;
		map = AppUtils.getMap("project", project, "no", no);
		Pipe temp = pipeBiz.findInfoPipe(map);
		Pipe pipe = new Pipe();
		System.out.println(StringUtils.isEmpty(temp));
		if (StringUtils.isEmpty(temp)) {
			pipe.setNo(no + 1);
			pipe.setMethod("电视检测");
			pipe.setPipetype("--");
			pipe.setMaterial("--");
			pipe.setDirection("--");
			pipe.setSite(project.getSite());
		} else {
			pipe.setNo(temp.getNo() + 1);
			pipe.setMethod(temp.getMethod());
			pipe.setLaidyear(temp.getLaidyear());
			pipe.setPipetype(temp.getPipetype());
			pipe.setMaterial(temp.getMaterial());
			pipe.setDiameter(temp.getDiameter());
			pipe.setSite(temp.getSite());
			pipe.setDate(temp.getDate());
		}
		pipe.setProject(project);
		pipeBiz.appendPipe(pipe);
		String param = "id=" + id + "&no=" + pipe.getNo();
		view.setViewName("redirect:makepipe?" + param);
		return view;
	}

	@RequestMapping(value = "/update")
	public boolean update(Pipe pipe) {
		User user = (User) AppUtils.findMap("user");
		map = AppUtils.getMap("id", pipe.getId(), "user", user);
		if (pipeBiz.findInfoPipe(map) == null)
			return true;
		pipeBiz.updatePipe(pipe);
		List<Item> items = pipe.getItems();
		for (int i = 0; items != null && i < items.size(); i++) {
			Item item = items.get(i);
			String data = item.getPath();
			if (data != null && data.length() > 40) {
				String name = AppUtils.UUIDCode();
				AppUtils.saveImage(data, path, name);
				item.setPath(name);
			}
			items.get(i).setNo(i + 1);
			items.get(i).setPipe(pipe);
			if (items.get(i).getId() == 0)
				itemBiz.insertItem(items.get(i));
			else
				itemBiz.updateItem(items.get(i));
		}
		itemBiz.sortImage(pipe);
		return true;
	}

	@RequestMapping(value = "/delete")
	public boolean delete(int id) {
		User user = (User) AppUtils.findMap("user");
		map = AppUtils.getMap("id", id, "user", user);
		Pipe pipe = pipeBiz.findInfoPipe(map);
		if (!StringUtils.isEmpty(pipe))
			pipeBiz.deletePipe(pipe);
		return true;
	}

}
