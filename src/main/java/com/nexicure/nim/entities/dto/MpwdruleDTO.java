package com.nexicure.nim.entities.dto;

import com.nexicure.nim.entities.vo.MpwdruleVO;

public class MpwdruleDTO extends MpwdruleVO {
	public String rule_dept_name;
	public String createusername;
	public int apply_service_count;
	public int apply_account_count;
	/**
	 * @return the rule_dept_name
	 */
	public String getRule_dept_name() {
		return rule_dept_name;
	}
	/**
	 * @param ruleDeptName the rule_dept_name to set
	 */
	public void setRule_dept_name(String ruleDeptName) {
		rule_dept_name = ruleDeptName;
	}
	/**
	 * @return the create_username
	 */
	public String getCreateusername() {
		return createusername;
	}
	/**
	 * @param createUsername the create_username to set
	 */
	public void setCreateusername(String createUsername) {
		createusername = createUsername;
	}
	/**
	 * @return the apply_service_count
	 */
	public int getApply_service_count() {
		return apply_service_count;
	}
	/**
	 * @param applyServiceCount the apply_service_count to set
	 */
	public void setApply_service_count(int applyServiceCount) {
		apply_service_count = applyServiceCount;
	}
	/**
	 * @return the apply_account_count
	 */
	public int getApply_account_count() {
		return apply_account_count;
	}
	/**
	 * @param applyAccountCount the apply_account_count to set
	 */
	public void setApply_account_count(int applyAccountCount) {
		apply_account_count = applyAccountCount;
	}
	
	public String rule_system_name;
	public String getRule_system_name() {
		return rule_system_name;
	}
	public void setRule_system_name(String rule_system_name) {
		this.rule_system_name = rule_system_name;
	}
}

