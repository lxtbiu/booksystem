package com.java456.booksystem.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.java456.booksystem.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java456.booksystem.dao.RoleDao;
import com.java456.booksystem.entity.Role;
import net.sf.json.JSONObject;


@Controller
@RequestMapping("/admin/role")
public class Admin_Role_Controller {

	@Resource
	private RoleService roleService;
	@Resource
	private RoleDao roleDao;
	
	/**
	 * /admin/role/add
	 */
	@ResponseBody
	@RequestMapping("/add")
	public JSONObject add(@Valid Role role,BindingResult bindingResult, HttpServletResponse response, HttpServletRequest request) throws Exception {
		JSONObject result = new JSONObject();
		
		if(bindingResult.hasErrors()){
			result.put("success", false);
			result.put("msg", bindingResult.getFieldError().getDefaultMessage());
			return result;
		}else{
			roleDao.save(role);
			result.put("success", true);
			result.put("msg", "添加成功");
			return result;
		}
	}
	
	/**
	 * /admin/role/update
	 */
	@ResponseBody
	@RequestMapping("/update")
	public JSONObject update(@Valid Role role,BindingResult bindingResult)throws Exception {
		JSONObject result = new JSONObject();
		
		if(bindingResult.hasErrors()){
			result.put("success", false);
			result.put("msg", bindingResult.getFieldError().getDefaultMessage());
			return result;
		}else{
			roleService.update(role);
			result.put("success", true);
			result.put("msg", "修改成功");
			return result;
		}
	}
	
	/**
	 * /admin/role/list
	 * @param page    默认1(layui传来的)
	 * @param limit   数据多少（每页数据）
	 */
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit, 
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<Role> list = roleService.list(map, page-1, limit);
		long total = roleService.getTotal(map);
		map.put("data", list);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	
	
	/**
	 * /admin/role/delete
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public JSONObject delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
			throws Exception {
		String[] idsStr = ids.split(",");
		JSONObject result = new JSONObject();
		
		for (int i = 0; i < idsStr.length; i++) {
			try {
				roleDao.deleteById(Integer.parseInt(idsStr[i]));
			} catch (Exception e) {
				e.printStackTrace();
				result.put("success", false);
				result.put("msg", "有用户正在使此角色");
				return result;
			}
		}
		
		result.put("success", true);
		return result;
	}
	
	
	
}
