<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%
	String sessionName = (String)session.getAttribute("username");
	String sessionId = (String)session.getAttribute("userid");
	System.out.println("jsp sessionName >>> : " + sessionName);
	System.out.println("jsp sessionId >>> : " + sessionId);
%>

<script type="text/javascript">

	$(document).ready(function(){
		var sessionId = '<%= sessionId%>';
		var sessionName = '<%= sessionName%>';
		console.log(sessionId);
		if (sessionId !='null') {
			$("#loginBtn").hide();
			$("#loginMsg").hide();
		}else{
		$("#logoutBtn").hide();
		$("#topUserDetail").hide();		

	}

	});

</script>

		<div class="thd">
			<div class="util">
				<div class="tuser">
					<div class="user"></div>
					<div class="text" >
					<!-- 여기서 세션 데이터로 넣는 작업
					
					<spring:message code="MSG_0000" arguments=""/>
					 -->
						<div id="topUserDetail">
							<spring:message code="MSG_0000" arguments="<%= sessionName%>"/>
						</div>
						<div id="loginMsg">
							<a>로그인이 <br> 필요합니다.</a>
						</div>
					</div>
				</div>
				<div class="tlogin" id="loginBtn">
					<a id="chatlogin"/>
						<img src="${img_url}main/hd_btn_login.png" alt="<spring:message code="ISM_0002"/>">
					</a>
				</div>
				<div class="tlogout" id="logoutBtn">
					<a href="<spring:url value="/logout"/>">
						<img src="${img_url}main/hd_btn_logout.png" alt="<spring:message code="ISM_0003"/>">
					</a>
				</div>
			</div>
		</div>

