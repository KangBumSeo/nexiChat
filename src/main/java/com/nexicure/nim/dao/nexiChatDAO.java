package com.nexicure.nim.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface nexiChatDAO {
	public ArrayList<HashMap<String, Object>> nexiChatAll() ;
	public ArrayList<HashMap<String, Object>> nexiChat(HashMap<String, Object> cParam) ;
	public ArrayList<HashMap<String, Object>> userSelect(HashMap<String, Object> param) ;
	
	public HashMap<String, Object> nexiChatSeqchk (HashMap<String, Object> iParam);
	public int nexiChatInsert(HashMap<String, Object> iParam);
	public int nexichatTableSeq(HashMap<String, Object> sParam) throws SQLException, Exception ;
	public int nexichatFileSeq(HashMap<String, Object> sParam) throws SQLException, Exception ;
	public int nexichatTableInsert(HashMap<String, Object> tParam);
	public int nexiFileTableInsert(HashMap<String, Object> fParam);
	
	public int nexiChatUpdateOk(HashMap<String, Object> uParam);
	public void nexichatTableUpdate(HashMap<String, Object> uParam);
	
	public int nexiChatDelete(HashMap<String, Object> dParam);
	// 채팅방 지울 때 원래 있던 사람들 전부 N으로 날려야 하나?
	public int userChattalbeDel(HashMap<String, Object> dParam);
	public int nexichatTableDrop(HashMap<String, Object> tdParam);
	public int nexiTableSeqDrop(HashMap<String, Object> sParam);
	public int nexiFileSeqDrop(HashMap<String, Object> sParam);
	public int nexiFileTableDrop(HashMap<String, Object> fParam);
	
	public ArrayList<HashMap<String, Object>> nexiConvSelect(HashMap<String, Object> sParam);
	public int nexiConvInsert(HashMap<String, Object> param);
	
	
	//
	public ArrayList<HashMap<String, Object>> loginChat(HashMap<String, Object> loginParam);
	public int signUpInsert(HashMap<String, Object> signParam);
	
	public HashMap<String, Object> nexiFileSeqCheck(HashMap<String, Object> param);
	public int nexiFileUpload(HashMap<String, Object> fileMap);

	//
	public ArrayList<HashMap<String, Object>> userChattableSel(HashMap<String, Object> param);
	public ArrayList<HashMap<String, Object>> userChattableChk(HashMap<String, Object> param);
	public int userChattable(HashMap<String, Object> param);
	public int userChattableIn(HashMap<String, Object> iParam);

	
	public int userConvDelete(HashMap<String, Object> dParam);
	public int userFileDelete(HashMap<String, Object> dParam);
}
