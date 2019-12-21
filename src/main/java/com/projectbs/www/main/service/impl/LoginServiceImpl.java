package com.projectbs.www.main.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.projectbs.www.main.service.LoginService;
import com.projectbs.www.vo.UserVo;



@Service
public class LoginServiceImpl implements LoginService {

	@Resource(name = "loginMapper")
	LoginMapper loginMapper;

	@Override
	public UserVo loginCheck(UserVo vo) throws Exception {
		// TODO Auto-generated method stub

		return loginMapper.loginCheck(vo);
		
	}
	
	@Override
	public void insertMemberInfo(UserVo vo)  throws Exception {
		
		loginMapper.insertMemberInfo(vo);

	}


}
