package com.nexicure.nim.entities.vo;

import java.io.Serializable;

public class ActivityVO implements Serializable {

	private static final long serialVersionUID = -5775878336425755659L;

	public String id;

	public String process_id;

	public String definition_id;

	public String activity_index;

	public String loop_count;

	public String loop_runcount;

	public String retry_count;

	public String lock_count;

	public String subprocess_id;

	private String name;

	public String description;

	public String type;

	public String subtype;

	public String priority;

	public String started;

	public String completed;

	public String lastmodified;

	public String state;

	public String result_summary;

	public String result_detail;

	public String getActivity_index() {
		return activity_index;
	}

	public void setActivity_index(String activity_index) {
		this.activity_index = activity_index;
	}

	public String getDefinition_id() {
		return definition_id;
	}

	public void setDefinition_id(String definition_id) {
		this.definition_id = definition_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLastmodified() {
		return lastmodified;
	}

	public void setLastmodified(String lastmodified) {
		this.lastmodified = lastmodified;
	}

	public String getLock_count() {
		return lock_count;
	}

	public void setLock_count(String lock_count) {
		this.lock_count = lock_count;
	}

	public String getLoop_count() {
		return loop_count;
	}

	public void setLoop_count(String loop_count) {
		this.loop_count = loop_count;
	}

	public String getLoop_runcount() {
		return loop_runcount;
	}

	public void setLoop_runcount(String loop_runcount) {
		this.loop_runcount = loop_runcount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getProcess_id() {
		return process_id;
	}

	public void setProcess_id(String process_id) {
		this.process_id = process_id;
	}

	public String getResult_detail() {
		return result_detail;
	}

	public void setResult_detail(String result_detail) {
		this.result_detail = result_detail;
	}

	public String getResult_summary() {
		return result_summary;
	}

	public void setResult_summary(String result_summary) {
		this.result_summary = result_summary;
	}

	public String getRetry_count() {
		return retry_count;
	}

	public void setRetry_count(String retry_count) {
		this.retry_count = retry_count;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSubprocess_id() {
		return subprocess_id;
	}

	public void setSubprocess_id(String subprocess_id) {
		this.subprocess_id = subprocess_id;
	}

	public String getSubtype() {
		return subtype;
	}

	public void setSubtype(String subtype) {
		this.subtype = subtype;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCompleted() {
		return completed;
	}

	public void setCompleted(String completed) {
		this.completed = completed;
	}

	public String getStarted() {
		return started;
	}

	public void setStarted(String started) {
		this.started = started;
	}
}
