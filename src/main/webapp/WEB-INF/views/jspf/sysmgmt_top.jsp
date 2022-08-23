<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

		<div class="thd">
			<div class="util">
				<div class="tuser">
					<div class="user"></div>
					<div class="text" id="topUserDetail">
						<spring:message code="MSG_0000" arguments="NIM Admin"/>
					</div>
				</div>
				<div class="tlogin">
					<a id="chatlogin"/>
						<img src="${img_url}main/login_login.png" alt="<spring:message code="ISM_0002"/>">
					</a>
				</div>
				<div class="tlogout">
					<a href="<spring:url value="/logout"/>">
						<img src="${img_url}main/hd_btn_logout.png" alt="<spring:message code="ISM_0003"/>">
					</a>
				</div>
			</div>
		</div>

