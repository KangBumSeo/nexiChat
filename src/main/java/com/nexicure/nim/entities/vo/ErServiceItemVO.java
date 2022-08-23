package com.nexicure.nim.entities.vo;

import java.io.Serializable;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import com.nexicure.es.services.common.StringUtils;

@Entry(
		objectClasses = {"top","erManagedItem","erServiceItem","erRemoteServiceItem","erAccessItem"}
	)
public class ErServiceItemVO implements Serializable,Comparable<ErServiceItemVO> {
	private static final long serialVersionUID = 1173011758709l;
	
	@Id
	public Name id;

	public String erparent;

	public String erglobalid;

	public String erservicename;

	public String erserviceuid;

	public String erpassword;

	public String erurl;

	public String servicetype;

	public String dn;

	public String description;

	public String simcategory;

	public String simservicegroup;

	public String simreconstat;

	public String simresstatus;

	public String simsystempwdvl;

	public String provider_id;

	public String recon_id;

	public String recon_status;

	public String last_recon_time;

	public String resource_status;

	public String day_of_month;

	public String month_num;

	public String day_of_week;

	public String hour_num;

	public String minute_num;

	public String max_duration;

	public String owner;

	public String ownername;
	
	public String support_data_only;

	public String check_item;

	public String column_status;

	public Long column_idx;

	
	
	public String getId() {
		return id==null?null:id.toString();
	}

	public void setId(Name id) {
		this.id = id;
	}

	public Long getColumn_idx() {
		return column_idx;
	}

	public void setColumn_idx(Long column_idx) {
		this.column_idx = column_idx;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getErservicename() {
		return erservicename;
	}

	public void setErservicename(String erservicename) {
		this.erservicename = erservicename;
	}

	public String getErparent() {
		return erparent;
	}

	public void setErparent(String erparent) {
		this.erparent = erparent;
	}

	public String getSimservicegroup() {
		return simservicegroup;
	}

	public void setSimservicegroup(String simservicegroup) {
		this.simservicegroup = simservicegroup;
	}

	public String getSimreconstat() {
		return simreconstat;
	}

	public void setSimreconstat(String simreconstat) {
		this.simreconstat = simreconstat;
	}

	public String getSimcategory() {
		return simcategory;
	}

	public void setSimcategory(String simcategory) {
		this.simcategory = simcategory;
	}

	public String getSimresstatus() {
		return simresstatus;
	}

	public void setSimresstatus(String simresstatus) {
		this.simresstatus = simresstatus;
	}

	public String getErurl() {
		return erurl;
	}

	public void setErurl(String erurl) {
		this.erurl = erurl;
	}

	public String getServicetype() {
		return servicetype;
	}

	public void setServicetype(String servicetype) {
		this.servicetype = servicetype;
	}

	public String getProvider_id() {
		return provider_id;
	}

	public void setProvider_id(String providerId) {
		provider_id = providerId;
	}

	public String getRecon_id() {
		return recon_id;
	}

	public void setRecon_id(String reconId) {
		recon_id = reconId;
	}

	public String getDay_of_month() {
		return day_of_month;
	}

	public void setDay_of_month(String dayOfMonth) {
		day_of_month = dayOfMonth;
	}

	public String getMonth_num() {
		return month_num;
	}

	public void setMonth_num(String monthNum) {
		month_num = monthNum;
	}

	public String getDay_of_week() {
		return day_of_week;
	}

	public void setDay_of_week(String dayOfWeek) {
		day_of_week = dayOfWeek;
	}

	public String getHour_num() {
		return hour_num;
	}

	public void setHour_num(String hourNum) {
		hour_num = hourNum;
	}

	public String getMinute_num() {
		return minute_num;
	}

	public void setMinute_num(String minuteNum) {
		minute_num = minuteNum;
	}

	public String getMax_duration() {
		return max_duration;
	}

	public void setMax_duration(String maxDuration) {
		max_duration = maxDuration;
	}

	public String getRecon_status() {
		return recon_status;
	}

	public void setRecon_status(String reconStatus) {
		recon_status = reconStatus;
	}

	public String getLast_recon_time() {
		return last_recon_time;
	}

	public void setLast_recon_time(String lastReconTime) {
		last_recon_time = lastReconTime;
	}

	public String getResource_status() {
		return resource_status;
	}

	public void setResource_status(String resourceStatus) {
		resource_status = resourceStatus;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getOwnername() {
		return ownername;
	}

	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}

	public String getSimsystempwdvl() {
		return simsystempwdvl;
	}

	public void setSimsystempwdvl(String simsystempwdvl) {
		this.simsystempwdvl = simsystempwdvl;
	}

	public String getErserviceuid() {
		return erserviceuid;
	}

	public void setErserviceuid(String erserviceuid) {
		this.erserviceuid = erserviceuid;
	}

	public String getErpassword() {
		return erpassword;
	}

	public void setErpassword(String erpassword) {
		this.erpassword = erpassword;
	}

	public String getSupport_data_only() {
		return support_data_only;
	}

	public void setSupport_data_only(String support_data_only) {
		this.support_data_only = support_data_only;
	}
	
	@Override
	public int compareTo(ErServiceItemVO vo) {
		if(this.dn == null && this.id != null) {this.dn = this.id.toString();} // Set DN
		if(vo.getDn() == null && vo.getId() != null) {vo.setDn(vo.getId());} // Set DN

		return StringUtils.nullToString(vo.getErservicename(),"")
				.compareTo( StringUtils.nullToString(vo.getErservicename(),"") );
	}
}
