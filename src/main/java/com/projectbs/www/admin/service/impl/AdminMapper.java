package com.projectbs.www.admin.service.impl;

import java.util.HashMap;
import java.util.List;

import com.projectbs.www.vo.UserVo;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface AdminMapper {

	public List<UserVo> selectUserList(HashMap<String, Object> map) throws Exception;
	
	public int selectTotalCount() throws Exception;
	
	public void updateAdminDate(UserVo vo) throws Exception;

}
