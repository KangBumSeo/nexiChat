<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<%@ include file="/WEB-INF/views/jspf/anony_header.jsp" %>
	<title><spring:message code="MNU_0001"/></title>

<script type="text/javascript">
</script>
</head>
<body id="loginPage">
<div id="login">

	<div class="loginwrap">
		<c:url value="/security/proc/login" var="loginUrl"/>
		<form id="submitForm" action="${loginUrl}" method="post">
			<div class="hd">
				<h2><img src="${img_url}main/login_login.png" alt=""/></h2>
				<h1><img src="${img_url}main/logo1.png" alt=""/></h1>
			</div>
			<div class="form">
				<ul>
					<li><input type="text" id="uid" name="uid" class="uid" placeholder="Username" value="admin"/></li>
					<li><input type="password" id="PASSWORD" name="PASSWORD" class="upw" placeholder="PassWord" value=""/></li>
				</ul>
				<div class="chkbt">
					<div class="chk"><input type="checkbox" id="chkRemember" name="chkRemember"/><label for="chkRemember">Remember me</label></div>
					<div class="r"><a href="#">Need an account?</a></div>
				</div>

				<div class="cbtn">
					<a onclick="javascript:document.getElementById('submitForm').submit();" class="btn btn_login" style="cursor: pointer;">LOGIN</a>
				</div>
				<legend>Please Login</legend>

				<c:if test="${param.error != null}">
				<div class="alert alert-error">
				Invalid username and password.
				</div>
				</c:if>
				<c:if test="${param.logout != null}">
				<div class="alert alert-success">
				You have been logged out.
				</div>
				</c:if>
			</div>
		</form>
	</div>
</div>
</body>
</html>
