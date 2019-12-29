package com.projectbs.www.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.projectbs.www.admin.service.AdminService;
import com.projectbs.www.main.MainController;
import com.projectbs.www.main.service.LoginService;
import com.projectbs.www.vo.UserVo;

@Controller
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Resource
	AdminService adminService;
	
	@RequestMapping(value = "/admin.do")
	public ModelAndView admin(HttpSession session, HttpServletRequest request) throws Exception {
		
		UserVo userVo =  (UserVo) request.getSession().getAttribute("UserVo");
		ModelAndView mav = new ModelAndView();
		int limit =10;
		int start =1;
		
		List<UserVo> adminList =adminService.selectUserList(limit, start);
		
		logger.info("adminList : " +adminList);

		Gson gson = new Gson();
		String admin_json = gson.toJson(adminList);
		logger.info("admin_json : " + admin_json);


		mav.addObject("userVo", userVo);
		mav.addObject("adminJson", admin_json);
		
		mav.setViewName("admin/admin.tiles");
		
		return mav;
		
	}
	
	
}
