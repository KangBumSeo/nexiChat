package com.nexicure.nim.entities;

import java.io.Serializable;

import javax.security.auth.Subject;

import com.nexicure.nim.entities.vo.TbUserVO;

public class UserBean implements Serializable {
	private static final long serialVersionUID = -3853259264775903083L;

	private String userId;
	private String userName;
	public String deptCode;
	public String deptName;
	public String[] authority;
	private Subject subject;
	private boolean regular;

	public UserBean(String userid, Subject subject) throws Exception {
		this.userId = userId;
		this.subject = subject;
		this.regular = false;
	}
	
	public UserBean(TbUserVO tbUserVO, String[] authority) throws Exception {
		this.userId = tbUserVO.getUserId();
		this.userName = tbUserVO.getUserName();
		this.deptCode = tbUserVO.getDeptCode();
		this.deptName = tbUserVO.getDeptName();
		this.authority = authority;
	}

	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String[] getAuthority() {
		return authority;
	}

	public void setAuthority(String[] authority) {
		this.authority = authority;
	}

	public Subject getSubject() {
		return subject;
	}
	
	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public boolean isRegular() {
		return regular;
	}

	public void setRegular(boolean regular) {
		this.regular = regular;
	}
}