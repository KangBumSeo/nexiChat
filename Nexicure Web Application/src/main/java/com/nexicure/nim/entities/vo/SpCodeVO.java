package com.nexicure.nim.entities.vo;

import java.io.Serializable;
import java.sql.*;

public class SpCodeVO implements Serializable {
	private static final long serialVersionUID = 1186746934937l;
	public String defaultQry = "SELECT SpCode.* FROM SP_CODE SpCode ";
	
	public String dn;
	/**
	 * ColumnName = uuid
	 * ColumnDataType = NUMBER(8)
	 * Remarks = null
	 * Default Value = '' (Nullable = NO)
	 */
	public Long uuid;
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public Long getUuid() {
		return this.uuid;
	}

	/**
	 * ColumnName = uname
	 * ColumnDataType = VARCHAR2(64)
	 * Remarks = null
	 * Default Value = '' (Nullable = YES)
	 */
	public String uname;
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUname() {
		return this.uname;
	}

	/**
	 * ColumnName = desct
	 * ColumnDataType = VARCHAR2(256)
	 * Remarks = null
	 * Default Value = '' (Nullable = YES)
	 */
	public String desct;
	public void setDesct(String desct) {
		this.desct = desct;
	}
	public String getDesct() {
		return this.desct;
	}

	/**
	 * ColumnName = use_flag
	 * ColumnDataType = VARCHAR2(1)
	 * Remarks = null
	 * Default Value = '' (Nullable = YES)
	 */
	public String use_flag;
	public void setUse_flag(String use_flag) {
		this.use_flag = use_flag;
	}
	public String getUse_flag() {
		return this.use_flag;
	}

	/**
	 * ColumnName = cre_empno
	 * ColumnDataType = VARCHAR2(32)
	 * Remarks = null
	 * Default Value = '' (Nullable = YES)
	 */
	public String cre_empno;
	public void setCre_empno(String cre_empno) {
		this.cre_empno = cre_empno;
	}
	public String getCre_empno() {
		return this.cre_empno;
	}

	/**
	 * ColumnName = cre_date
	 * ColumnDataType = DATE(7)
	 * Remarks = null
	 * Default Value = '' (Nullable = YES)
	 */
	public Timestamp cre_date;
	public void setCre_date(Timestamp cre_date) {
		this.cre_date = cre_date;
	}
	public Timestamp getCre_date() {
		return this.cre_date;
	}

	/**
	 * ColumnName = mod_empno
	 * ColumnDataType = VARCHAR2(32)
	 * Remarks = null
	 * Default Value = '' (Nullable = YES)
	 */
	public String mod_empno;
	public void setMod_empno(String mod_empno) {
		this.mod_empno = mod_empno;
	}
	public String getMod_empno() {
		return this.mod_empno;
	}

	/**
	 * ColumnName = mod_date
	 * ColumnDataType = DATE(7)
	 * Remarks = null
	 * Default Value = '' (Nullable = YES)
	 */
	public Timestamp mod_date;
	public void setMod_date(Timestamp mod_date) {
		this.mod_date = mod_date;
	}
	public Timestamp getMod_date() {
		return this.mod_date;
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
	public String getDn() {
		return dn;
	}
	public void setDn(String dn) {
		this.dn = dn;
	}
}

