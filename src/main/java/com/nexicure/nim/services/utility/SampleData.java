package com.nexicure.nim.services.utility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.springframework.stereotype.Service;

import com.nexicure.nim.entities.vo.McodedescVO;

@Service
public class SampleData {
	private Logger logger = LogManager.getLogger(SampleData.class);
	
	public List getDeptCode() {
		McodedescVO mcodedescVO = new McodedescVO();
		List<McodedescVO> list = new ArrayList();
		
		mcodedescVO.setCode("D001");
		mcodedescVO.setCodeName("ITO개발팀");
		list.add(mcodedescVO);
		mcodedescVO = new McodedescVO();
		mcodedescVO.setCode("D002");
		mcodedescVO.setCodeName("SCM팀");
		list.add(mcodedescVO);
		
		return list;
	}
	public List getPositionCode() {
		McodedescVO mcodedescVO = new McodedescVO();
		List<McodedescVO> list = new ArrayList();
		
		mcodedescVO.setCode("P001");
		mcodedescVO.setCodeName("파트리더");
		list.add(mcodedescVO);
		mcodedescVO = new McodedescVO();
		mcodedescVO.setCode("P003");
		mcodedescVO.setCodeName("팀리더");
		list.add(mcodedescVO);
		mcodedescVO = new McodedescVO();
		mcodedescVO.setCode("P002");
		mcodedescVO.setCodeName("관리부장");
		list.add(mcodedescVO);
		
		return list;
	}
}
