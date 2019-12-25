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
	public ModelAndView initMain(HttpSession session, HttpServletRequest request) throws Exception {
		
		UserVo userVo =  (UserVo) request.getSession().getAttribute("UserVo");
		              
		ModelAndView mav = new ModelAndView();
		
		
		mav.addObject("userVo", userVo);
		mav.setViewName("main/main.tiles");
		return mav;
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
		        }else if(user.getDEL_YN()!= null && user.getDEL_YN() =="N") {
		          map.put("loginCheck", "delete");
		        
		        } else {
		          
		          session.setAttribute("UserVo", user);
		          //session.setAttribute("userId", vo.getUSER_ID());
		          //session.setAttribute("userLevel", vo.getUSER_LVL());
		        
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

	/**
	 * 회원가입 페이지 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/signUp.do")
	public String signUp(HttpServletRequest request, Model model) throws Exception{
	
		return "main/register";			
	}

	/**
	 * 회원가입 페이지 
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/registerCheck.do")
	@ResponseBody
	public Map<String, Object> registerCheck(HttpSession session,  @ModelAttribute("UserVo") UserVo vo) throws Exception{
	
		Map<String, Object> map = new HashMap<String, Object>();		
		UserVo user = new UserVo();	
		
		try {
			user =loginService.loginCheck(vo);
	
		      	if(StringUtils.isEmpty(user)) {
		          map.put("registerCheck", "ok");
		        } else {
		 	      map.put("registerCheck", "fail");		                     
		        }
		      	
		} catch (Exception e) {
			logger.error("error : ",e);
		}	
		
    	return map;
								
	}

	/**
	 * 회원가입  
	 * @param request
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/registerId.do")
	public ModelAndView registerId(HttpSession session,  @ModelAttribute("UserVo") UserVo vo) throws Exception{
	
		
		Map<String, Object> map = new HashMap<String, Object>();
		ModelAndView mav = new ModelAndView();
				
		UserVo user = new UserVo();	
		try {
			loginService.insertMemberInfo(vo);
		} catch (Exception e) {
			logger.error("error : ",e);
		}	
		mav.setViewName("main/login");
		
    	return mav;
								
	}
}



