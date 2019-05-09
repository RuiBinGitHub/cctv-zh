package com.springboot.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import sun.misc.BASE64Decoder;

public class AppUtils {

	/** 获取参数列表 */
	public static Map<String, Object> getMap(Object... values) {
		Map<String, Object> map = new HashMap<>();
		for (int i = 0; i < values.length; i += 2)
			map.put((String) values[i], values[i + 1]);
		return map;
	}

	/** 以指定格式获取当前时间格式字符串 */
	public static String getDate(String format) {
		if (format == null)
			format = "yyyy-MM-dd";
		Date date = new Date();
		SimpleDateFormat simple = new SimpleDateFormat(format);
		return simple.format(date);
	}

	/** 创建图片序列码 */
	public static String UUIDCode() {
		UUID uuid = UUID.randomUUID();
		String code = uuid.toString();
		return code.toUpperCase();
	}

	/** 获取session */
	public static HttpSession getHttpSession() {
		RequestAttributes  attributes = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes) attributes).getRequest();
		HttpSession session = request.getSession(true);
		return session;
	}

	/** 添加数据至session */
	public static void pushMap(String key, Object value) {
		HttpSession session = getHttpSession();
		session.setAttribute(key, value);
	}

	/** 从session获取数据 */
	public static Object findMap(String key) {
		HttpSession session = getHttpSession();
		return session.getAttribute(key);
	}

	/** 从session移除数据 */
	public static void removeSession(String key) {
		HttpSession session = getHttpSession();
		session.removeAttribute(key);
	}

	public static void saveImage(String data, String path, String name) {
		try {
			BASE64Decoder decoder = new BASE64Decoder();
			byte[] bytes = decoder.decodeBuffer(data);
			for (int i = 0; i < bytes.length; i++)
				bytes[i] = bytes[i] < 0 ? bytes[i] += 256 : bytes[i];
			OutputStream output = new FileOutputStream(path + name + ".png");
			System.out.println(path + name + ".png");
			output.write(bytes);
			output.flush();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
