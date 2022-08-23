<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

	<div id="left">
		<div class="hdtit">
			<h1 style="font-size:large; font-weight: bold;">
			<a href="#"><%-- <img src="${img_url}main/sub_logo1.png" alt=""/>--%>ICM2</a>
			</h1>
		</div>
		<nav id="menu">
		    <ul class="sidebar-menu">
		    <%--
		    	<!-- 통계 -->
				<!--li><a href="#"><img class='img-icon' src="${img_url}icon/equalizer.png"/> <spring:message code="MNU_1000"/> <span class="ar"></span></a>
					<ul class="sidebar-submenu">
						<li><a href="#"><spring:message code="MNU_1100"/></a></li><! -- 전체현황(전일) -- >
						<li><a href="#"><spring:message code="MNU_1200"/></a></li><! -- 월별추이 -- >
						<li><a href="#"><spring:message code="MNU_1300"/></a></li><! -- 일별추이 -- >
					</ul>
				</li-->
		    	<!-- 시스템 계정관리 -->
				<li><a href="#"><img class='img-icon' src="${img_url}icon/local_offer.png"/> <spring:message code="MNU_2000"/> <span class="ar"></span></a>
					<ul class="sidebar-submenu">
						<li><a href="#"><spring:message code="MNU_2100"/></a></li><!-- AIX -->
						<li><a href="#"><spring:message code="MNU_2200"/></a></li><!-- Linux -->
						<li><a href="#"><spring:message code="MNU_2300"/></a></li><!-- HP-UX -->
						<li><a href="#"><spring:message code="MNU_2400"/></a></li><!-- Solaris -->
						<li><a href="#"><spring:message code="MNU_2500"/></a></li><!-- Windows -->
						<li><a href="#"><spring:message code="MNU_2600"/></a></li><!-- Oracle -->
						<li><a href="#"><spring:message code="MNU_2700"/></a></li><!-- MSSQL -->
						<li><a href="#"><spring:message code="MNU_2800"/></a></li><!-- MainFrame -->
						<li><a href="#"><spring:message code="MNU_2900"/></a></li><!-- WebApps -->
						<li><a href="#"><spring:message code="MNU_2A00"/></a></li><!-- WebAppsR -->
						<li><a href="#"><spring:message code="MNU_2B00"/></a></li><!-- SHARED-->
						<li><a href="#"><spring:message code="MNU_2C00"/></a></li><!-- ETC -->
					</ul>
				</li>
		    	<!-- 사용자 관리 -->
				<li><a href="#"><img class='img-icon' src="${img_url}icon/person.png"/> <spring:message code="MNU_3000"/> <span class="ar"></span></a>
					<ul class="sidebar-submenu">
						<li><a href="#"><spring:message code="MNU_3100"/></a></li><!-- 사용자 -->
					</ul>
				</li>
		    	<!-- ISIM 관리자 -->
				<li><a href="#"><img class='img-icon' src="${img_url}icon/account_box.png"/> <spring:message code="MNU_4000"/> <span class="ar"></span></a>
					<ul class="sidebar-submenu">
						<li><a href="<spring:url value="/sysmgmt/system/main"/>"><spring:message code="MNU_4100"/></a></li><!-- 시스템관리 -->
						<li><a href="<spring:url value="/sysmgmt/account/main"/>"><spring:message code="MNU_4200"/></a></li><!-- 계정관리 -->
						<li><a href="#"><spring:message code="MNU_4300"/></a></li><!-- 코드기본 -->
						<li><a href="#"><spring:message code="MNU_4400"/></a></li><!-- 코드상세 -->
						<li><a href="#"><spring:message code="MNU_4500"/></a></li><!-- Config -->
						<li><a href="#"><spring:message code="MNU_4600"/></a></li><!-- TIMADM상태 -->
						<!--li><a href="#"><spring:message code="MNU_4700"/></a></li--><!-- 계정 암호정책 -->
						<li><a href="#"><spring:message code="MNU_4800"/></a></li><!-- 계정 사용자 -->
					</ul>
				</li>
		    	<!-- SIM -->
				<li><a href="#"><img class='img-icon' src="${img_url}icon/bookmarks.png"/> <spring:message code="MNU_5000"/> <span class="ar"></span></a>
					<ul class="sidebar-submenu">
						<li><a href="#"><spring:message code="MNU_6100"/></a></li><!-- SIM시스템 -->
						<li><a href="#"><spring:message code="MNU_5200"/></a></li><!-- LDAP시스템 -->
						<li><a href="#"><spring:message code="MNU_5300"/></a></li><!-- LDAP Search -->
						<li><a href="#"><spring:message code="MNU_5400"/></a></li><!-- 처리 조회 -->
						<li><a href="#"><spring:message code="MNU_5500"/></a></li><!-- 서비스 조회 -->
					</ul>
				</li>
		    	<!-- 시스템 설정 -->
				<!--li><a href="#"><img class='img-icon' src="${img_url}icon/settings.png"/> <spring:message code="MNU_6000"/> <span class="ar"></span></a>
					<ul class="sidebar-submenu">
						<li><a href="#"><spring:message code="MNU_6100"/></a></li><! -- 그룹관리 -- >
						<li><a href="#"><spring:message code="MNU_6200"/></a></li><! -- 비밀번호변경정책 -- >
					</ul>
				</li-->
		    	<!-- LOG조회 -->
				<li><a href="#"><img class='img-icon' src="${img_url}icon/assignment.png"/> <spring:message code="MNU_7000"/> <span class="ar"></span></a>
					<ul class="sidebar-submenu">
						<li><a href="#"><spring:message code="MNU_7100"/></a></li><!-- 시스템 작업내역 -->
					</ul>
				</li>
			 --%>
			
				<!-- 채팅 -->
				<li><a href="#">채팅<span class="ar"></span></a>
					<ul class="sidebar-submenu">
						<!-- <a href="/chatmain"> 채팅 <span class="ar"></span></a> -->
						<li><a id="test">채팅 검색<span class="ar"></span></a></li>
					</ul>
					<ul class="sidebar-submenu">
						<li><a id="chat_insert">채팅 등록<span class="ar"></span></a></li>
					</ul>
					<ul class="sidebar-submenu">
						<li><a id="chat_update">채팅 수정<span class="ar"></span></a></li>
					</ul>								
				</li>
				
				<!--li class="active">
					// 최초 출력시 오픈
					
					// Spring Security tag
				<sec:authorize access="hasRole('ROLE_USER')">
 					<sec:authentication property="principal.username" />
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
 					<sec:authentication property="principal.username" />
				</sec:authorize>
				<li-->
		    </ul>
		</nav>
	<script>
		$.sidebarMenu($('.sidebar-menu'));
	</script>
	</div>

