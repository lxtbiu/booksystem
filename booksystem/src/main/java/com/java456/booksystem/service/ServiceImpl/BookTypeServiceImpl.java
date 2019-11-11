package com.java456.booksystem.service.ServiceImpl;

import com.java456.booksystem.dao.BookTypeDao;
import com.java456.booksystem.entity.BookType;
import com.java456.booksystem.service.BookTypeService;
import com.java456.booksystem.service.BookTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service("bookTypeService")
public class BookTypeServiceImpl implements BookTypeService {
	@Resource
	private BookTypeDao  bookTypeDao;


	@Override
	public void update(BookType bookType) {
		BookType origin = bookTypeDao.findId(bookType.getId());
		//把没有值的数据  换成原数据库的数据。
		bookType = repalce(bookType, origin);
		bookTypeDao.save(bookType);
	}

	/**
	 * @param curr  当前更新的数据
	 * @param origin   源数据  以前的数据
	 * @return  curr
	 */
	public BookType repalce(BookType curr,BookType origin){

		if(curr.getName()==null){
			curr.setName(origin.getName());
		}
		if(curr.getOrderNo()==null){
			curr.setOrderNo(origin.getOrderNo());
		}
		return curr;
	}

	
	
}
