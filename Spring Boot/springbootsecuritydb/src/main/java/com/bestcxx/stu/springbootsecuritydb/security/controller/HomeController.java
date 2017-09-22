package com.bestcxx.stu.springbootsecuritydb.security.controller;

import java.util.HashMap;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {
	
	@RequestMapping(value={"/","/login"})
	public ModelAndView index(Model model){
		if (!SecurityContextHolder.getContext().getAuthentication().getName().equals("anonymousUser")){ //已登录状态访问 则自动跳到系统首页
			//虽然这里什么也没有，但是其实，Security 用户登陆的检测已经在起作用了
			HashMap<String,Object> msg=new HashMap<String,Object>();
			msg.put("title", "无需特殊权限-测试标题");
			msg.put("content", "无需特殊权限-测试内容");
			msg.put("etraInfo", "额外信息，只对具有 ROLE_ADMIN 权限的显示");
			model.addAttribute("msg", msg);
			return new ModelAndView("home").addObject(msg);
        }
		return new ModelAndView("login");
		
	}
	
	@RequestMapping("/common/test")
	public String test(){
		return "common/test";
	}

}
