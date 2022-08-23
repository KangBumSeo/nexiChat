package com.nexicure.nim.controller;

import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nexicure.es.services.common.StringUtils;
import com.nexicure.nim.ConfigConstants;
import com.nexicure.nim.entities.User;
import com.nexicure.nim.entities.UserBean;
import com.nexicure.nim.entities.vo.TbUserVO;

@RequestMapping("/security")
@Controller
public class SecurityController {
	private static Logger logger = LogManager.getLogger(SecurityController.class);


	//@ Autowired
	//private AuthInfo authInfo;
	
	//@ Autowired
	//private AuthManager authManager;
	
	@RequestMapping("/test")
	public String test(Model uiModel, Locale locale) {
		return "test";
	}
	
	@RequestMapping("/login")
	public String login(Model uiModel, Locale locale)  {
		return "screen/login";
	}

	
	
	@RequestMapping(value = "/proc/binding")
	public String loginProc(HttpServletRequest req, Model uiModel,Locale locale) {
		
	//	System.out.println(user.getUsername());
	//	System.out.println(username);
	//	System.out.println(password);
		System.out.println(ConfigConstants.ITIM_GLOBALID_BASE);
	//	ITIMAuthManager authManager = new ITIMAuthManager();
		String uid = StringUtils.nullToString(req.getParameter("uid"), "").toUpperCase();
		//	String uid = "ITIM MANAGER";

		TbUserVO loginVO = new TbUserVO();
		loginVO.setUserId(uid);
		
		ArrayList<TbUserVO> userInfo = null;
		String[] authority = null;
		UserBean userBean = null;
		try {
			userInfo = new ArrayList();//(ArrayList) authInfo.getLoginUser(loginVO);
			authority = new String[] {};//authInfo.getAuthority(loginVO);
			
			if(authority == null || authority.length == 0) {
				authority = new String[]{"USER"};
			}
			if(userInfo.size() > 0) {
				userBean = new UserBean(userInfo.get(0), authority);
			}
			
			//authManager.setLoginSession(req, userBean);
		//	UserBean userBean = authManager.login(uid, StringUtils.nullToString(req.getParameter("password"), ""));
	//		UserBean userBean = authManager.login(uid, "Secure99");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return "redirect:/";
	}
}
