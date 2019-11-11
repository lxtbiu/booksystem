package com.java456.booksystem.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.java456.booksystem.util.BrowserUtil;
import net.sf.json.JSONObject;


@Controller
public class IndexController {

	@Autowired
	private ServletContext servletContext;

	/**
	 *# 请求首页
	 */
	@RequestMapping("/")
	public String  index_1(HttpServletResponse  res,HttpServletRequest req) throws Exception {
		return "redirect:/login";
	}

	/**
	 *   #请求首页  /index
	 */
	@RequestMapping("/index")
	public String index(HttpServletResponse  res,HttpServletRequest req) throws Exception {
		return "redirect:/login";
	}


	/**
	 *    /login
	 *    #后台 用户电脑登陆
	 */
	@RequestMapping("/login")
	public ModelAndView login(HttpServletResponse  res,HttpServletRequest req) throws Exception {
		ModelAndView mav = new ModelAndView();
		String UserAgent = req.getHeader("User-Agent");
		//判断AppleWebKit 和  Firefox
			mav.setViewName("/pc/login/login");
		return mav;
	}



	/**
	 * # 后台主页
	 */
	@RequestMapping("/admin/main")
	public ModelAndView admin_main(HttpServletResponse  res,HttpServletRequest req) throws Exception {
		ModelAndView mav = new ModelAndView();
		String UserAgent = req.getHeader("User-Agent");
		//判断AppleWebKit 和  Firefox
			mav.setViewName("/admin/main");
		return mav;
	}

}
