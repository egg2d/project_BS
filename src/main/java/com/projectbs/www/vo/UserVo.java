package com.projectbs.www.vo;

import lombok.Data;

@Data
public class UserVo {

	private String USER_ID;
	private String PASSWORD;
	private String EMAIL;
	private String USER_LVL;
	private String DEL_YN;
	
	public String getDEL_YN() {
		return DEL_YN;
	}
	public void setDEL_YN(String dEL_YN) {
		DEL_YN = dEL_YN;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public String getUSER_ID() {
		return USER_ID;
	}
	public void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}

	public String getUSER_LVL() {
		return USER_LVL;
	}
	public void setUSER_LVL(String uSER_LVL) {
		USER_LVL = uSER_LVL;
	}
}
