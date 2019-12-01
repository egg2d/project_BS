package com.projectbs.www.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.projectbs.www.main.service.LoginService;
import com.projectbs.www.vo.UserVo;

import egovframework.rte.psl.dataaccess.util.EgovMap;

@RestController
public class MainController {
	
	@Resource
	LoginService loginService;

	@RequestMapping(value = "/main.do")
	public String initMain() throws Exception {
		return "main/main.tiles";
	}
	
	@RequestMapping(value = "/login.do")
	public String initLogin() throws Exception {
		return "main/login";
	}
	
	@RequestMapping(value = "/loginTry.do" , method = RequestMethod.POST)
	@ResponseBody 
	public Map<String, Object> loginTry(HttpSession session,  @ModelAttribute("UserVo") UserVo vo) throws Exception {
	
		Map<String, Object> map = new HashMap<String, Object>();
		
		
		try {
		
			vo =loginService.loginCheck(vo);
		
		} catch (Exception e) {
		
			System.out.println(e);
		}
		
        if(vo.getId() !=  null && vo.getPasswd() != null) {
        	session.setAttribute("userId", vo.getId());
        	map.put("loginCheck", "success");
        	return map;
        }else {
          	map.put("loginCheck", "fail");
         	return map;
                     
        }
	}
	
}
