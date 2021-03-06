package com.projectbs.www.admin.service;

import java.util.List;

import com.projectbs.www.vo.UserVo;

public interface AdminService {

	public List<UserVo> selectUserList(int limit ,int start) throws Exception;
	
	public int selectTotalCount() throws Exception;
	
	public void updateAdminDate(UserVo vo) throws Exception;

}
