package com.java456.booksystem.controller.houtai;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.java456.booksystem.dao.BookTypeDao;
import com.java456.booksystem.entity.BookType;
import com.java456.booksystem.entity.Role;
import com.java456.booksystem.entity.User;

@Controller
@RequestMapping("/houtai/book/type")
public class HouTai_BookType_Controller {
	
	@Resource
	private BookTypeDao bookTypeDao;
	
	/**
	 * /houtai/book/type/manage
	 */
	@RequestMapping("/manage")
	public ModelAndView manage() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", "图书类型管理");
		mav.setViewName("/admin/page/booktype/booktype_manage");
		return mav;
	}
	
	/**
	 * /houtai/book/type/add
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/add")
	public ModelAndView add() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/admin/book/type/add");
		mav.setViewName("/admin/page/booktype/add_update");
		return mav;
	}
	
	
	/**
	 * /houtai/book/type/edit?id=1
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		
		BookType bookType = bookTypeDao.findId(id);
		mav.addObject("bookType", bookType);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/admin/book/type/update?id=" + id);
		mav.setViewName("/admin/page/booktype/add_update");
		return mav;
	}
	
	
	
	
}
