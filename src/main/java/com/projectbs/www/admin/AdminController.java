package com.projectbs.www.admin;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.projectbs.www.admin.service.AdminService;
import com.projectbs.www.main.service.LoginService;
import com.projectbs.www.vo.UserVo;

import org.json.simple.JSONObject; // JSON객체 생성
@Controller
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Resource
	AdminService adminService;
	
	@Resource
	LoginService loginService;
	
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
		
		int pageStart = (start-1) * 10;
		List<UserVo> adminList =adminService.selectUserList(10, pageStart);
		int totalCount = adminService.selectTotalCount();
		
		Gson gson = new Gson();
		//String admin_json = gson.toJson(adminList);
		
		String json = null;
		JSONObject paging =  new JSONObject();
		paging.put("page", start);
		paging.put("totalCount", totalCount);
		
		JSONObject data =  new JSONObject();
		data.put("pagination", paging);
		data.put("contents", adminList);
		JSONObject result =  new JSONObject();
		result.put("result", true);
		result.put("data", data);

		logger.info("result_JSONSTRING " +  result.toJSONString());
		return result.toJSONString();
	}
		

	@RequestMapping(value = "/admin/updateAdminDate.do",  method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object>  updateAdminDate(HttpSession session, HttpServletRequest request, 
			HttpEntity<byte[]> body, @RequestBody String updatedRows
	) throws Exception {
		
		UserVo userVo =  (UserVo) request.getSession().getAttribute("UserVo");
		HashMap<String , Object> resultMap = new HashMap<>();
		
		
		String updatedRowsDecode = URLDecoder.decode(updatedRows, "EUC_KR"); 
	    logger.info(updatedRowsDecode);
		
		String[] parts = updatedRowsDecode.split("&");
	    JsonObject json = new JsonObject();	    
	    List<UserVo> userList =new ArrayList<UserVo>();
	    
	    for(int i=0;  i < parts.length; i++) {
	    	
		      String[] keyVal = parts[i].split("="); // The equal separates key and values
		      
		 /*     if(i %7 == 0){
		    	  userList.add(new UserVo());
		    	  userList.get(i/7).setPASSWORD(keyVal[1]);
		      }

		      if(i %7 == 1){
		    	  userList.get(i/7).setUSER_ID_NO(Integer.parseInt(keyVal[1]));
		      }

		      if(i %7 == 2){
		    	  userList.get(i/7).setUSER_ID(keyVal[1]);
		      }	    	
		      if(i %7 == 3){
		    	  userList.get(i/7).setEMAIL(keyVal[1]);
		      }
	    	*/
		    
		      if(i %9 == 0){
		    	  userList.add(new UserVo());
		    	  userList.get(i/9).setDEL_YN(keyVal[1]);
		      } else if(i %9 == 1){
		    	  userList.get(i/7).setPASSWORD(keyVal[1]);
		      } else if(i %9 == 2){
		    	  userList.get(i/9).setUSER_ID_NO(Integer.parseInt(keyVal[1]));
		      } else if(i %9 == 3){
		    	  userList.get(i/9).setUSER_ID(keyVal[1]);
		      } else if(i%9 == 4) {
		    	  userList.get(i/9).setUSER_LVL(keyVal[1]);
		      } else if(i%9 == 5) {
		    	  userList.get(i/9).setEMAIL(keyVal[1]);
		      }
		      
		      
	    }
	    
	    // 중복 계정  조회 
		UserVo user = new UserVo();	
		ArrayList<String> idCheck = new ArrayList<String>();
		int updateFail =0;
	    
	    try {
	    	for(int i=0 ;i < userList.size(); i++){
				user =loginService.loginCheck(userList.get(i));
		      	if(StringUtils.isEmpty(user)) {
		      		adminService.updateAdminDate(userList.get(i));
			    } else{
			    	updateFail ++;
			    	idCheck.add(user.getUSER_ID());			    	
		      	}
		      	
	    	}
	    } catch (Exception e) {
	    	resultMap.put("message", "계정 업데이트를 실패하였습니다.");
		}
	    
	    if(updateFail ==0)
	    	resultMap.put("message", "계정 업데이트를 성공하였습니다.");
	    else{
	    	String  failList =null;
	    	for(String fail : idCheck){
	    		
	    		failList += fail;
	    		failList += ", ";
	    	}
	    	
	    	resultMap.put("message", failList + "를 제외하고 계정 업데이트를 성공하였습니다.");
	    }
	    
		return resultMap;
	}
	
}
