package com.nexicure.nim.controller;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.nexicure.nim.services.nexiChatService;
import com.nexicure.nim.services.testService;

import com.song.string.util.*;

@Controller
public class ChatController {
	private static Logger logger = LogManager.getLogger(ChatController.class);

	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(30000000); //30mb
		return multipartResolver;
	}

	@Autowired
	private testService ts;

	@Autowired
	private nexiChatService ns;

	@RequestMapping(value = "/nexiFileUpload", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<HashMap<String, Object>> nexiFileUpload(@RequestParam("file") MultipartFile[] file) throws IOException {
		logger.info("ChatController nexiFileUpload()시작  >> :");
		
		// 변수 초기화
		String tempFileName = null;
		String originFileName = null;
		String idate = null;
		String fileStatus = null; 
		
		//파일 경로 
		String filePath = "D:\\sts\\work\\workspace\\Nexicure Web Application\\src\\main\\webapp\\contents\\images\\newimage";
		// 파일 이름		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		
		int nCnt = 0;
		
		try {
			ArrayList<HashMap<String, Object>> fileList = new ArrayList<>();
			for(int i=0; i<file.length; i++) {
				HashMap<String, Object> fileMap = new HashMap<>();
				originFileName = file[i].getOriginalFilename();
				String[] fileExtention = originFileName.split("\\.");
				
				System.out.println("originFileName  >>>:  " + file[i].getOriginalFilename());
				//System.out.println(fileExtention[fileExtention.length]);
				System.out.println(fileExtention.toString());
				int leng = fileExtention.length-1;
				System.out.println(leng);
				System.out.println("fileExtention  >>>:  " + fileExtention[leng]);
				
				idate = dateFormat.format(new Date());
				tempFileName = idate+"_"+i+"."+fileExtention[leng];	
				
				File uploadFile = new File(filePath, tempFileName);
				
				file[i].transferTo(uploadFile);	
				
				fileMap.put("tempFileName", tempFileName);
				fileMap.put("originFileName", originFileName);
				fileMap.put("idate", idate);
				
				fileList.add(fileMap);
			}
			
			System.out.println("fileList >>> : " + fileList);
			
			return fileList;
		}catch(Exception e) {
			logger.debug( "ERROR : " +e);
			logger.debug( "ERROR : " +e.getMessage());
			
			return null;
		}	
		  
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView test(Model uiModel, Locale locale) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/login");
		return mav;
	}

	@RequestMapping(value = "/loginChat", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> loginChat(@RequestBody HashMap<String, Object> loginParam,
			HttpServletRequest request) {
		logger.info("ChatController loginChat()시작  >> :");
		System.out.println("loginParam >>>> : " + loginParam);

		ArrayList<HashMap<String, Object>> loginResult = ns.loginChat(loginParam);
		HashMap<String, Object> returnMap = new HashMap<>();

		if (loginResult != null && loginResult.size() != 0) {

			String loginName = loginResult.get(0).get("USERNAME").toString();
			String loginId = loginResult.get(0).get("USERID").toString();
			String loginSeq = loginResult.get(0).get("SEQ").toString();
			// String loginId = loginResult.get(0).get("").toString();

			HttpSession session = request.getSession();
			session.setAttribute("username", loginName);
			session.setAttribute("userid", loginId);
			session.setAttribute("userseq", loginSeq);

			System.out.println("controller username >>>> : " + loginId);

			// session.setMaxInactiveInterval(int interval)
			// session.getCreationTime()

			returnMap.put("redirect", "/");
		} else {
			returnMap.put("loginStatus", "Fail");
		}

		// return "/index" ;//view->redirect.jsp를 호출
		// return "forward:index.jsp";
		return returnMap;
	}

	@RequestMapping(value = "/signUpInsert", method = RequestMethod.POST)
	@ResponseBody
	public int signUpInsert(@RequestBody HashMap<String, Object> signParam) {
		logger.info("ChatController signUpInsert()시작  >> :");
		System.out.println("signParam >>> : " + signParam);
		int nCnt = ns.signUpInsert(signParam);
		return nCnt;
	}

	@RequestMapping(value = "/signUpIdChk", method = RequestMethod.POST)
	@ResponseBody
	public int signUpIdChk(@RequestBody HashMap<String, Object> signParam) {
		logger.info("ChatController signUpIdChk()시작  >> :");
		System.out.println("signParam >>> : " + signParam);
		int nCnt = ns.loginChat(signParam).size();
		return nCnt;
	}

	// --------------chat--------------------
	// select 시작

	@RequestMapping(value = "/chatmain", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView chatmain(Model uiModel, Locale locale) {
		logger.info("ChatController Chat()시작  >> :");
		ModelAndView returnMV = new ModelAndView();
		returnMV.setViewName("/chat/chatmain");
		return returnMV;
	}

	@RequestMapping(value = "/chatselectAll")
	@ResponseBody
	public ArrayList<HashMap<String, Object>> chatselectAll(@RequestBody HashMap<String, Object> param) {
		logger.info("ChatController chatselectAll시작  >> :");
		// System.out.println("param >>> : " + param);
		ArrayList<HashMap<String, Object>> listAll = ns.nexiChatAll();
		logger.info("listAll >>>> : " + listAll);
		return listAll;
	}
	
	@RequestMapping(value = "/userSelect", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<HashMap<String, Object>> userSelect( @RequestBody HashMap<String, Object> param) {
		logger.info("ChatController userSelect시작  >> :");
		System.out.println("param >>> : " + param);
		ArrayList<HashMap<String, Object>> userList = ns.userSelect(param);
		return userList;
	}

	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping( value="/chat" , method = RequestMethod.GET ) public
	 * ModelAndView chat ( HttpServletRequest http ) { String test =
	 * http.getParameter("subject"); logger.debug( "test >>>>>>>>>>>>>>> "+test);
	 * return null; }
	 */

	@RequestMapping(value = "/chatselect", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<HashMap<String, Object>> chatselect(@RequestBody HashMap<String, Object> cParam) {
		logger.info("ChatController chatselect 시작  >> :");
		System.out.println("cParam>>> : " + cParam);

		ArrayList<HashMap<String, Object>> list = ns.nexiChat(cParam); // 함수 호출시 parameter 전달 !
		logger.info("list >>> : " + list);
		return list;
	} // select 끝

	@RequestMapping(value = "/chatUserCheck", method = RequestMethod.POST)
	@ResponseBody
	public int chatUserCheck(@RequestBody HashMap<String, Object> cParam) {
		logger.info("ChatController chatUserCheck 시작  >> :");
		System.out.println("cParam>>> : " + cParam);
		int nCnt = ns.nexiChat(cParam).size();
		logger.info("nCnt >>> : " + nCnt);

		return nCnt;
	}

	// Insert 시작
	@RequestMapping(value = "/chatinsert", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView chatinsert(Model uiModel, Locale locale) {
		logger.info("ChatController chatinsert 시작  >> :");
		ModelAndView returnMV = new ModelAndView();
		returnMV.setViewName("/chat/chatinsert");
		return returnMV;
	}

	@RequestMapping(value = "/chatinsertdata", method = RequestMethod.POST)
	@ResponseBody
	public int chatinsertdata(@RequestBody HashMap<String, Object> iParam) throws SQLException, Exception {
		logger.info("ChatController chatinsertdata 시작  >> :");
		System.out.println("iParam >>>> : " + iParam);
		/*
		 * String file_table_name = iParam.get("tableName").toString()+"_file";
		 * 
		 * iParam.put("fileTableName", file_table_name);
		 */
		int nCnt = ns.nexiChatSeqchk(iParam);

		logger.info("nCnt >>> : " + nCnt);

		return nCnt;
	}// insert 끝

	/*
	 * @RequestMapping(value="/chatTableInsert", method=RequestMethod.POST)
	 * 
	 * @ResponseBody public int chatTableInsert (@RequestBody HashMap<String,
	 * Object> tParam) { logger.info("ChatController chatTableInsert 시작  >> :");
	 * System.out.println("tParam >>>> : " + tParam); int nCnt =
	 * ns.nexichatTableInsert(tParam); return 0; }
	 */

	// Update&Delete
	@RequestMapping(value = "/chatupdate", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView chatupdate(Model uiModel, Locale locale) {
		logger.info("ChatController chatupdate 시작  >> :");
		ModelAndView returnMV = new ModelAndView();
		returnMV.setViewName("/chat/chatupdate");
		return returnMV;
	}

	// Update duplication check
	@RequestMapping(value = "/chatupdateDup", method = RequestMethod.POST)
	@ResponseBody
	public int nexiChatUpdateDup(@RequestBody HashMap<String, Object> dParam) {
		logger.info("ChatController nexiChatUpdateDup 시작  >> :");
		System.out.println("dParam >>> : " + dParam);

		return ns.nexiChatUpdateDup(dParam);
	}

	// Update 시작
	@RequestMapping(value = "/chatupdateok", method = RequestMethod.POST)
	@ResponseBody
	public int chatupdateok(@RequestBody HashMap<String, Object> uParam) {
		logger.info("ChatController chatupdateok 시작  >> :");
		System.out.println("uParam >>> : " + uParam);
		int nCnt = ns.nexiChatUpdateOk(uParam);
		return nCnt;
	}

	// Delete 시작
	@RequestMapping(value = "/chatdelete", method = RequestMethod.POST)
	@ResponseBody
	public int chatdelete(@RequestBody HashMap<String, Object> dParam) {
		logger.info("ChatController chatdelete 시작  >> :");
		System.out.println("dParam >>> : " + dParam);
		int nCnt = ns.nexiChatDelete(dParam);
		
		logger.info("nCnt >>> : " + nCnt);

		return nCnt;
	}

	/*
	 * // table drop 시작
	 * 
	 * @RequestMapping(value = "/chatTableDrop", method = RequestMethod.POST)
	 * 
	 * @ResponseBody public int chatTableDrop(@RequestBody HashMap<String, Object>
	 * tdParam) { logger.info("ChatController chatTableDrop 시작  >> :");
	 * System.out.println("tdParam >>> : " + tdParam); int nCnt =
	 * ns.nexichatTableDrop(tdParam); return nCnt; }
	 */

	@RequestMapping(value = "/chatpop", method = RequestMethod.GET)
	public ModelAndView chatpop(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("chat/chatpop");
		mv.addObject("chatSeq", req.getParameter("seq"));
		mv.addObject("chatTname", req.getParameter("subject"));
		mv.addObject("chatUserid", req.getParameter("userid"));
		return mv;
	}

	@RequestMapping(value = "/chatConvSel", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<HashMap<String, Object>> chatConvSel(@RequestBody HashMap<String, Object> sParam) {
		logger.info("ChatController chatConvSel 시작  >> :");
		System.out.println("sParam >>> : " + sParam);
		ArrayList<HashMap<String, Object>> listChat = ns.nexiConvSelect(sParam);
		return listChat;
	}
	

	@RequestMapping(value = "/chatConvIn", method = RequestMethod.POST)
	@ResponseBody
	public int chatConvIn(@RequestBody HashMap<String, Object> param) {
		logger.info("ChatController chatConvIn 시작  >> :");
		System.out.println("param >>> : " + param);
		
		//String fileStatus = song.safe(param.get("fileStatus"));
		
		/*
		 * HashMap<String, Object> paramMap = new HashMap<>(); paramMap = param.get(0);
		 */
		
		int nCnt = ns.nexiFileSeqCheck(param);
		//int nCnt = ns.nexiConvInsert(param);
		return nCnt;
	}
	
	
	
	// ===================접속 채팅방 ===================
	@RequestMapping(value="/chatting", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView mychatin(Model uiModel, Locale locale) {

		ModelAndView returnView = new ModelAndView();
		returnView.setViewName("/chat/chatting");
		
		return returnView;
	}
	
	@RequestMapping(value="/userChattableSel", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<HashMap<String, Object>> userChattableSel( @RequestBody HashMap<String, Object> param) {
		
		logger.info("ChatController userChattableSel 시작  >> :");
		System.out.println("param >>>> ::: " + param);
		ArrayList<HashMap<String, Object>> returnList = ns.userChattableSel(param);
		return returnList;
	}
	
	@RequestMapping(value="/userChattable", method=RequestMethod.POST)
	@ResponseBody
	public ArrayList<HashMap<String, Object>> userChattable( @RequestBody HashMap<String, Object> param) {
		logger.info("ChatController userChattable 시작  >> :");
		System.out.println("param >>>> ::: " + param);
		ArrayList<HashMap<String, Object>> returnList = ns.userChattableChk(param);
		logger.info("returnList >>> : " + returnList);
		return returnList;
	}
	
	@RequestMapping(value="/userChatDelete", method=RequestMethod.POST)
	@ResponseBody
	public int userChatDelete ( @RequestBody HashMap<String, Object> dParam) throws SQLException, Exception {
		logger.info("ChatController userChatDelete 시작  >> :");
		System.out.println("dParam >>>> ::: " + dParam);
		int nCnt = ns.userChatDelete(dParam);
		return nCnt;
	}
	
	@RequestMapping(value="/userChattalbeDel", method=RequestMethod.POST)
	@ResponseBody
	public int userChattalbeDel( @RequestBody HashMap<String, Object> param) {
		logger.info("ChatController userChatDelete 시작  >> :");
		System.out.println("param >>>>> : " + param);
		int nCnt = ns.userChattalbeDel(param);
		return nCnt;
	} 
	

}