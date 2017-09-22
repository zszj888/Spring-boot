package com.bestcxx.stu.springbootsecuritydb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.bestcxx.stu.springbootsecuritydb.common.util.MD5Util;
import com.bestcxx.stu.springbootsecuritydb.security.service.CustomUserService;

/**
 * 注意  protected void configure(AuthenticationManagerBuilder auth) throws Exception { 方法中
 * 密码校验具有加密和不加密两种情况，所谓加密是值，前台传递来的是明文，后台被比较的-从数据库取出来的密码是明文加密后存储的，
 * 这样需要将前台明文密码加密后和数据库存储的密码进行比较，
 * 需要结合 com.bestcxx.stu.springbootsecuritydb.security.service.CustomUserService 类中的注释进行理解 
 * @author wj
 *
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Bean
	UserDetailsService customUserService() {
		return new CustomUserService();
	}

	/**
	 * 用户登陆校验
	 * 调用了customUserService()，内部覆盖重写了 UserDetailsService.loadUserByUsername,需返回 配置了权限的UserDetails的子类对象
	 * 作为登陆用户权限配置的依据
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/**对于数据库密码不加密的情况*/
		//auth.userDetailsService(customUserService()); 
		
		/**对于数据库密码加密的情况*/
		auth.userDetailsService(customUserService()).passwordEncoder(new PasswordEncoder(){
			//rawPassword 前台传递来的 password
			//encodedPassword 后台计算的 password
			@Override
			public String encode(CharSequence rawPassword) {
				return MD5Util.encode((String)rawPassword);
			}

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				 return encodedPassword.equals(MD5Util.encode((String)rawPassword));
			}
			
		});
	}

	/**
	 * 配置 特殊权限-特殊路径
	 * 配置 任意权限-剩余路径
	 * 配置 登陆页-用户名、密码-登陆失败页-不需要权限
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/common/**").hasRole("COMMON")//需要权限ROLE_COMMON 才可以访问的路径   <a th:href="@{/common/test}">去test.html</a>
		.anyRequest().authenticated() // 只有具有任意的某个权限就可以访问其他访问-没有权限还是无法访问的
		.and()
		.formLogin()//对于form表单登陆
		//.passwordParameter("a").usernameParameter("b")//如果你前台登陆的form表单登录名和密码不是username,password，那么就配置本行修改你需要的名字
		.loginPage("/login")//未登陆的情况下，默认跳转的页面
		.failureUrl("/login?error").permitAll() //如果登陆失败，跳转的url
		.and().logout().permitAll(); // 允许任何请求（不管有没有权限以及拥有何种权限）登出
		
	}
	
	

}
