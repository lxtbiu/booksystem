package com.java456.booksystem.service;

import com.java456.booksystem.entity.Book;

import java.util.List;
import java.util.Map;

public interface BookService {
	
	
	public void update(Book book);
	
	/**
	 * @param map
	 * @param page  从0开始 
	 * @param pageSize
	 */
	public List<Book> list(Map<String, Object> map, Integer page, Integer pageSize);
	
	public Long getTotal(Map<String, Object> map);
	
	
	
}
