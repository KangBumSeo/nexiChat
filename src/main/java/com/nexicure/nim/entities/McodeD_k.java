package com.nexicure.nim.entities;

import java.io.Serializable;

public class McodeD_k implements Serializable {

	protected String company_code;
	protected String code_class;
	protected String code;
	
	public McodeD_k() {}
	public McodeD_k(String company_code, String code_class, String code) {
		this.company_code = company_code;
		this.code_class = code_class;
		this.code = code;
	}
}
