package com.nexicure.nim.services.interf;

import java.util.ArrayList;
import java.util.HashMap;

public interface nexiChatServiceInterface {
	public ArrayList<HashMap<String, Object>> nexiChatAll() ;
	public ArrayList<HashMap<String, Object>> nexiChat( HashMap<String, Object>cParam);
	
	public int nexiChatInsert( HashMap<String, Object> iParam);
	public int nexichatTableInsert(HashMap<String, Object> tParam);
	public int nexiChatUpdateOk(HashMap<String, Object> uParam);
	public int nexiChatDelete(HashMap<String, Object> dParam);
	public int nexichatTableDrop(HashMap<String, Object> tdParam);
	
	public ArrayList<HashMap<String, Object>> nexiConvSelect(HashMap<String, Object> sParam);
	public int nexiConvInsert(HashMap<String, Object> param);
	
	
	//
	public ArrayList<HashMap<String, Object>> loginChat(HashMap<String, Object> loginParam);
	public int signUpInsert(HashMap<String, Object> loginParam);
	
}
