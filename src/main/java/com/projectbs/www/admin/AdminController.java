package com.projectbs.www.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.projectbs.www.vo.UserVo;

@Controller
public class AdminController {

	

	@RequestMapping(value = "/admin.do")
	public ModelAndView admin(HttpSession session, HttpServletRequest request) throws Exception {
		
		UserVo userVo =  (UserVo) request.getSession().getAttribute("UserVo");
		              
		ModelAndView mav = new ModelAndView();
		
		
		mav.addObject("userVo", userVo);
		mav.setViewName("admin/admin.tiles");
		return mav;
	}
	
	
}
