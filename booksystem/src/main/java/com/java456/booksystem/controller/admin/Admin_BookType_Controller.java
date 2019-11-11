package com.java456.booksystem.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.java456.booksystem.service.BookTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java456.booksystem.dao.BookTypeDao;
import com.java456.booksystem.entity.BookType;

import net.sf.json.JSONObject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;



@Controller
@RequestMapping("/admin/book/type")
public class Admin_BookType_Controller {
	
	@Resource
	private BookTypeDao bookTypeDao;
   @Resource 
   private BookTypeService bookTypeService;
   
   /**
	 *   /admin/book/type/add
	 */
	@ResponseBody
	@RequestMapping("/add")
	public JSONObject add(@Valid BookType bookType  ,BindingResult bindingResult, HttpServletResponse response, HttpServletRequest request) throws Exception {
		JSONObject result = new JSONObject();
		
		if(bindingResult.hasErrors()){
			result.put("success", false);
			result.put("msg", bindingResult.getFieldError().getDefaultMessage());
			return result;
		}else{
			bookTypeDao.save(bookType);
			result.put("success", true);
			result.put("msg", "添加成功");
			return result;
		}
	}
	
	
	

	/**
	 *  /admin/book/type/update
	 */
	@ResponseBody
	@RequestMapping("/update")
	public JSONObject update(@Valid BookType bookType  ,BindingResult bindingResult, HttpServletRequest request)throws Exception {
		JSONObject result = new JSONObject();
		
		if(bindingResult.hasErrors()){
			result.put("success", false);
			result.put("msg", bindingResult.getFieldError().getDefaultMessage());
			return result;
		}else{
			bookTypeService.update(bookType);
			result.put("success", true);
			result.put("msg", "修改成功");
			return result;
		}
	}
	
	
	
	/**
	 * /admin/book/type/list
	 * @param page    默认1
	 * @param limit   数据多少
	 */
	@ResponseBody
	@RequestMapping("/list")
	public Map<String, Object> list(@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "limit", required = false) Integer limit, 
			HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		Pageable pageable=new PageRequest(page-1,limit, Sort.Direction.ASC,"orderNo");
		Page<BookType> list = bookTypeDao.findAll(pageable);
		List<BookType> bookTypeList = list.getContent();//拿到list集合
		long total = bookTypeDao.count();
		map.put("data", bookTypeList);
		map.put("count", total);
		map.put("code", 0);
		map.put("msg", "");
		return map;
	}
	
	/**
	 * /admin/book/type/delete
	 */
	@ResponseBody
	@RequestMapping("/delete")
	public JSONObject delete(@RequestParam(value = "ids", required = false) String ids, HttpServletResponse response)
			throws Exception {
		String[] idsStr = ids.split(",");
		JSONObject result = new JSONObject();

		for (int i = 0; i < idsStr.length; i++) {
			bookTypeDao.deleteById(Integer.parseInt(idsStr[i]));
		}
		result.put("success", true);
		return result;
	}
	
	
	
}
