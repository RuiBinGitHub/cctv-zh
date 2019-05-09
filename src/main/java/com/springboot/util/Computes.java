package com.springboot.util;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.springboot.biz.CodeBiz;
import com.springboot.biz.ItemBiz;
import com.springboot.biz.ItemInfoABiz;
import com.springboot.entity.Code;
import com.springboot.entity.Item;
import com.springboot.entity.ItemInfoA;
import com.springboot.entity.Pipe;
import com.springboot.entity.Project;

@Component(value = "computes")
public class Computes {

	@Resource
	private ItemBiz itemBiz;
	@Resource
	private CodeBiz codeBiz;
	@Resource
	private ItemInfoABiz itemInfoABiz;

	private Map<String, Object> map = null;

	int S_Count = 0; // 管道结构性缺陷数量
	int Y_Count = 0; // 管道功能性缺陷数量
	double RI = 0; // 管道修复指数
	double MI = 0; // 管道养护指数
	double S_M_T = 0, Y_M_T = 0;
	double values[] = new double[10];
	// 结构性数据
	// a[0] 结构性损坏状况参数最大值。
	// a[1] 结构性损坏状况参数平均值。
	// a[2] 结构性损坏状况参数总值。
	// a[3] 结构性参数(F：平均值与最大值中选择较大值)。
	// a[4] 结构性缺陷密度。
	// 功能性数据
	// a[5] 功能性运行状况参数最大值。
	// a[6] 功能性运行状况参数平均值。
	// a[7] 功能性运行状况参数总值。
	// a[8] 功能性参数(G：平均值与最大值中选择较大值)。
	// a[9] 功能性缺陷密度。

	public Pipe computePipe(Project project, Pipe pipe) {
		map = AppUtils.getMap("project", project);
		ItemInfoA itemInfoA = itemInfoABiz.findInfoItemInfoA(map);
		map = AppUtils.getMap("pipe", pipe);
		List<Item> items = itemBiz.findListItem(map);
		for (int i = 0; items != null && i < items.size(); i++) {
			Item item = items.get(i);
			if (item.getCode() != null && item.getCode().length() == 4)
				item.setCode(item.getCode().substring(2, 4));
			Code code = codeBiz.findInfoCode(item.getCode(), item.getGrade());
			if (StringUtils.isEmpty(code))
				continue; // 结束本次循环
			double item_score = getValue(code.getScore());
			if (code.getType().equals("type1")) { // 结构性缺陷
				values[2] += item_score;
				S_M_T += item_score * 1; // 分值*距离
				if (item_score > values[0])
					values[0] = item_score;
				S_Count++;
			}
			if (code.getType().equals("type2")) { // 功能性缺陷
				values[7] += item_score;
				Y_M_T += item_score * 1; // 分值*距离
				if (item_score > values[5])
					values[5] = item_score;
				Y_Count++;
			}
		}
		S_Count = S_Count == 0 ? 1 : S_Count;
		Y_Count = Y_Count == 0 ? 1 : Y_Count;
		values[1] = values[2] / S_Count;
		values[6] = values[7] / Y_Count;
		// 计算管道缺陷参数
		values[3] = values[0] > values[1] ? values[0] : values[1];
		values[8] = values[5] > values[6] ? values[5] : values[6];
		// 计算管道缺陷密度
		values[4] = S_M_T / (values[3] * getValue(pipe.getPipelength()));
		values[9] = Y_M_T / (values[8] * getValue(pipe.getPipelength()));

		double K = getK(itemInfoA, values[3]); // 地区重要性参数
		double T = getT(itemInfoA, values[3]); // 土质影响参数
		double E = getE(pipe, values[3]); // 管道重要性参数
		RI = 0.7 * values[3] + 0.10 * K + 0.15 * T + 0.05 * E;
		MI = 0.8 * values[8] + 0.15 * K + 0.05 * E;
		pipe.setItems(items);
		pipe.setValue(values);
		pipe.setRI(RI);
		pipe.setMI(MI);
		return pipe;
	}

	private int getK(ItemInfoA itemInfoA, double F) {
		if (F < 4)
			return 0;
		else if (itemInfoA.getConditio().indexOf("甲") != -1)
			return 10;
		else if (itemInfoA.getConditio().indexOf("乙") != -1)
			return 6;
		else if (itemInfoA.getConditio().indexOf("丙") != -1)
			return 3;
		else
			return 0;
	}

	private int getT(ItemInfoA itemInfoA, double F) {
		if (F == 0 || itemInfoA.getMaterial().equals("一般土层"))
			return 0;
		else if (itemInfoA.getMaterial().equals("粉砾层"))
			return 10;
		else if (itemInfoA.getMaterial().equals("红黏土"))
			return 10;
		else if (itemInfoA.getMaterial().equals("湿陷性黄土(Ⅰ级)"))
			return 6;
		else if (itemInfoA.getMaterial().equals("湿陷性黄土(Ⅱ级)"))
			return 6;
		else if (itemInfoA.getMaterial().equals("湿陷性黄土(Ⅲ级)"))
			return 8;
		else if (itemInfoA.getMaterial().equals("湿陷性黄土(Ⅳ级)"))
			return 10;
		else if (itemInfoA.getMaterial().equals("膨胀土(强)"))
			return 10;
		else if (itemInfoA.getMaterial().equals("膨胀土(中)"))
			return 8;
		else if (itemInfoA.getMaterial().equals("膨胀土(弱)"))
			return 6;
		else if (itemInfoA.getMaterial().equals("淤泥类土(淤泥)"))
			return 10;
		else if (itemInfoA.getMaterial().equals("淤泥类土(质土)"))
			return 10;
		else
			return 0;
	}

	private int getE(Pipe pipe, double F) {
		double diameter = getValue(pipe.getDiameter());
		if (F < 4)
			return 0;
		else if (diameter > 1500)
			return 10;
		else if (1000 < diameter && diameter >= 1500)
			return 6;
		else if (600 <= diameter && diameter >= 1000)
			return 3;
		else
			return 0;
	}

	private double getValue(String text) {
		try {
			return Double.valueOf(text);
		} catch (Exception e) {
			return 0;
		}
	}
}
