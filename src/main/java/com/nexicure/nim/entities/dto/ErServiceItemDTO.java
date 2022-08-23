package com.nexicure.nim.entities.dto;

import com.nexicure.nim.entities.vo.ErServiceItemVO;

public class ErServiceItemDTO extends ErServiceItemVO {
	private static final long serialVersionUID = 1173011758719l;
	public String accountdn;
	public String eraccountstatus;
	public String eraccountcompliance;
	public String erprofilename;
	public String getErprofilename() {
		return erprofilename;
	}
	public void setErprofilename(String erprofilename) {
		this.erprofilename = erprofilename;
	}
	public String eruid;

	public String getEruid() {
		return eruid;
	}
	public void setEruid(String eruid) {
		this.eruid = eruid;
	}
	public String getAccountdn() {
		return accountdn;
	}
	public void setAccountdn(String accountdn) {
		this.accountdn = accountdn;
	}
	public String getEraccountstatus() {
		return eraccountstatus;
	}
	public void setEraccountstatus(String eraccountstatus) {
		this.eraccountstatus = eraccountstatus;
	}
	public String getEraccountcompliance() {
		return eraccountcompliance;
	}
	public void setEraccountcompliance(String eraccountcompliance) {
		this.eraccountcompliance = eraccountcompliance;
	}
}
