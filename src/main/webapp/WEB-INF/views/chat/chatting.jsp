<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="/contents/css/bootstrap-datepicker3.css">
<link rel="stylesheet" href="/contents/css/bootstrap-datepicker3.standalone.css">
<script src="/contents/js/bootstrap-datepicker.js"></script>
<script src="/contents/js/bootstrap-datepicker.kr.min.js"></script>
<script type="text/javascript" src="/contents/js/nCommon.js"></script>
<%
	String sessionId = (String)session.getAttribute("userid");
	System.out.println("jsp sessionId >>> : " + sessionId);
%>
<script type="text/javascript">

	$(document).ready(function(){
		var sessionId = '<%= sessionId %>';
		console.log(sessionId);

		var param = {'userid' : sessionId}

		var returnVal = chatAjax("/userChattableSel", param, 'post');
		console.log(returnVal);
		chattingSel(returnVal);
		mainClick();
			
	});

	function chattingSel(fnVal){
		console.log("function>>>>>>>>>");
		var html = '<div id="chat_content">';
		$.each(fnVal, function(i, v){
			html += '<div class="chat content" style="background:#fff;">';
			html += '	<div class="content_in no_top">'+v.SEQ+'</div>';
			html += '	<div class="content_in no_top no_left">'+v.SUBJECT+'</div>';
			html += '	<div class="content_in no_top no_left">'+v.USERID+'</div>';
			html += '	<div class="content_in no_top no_left">'+v.IDATE+'</div>';
			html += '</div>';
		});
		html += '</div>';
		$('#chat').append(html);

	};

	function mainClick(){

		var key = $("#selectBox option:selected").val();
		console.log("key >>> : "  + key)
		$("#selectBox").change(function(){
			key = $("#selectBox option:selected").val();
			console.log("key >>> : " + key);
			if (key == 'idate'){
				$("#select").replaceWith('<div class="select" id="date"><input type="text" class="dateSearch" id="datePicker_0"> <a>-</a> <input type="text" class="dateSearch" id="datePicker_1"></div>');

				$("#datePicker_0").datepicker({
					format: "yyyy-mm-dd",
					todayHightlight :true
				});
			
				$("#datePicker_1").datepicker({
					format: "yyyy-mm-dd",
					todayHightlight :true
				});
			}else{
				$("#date").replaceWith('<div class="select" id="select"><input type="text" name="searchSel" id="searchSel"></div>');				
			}
			
		});

		$(document).on("keypress", "#searchSel", function(e){
			if(e.keyCode == 13){
				optionSearch(key);
			}
		});

		$("#searchBtn").off().click(function(e){
			optionSearch(key);
		});

		
	};


	function optionSearch(fnKey){
		if (fnKey == 'idate') {
			var startDate = $("#datePicker_0").val();
			var endDate = $("#datePicker_1").val();

			if (value === "") {
				alert("검색할 내용을 입력하세요!");
				$("#searchSel").focus();
				return;
			}

			$('#chat_data').empty();
			var cParam = { "key" : fnKey, "startdate" : startDate, "enddate" : endDate};
			var selResult = chatAjax('/chatselect', cParam ,'post');
			console.log(selResult);

			$("#container > #contents > #chat > #chat_content").remove();
			
			chattingSel(selResult);
	
		}else{
			var value = $.trim($("#searchSel").val());	
			
			if (value === "") {
				alert("검색할 내용을 입력하세요!");
				$("#searchSel").focus();
				return;
			}

			$('#chat_data').empty();
			var cParam = { "key" : fnKey, "value" : value};
			var selResult = chatAjax('/chatselect', cParam ,'post');
			console.log(selResult);

			$("#chat_content").remove();
			chattingSel(selResult);
		}
	};

	

</script>
</head>
<style>

.chatting{
	margin-left: 2%;
	margin-top: 3.5%;
	text-align: center;
	

}

.chatInTable{
	width: 700px;
	margin-left: 2%;
	text-align: center;
	background: #eee;
}

.chat{
	width: 700px;
	height: 43.5px;
	background: #bccae7;
	font-weight: bold;
	
}


.content_header{

	padding: 10px;
	font-size: larger;
	text-align:center;
}

.content_sub_header{
    background:#eee;
 
}

.select{
	height: 100%;
	float: left;
	margin-left: 50px;
}


.content_title{
    float: left;
    width: 25%;
    font-size: larger;
    padding: 10px;
	border: 1px solid #ccc;
    height: 100%;

}

.content_in{
	float: left;
    width: 25%;
    font-size: larger;
    font-weight: 500;
    padding: 10px;
	border: 1px solid #ccc;
    height: 100%;

}

.no_top {
	border-top : 0px;

}

.no_left{
	border-left : 0px;
}

</style>
<body>





<div class="chatting">
		
	<div class="chatInTable" id="chat">
		<div class="chat content_header">접속 채팅방</div>
		<div class="chat content_sub_header" style="padding:10px;">
			<div class="select"> 
				<select id="selectBox">
					<option value="seq" selected>번호</option>
					<option value="subject">제목</option>
					<option value="userid">방장</option>
					<option value="idate">날짜</option>
				</select>
			</div>
			<div class="select" id="select" >
				<input type="text" name="searchSel" id="searchSel">
			</div>
			<div >
				<input type="button" name="searchBtn" id="searchBtn" value="검색">
			</div>
		</div>
		<div class="chat content_sub_header">
			<div class="content_title">번호</div>
			<div class="content_title no_left">제목</div>
			<div class="content_title no_left">방장</div>
			<div class="content_title no_left">입력/수정 날짜</div>
		</div>
	
<!-- 		<div class="chat content" style="background:#fff;">
			<div class="content_title">11</div>
			<div class="content_title">22</div>
			<div class="content_title">33</div>
			<div class="content_title">44</div>
		</div> -->

	</div>

</div>

</body>
</html>