<%@page import="com.nexicure.nim.ConfigConstants"%>
<%@page import="com.nexicure.commons.SpitConstants"%>
<%@page import="com.nexicure.nim.entities.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

	<title><spring:message code="MNU_0001"/></title>

	<!-- style.css start -->
  	<spring:theme code="styleSheet" var="app_css" />
	<spring:url value="/${app_css}" var="app_css_url" />
	<link rel="stylesheet" type="text/css" media="screen" href="${app_css_url}" />
	<!-- style.css end -->
	
    <spring:url value="/resources/contents/images/" var="img_url" />
    <spring:url value="/resources/contents/css/" var="css_url" />
    <spring:url value="/resources/contents/js/" var="js_url" />
    
	<script type="text/javascript" src="${js_url}jquery-3.6.0.js"></script>
	<script type="text/javascript" src="${js_url}common.js"></script>
	
	<script type="text/javascript" src="${js_url}jqgrid/i18n/grid.locale-kr.js"></script>
	<script type="text/javascript" src="${js_url}jqgrid/jquery.jqGrid.js"></script>
	
	<script type="text/javascript" src="${js_url}jquery.validate.custom.js"></script>


	<link rel="stylesheet" type="text/css" href="${css_url}jqgrid/jquery-ui-1.10.3.custom.css" />
	<link rel="stylesheet" type="text/css" href="${css_url}jqgrid/ui.jqgrid.css" />
	<link rel="stylesheet" type="text/css" href="${css_url}jqgrid/paginate.css" />

	<link rel="stylesheet" type="text/css" href="${css_url}font/font-awesome.min.css"/>
	<link rel="stylesheet" type="text/css" href="${css_url}menu.css"/>
 
	<script type="text/javascript" src="${js_url}jsgrid/jsgrid.js"></script>

<%
	String pathCd = request.getRequestURL().toString().replace(".jsp", "");
	if(pathCd.indexOf("views") > -1) {
		pathCd = pathCd.substring(pathCd.indexOf("views")+6);
	} else {
		pathCd = pathCd.substring(pathCd.indexOf(ConfigConstants.BASEURL)+6);
	}
	
	/*
	UserBean sessionUserView = (UserBean)session.getAttribute(SpitConstants.SESSION.USER_KEY);
	if(sessionUserView == null)
		response.sendRedirect(ConfigConstants.APP_BASEURL+"logout");
	*/
%>
	