package com.projectbs.www.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.projectbs.www.admin.service.AdminService;
import com.projectbs.www.vo.UserVo;
import org.json.simple.JSONObject; // JSON객체 생성
import org.json.simple.parser.JSONParser; // JSON객체 파싱
import org.json.simple.parser.ParseException; // 예외처리

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
		int start = 0;
		
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
	

	@RequestMapping(value = "/admin/ChangeLimit.do")
	@ResponseBody
	public String changeLimit(HttpSession session, HttpServletRequest request, 
	@RequestParam (value= "perPage", required=false , defaultValue = "10") int limit , @RequestParam  (value= "page", required=false , defaultValue= "1") int start) throws Exception {
		
		UserVo userVo =  (UserVo) request.getSession().getAttribute("UserVo");

		Map<String, Object> pagingMap = new HashMap<String, Object>();		
		Map<String, Object> dataMap = new HashMap<String, Object>();		
		Map<String, Object> resultMap = new HashMap<String, Object>();		
		
		
		
		System.out.println("dddddd" + limit + " " + start);
		List<UserVo> adminList =adminService.selectUserList(limit, start-1);
		Gson gson = new Gson();
		//String admin_json = gson.toJson(adminList);
		
		
		
		String json = null;
		JSONObject paging =  new JSONObject();
		paging.put("page", start);
		paging.put("totalCount", adminList.size());
		
		JSONObject data =  new JSONObject();
		data.put("pagination", paging);
		data.put("contents", adminList);
		JSONObject result =  new JSONObject();
		result.put("result", true);
		result.put("data", data);

		System.out.println(result);
		
		return result.toJSONString();
		
	/*	dataMap.put("contents",adminList);

		pagingMap.put("page", start);
		pagingMap.put("totalCount", adminList.size());
		dataMap.put("pagination",pagingMap);

		
		resultMap.put("result", true);
		resultMap.put("data", dataMap);
		String resultJson = gson.toJson(resultMap);
		
		
		System.out.println("resultJson" + resultJson);*/
		
		//return resultJson;
	}
		
	
	
}
