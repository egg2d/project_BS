package com.projectbs.www.leftmenu;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LeftController {

    private static final Logger logger = LoggerFactory.getLogger(LeftController.class);
	
	@RequestMapping("/Daily.do")
	public ModelAndView dailyController(HttpServletRequest request, ModelMap model) {
		ModelAndView mav = new ModelAndView();
		
		System.out.println(request.getParameter("pageName"));
		
		mav.setViewName("views/daily.tiles");
		
		return mav;
	}
	
	@RequestMapping("/Weekly.do")
	public ModelAndView weeklyController(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		System.out.println(request.getParameter("pageName"));
		
		mav.setViewName("views/weekly.tiles");
		
		return mav;
	}
	
	@RequestMapping("/Monthly.do")
	public ModelAndView monthlyController(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		
		System.out.println(request.getParameter("pageName"));
		
		mav.setViewName("views/monthly.tiles");
		
		return mav;
	}
	
	
}
