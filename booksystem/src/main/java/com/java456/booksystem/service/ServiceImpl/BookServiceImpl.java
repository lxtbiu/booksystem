package com.java456.booksystem.service.ServiceImpl;


import com.java456.booksystem.dao.BookDao;
import com.java456.booksystem.entity.Book;
import com.java456.booksystem.service.BookService;
import com.java456.booksystem.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Map;


@Service("bookService")
public class BookServiceImpl implements BookService {
	
	@Resource
	private BookDao  bookDao;
	
	/**
	 * @param curr  当前更新的数据
	 * @param origin   源数据  以前的数据
	 * @return  curr
	 */
	public Book repalce(Book curr,Book origin){
		
		if(curr.getName()==null){
			curr.setName(origin.getName());
		}
		if(curr.getDanjia()==null){
			curr.setDanjia(origin.getDanjia());
		}
		if(curr.getAuthor ()==null){
			curr.setAuthor(origin.getAuthor());
		}
		if(curr.getNum()==null){
			curr.setNum(origin.getNum());
		}
		if(curr.getBookType()==null){
			curr.setBookType(origin.getBookType());
		}
		
		return curr;
	}
	
	
	
	@Override
	public void update(Book book) {
		Book origin = bookDao.findId(book.getId());
		//把没有值的数据  换成原数据库的数据。
		book = repalce(book, origin);
		//把没有值的数据  换成原数据库的数据。
		
		bookDao.save(book);
	}
	
	@Override
	public List<Book> list(Map<String, Object> map, Integer page, Integer pageSize) {
		Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.DESC, "id");
		Page<Book> pages = bookDao.findAll(new Specification<Book>() {
			
			@Override
			public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
				
				// 加入 等于 
				if (map.get("bookType") != null) {
					predicate.getExpressions().add(cb.equal(root.get("bookType"), map.get("bookType")));
				}
				
				return predicate;
			}
		}, pageable);
		return pages.getContent();
	}
	
	
	@Override
	public Long getTotal(Map<String, Object> map) {
		Long count=bookDao.count(new Specification<Book>() {
			@Override
			public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate=cb.conjunction();
				 
				// 加入 等于 
				if (map.get("bookType") != null) {
					predicate.getExpressions().add(cb.equal(root.get("bookType"), map.get("bookType")));
				}
				
				return predicate;
			}
		});
		return count;
	}
	
	
	
	
}
