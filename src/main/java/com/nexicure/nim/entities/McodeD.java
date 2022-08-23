package com.nexicure.nim.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "mcode_d")
public class McodeD implements Serializable {

	@EmbeddedId
	private McodeD_k mcodeD_k;
	
	public McodeD_k getMcodeD_k() {
		return mcodeD_k;
	}
	public void setMcodeD_k(McodeD_k mcodeD_k) {
		this.mcodeD_k = mcodeD_k;
	}
	
	@Column(name = "code_name")
	private String code_name;
	
	@Column(name = "code_eng_name")
	private String code_eng_name;
	
	@Column(name = "use_flag")
	private String use_flag;
	
	@Column(name = "sort_num")
	private String sort_num;
	

	
	public String getCode_name() {
		return code_name;
	}
	public void setCode_name(String code_name) {
		this.code_name = code_name;
	}
	public String getCode_eng_name() {
		return code_eng_name;
	}
	public void setCode_eng_name(String code_eng_name) {
		this.code_eng_name = code_eng_name;
	}
	public String getUse_flag() {
		return use_flag;
	}
	public void setUse_flag(String use_flag) {
		this.use_flag = use_flag;
	}
	public String getSort_num() {
		return sort_num;
	}
	public void setSort_num(String sort_num) {
		this.sort_num = sort_num;
	}
	
}
