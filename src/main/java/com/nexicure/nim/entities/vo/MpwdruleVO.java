package com.nexicure.nim.entities.vo;

import java.io.Serializable;
import java.sql.*;

public class MpwdruleVO implements Serializable {
	private static final long serialVersionUID = 1259412354796l;
	public String defaultQry = "SELECT Mpwdrule.* FROM MPWDRULE Mpwdrule ";

	
	/**
	 * ColumnName = uuid
	 * ColumnDataType = INTEGER(10)
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
	 * ColumnName = rule_dept
	 * ColumnDataType = VARCHAR(100)
	 * Remarks = null
	 * Default Value = '' (Nullable = YES)
	 */
	public String rule_dept;
	public void setRule_dept(String rule_dept) {
		this.rule_dept = rule_dept;
	}
	public String getRule_dept() {
		return this.rule_dept;
	}

	/**
	 * ColumnName = rule_type
	 * ColumnDataType = VARCHAR(100)
	 * Remarks = null
	 * Default Value = '' (Nullable = YES)
	 */
	public String rule_type;
	public void setRule_type(String rule_type) {
		this.rule_type = rule_type;
	}
	public String getRule_type() {
		return this.rule_type;
	}

	/**
	 * ColumnName = rule_name
	 * ColumnDataType = VARCHAR(100)
	 * Remarks = null
	 * Default Value = '' (Nullable = YES)
	 */
	public String rule_name;
	public void setRule_name(String rule_name) {
		this.rule_name = rule_name;
	}
	public String getRule_name() {
		return this.rule_name;
	}
	
	/**
	 * ColumnName = rule_desc
	 * ColumnDataType = VARCHAR(200)
	 * Remarks = null
	 * Default Value = '' (Nullable = YES)
	 */
	public String rule_desc;
	public void setRule_desc(String rule_desc) {
		this.rule_desc = rule_desc;
	}
	public String getRule_desc() {
		return this.rule_desc;
	}
	
	/**
	 * ColumnName = exe_day
	 * ColumnDataType = VARCHAR(2)
	 * Remarks = null
	 * Default Value = '' (Nullable = YES)
	 */
	public String exe_day;
	public void setExe_day(String exe_day) {
		this.exe_day = exe_day;
	}
	public String getExe_day() {
		return this.exe_day;
	}
	
	/**
	 * ColumnName = exe_time
	 * ColumnDataType = VARCHAR(2)
	 * Remarks = null
	 * Default Value = '' (Nullable = YES)
	 */
	public String exe_time;
	public void setExe_time(String exe_time) {
		this.exe_time = exe_time;
	}
	public String getExe_time() {
		return this.exe_time;
	}

	/**
	 * ColumnName = persondn
	 * ColumnDataType = VARCHAR(200)
	 * Remarks = null
	 * Default Value = '' (Nullable = YES)
	 */
	public String persondn;
	public void setPersondn(String persondn) {
		this.persondn = persondn;
	}
	public String getPersondn() {
		return this.persondn;
	}
	
	/**
	 * ColumnName = createuserid
	 * ColumnDataType = VARCHAR(50)
	 * Remarks = null
	 * Default Value = '' (Nullable = YES)
	 */
	public String createuserid;
	public void setCreateuserid(String createuserid) {
		this.createuserid = createuserid;
	}
	public String getCreateuserid() {
		return this.createuserid;
	}
	
	/**
	 * ColumnName = createuserdn
	 * ColumnDataType = VARCHAR(50)
	 * Remarks = null
	 * Default Value = '' (Nullable = YES)
	 */
	public String createuserdn;
	public void setCreateuserdn(String createuserdn) {
		this.createuserdn = createuserdn;
	}
	public String getCreateuserdn() {
		return this.createuserdn;
	}

	/**
	 * ColumnName = createdate
	 * ColumnDataType = DATE(10)
	 * Remarks = null
	 * Default Value = '' (Nullable = YES)
	 */
	public Timestamp createdate;
	public void setCreatedate(Timestamp createdate) {
		this.createdate = createdate;
	}
	public Timestamp getCreatedate() {
		return this.createdate;
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
	
	public String rule_system;
	public String getRule_system() {
		return rule_system;
	}
	public void setRule_system(String rule_system) {
		this.rule_system = rule_system;
	}
}

