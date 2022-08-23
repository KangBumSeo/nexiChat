package com.nexicure.nim.entities.vo;

import java.io.Serializable;
import java.sql.*;

public class MpwdruledescVO implements Serializable {
	private static final long serialVersionUID = 1259412354843l;
	public String defaultQry = "SELECT Mpwdruledesc.* FROM MPWDRULEDESC Mpwdruledesc ";

	/**
	 * ColumnName = rule_uuid
	 * ColumnDataType = INTEGER(10)
	 * Remarks = null
	 * Default Value = '' (Nullable = NO)
	 */
	public Long rule_uuid;
	public void setRule_uuid(Long rule_uuid) {
		this.rule_uuid = rule_uuid;
	}
	public Long getRule_uuid() {
		return this.rule_uuid;
	}

	/**
	 * ColumnName = rule_gubun
	 * ColumnDataType = VARCHAR(2)
	 * Remarks = null
	 * Default Value = '' (Nullable = NO)
	 */
	public String rule_gubun;
	public void setRule_gubun(String rule_gubun) {
		this.rule_gubun = rule_gubun;
	}
	public String getRule_gubun() {
		return this.rule_gubun;
	}

	/**
	 * ColumnName = pre
	 * ColumnDataType = VARCHAR(48)
	 * Remarks = null
	 * Default Value = '' (Nullable = YES)
	 */
	public String pre;
	public void setPre(String pre) {
		this.pre = pre;
	}
	public String getPre() {
		return this.pre;
	}

	/**
	 * ColumnName = pre_desc
	 * ColumnDataType = VARCHAR(200)
	 * Remarks = null
	 * Default Value = '' (Nullable = YES)
	 */
	public String pre_desc;
	public void setPre_desc(String pre_desc) {
		this.pre_desc = pre_desc;
	}
	public String getPre_desc() {
		return this.pre_desc;
	}

	/**
	 * ColumnName = mid
	 * ColumnDataType = VARCHAR(48)
	 * Remarks = null
	 * Default Value = '' (Nullable = YES)
	 */
	public String mid;
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMid() {
		return this.mid;
	}

	/**
	 * ColumnName = mid_desc
	 * ColumnDataType = VARCHAR(200)
	 * Remarks = null
	 * Default Value = '' (Nullable = YES)
	 */
	public String mid_desc;
	public void setMid_desc(String mid_desc) {
		this.mid_desc = mid_desc;
	}
	public String getMid_desc() {
		return this.mid_desc;
	}

	/**
	 * ColumnName = post
	 * ColumnDataType = VARCHAR(48)
	 * Remarks = null
	 * Default Value = '' (Nullable = YES)
	 */
	public String post;
	public void setPost(String post) {
		this.post = post;
	}
	public String getPost() {
		return this.post;
	}

	/**
	 * ColumnName = post_desc
	 * ColumnDataType = VARCHAR(200)
	 * Remarks = null
	 * Default Value = '' (Nullable = YES)
	 */
	public String post_desc;
	public void setPost_desc(String post_desc) {
		this.post_desc = post_desc;
	}
	public String getPost_desc() {
		return this.post_desc;
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

