package com.onttp.zkmanager.config.beetl.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.onttp.zkmanager.model.User;

/**
 * beetl 自定义函数
 * 
 */
public class Functions {

	/**
	 * 继续encode URL (url,传参tomcat会自动解码) 要作为参数传递的话，需要再次encode
	 * 
	 * @param encodeUrl
	 * @return String
	 */
	public String encodeUrl(String url) {
		try {
			url = URLEncoder.encode(url, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// ignore
		}
		return url;
	}

	/**
	 * 模版中拿取cookie中的用户信息
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	public User currentUser(HttpServletRequest request, HttpServletResponse response) {
		return new User();
	}

}
