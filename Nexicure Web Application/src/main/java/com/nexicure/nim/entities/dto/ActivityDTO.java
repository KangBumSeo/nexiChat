package com.nexicure.nim.entities.dto;

import com.nexicure.nim.entities.vo.ActivityVO;

public class ActivityDTO extends ActivityVO {

	private static final long serialVersionUID = -3853259264675903083L;

	public String tname;

	public String requester;

	public String requester_type;

	public String requester_name;

	public String requestee;

	public String requestee_name;

	public String subject;

	public String subject_profile;

	public String subject_service;

	public String requestor;

	public String root_process_id;

	public String tname_kor;

	public String getTname_kor() {
		return tname_kor;
	}

	public void setTname_kor(String tname_kor) {
		this.tname_kor = tname_kor;
	}

	public String getRoot_process_id() {
		return root_process_id;
	}

	public void setRoot_process_id(String root_process_id) {
		this.root_process_id = root_process_id;
	}

	public String getRequestor() {
		return requestor;
	}

	public void setRequestor(String requestor) {
		this.requestor = requestor;
	}

	public String getRequestee() {
		return requestee;
	}

	public void setRequestee(String requestee) {
		this.requestee = requestee;
	}

	public String getRequestee_name() {
		return requestee_name;
	}

	public void setRequestee_name(String requestee_name) {
		this.requestee_name = requestee_name;
	}

	public String getRequester() {
		return requester;
	}

	public void setRequester(String requester) {
		this.requester = requester;
	}

	public String getRequester_name() {
		return requester_name;
	}

	public void setRequester_name(String requester_name) {
		this.requester_name = requester_name;
	}

	public String getRequester_type() {
		return requester_type;
	}

	public void setRequester_type(String requester_type) {
		this.requester_type = requester_type;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubject_profile() {
		return subject_profile;
	}

	public void setSubject_profile(String subject_profile) {
		this.subject_profile = subject_profile;
	}

	public String getSubject_service() {
		return subject_service;
	}

	public void setSubject_service(String subject_service) {
		this.subject_service = subject_service;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

}
