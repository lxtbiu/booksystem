package com.java456.booksystem.entity;


import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;



/**
 *
 * ## 后吧 用户  管理 网站的用户    
 *
 */
@Entity
@Table(name = "t_a_user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull(message="用户名不能为空！")
	@Column(length=30)
	private  String name;//用户名
	@ManyToOne
	@JoinColumn(name="roleId")
	private Role role; // 角色  
	@Column(length=200)
	private  String pwd;
	@NotNull(message="真实姓名不能为空！")
	@Column(length=200)
	private  String trueName;

	@NotNull(message="排序号不能为空！")
	@Column(length=10)
	private Integer orderNo;



	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

	public Integer getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(Integer orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getTrueName() {
		return trueName;
	}

	public void setTrueName(String trueName) {
		this.trueName = trueName;
	}

}
