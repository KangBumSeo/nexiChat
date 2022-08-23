package com.nexicure.nim.entities.vo;

import org.apache.commons.lang.StringUtils;


/**
 * CREATE TABLE MUSER
(
   UID varchar(20) NOT NULL,
   EMPNO varchar(20) NOT NULL,
   NAME varchar(100),
   DEPT varchar(100),
   DEPT_NM varchar(100),
   POSITION varchar(100),
   POSITION_NM varchar(100),
   GRADE varchar(100),
   GRADE_NM varchar(100),
   MAIL varchar(100),
   CONSTRAINT MUSER_PK PRIMARY KEY (UID, EMPNO)
)
;

 * 
 * 
 */
public class MuserVO extends CommonVO {

	public MuserVO(int testSeq) {
		//this.id = "A"+ StringUtils.leftPad(testSeq+"", 6, "0");	// jqgrid 필수값 (uniq)
		this.uid = "A"+ StringUtils.leftPad(testSeq+"", 6, "0");
		this.empno = "A"+ StringUtils.leftPad(testSeq+"", 6, "0");
		this.name = "Manager"+ testSeq;
		this.dept = "D00"+ (testSeq%2+1);
		this.deptNm = "ITO개발팀";
		this.position = "P00"+ (testSeq%3+1);
		this.positionNm = "Manager";
	}
	
	public MuserVO() {
	}

	/*
	String id;			// jqgrid key필드로 가능한 미사용. 사용시 uniq한 값
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	*/
	
	public String uid;
	public String empno;
	public String name;
	public String dept;
	public String deptNm;
	public String position;
	public String positionNm;
	public String grade;
	public String gradeNm;
	public String mail;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getEmpno() {
		return empno;
	}
	public void setEmpno(String empno) {
		this.empno = empno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDept() {
		return dept;
	}
	public void setDept(String dept) {
		this.dept = dept;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getDeptNm() {
		return deptNm;
	}

	public void setDeptNm(String deptNm) {
		this.deptNm = deptNm;
	}

	public String getPositionNm() {
		return positionNm;
	}

	public void setPositionNm(String positionNm) {
		this.positionNm = positionNm;
	}

	public String getGradeNm() {
		return gradeNm;
	}

	public void setGradeNm(String gradeNm) {
		this.gradeNm = gradeNm;
	}

	
	

}
