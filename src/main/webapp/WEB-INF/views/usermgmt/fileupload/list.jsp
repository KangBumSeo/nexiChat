<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/views/jspf/sysmgmt_header.jsp" %>
<title>Insert title here</title>


<script type="text/javascript">
function registry(){

	$("#f_acct").submit();
}
</script>
</head>
<body>
<form method="POST" id="f_acct" action="<spring:url value="/usermgmt/fileupload/proc"/>" enctype="multipart/form-data">
	<input type="file" name="file" />
	<input type="file" name="file" />
	<input type="text" name="title" value="<script>alert('a');</script>"/>
	<input type="button" value="등록" onclick="javascript:registry();" />
</form>
</body>
</html>