package com.nexicure.nim.entities.vo;

import java.io.Serializable;

import com.nexicure.commons.services.beans.InetOrgPersonImpl;

public class ErPersonItemVO extends InetOrgPersonImpl implements Serializable {
	private static final long serialVersionUID = 1173011758704l;

	public String eruid;

	public String erpassword;

	public String dn;

	public String erroles;

	public String st;

	public String erparent;

	public String erpersonstatus;

	public String erglobalid;

	public String erlastmodifiedtime;
	
	public String department;
	
	public String departmentname;

	public String check_item;

	public String column_status;

	public Long column_idx;
	
	public String persongrade;

	public String getPersongrade() {
		return persongrade;
	}

	public void setPersongrade(String persongrade) {
		this.persongrade = persongrade;
	}
	

	public String getErglobalid() {
		return erglobalid;
	}

	public void setErglobalid(String erglobalid) {
		this.erglobalid = erglobalid;
	}

	public String getErlastmodifiedtime() {
		return erlastmodifiedtime;
	}

	public void setErlastmodifiedtime(String erlastmodifiedtime) {
		this.erlastmodifiedtime = erlastmodifiedtime;
	}
	

	public String getErparent() {
		return erparent;
	}

	public void setErparent(String erparent) {
		this.erparent = erparent;
	}
	
	public String getSt() {
		return st;
	}

	public void setSt(String st) {
		this.st = st;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDepartmentname() {
		return departmentname;
	}

	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}

	public String getErpersonstatus() {
		return erpersonstatus;
	}

	public void setErpersonstatus(String erpersonstatus) {
		this.erpersonstatus = erpersonstatus;
	}

	public String getErroles() {
		return erroles;
	}

	public void setErroles(String erroles) {
		this.erroles = erroles;
	}

	public String getDn() {
		return dn;
	}

	public void setDn(String dn) {
		this.dn = dn;
	}

	public String getErpassword() {
		return erpassword;
	}

	public void setErpassword(String erpassword) {
		this.erpassword = erpassword;
	}

	public String getEruid() {
		return eruid;
	}

	public void setEruid(String eruid) {
		this.eruid = eruid;
	}

	public String getCheck_item() {
		return check_item;
	}

	public void setCheck_item(String check_item) {
		this.check_item = check_item;
	}

	public Long getColumn_idx() {
		return column_idx;
	}

	public void setColumn_idx(Long column_idx) {
		this.column_idx = column_idx;
	}

	public String getColumn_status() {
		return column_status;
	}

	public void setColumn_status(String column_status) {
		this.column_status = column_status;
	}
}
