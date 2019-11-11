package com.java456.booksystem.controller.houtai;


import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.java456.booksystem.dao.RoleDao;
import com.java456.booksystem.entity.Role;


@Controller
@RequestMapping("/houtai/role")
public class HouTai_Role_Controller {
	

	@Resource
	private RoleDao roleDao;
	
	/**
	 * /houtai/role/manage
	 */
	@RequestMapping("/manage")
	public ModelAndView manage() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageTitle", "角色管理");
		mav.addObject("title", "角色管理");
		mav.setViewName("/admin/page/role/role_manage");
		return mav;
	}
	
	
	/**
	 * /houtai/role/add
	 */
	@RequestMapping("/add")
	public ModelAndView add() throws Exception {
		ModelAndView mav = new ModelAndView();
		mav.addObject("btn_text", "添加");
		mav.addObject("save_url", "/admin/role/add");
		mav.setViewName("/admin/page/role/add_update");
		return mav;
	}
	
	
	/**
	 * /houtai/role/edit?id=1
	 */
	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam(value = "id", required = false) Integer id) throws Exception {
		ModelAndView mav = new ModelAndView();
		Role role = roleDao.findId(id);
		mav.addObject("role", role);
		mav.addObject("btn_text", "修改");
		mav.addObject("save_url", "/admin/role/update?id=" + id);
		mav.setViewName("/admin/page/role/add_update");
		return mav;
	}
	
	
}
