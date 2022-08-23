package com.nexicure.nim.services.utility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nexicure.es.services.common.StringUtils;
import com.nexicure.nim.ConfigConstants;
import com.nexicure.nim.config.DataServiceConfig;
import com.nexicure.nim.entities.UserBean;
import com.nexicure.nim.entities.vo.McodedescVO;

@Service
public class CommUtil {
	private static Logger logger = LogManager.getLogger(CommUtil.class);
	
    public static String lPad(String input, int limit, String prefix) {
        if(limit <= input.getBytes().length)
            return input;
        
        int loop_length = limit - input.getBytes().length;
        StringBuffer prefixSB = new StringBuffer();
        
        for(int j=0;j<loop_length;j++)
        	prefixSB.append(prefix);
        
        return prefixSB.toString() + input;
    }

    public static String getDateFormat(String input, String seperator) {
    	return input == null ? "" : input.substring(0,4) + seperator + input.substring(4,6) + seperator + input.substring(6);
    }

	public final static int nullToInt(Object src, int value) {
		int tmp = value;
		if (src == null) {
			return tmp;
		} else {
			try {
				tmp = Integer.parseInt(String.valueOf(src));
			}catch(Exception e) {
			}finally {
				return tmp;
			}
		}
	}
	
	public static void setCodeMap(ObjectNode rootNode, List<McodedescVO> voList, String tag) {
		Map<String, String> map = new HashMap();
		ObjectMapper mapper = new ObjectMapper();
		for(McodedescVO tVO : voList) {
			map.put(tVO.getCode(), tVO.getCodeName());
		}
		try {
			rootNode.put(tag, mapper.readTree(mapper.writeValueAsString(map)));
		} catch(Exception e) {
			e.printStackTrace();
			logger.error(e);
		}
	}
	
	public static boolean ipCheck(HttpServletRequest request) {
		return ipCheck("", request, "");
	}
	public static boolean ipCheck(String ipList, HttpServletRequest request) {
		return ipCheck(ipList, request, "");
	}
	public static boolean ipCheck(HttpServletRequest request, String args) {
		return ipCheck("", request, args);
	}
	public static boolean ipCheck(String ipList, HttpServletRequest request, String args) {
		String ip = request.getHeader("X-FORWARDED-FOR");
		if (ip == null)
			ip = request.getRemoteAddr();
		
		logger.info("Request from <" + ip + "> as " +request.getRequestURI() + "?" + StringUtils.nullToString(request.getQueryString(),""));
		if(args.equals("fsProcess")) {
			return true;
		}
		
	    
	    String allowedIP = StringUtils.nullToString(ConfigConstants.CONFIG.getString("sys.itim.tdi.ip"),"");
	    if(allowedIP.indexOf(ip) > -1) {
    		return true;
	    }
	    

		logger.info("Deny the Request!!");
		return false;
	}
	
}
