package com.nexicure.nim.entities.vo;

import java.io.Serializable;

public class ErSecurityDomainItemVO implements Serializable {
	private static final long serialVersionUID = -788753603612529474L;
	
	public String dn;
	public String ou;
	public String description;
	public String eradministrator;
	
	public String persondn;
	public String uid;
	public String cn;
	public String departmentname;

	public String check_item;
	public String column_status;
	public Long column_idx;
	
	public String getDn() {
		return dn;
	}
	public void setDn(String dn) {
		this.dn = dn;
	}
	public String getOu() {
		return ou;
	}
	public void setOu(String ou) {
		this.ou = ou;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getEradministrator() {
		return eradministrator;
	}
	public void setEradministrator(String eradministrator) {
		this.eradministrator = eradministrator;
	}
	public String getPersondn() {
		return persondn;
	}
	public void setPersondn(String persondn) {
		this.persondn = persondn;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getCn() {
		return cn;
	}
	public void setCn(String cn) {
		this.cn = cn;
	}
	public String getDepartmentname() {
		return departmentname;
	}
	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}
	public String getCheck_item() {
		return check_item;
	}
	public void setCheck_item(String check_item) {
		this.check_item = check_item;
	}
	public String getColumn_status() {
		return column_status;
	}
	public void setColumn_status(String column_status) {
		this.column_status = column_status;
	}
	public Long getColumn_idx() {
		return column_idx;
	}
	public void setColumn_idx(Long column_idx) {
		this.column_idx = column_idx;
	}
}
