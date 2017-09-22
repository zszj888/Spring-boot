package com.bestcxx.stu.springbootsecuritydb.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.bestcxx.stu.springbootsecuritydb.bean.Role;
import com.bestcxx.stu.springbootsecuritydb.bean.User;
import com.bestcxx.stu.springbootsecuritydb.security.bean.SecurityUser;

/**
 * 本类需要实现 org.springframework.security.core.userdetails.UserDetailsService 接口
 * 然后覆盖重写 loadUserByUsername(String userName) 方法
 * 在该方法内部，需要添加 userName,passWord,权限集合,其他参数 到我们已经处理好的com.bestcxx.stu.springbootsecuritydb.security.bean.SecurityUser
 * 然后返回即可-没有密码校验？是的，密码校验不在这个地方,已经被封装好了，但是-数据库密码一般都是加密的，前端信息传递到这还是明文形式，
 * 所以肯定有办法覆盖重写密码校验的方法，这里先卖个关子，先让我们的程序在密码明文校验的情况下顺利运行吧
 * 好吧，就是在 com.bestcxx.stu.springbootsecuritydb.config.WebSecurityConfig 类中配置了怎么校验密码
 * 
 * 本例没有连接数据库，但是会按照连接数据库的流程进行讲解
 * @author wj
 *
 */
public class CustomUserService implements UserDetailsService {

	/**
	 * 用户登陆校验
	 * 覆盖重写了 UserDetailsService.loadUserByUsername,需返回 配置了权限的UserDetails的子类对象，增加权限
	 * 作为登陆用户权限配置的依据
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("录入的username值为："+username);
		
		//1、根据 username 从数据库获取用户信息
		//com.bestcxx.stu.springbootsecuritydb.bean.User 对应数据库用户信息
		User user=new User();
		user.setUserName(username);
		//user.setPassword("admin");//admin md5加密 21232f297a57a5a743894a0e4a801fc3
		user.setPassword("21232f297a57a5a743894a0e4a801fc3");//admin md5加密 21232f297a57a5a743894a0e4a801fc3
		user.setAge(20);
		
				//理论上，如果真的是从数据库查询，这里需要做一个查询判空
				if(user==null){
					throw new UsernameNotFoundException("username 不存在");
				}
		
		//2、根据 user 获取权限
		//com.bestcxx.stu.springbootsecuritydb.bean.Role 对应数据库 用户权限信息
		List<Role> roleList=new ArrayList<Role>();
		
		List<GrantedAuthority> authorities=new ArrayList<GrantedAuthority>();
		
		//遍历 roleList 将 相应的权限放置到 authorities 中
		for(Role role:roleList){
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getRoleName());//权限实体
			authorities.add(grantedAuthority);//增加到权限队列中
		}
		
		//由于我们这里并没有从数据库中获取Role 数据，所以这里写一个默认值,根据规则，ROLE_开头，养成良好的命名规范
		//这里我们规定了用户权限ROLE_ADMIN 这样在页面 home.html中 sec:authorize="hasRole('ROLE_ADMIN')" 就限定了拥有该权限的才可以访问
		authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		
		//这里我们规定了用户权限ROLE_COMMON 这样在
		authorities.add(new SimpleGrantedAuthority("ROLE_COMMON"));
		
		
		return new SecurityUser(user.getUserName(),user.getPassword(),authorities,user.getAge());
	}

}
