package com.nexicure.nim.entities.vo;

import java.io.Serializable;

public class ErFpwdItemVO implements Serializable {
	private static final long serialVersionUID = 1173011758705l;

	public String dn;

	public String getDn() {
		return dn;
	}

	public void setDn(String dn) {
		this.dn = dn;
	}

	public String erword;

	public String description;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getErword() {
		return erword;
	}

	public void setErword(String erword) {
		this.erword = erword;
	}

	public Long column_idx;

	public void setColumn_idx(Long column_idx) {
		this.column_idx = column_idx;
	}

	public Long getColumn_idx() {
		return this.column_idx;
	}

	public String column_status;

	public void setColumn_status(String column_status) {
		this.column_status = column_status;
	}

	public String getColumn_status() {
		return this.column_status;
	}

	public String check_item;

	public void setCheck_item(String check_item) {
		this.check_item = check_item;
	}

	public String getCheck_item() {
		return this.check_item;
	}
}
