package com.bestcxx.stu.springbootsecuritydb.security.bean;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * Security 安全校验实体-用于封转登陆用户的身份信息和权限信息
 * 实质上是对 org.springframework.security.core.userdetails.UserDetails 接口的实现
 * org.springframework.security.core.userdetails.User 提供了构造方法，便于我们业务用户实体和Security 校验身份的实体分离
 * @author Administrator
 *
 */
public class SecurityUser extends User {
	
	private static final long serialVersionUID = -254576396255401176L;

	//这里可以增加自定义参数
	/**
	 * private int age;
	 * private int number;
	 * eg.
	 */
	/**年龄*/
	private int age;
	
	/**
	 * 有参构造方法，可以扩充参数
	 * @param username      基本参数
	 * @param password      基本参数
	 * @param authorities   基本参数-表示登陆权限的字符串集合-比如ROLE_ADMIN，可以自定义
	 */
	public SecurityUser(String username, String password, Collection<? extends GrantedAuthority> authorities,int age) {
		super(username, password, authorities);
		this.age=age;
	}

	
	
	/**
	 * 对于新增的自定义参数
	 * 赋值操作在有参构造方法中
	 * 取值操作需要提供get方法
	 */
	public int getAge() {
		return age;
	}
	
}
