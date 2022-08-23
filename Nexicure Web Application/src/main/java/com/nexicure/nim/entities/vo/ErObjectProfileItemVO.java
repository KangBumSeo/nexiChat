package com.nexicure.nim.entities.vo;

import java.io.Serializable;

public class ErObjectProfileItemVO implements Serializable {
	private static final long serialVersionUID = 1173011758729l;

	public String dn;

	public String erobjectprofilename;

	public String eraccountclass;

	public String eraccountname;

	public String ercategory;

	public String ercustomclass;

	public String errdnattr;

	public String check_item;

	public String column_status;

	public Long column_idx;

	public String getDn() {
		return dn;
	}

	public void setDn(String dn) {
		this.dn = dn;
	}

	public String getErobjectprofilename() {
		return erobjectprofilename;
	}

	public void setErobjectprofilename(String erobjectprofilename) {
		this.erobjectprofilename = erobjectprofilename;
	}

	public String getEraccountclass() {
		return eraccountclass;
	}

	public void setEraccountclass(String eraccountclass) {
		this.eraccountclass = eraccountclass;
	}

	public String getEraccountname() {
		return eraccountname;
	}

	public void setEraccountname(String eraccountname) {
		this.eraccountname = eraccountname;
	}

	public String getErcategory() {
		return ercategory;
	}

	public void setErcategory(String ercategory) {
		this.ercategory = ercategory;
	}

	public String getErcustomclass() {
		return ercustomclass;
	}

	public void setErcustomclass(String ercustomclass) {
		this.ercustomclass = ercustomclass;
	}

	public String getErrdnattr() {
		return errdnattr;
	}

	public void setErrdnattr(String errdnattr) {
		this.errdnattr = errdnattr;
	}

	public String getCheck_item() {
		return check_item;
	}

	public void setCheck_item(String checkItem) {
		check_item = checkItem;
	}

	public String getColumn_status() {
		return column_status;
	}

	public void setColumn_status(String columnStatus) {
		column_status = columnStatus;
	}

	public Long getColumn_idx() {
		return column_idx;
	}

	public void setColumn_idx(Long columnIdx) {
		column_idx = columnIdx;
	}

}
