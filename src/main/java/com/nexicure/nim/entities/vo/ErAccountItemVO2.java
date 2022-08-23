package com.nexicure.nim.entities.vo;

import java.io.Serializable;

import com.nexicure.commons.services.beans.InetOrgPersonImpl;

public class ErAccountItemVO2 implements Serializable {
	private static final long serialVersionUID = -4331108736105827653L;

	public String dn;
	public String eruid;
	public String orphan;
	public String owner;

	public String erservice;
	public String street;

	public String orgchartname;

	public String simsystempwdvl;

	public String uid;
	public String cn;
	
	public String check_item;
	public String column_status;
	public Long column_idx;

	public String getEruid() {
		return eruid;
	}

	public void setEruid(String eruid) {
		this.eruid = eruid;
	}

	public String getErservice() {
		return erservice;
	}

	public void setErservice(String erservice) {
		this.erservice = erservice;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getSimsystempwdvl() {
		return simsystempwdvl;
	}

	public void setSimsystempwdvl(String simsystempwdvl) {
		this.simsystempwdvl = simsystempwdvl;
	}

	public String getDn() {
		return dn;
	}

	public void setDn(String dn) {
		this.dn = dn;
	}

	public String getOrgchartname() {
		return orgchartname;
	}

	public void setOrgchartname(String orgchartname) {
		this.orgchartname = orgchartname;
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

	public String getOrphan() {
		return orphan;
	}

	public void setOrphan(String orphan) {
		this.orphan = orphan;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
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
}
