package com.nexicure.nim.services;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexicure.nim.dao.nexiChatDAO;
import com.nexicure.nim.services.interf.nexiChatServiceInterface;

@Service("nexiChatService")
public class nexiChatService implements nexiChatServiceInterface {
	private Logger logger = LogManager.getLogger(nexiChatService.class);

	@Autowired
	private nexiChatDAO nd;
	
	@Override
	public ArrayList<HashMap<String, Object>> nexiChatAll() {
		// TODO Auto-generated method stub
		logger.info("nexiChatService nexiChatAll() 진입 >>> : ");
		return nd.nexiChatAll();
	}
	
	// select
	@Override
	public ArrayList<HashMap<String, Object>> nexiChat(HashMap<String, Object> cParam) {
		// TODO Auto-generated method stub
		logger.info("nexiChatService nexiChat() 진입 >>> : ");
		logger.info("nexiChatService cParam >>> : " + cParam);
		return nd.nexiChat(cParam);
	}

	// insert
	@Override
	public int nexiChatInsert(HashMap<String, Object> iParam) {
		// TODO Auto-generated method stub
		logger.info("nexiChatServiceInsert 진입 >>> : ");
		logger.info("nexiChatService iParam >>> : " + iParam);
		
		int tmp =  nd.nexiChatInsert(iParam);
		if(tmp == 0) {
			return tmp;
		}
		else {
			tmp = nexichatTableInsert(iParam);
		
			return tmp;
		}
	}
	
	// table create
	@Override
	public int nexichatTableInsert(HashMap<String, Object> iParam) {
		// TODO Auto-generated method stub
		logger.info("nexichatTableInsert 진입 >>> : ");
		return nd.nexichatTableInsert(iParam);
	}

	
	
	// update
	@Override
	public int nexiChatUpdateOk(HashMap<String, Object> uParam) {
		// TODO Auto-generated method stub
		logger.info("nexiChatUpdateOk 진입 >>> : ");
		return nd.nexiChatUpdateOk(uParam);
	}

	
	
	// delete
	@Override
	public int nexiChatDelete(HashMap<String, Object> dParam) {
		// TODO Auto-generated method stub
		logger.info("nexiChatDelete 진입 >>> : ");
		return nd.nexiChatDelete(dParam);
	}

	// table drop
	@Override
	public int nexichatTableDrop(HashMap<String, Object> tdParam) {
		// TODO Auto-generated method stub
		logger.info("nexichatTableDrop 진입 >>> : ");
		return nd.nexichatTableDrop(tdParam);
	}
	
	// conv select
	@Override
	public ArrayList<HashMap<String, Object>> nexiConvSelect(HashMap<String, Object> sParam) {
		logger.info("nexiConvSelect 진입 >>> : ");
		return nd.nexiConvSelect(sParam);
	}
	
	// conv insert
	@Override
	public int nexiConvInsert(HashMap<String, Object> param) {
		// TODO Auto-generated method stub
		logger.info("nexiConvInsert 진입 >>> : ");
		//StringUtils.defaultString(문자열, 반환할문자열)
		//param.get("tableName")
		return nd.nexiConvInsert(param);
	}


	
	
	// login
	@Override
	public ArrayList<HashMap<String, Object>> loginChat(HashMap<String, Object> loginParam) {
		// TODO Auto-generated method stub
		logger.info("nexiChatService loginChat 진입 >>> : ");
		return nd.loginChat(loginParam);
	}
	
	//signup
	@Override
	public int signUpInsert(HashMap<String, Object> signParam) {
		// TODO Auto-generated method stub
		logger.info("nexiChatService signUpInsert 진입 >>> : ");
		return nd.signUpInsert(signParam);
	}


	

	
}
