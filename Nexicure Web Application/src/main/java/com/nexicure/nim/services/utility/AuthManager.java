package com.nexicure.nim.services.utility;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nexicure.commons.SpitConstants;
import com.nexicure.nim.entities.UserBean;

public class AuthManager {
	private Logger logger = LogManager.getLogger(AuthManager.class);
	
	public void setLoginSession(HttpServletRequest req, UserBean userBean) throws Exception {
		HttpSession session = req.getSession(false);
		if (session != null) {
			session.invalidate();
		}
		session = req.getSession(true);
		session.setAttribute(SpitConstants.SESSION.USER_KEY, userBean);
//		checkChallengeResponse(session, ITIMUtil.getPlatformContext(), userBean.getSubject());
	}
	
	public void setLoginSession(HttpServletRequest req, String auth) throws Exception {
		HttpSession session = req.getSession(false);
		
		session = req.getSession(true);
		session.setAttribute("auth_level", auth);
//		checkChallengeResponse(session, ITIMUtil.getPlatformContext(), userBean.getSubject());
	}
}
