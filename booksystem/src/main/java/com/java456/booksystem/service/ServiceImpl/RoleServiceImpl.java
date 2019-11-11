package com.java456.booksystem.service.ServiceImpl;

import com.java456.booksystem.dao.RoleDao;
import com.java456.booksystem.entity.Role;
import com.java456.booksystem.service.RoleService;
import com.java456.booksystem.service.RoleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@Service("roleService")
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleDao roleDao;
	
	
	@Override
	public Integer update(Role role) {
		Role origin = roleDao.findId(role.getId());
		role = repalce(role, origin);
		roleDao.save(role);
		return 1;
	}
	
	@Override
	public List<Role> list(Map<String, Object> map, Integer page, Integer pageSize) {
		Pageable pageable = new PageRequest(page, pageSize, Sort.Direction.ASC, "orderNo");
		Page<Role> list = roleDao.findAll(pageable);
		return list.getContent();// 拿到list集合
	}
	
	
	@Override
	public Long getTotal(Map<String, Object> map) {
		return roleDao.count();
	}
	
	
	/**
	 * @param curr   当前更新的数据
	 * @param origin 源数据 以前的数据
	 * @return curr
	 */
	public Role repalce(Role curr, Role origin) {

		if (curr.getName() == null) {
			curr.setName(origin.getName());
		}
		if (curr.getOrderNo() == null) {
			curr.setOrderNo(origin.getOrderNo());
		}
		
		return curr;
	}

}
