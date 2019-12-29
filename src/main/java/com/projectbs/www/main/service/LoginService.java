package com.projectbs.www.main.service;

import java.util.List;
import java.util.Map;

import com.projectbs.www.vo.UserVo;


public interface LoginService  {

	// 로그인 체크
	public UserVo loginCheck(UserVo vo) throws Exception;
	// 로그인 insert
	public void insertMemberInfo(UserVo vo)  throws Exception;;
	
}
