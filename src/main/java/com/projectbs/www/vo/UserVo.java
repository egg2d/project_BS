package com.projectbs.www.vo;

import lombok.Data;

@Data
public class UserVo {

	
	private int USER_ID_NO;
	private String USER_ID;
	private String PASSWORD;
	private String EMAIL;
	private String USER_LVL;
	private String DEL_YN;
	private int rowKey;
	private int sortKey;
	private String uniqueKey;
	
	public int getUSER_ID_NO() {
		return USER_ID_NO;
	}
	public void setUSER_ID_NO(int uSER_ID_NO) {
		USER_ID_NO = uSER_ID_NO;
	}

	public int getRowKey() {
		return rowKey;
	}
	public void setRowKey(int rowKey) {
		this.rowKey = rowKey;
	}
	public int getSortKey() {
		return sortKey;
	}
	public void setSortKey(int sortKey) {
		this.sortKey = sortKey;
	}
	public String getUniqueKey() {
		return uniqueKey;
	}
	public void setUniqueKey(String uniqueKey) {
		this.uniqueKey = uniqueKey;
	}
	
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
