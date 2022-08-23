package com.nexicure.nim.services;

import org.springframework.security.core.userdetails.User.UserBuilder;

import java.util.ArrayList;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.nexicure.nim.controller.SecurityController;
import com.nexicure.nim.entities.User;
import com.nexicure.nim.entities.vo.TbUserVO;


public class UserDetailsServiceImpl implements UserDetailsService {
	private static Logger logger = LogManager.getLogger(UserDetailsServiceImpl.class);

	//@ Autowired
	//private AuthInfo authInfo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		//User user = findUserbyUername(username);
	

		//logger.info(" UserDetailsServiceImp > check AuthInfo : "+ username);
		
		TbUserVO loginVO = new TbUserVO();
		loginVO.setUserId(username);
		
		ArrayList<TbUserVO> userInfo = null;
		UserBuilder builder = null;
		String[] authority = null;
		try {
			userInfo = new ArrayList();//(ArrayList) authInfo.getLoginUser(loginVO);
			authority = new String[] {};//authInfo.getAuthority(loginVO);
			
			if (userInfo.size() > 0) {
				TbUserVO authVO = userInfo.get(0);
			//	logger.info(" UserDetailsServiceImp > check AuthInfo : "+ authVO.getUserId());
				
				builder = org.springframework.security.core.userdetails.User.withUsername(authVO.getUserId());
				builder.password(new BCryptPasswordEncoder().encode(authVO.getUserId()));
				if(authority == null || authority.length == 0) {
					builder.roles("USER");
				} else {
					builder.roles(authority);
				}
			} else {
				throw new UsernameNotFoundException("User not found.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return builder.build();
	}

	private User findUserbyUername(String username) {
		if(username.equalsIgnoreCase("admin")) {
			return new User(username, "admin123", "ADMIN");
		}
		return null;
	}
}
