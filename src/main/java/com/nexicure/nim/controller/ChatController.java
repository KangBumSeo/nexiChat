package com.nexicure.nim.controller;

import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nexicure.nim.services.nexiChatService;
import com.nexicure.nim.services.testService;

import jdk.nashorn.internal.ir.RuntimeNode.Request;

@Controller
public class ChatController  {
	private static Logger logger = LogManager.getLogger(ChatController.class);
	
	@Autowired
	private testService ts;
	
	@Autowired
	private nexiChatService ns;
	
	@RequestMapping( value="/login" , method = RequestMethod.GET ) 
	@ResponseBody
	public ModelAndView test (Model uiModel, Locale locale) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/login");
		return mav;
	}
	
	@RequestMapping( value="/loginChat", method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String,Object> loginChat (@RequestBody HashMap<String, Object> loginParam, HttpServletRequest request) {
		logger.info("ChatController loginChat()시작  >> :");
		System.out.println("loginParam >>>> : " + loginParam);
		
		
		ArrayList<HashMap<String, Object>> loginResult = ns.loginChat(loginParam);
		HashMap<String,Object> returnMap = new HashMap<>();

		
		if (loginResult != null && loginResult.size() != 0) {

			String loginName =  loginResult.get(0).get("USERNAME").toString();
			String loginId = loginResult.get(0).get("USERID").toString();
			String loginSeq = loginResult.get(0).get("SEQ").toString();
			//String loginId = loginResult.get(0).get("").toString();
			
			HttpSession session = request.getSession();
			session.setAttribute("username",loginName );
			session.setAttribute("userid", loginId);
			session.setAttribute("userseq", loginSeq);
			
			System.out.println("controller username >>>> : " + loginId);
		
			//  session.setMaxInactiveInterval(int interval)
			//session.getCreationTime()
			
			returnMap.put("redirect", "/");
		}
		else {
			returnMap.put("loginStatus", "Fail");
		}
		
		 
				 
		//return "/index" ;//view->redirect.jsp를 호출
		//return "forward:index.jsp";
		return returnMap;
	}
	
	@RequestMapping(value="/signUpInsert", method=RequestMethod.POST)
	@ResponseBody
	public int signUpInsert (@RequestBody HashMap<String, Object> signParam) {
		logger.info("ChatController signUpInsert()시작  >> :");
		System.out.println("signParam >>> : "  + signParam);
		int nCnt = ns.signUpInsert(signParam); 
		return nCnt;
	}
	
	@RequestMapping(value="/signUpIdChk", method=RequestMethod.POST)
	@ResponseBody
	public int signUpIdChk (@RequestBody HashMap<String, Object> signParam) {
		logger.info("ChatController signUpIdChk()시작  >> :");
		System.out.println("signParam >>> : "  + signParam);
		int nCnt = ns.loginChat(signParam).size();
		return nCnt;
	}
	
	
	
	//--------------chat--------------------
	// select 시작
	
	@RequestMapping( value="/chatmain" , method = RequestMethod.GET ) 
	@ResponseBody
	public ModelAndView chatmain (Model uiModel, Locale locale) {
		logger.info("ChatController Chat()시작  >> :");
		ModelAndView returnMV = new ModelAndView();
		returnMV.setViewName("/chat/chatmain");
		return returnMV;
	}
	
	@RequestMapping(value="/chatselectAll")
	@ResponseBody
	public ArrayList<HashMap<String,Object>> chatselectAll(@RequestBody HashMap<String, Object>param){
		logger.info("ChatController chatselectAll시작  >> :");
	//	System.out.println("param >>> : " + param);
		ArrayList<HashMap<String,Object>> listAll = ns.nexiChatAll();
		logger.info("listAll >>>> : " + listAll);
		return listAll;
	}
	
	/*
	@ResponseBody
	@RequestMapping( value="/chat" , method = RequestMethod.GET )
	public ModelAndView chat ( HttpServletRequest http ) {
		String test  = http.getParameter("subject");
		logger.debug( "test >>>>>>>>>>>>>>> "+test);
		return null;
	}
	*/
	
	@RequestMapping( value="/chatselect", method = RequestMethod.POST)
	@ResponseBody
	public ArrayList<HashMap<String, Object>> chatselect( @RequestBody HashMap<String, Object> cParam ) {
		logger.info("ChatController chatselect 시작  >> :");
		System.out.println("cParam>>> : " + cParam);
		
		ArrayList<HashMap<String, Object>> list = ns.nexiChat(cParam); // 함수 호출시 parameter 전달 ! 
		logger.info("list >>> : " + list);
	
		return list;
	} // select  끝
	
	
	@RequestMapping( value="/chatUserCheck", method=RequestMethod.POST)
	@ResponseBody
	public int chatUserCheck ( @RequestBody HashMap<String, Object> cParam) {
		logger.info("ChatController chatUserCheck 시작  >> :");
		System.out.println("cParam>>> : " + cParam);		
		int nCnt = ns.nexiChat(cParam).size();
		logger.info("nCnt >>> : " + nCnt);
		
	 return nCnt;
	}

	// Insert 시작
	@RequestMapping(value="/chatinsert", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView chatinsert(Model uiModel, Locale locale) {
		logger.info("ChatController chatinsert 시작  >> :");
		ModelAndView returnMV = new ModelAndView();
		returnMV.setViewName("/chat/chatinsert");
		return returnMV;
	}
	
	@RequestMapping(value="/chatinsertdata", method=RequestMethod.POST)
	@ResponseBody
	public int chatinsertdata( @RequestBody HashMap<String, Object> iParam){
		logger.info("ChatController chatinsertdata 시작  >> :");
		System.out.println("iParam >>>> : " + iParam);
		int nCnt = ns.nexiChatInsert(iParam);
		logger.info("nCnt >>> : " + nCnt);
		
		
		return nCnt;
	}// insert 끝
	
	/*
	@RequestMapping(value="/chatTableInsert", method=RequestMethod.POST)
	@ResponseBody
	public int chatTableInsert (@RequestBody HashMap<String, Object> tParam) {
		logger.info("ChatController chatTableInsert 시작  >> :");
		System.out.println("tParam >>>> : " + tParam);
		int nCnt = ns.nexichatTableInsert(tParam);
		return 0;
	}
	*/
	
	
	
	// Update&Delete
	@RequestMapping(value="/chatupdate", method=RequestMethod.GET)
	@ResponseBody
	public ModelAndView chatupdate(Model uiModel, Locale locale) {
		logger.info("ChatController chatupdate 시작  >> :");
		ModelAndView returnMV = new ModelAndView();
		returnMV.setViewName("/chat/chatupdate");
		return returnMV;
	}
	
	// Update 시작
	@RequestMapping(value="/chatupdateok", method=RequestMethod.POST)
	@ResponseBody
	public int chatupdateok ( @RequestBody HashMap<String, Object> uParam) {
		logger.info("ChatController chatupdateok 시작  >> :");
		System.out.println("uParam >>> : " + uParam);
		int nCnt = ns.nexiChatUpdateOk(uParam);
		return nCnt;
	}
	
	
	// Delete 시작
	@RequestMapping(value="/chatdelete", method=RequestMethod.POST)
	@ResponseBody
	public int chatdelete ( @RequestBody HashMap<String, Object> dParam) {
		logger.info("ChatController chatdelete 시작  >> :");
		System.out.println("dParam >>> : " + dParam);
		int nCnt = ns.nexiChatDelete(dParam);
		return nCnt;
	}
	
	
	// table drop 시작
	@RequestMapping(value="/chatTableDrop", method=RequestMethod.POST)
	@ResponseBody
	public int chatTableDrop(@RequestBody HashMap<String, Object> tdParam) {
		logger.info("ChatController chatTableDrop 시작  >> :");
		System.out.println("tdParam >>> : " + tdParam);
		int nCnt = ns.nexichatTableDrop(tdParam);
		return nCnt;
	}
	
	@RequestMapping(value="/chatpop", method=RequestMethod.GET)
	public ModelAndView chatpop( HttpServletRequest req) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("chat/chatpop");
		mv.addObject("chatSeq", req.getParameter("seq") );
		mv.addObject("chatTname", req.getParameter("subject"));
		mv.addObject("chatUserid", req.getParameter("userid"));
		return mv;
	}
	
	@RequestMapping(value="/chatConvSel", method=RequestMethod.POST)
	@ResponseBody
	public ArrayList<HashMap<String, Object>> chatConvSel(@RequestBody HashMap<String, Object> sParam) {
		logger.info("ChatController chatConvSel 시작  >> :");
		System.out.println("sParam >>> : " + sParam);
		ArrayList<HashMap<String, Object>> listChat = ns.nexiConvSelect(sParam);		
		return listChat;
	}
	
	@RequestMapping(value="/chatConvIn", method=RequestMethod.POST)
	@ResponseBody
	public int chatConvIn(@RequestBody HashMap<String, Object> param) {
		logger.info("ChatController chatConvIn 시작  >> :");
		System.out.println("param >>> : " + param);
		int nCnt = ns.nexiConvInsert(param);		
		return nCnt;
	}

}