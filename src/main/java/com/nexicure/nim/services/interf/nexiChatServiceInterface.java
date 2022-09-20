package com.nexicure.nim.services.interf;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public interface nexiChatServiceInterface {
	public ArrayList<HashMap<String, Object>> nexiChatAll() ;
	public ArrayList<HashMap<String, Object>> nexiChat( HashMap<String,Object>cParam);
	public ArrayList<HashMap<String, Object>> userSelect(HashMap<String, Object> param) ;
	
	public int nexiChatSeqchk (HashMap<String, Object> iParam) throws SQLException, Exception;
	public int nexiChatInsert( HashMap<String, Object> iParam) throws SQLException, Exception;
	public int nexichatTableSeq(HashMap<String, Object> sParam) throws SQLException, Exception;
		
	public int nexiChatUpdateDup(HashMap<String, Object> dParam);
	public int nexiChatUpdateOk(HashMap<String, Object> uParam);
	public int nexichatTableUpdate(HashMap<String, Object> uParam);
	
	public int userChattalbeDel(HashMap<String, Object> dParam);
	public int nexiChatDelete(HashMap<String, Object> dParam);
	public int nexichatTableDrop(HashMap<String, Object> tdParam);
	
	public ArrayList<HashMap<String, Object>> nexiConvSelect(HashMap<String, Object> sParam);
	public int nexiConvInsert(HashMap<String, Object> param);
	
	
	//
	public ArrayList<HashMap<String, Object>> loginChat(HashMap<String, Object> loginParam);
	public int signUpInsert(HashMap<String, Object> loginParam);
	
	//public HashMap<String, Object> nexiFileSeqCheck(HashMap<String, Object> param);
	public int nexiFileSeqCheck(HashMap<String, Object> param);
	public int nexiFileUpload(HashMap<String, Object> fileMap);
	
	
	//
	public ArrayList<HashMap<String, Object>> userChattableSel(HashMap<String, Object> param);
	public ArrayList<HashMap<String, Object>> userChattableChk(HashMap<String, Object> param);
	//public int userChattable(HashMap<String, Object> param);
	public int userChatDelete(HashMap<String, Object> dParam) throws SQLException, Exception;
}
