package com.nexicure.nim.entities.vo;

import java.io.Serializable;
import java.sql.*;

public class SpCodedescVO implements Serializable {
	private static final long serialVersionUID = 1186746935031l;
	public String defaultQry = "SELECT SpCodedesc.* FROM SP_CODEDESC SpCodedesc ";

	public String dn;
	/**
	 * ColumnName = uuid
	 * ColumnDataType = VARCHAR2(32)
	 * Remarks = null
	 * Default Value = '' (Nullable = NO)
	 */
	public String uuid;
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getUuid() {
		return this.uuid;
	}

	/**
	 * ColumnName = code_uuid
	 * ColumnDataType = NUMBER(8)
	 * Remarks = null
	 * Default Value = '' (Nullable = NO)
	 */
	public Long code_uuid;
	public void setCode_uuid(Long code_uuid) {
		this.code_uuid = code_uuid;
	}
	public Long getCode_uuid() {
		return this.code_uuid;
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
	 * ColumnName = temp1
	 * ColumnDataType = VARCHAR2(256)
	 * Remarks = null
	 * Default Value = '' (Nullable = YES)
	 */
	public String temp1;
	public void setTemp1(String temp1) {
		this.temp1 = temp1;
	}
	public String getTemp1() {
		return this.temp1;
	}

	/**
	 * ColumnName = temp2
	 * ColumnDataType = VARCHAR2(256)
	 * Remarks = null
	 * Default Value = '' (Nullable = YES)
	 */
	public String temp2;
	public void setTemp2(String temp2) {
		this.temp2 = temp2;
	}
	public String getTemp2() {
		return this.temp2;
	}

	/**
	 * ColumnName = temp3
	 * ColumnDataType = VARCHAR2(256)
	 * Remarks = null
	 * Default Value = '' (Nullable = YES)
	 */
	public String temp3;
	public void setTemp3(String temp3) {
		this.temp3 = temp3;
	}
	public String getTemp3() {
		return this.temp3;
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
	 * ColumnName = dorder
	 * ColumnDataType = NUMBER(4)
	 * Remarks = null
	 * Default Value = '' (Nullable = YES)
	 */
	public Long dorder;
	public void setDorder(Long dorder) {
		this.dorder = dorder;
	}
	public Long getDorder() {
		return this.dorder;
	}

	/**
	 * ColumnName = gtype
	 * ColumnDataType = VARCHAR2(16)
	 * Remarks = null
	 * Default Value = '' (Nullable = YES)
	 */
	public String gtype;
	public void setGtype(String gtype) {
		this.gtype = gtype;
	}
	public String getGtype() {
		return this.gtype;
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

