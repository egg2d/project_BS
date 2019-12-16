package com.projectbs.www.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
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

@Controller
public class MainController {
	
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Resource
	LoginService loginService;

	@RequestMapping(value = "/main.do")
	public String initMain(HttpSession session, HttpServletRequest request) throws Exception {
		
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
		UserVo user = new UserVo();			
		try {
			user =loginService.loginCheck(vo);
	
		      	if(StringUtils.isEmpty(user)) {
		          map.put("loginCheck", "fail");
		        }else {
		          session.setAttribute("userId", vo.getUSER_ID());
			      map.put("loginCheck", "success");		                     
		        }
		} catch (Exception e) {
			logger.error("error : ",e);
		}	
    	return map;
	}
	
		
	/**
	 * 로그아웃
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/logout.do")
	public String logout(HttpServletRequest request, Model model) throws Exception{
		request.getSession().invalidate();
		//resultMap.put("logout", "success");
		return "main/login";	
	}
}
