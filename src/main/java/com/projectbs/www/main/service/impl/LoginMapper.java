package com.projectbs.www.main.service.impl;

import java.util.List;
import java.util.Map;

import com.projectbs.www.vo.UserVo;

import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper
public interface LoginMapper {

	public UserVo loginCheck(UserVo vo) throws Exception;

	public void insertMemberInfo(UserVo vo)  throws Exception;
		
}
