package com.nexicure.nim.entities.vo;

import java.io.Serializable;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

@Entry(
		objectClasses = {"top","erManagedItem"}
	)
public class ErManagedItemVO implements Serializable, Comparable {
	private static final long serialVersionUID = 1173011758705l;

	public String erparent;

	public String erglobalid;

	public String o;

	public String erorgstatus;

	@Id
	public Name id;

	public String dn;

	public String check_item;

	public String column_status;

	public Long column_idx;

	public String erurl;

	public String erpassword;

	public String erservicename;

	public String description;

	public String eritdicategory;

	public String eruid;

	public String ou;

	public String namingcontexts;


	public String getNamingcontexts() {
		return namingcontexts;
	}

	public void setNamingcontexts(String namingcontexts) {
		this.namingcontexts = namingcontexts;
	}

	public String getOu() {
		return ou;
	}

	public void setOu(String ou) {
		this.ou = ou;
	}

	public String getEruid() {
		return eruid;
	}

	public void setEruid(String eruid) {
		this.eruid = eruid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEritdicategory() {
		return eritdicategory;
	}

	public void setEritdicategory(String eritdicategory) {
		this.eritdicategory = eritdicategory;
	}

	public String getErpassword() {
		return erpassword;
	}

	public void setErpassword(String erpassword) {
		this.erpassword = erpassword;
	}

	public String getErservicename() {
		return erservicename;
	}

	public void setErservicename(String erservicename) {
		this.erservicename = erservicename;
	}

	public String getErurl() {
		return erurl;
	}

	public void setErurl(String erurl) {
		this.erurl = erurl;
	}


	public String getId() {
		return id==null?null:id.toString();
	}

	public void setId(Name id) {
		this.id = id;
	}

	public String getDn() {
		return dn;
	}

	public void setDn(String dn) {
		this.dn = dn;
	}

	public String getErglobalid() {
		return erglobalid;
	}

	public void setErglobalid(String erglobalid) {
		this.erglobalid = erglobalid;
	}

	public String getErorgstatus() {
		return erorgstatus;
	}

	public void setErorgstatus(String erorgstatus) {
		this.erorgstatus = erorgstatus;
	}

	public String getErparent() {
		return erparent;
	}

	public void setErparent(String erparent) {
		this.erparent = erparent;
	}

	public String getO() {
		return o;
	}

	public void setO(String o) {
		this.o = o;
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

	public int compareTo(Object vo) {
		try{
			if(this.dn == null && this.id != null) {this.dn = this.id.toString();} // Set DN
			if(((ErManagedItemVO)vo).getDn() == null && ((ErManagedItemVO)vo).getId() != null) {((ErManagedItemVO)vo).setDn(((ErManagedItemVO)vo).getId());} // Set DN
			
			int ou = Integer.parseInt(this.ou);
			int ouVO = Integer.parseInt(((ErManagedItemVO)vo).ou);
			
			if(ou > ouVO) return 1;
			else if(ou == ouVO) return 0;
			else return -1;
		} catch(Exception e){
			return 0;
		}
	}
}
