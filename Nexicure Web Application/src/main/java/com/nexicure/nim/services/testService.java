package com.nexicure.nim.services;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.nexicure.nim.dao.testDAO;
import com.nexicure.nim.services.interf.testServiceInterface;

@Service("testService")
public class testService implements testServiceInterface{

	
	@Autowired
	private testDAO testDAO;
	
	/*
	[{}] 
	ArrayList<HashMap< String , Object>>
	ArrayList< Object >
	String temp 
	
	array 
	*/
	
	@Override
	public ArrayList<HashMap<String, Object>> testChat() {
		// TODO Auto-generated method stub
		return testDAO.testChat();
		//return null;
	}
	
}
