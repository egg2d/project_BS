package com.projectbs.www.admin.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.projectbs.www.admin.service.AdminService;
import com.projectbs.www.main.service.impl.LoginMapper;
import com.projectbs.www.vo.UserVo;

@Service
public class AdminServiceImpl implements AdminService {

	@Resource(name = "adminMapper")
	AdminMapper adminMapper;

	
	@Override
	public List<UserVo> selectUserList(int limit ,int start) throws Exception  {
		// TODO Auto-generated method stub
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("limit", limit);
		map.put("start", start);
		
		return adminMapper.selectUserList(map);
	}


	@Override
	public int selectTotalCount() throws Exception {
		// TODO Auto-generated method stub
		return adminMapper.selectTotalCount();
	}


	@Override
	public void updateAdminDate(UserVo vo) throws Exception {
		adminMapper.updateAdminDate(vo);
	}


}
