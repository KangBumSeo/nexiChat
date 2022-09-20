package com.nexicure.nim.services;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexicure.nim.dao.nexiChatDAO;
import com.nexicure.nim.services.interf.nexiChatServiceInterface;
import com.song.string.util.song;

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

	@Override
	public ArrayList<HashMap<String, Object>> userSelect(HashMap<String, Object> param) {
		// TODO Auto-generated method stub
		logger.info("nexiChatService userSelect() 진입 >>> : ");
		return nd.userSelect(param);
	}

	@Override
	public int nexiChatSeqchk(HashMap<String, Object> iParam) throws SQLException, Exception {
		// TODO Auto-generated method stub
		logger.info("nexiChatService nexiChatSeqchk() 진입 >>> : ");
		String status = (String) iParam.get("status");
		String userid = (String) iParam.get("userid");
		System.out.println("status >>> : " + status);
		System.out.println("status >>> : " + status.getClass().getName());

		HashMap<String, Object> seq = nd.nexiChatSeqchk(iParam);
		
		String chatSeq = (String) seq.get("NEXTVAL");
		System.out.println("chatseq >>>> : " + chatSeq);
		
		iParam.put("chatseq", seq.get("NEXTVAL"));
		
		if(status.equals("C")) {
			 System.out.println("status C!!!!!!"); 
			 iParam.put("subject", chatSeq);
			 iParam.put("tableName", userid+"_"+chatSeq); 
			 iParam.put("fileTableName", userid+"_"+chatSeq+"_file"); 
		}
		  
		System.out.println("status ::::: " + status + "iParam ::::: " + iParam);
		int nCnt = nexiChatInsert(iParam);
				 
		return nCnt;
	}

	// insert
	@Override
	public int nexiChatInsert(HashMap<String, Object> iParam) throws SQLException, Exception {
		// TODO Auto-generated method stub
		logger.info("nexiChatServiceInsert 진입 >>> : ");
		logger.info("nexiChatService iParam >>> : " + iParam);

		int tmp = nd.nexiChatInsert(iParam);

		if (tmp == 0) {
			return tmp;
		} else {
			nd.userChattableIn(iParam);
			tmp = nexichatTableSeq(iParam);
			// tmp = nexichatTableInsert(iParam);

			return tmp;
		}
	}

	// seq, table, filetable create
	@Override
	public int nexichatTableSeq(HashMap<String, Object> iParam) throws SQLException, Exception {
		// TODO Auto-generated method stub
		logger.info("nexichatTableSeq 진입 >>> : ");
		logger.info("nexichatTableSeq iParam >>> : " + iParam);
		int seqReturn = 1;
		try {
			nd.nexichatTableSeq(iParam);
			nd.nexichatFileSeq(iParam);
			nd.nexichatTableInsert(iParam);
			nd.nexiFileTableInsert(iParam);
			seqReturn = 1;
			return seqReturn;
		} catch (SQLException e) {
			logger.debug(e);
			seqReturn = 0;
			return seqReturn;
		} catch (Exception e) {
			logger.debug(e);
			seqReturn = 0;
			return seqReturn;
		}
	}

	// update Duplicate check
	@Override
	public int nexiChatUpdateDup(HashMap<String, Object> dParam) {
		// TODO Auto-generated method stub
		logger.info("nexiChatUpdateDup 진입 >>> : ");
		int nCnt = nd.loginChat(dParam).size();

		return nCnt;
	}

	// update
	@Override
	public int nexiChatUpdateOk(HashMap<String, Object> uParam) {
		// TODO Auto-generated method stub
		logger.info("nexiChatUpdateOk 진입 >>> : ");

		int udt = nd.nexiChatUpdateOk(uParam);
		if (udt == 0) {
			logger.debug("status >>>> " + udt);
			return udt;
		} else {
			udt = nexichatTableUpdate(uParam);
			logger.debug("rename :  >>> " + udt);
			return udt;
		}
	}

	// table update
	@Override
	public int nexichatTableUpdate(HashMap<String, Object> uParam) {
		// TODO Auto-generated method stub
		logger.info("nexichatTableUpdate 진입 >>> : ");
		int returnStatus = 0;
		try {
			nd.nexichatTableUpdate(uParam);
			returnStatus = 1;
			return returnStatus;
		} catch (Exception e) {
			// TODO: handle exception
			logger.debug(e);
			returnStatus = 0;
			return returnStatus;
		}
	}

	// delete

	@Override
	public int userChattalbeDel(HashMap<String, Object> dParam) {
		// TODO Auto-generated method stub
		logger.info("userChattalbeDel 진입 >>> : ");
		int nCnt = nd.userChattalbeDel(dParam);
		return nCnt;
	}

	@Override
	public int nexiChatDelete(HashMap<String, Object> dParam) {
		// TODO Auto-generated method stub
		logger.info("nexiChatDelete 진입 >>> : ");
		int delOk = nd.nexiChatDelete(dParam);

		if (delOk == 0) {
			logger.debug("status >>>> " + delOk);
			return delOk;
		} else {
			delOk = nexichatTableDrop(dParam);
			return delOk;
		}
	}

	// table drop
	@Override
	public int nexichatTableDrop(HashMap<String, Object> tdParam) {
		// TODO Auto-generated method stub
		logger.info("nexichatTableDrop 진입 >>> : ");
		int delReturn = 0;
		try {
			nd.nexichatTableDrop(tdParam);
			nd.nexiTableSeqDrop(tdParam);
			nd.nexiFileTableDrop(tdParam);
			nd.nexiFileSeqDrop(tdParam);
			delReturn = 1;
			return delReturn;

		} catch (Exception e) {
			logger.debug(e);
			delReturn = 0;
			return delReturn;
		}
	}

	// conv select

	@Override
	public ArrayList<HashMap<String, Object>> nexiConvSelect(HashMap<String, Object> sParam) {
		logger.info("nexiConvSelect 진입 >>> : ");
		return nd.nexiConvSelect(sParam);
	}

	// fileupload test
	/*
	 * @Override public HashMap<String, Object> nexiFileSeqCheck_1(HashMap<String,
	 * Object> param) { // TODO Auto-generated method stub
	 * logger.info("nexiChatService nexiFileSeqCheck 진입 >>> : ");
	 * 
	 * HashMap<String, Object> seqMap = nd.nexiFileSeqCheck(param); if
	 * (seqMap.size() > 0) { System.out.println("seqMap >>>> : " + seqMap);
	 * param.put("seq", seqMap.get("NEXTVAL")) ;
	 * 
	 * System.out.println("param >>> : " + param); }
	 * 
	 * return null; }
	 */

	// fileupload test
	@Override
	public int nexiFileSeqCheck(HashMap<String, Object> param) {
		// TODO Auto-generated method stub
		logger.info("nexiChatService nexiFileSeqCheck 진입 >>> : ");
		HashMap<String, Object> seqMap = new HashMap<>();
		seqMap.put("tableName", param.get("tableName"));

		HashMap<String, Object> mapResult = nd.nexiFileSeqCheck(seqMap);
		int returnVal = 0;

		if (mapResult.size() > 0) {
			System.out.println("mapResult >>>> : " + mapResult);
			param.put("seq", mapResult.get("NEXTVAL"));

			System.out.println("param >>> : " + param);

			returnVal = nexiConvInsert(param);
		}
		return returnVal;
	}

	// conv insert
	@Override
	public int nexiConvInsert(HashMap<String, Object> param) {
		// TODO Auto-generated method stub
		logger.info("nexiConvInsert 진입 >>> : ");
		// StringUtils.defaultString(문자열, 반환할문자열)
		// param.get("tableName")

		HashMap<String, Object> map = new HashMap<>();

		String fileStatus = song.safe(param.get("fileStatus"));
		logger.info("fileStatus!!!!! ::::::" + fileStatus);
		logger.info("nexiConInsert param >>>>> : " + param);
		if (fileStatus.equals("Y")) {
			logger.info("====================fileStatus = Y" + fileStatus);
			int nConv = nd.nexiConvInsert(param);
			int nFile = 0;

			System.out.println("file originFileName ===================");
			// System.out.println( param.get("paramList"));
			ArrayList<HashMap<String, Object>> paramList = (ArrayList<HashMap<String, Object>>) param.get("paramList");
			logger.info(paramList);
			logger.info(paramList.get(0));
			/*
			 * logger.info("paramList size :::::::" + paramList.size());
			 * 
			 * if(paramList.size() == 1) { logger.info("paramList size ::::::::: " +
			 * paramList.size()); HashMap<String, Object> tableParam = paramList.get(0);
			 * tableParam.put("userid", param.get("userid")); tableParam.put("seq",
			 * param.get("seq")); tableParam.put("fileTableName",
			 * param.get("fileTableName")); nFile = nd.nexiFileUpload(tableParam); }else {
			 */
			for (int i = 0; i < paramList.size(); i++) {
				logger.info("paramList size ::::::::: " + paramList.size());
				HashMap<String, Object> tableParam = paramList.get(i);
				tableParam.put("userid", param.get("userid"));
				tableParam.put("seq", param.get("seq"));
				tableParam.put("fileTableName", param.get("fileTableName"));
				nFile = nd.nexiFileUpload(tableParam);
			}
//			}

			// ArrayList<String> = param.get("paramList");
			// param.get("paramList");

			logger.info("return >>>>> : " + (nConv + nFile));
			return nConv + nFile;

		} else {
			logger.info("====================fileStatus = Null" + fileStatus);
			int nConv = nd.nexiConvInsert(param);

			logger.info("return >>>>> : " + nConv);
			return nConv;
		}

	}

	// login
	@Override
	public ArrayList<HashMap<String, Object>> loginChat(HashMap<String, Object> loginParam) {
		// TODO Auto-generated method stub
		logger.info("nexiChatService loginChat 진입 >>> : ");
		return nd.loginChat(loginParam);
	}

	// signup
	@Override
	public int signUpInsert(HashMap<String, Object> signParam) {
		// TODO Auto-generated method stub
		logger.info("nexiChatService signUpInsert 진입 >>> : ");
		return nd.signUpInsert(signParam);
	}

	@Override
	public int nexiFileUpload(HashMap<String, Object> fileMap) {
		// TODO Auto-generated method stub
		logger.info("nexiChatService nexiFileUpload 진입 >>> : ");
		return nd.nexiFileUpload(fileMap);
	}

	//
	@Override
	public ArrayList<HashMap<String, Object>> userChattableSel(HashMap<String, Object> param) {
		// TODO Auto-generated method stub
		logger.info("nexiChatService userChattableSel 진입 >>> : ");
		ArrayList<HashMap<String, Object>> chatList = nd.userChattableSel(param);

		return chatList;
	}

	@Override
	public ArrayList<HashMap<String, Object>> userChattableChk(HashMap<String, Object> param) {
		// TODO Auto-generated method stub
		logger.info("nexiChatService userChattableChk 진입 >>> : ");

		ArrayList<HashMap<String, Object>> resultList = nd.userChattableChk(param);

		try {
			int nCnt = nd.userChattableChk(param).size();

			if (nCnt != 0) {
				return resultList;
			} else {

				int returnVal = nd.userChattableIn(param);

				if (returnVal > 0) {
					resultList = nd.userChattableChk(param);
				}
			}
			return resultList;

		} catch (Exception e) {
			logger.info("에러 >>>> : " + e);
			return null;
		}
	}

	@Override
	public int userChatDelete(HashMap<String, Object> dParam) throws SQLException, Exception {
		// TODO Auto-generated method stub
		logger.info("nexiChatService userChattable 진입 >>> : ");

		int returnConv = 0;
		int returnFile = 0;
		/*
		 * 화면 단에서 null 처리 안해줬을 경우 []를 object로 인식 casting해서 ArrayList로 바꿔주고 사이즈 체크해서 null
		 * 체크 ArrayList<String> tempArr = (ArrayList<String>) dParam.get("tableseq");
		 * System.out.println(tempArr.size());
		 */
		if (dParam.get("tableseq") != null) {
			try {
				returnConv = nd.userConvDelete(dParam);
				if (returnConv == 0) {
					throw new Exception("대화 삭제에 실패했습니다.");
				}
				returnFile = nd.userFileDelete(dParam);
				System.out.println(returnConv);

				return returnConv + returnFile;

			} catch (Exception e) {
				logger.info("에러 >>>> : " + e);
				return returnConv + returnFile;
			}
		} else {
			try {
				returnConv = nd.userConvDelete(dParam);
				if (returnConv == 0) {
					throw new Exception("대화 삭제에 실패했습니다.");
				}
				return returnConv;

			} catch (Exception e) {
				logger.info("에러 >>>> : " + e);
				return returnConv + returnFile;
			}
		}
	}

}
