package com.nexicure.nim.entities.vo;

import java.io.Serializable;

import com.nexicure.commons.services.beans.InetOrgPersonImpl;

public class ErAccountItemVO extends InetOrgPersonImpl implements Serializable {
	private static final long serialVersionUID = 1173011758804l;

	public String dn;
	public String eruid;
	public String erpassword;
	public String erparent;
	public ErPersonItemVO parentperson;
	public String ercreatedate;
	public String erglobalid;
	public String erservice;
	public String eraccountstatus;
	public String eraccountcompliance;
	public String erchangepswdrequired;
	public String erlostpasswordanswer;
	public String erpswdlastchanged;
	public String erpasswordlastchange;
	public String erposixpwdlastchange;
	public String erntpasswordage;
	public String ernumlogonattempt;
	public String erunixprimarygroup;
	public String erunixsecondarygroup;
	public boolean isorphan;
	public String owner;
	public String pcn;
	public String pemployeenumber;
	public String erlastaccessdate;
	public String erposixlastaccessdate;
	public String erlaststatuschangedate;
	public String erunixshell;
	public String gecos;
	public String uidnumber;

	public String simnotlogonaccount;
	public String simdefaultaccount;
	public String simdormantaccount;

	public String erdeptname;
	public String orgchartname;

	public String erservicename;
	public String erservicedesc;
	public String erserviceip;
	public String simcategory;
	public String simsystempwdvl;

	public String check_item;
	public String column_status;
	public Long column_idx;
	
	public String getErservicename() {
		return erservicename;
	}

	public void setErservicename(String erservicename) {
		this.erservicename = erservicename;
	}

	public String getErservicedesc() {
		return erservicedesc;
	}

	public String getErserviceip() {
		return erserviceip;
	}

	public void setSimcategory(String simcategory) {
		this.simcategory = simcategory;
	}

	public String getSimcategory() {
		return simcategory;
	}

	public void setErservicedesc(String erservicedesc) {
		this.erservicedesc = erservicedesc;
	}

	public void setErserviceip(String erserviceip) {
		this.erserviceip = erserviceip;
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

	public String getEraccountstatus() {
		return eraccountstatus;
	}

	public void setEraccountstatus(String eraccountstatus) {
		this.eraccountstatus = eraccountstatus;
	}

	public String getErcreatedate() {
		return ercreatedate;
	}

	public void setErcreatedate(String ercreatedate) {
		this.ercreatedate = ercreatedate;
	}

	public String getErglobalid() {
		return erglobalid;
	}

	public void setErglobalid(String erglobalid) {
		this.erglobalid = erglobalid;
	}

	public String getErparent() {
		return erparent;
	}

	public void setErparent(String erparent) {
		this.erparent = erparent;
	}

	public String getErpassword() {
		return erpassword;
	}

	public void setErpassword(String erpassword) {
		this.erpassword = erpassword;
	}

	public String getErpswdlastchanged() {
		return erpswdlastchanged;
	}

	public void setErpswdlastchanged(String erpswdlastchanged) {
		this.erpswdlastchanged = erpswdlastchanged;
	}

	// TRU64, HPUX, SORARIS
	public String getErpasswordlastchange() {
		return erpasswordlastchange;
	}

	public void setErpasswordlastchange(String erpasswordlastchange) {
		if(erpasswordlastchange != null && erpasswordlastchange.length()>9)
			this.erpasswordlastchange = erpasswordlastchange.substring(0, 10);
		else
			this.erpasswordlastchange = erpasswordlastchange;
	}

	// POSIX
	public String getErposixpwdlastchange() {
		return erposixpwdlastchange;
	}

	public void setErposixpwdlastchange(String erposixpwdlastchange) {
		this.erposixpwdlastchange = erposixpwdlastchange;
	}

	public String getErservice() {
		return erservice;
	}

	public void setErservice(String erservice) {
		this.erservice = erservice;
	}

	public String getEruid() {
		return eruid;
	}

	public void setEruid(String eruid) {
		this.eruid = eruid;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getPcn() {
		return pcn;
	}

	public void setPcn(String pcn) {
		this.pcn = pcn;
	}

	public String getPemployeenumber() {
		return pemployeenumber;
	}

	public void setPemployeenumber(String pemployeenumber) {
		this.pemployeenumber = pemployeenumber;
	}

	public String getDn() {
		return dn;
	}

	public void setDn(String dn) {
		this.dn = dn;
	}

	public String getErchangepswdrequired() {
		return erchangepswdrequired;
	}

	public void setErchangepswdrequired(String erchangepswdrequired) {
		this.erchangepswdrequired = erchangepswdrequired;
	}

	public String getErlostpasswordanswer() {
		return erlostpasswordanswer;
	}

	public void setErlostpasswordanswer(String erlostpasswordanswer) {
		this.erlostpasswordanswer = erlostpasswordanswer;
	}

	public String getErnumlogonattempt() {
		return ernumlogonattempt;
	}

	public void setErnumlogonattempt(String ernumlogonattempt) {
		this.ernumlogonattempt = ernumlogonattempt;
	}

	public String getErlastaccessdate() {
		return erlastaccessdate;
	}

	public void setErlastaccessdate(String erlastaccessdate) {
		this.erlastaccessdate = erlastaccessdate;
	}

	public String getErposixlastaccessdate() {
		return erposixlastaccessdate;
	}

	public void setErposixlastaccessdate(String erposixlastaccessdate) {
		this.erposixlastaccessdate = erposixlastaccessdate;
	}

	public boolean isOrphan() {
		return isorphan;
	}

	public void setOrphan(boolean isorphan) {
		this.isorphan = isorphan;
	}

	public String getGecos() {
		return gecos;
	}

	public void setGecos(String gecos) {
		this.gecos = gecos;
	}

	public String getUidnumber() {
		return uidnumber;
	}

	public void setUidnumber(String uidnumber) {
		this.uidnumber = uidnumber;
	}

	public String getErunixprimarygroup() {
		return erunixprimarygroup;
	}

	public void setErunixprimarygroup(String erunixprimarygroup) {
		this.erunixprimarygroup = erunixprimarygroup;
	}

	public String getErunixsecondarygroup() {
		return erunixsecondarygroup;
	}

	public void setErunixsecondarygroup(String erunixsecondarygroup) {
		this.erunixsecondarygroup = erunixsecondarygroup;
	}

	public ErPersonItemVO getParentperson() {
		return parentperson;
	}

	public void setParentperson(ErPersonItemVO parentperson) {
		this.parentperson = parentperson;
	}

	public String getEraccountcompliance() {
		return eraccountcompliance;
	}

	public void setEraccountcompliance(String eraccountcompliance) {
		this.eraccountcompliance = eraccountcompliance;
	}

	public String getErlaststatuschangedate() {
		return erlaststatuschangedate;
	}

	public void setErlaststatuschangedate(String erlaststatuschangedate) {
		this.erlaststatuschangedate = erlaststatuschangedate;
	}

	public String getErunixshell() {
		return erunixshell;
	}

	public void setErunixshell(String erunixshell) {
		this.erunixshell = erunixshell;
	}

	public String getErntpasswordage() {
		return erntpasswordage;
	}

	public void setErntpasswordage(String erntpasswordage) {
		this.erntpasswordage = erntpasswordage;
	}

	public String getSimnotlogonaccount() {
		return simnotlogonaccount;
	}

	public void setSimnotlogonaccount(String simnotlogonaccount) {
		this.simnotlogonaccount = simnotlogonaccount;
	}

	public String getSimdefaultaccount() {
		return simdefaultaccount;
	}

	public void setSimdefaultaccount(String simdefaultaccount) {
		this.simdefaultaccount = simdefaultaccount;
	}

	public String getSimdormantaccount() {
		return simdormantaccount;
	}

	public void setSimdormantaccount(String simdormantaccount) {
		this.simdormantaccount = simdormantaccount;
	}
	
	public String getErdeptname() {
		return erdeptname;
	}

	public void setErdeptname(String erdeptname) {
		this.erdeptname = erdeptname;
	}

	public String getOrgchartname() {
		return orgchartname;
	}

	public void setOrgchartname(String orgchartname) {
		this.orgchartname = orgchartname;
	}

	public boolean isIsorphan() {
		return isorphan;
	}

	public void setIsorphan(boolean isorphan) {
		this.isorphan = isorphan;
	}

	public String getSimsystempwdvl() {
		return simsystempwdvl;
	}

	public void setSimsystempwdvl(String simsystempwdvl) {
		this.simsystempwdvl = simsystempwdvl;
	}
}
