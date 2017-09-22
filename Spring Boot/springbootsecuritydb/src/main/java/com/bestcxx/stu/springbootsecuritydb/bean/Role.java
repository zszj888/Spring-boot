package com.bestcxx.stu.springbootsecuritydb.bean;

import java.io.Serializable;

/**
 * 表示 用户名为 userName 的用户的权限实体
 * @author Administrator
 */
public class Role implements Serializable{

	/**Role 主键 id*/
	private Long id;
	
	/**关联的com.bestcxx.stu.springbootsecuritydb.bean.User 的userName*/
	private String userName;
	
	/**角色名称*/
	private String roleName;

	public Long getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	
	
	
	

}
