<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>chat_main</title>
<link rel="stylesheet" href="/contents/css/bootstrap-datepicker3.css">
<link rel="stylesheet" href="/contents/css/bootstrap-datepicker3.standalone.css">
<script type="text/javascript" src="/contents/js/nCommon.js"></script>
<script type="text/javascript" src="/contents/js/chatmain.js"></script>
<script src="/contents/js/bootstrap-datepicker.js"></script>
<script src="/contents/js/bootstrap-datepicker.kr.min.js"></script>
<%
	String sessionId = (String)session.getAttribute("userid");
	System.out.println("jsp sessionId >>> : " + sessionId);
%>
<script type="text/javascript">

	var sessionId = '<%=sessionId%>';
		console.log(sessionId);
			// ajax로 날릴 때 data 미리 세팅하기 
 			//var temp = {};
			// 특정 값 한개 뽑으려고 했을 시 사용 
			// $('#subject').val() : 이벤트 누르기 전에 먼저 값 뽑을 수 있도록
			// 만약 $('#subject') 하위를 뽑으려고 했을 시
			// var temp = $('#subject');
			// $.each( temp , function(i,v){
			// $('#'+v.id).val() : id로 타겟 되었을 시 사용 필요 
			// });
			//temp = { "Key" : $('#subject').val() };
	$(document).ready(function(){
		var param = {'':''};
		var test0 = chatAjax("/chatselectAll", param, 'post');
		console.log(test0);
		// 기본 화면 로딩
		whenSuccess(test0);

		mainClick();

	
	
	


		/*
		$('#searchSel').change(function(e){
			console.log("init")
			if(e.target.value === 'a'
				||e.target.value === 'b' ){
				$('#searchSel').val( 'b');
			}
		});
	
		$('#searchSel').keydown(function(e){
			console.log("init keydown")
			if(e.target.value === 'a'){
				$('#searchSel').val( 'b');
			}
		});
		*/
	});

	function mainClick(){

		var key = $("#selectBox option:selected").val();
		console.log("key >>> : "  + key)
		$("#selectBox").change(function(){
			key = $("#selectBox option:selected").val();
			console.log("key >>> : " + key);
			if (key == 'idate'){
				$("#select").replaceWith('<div id="date" style="float: left; margin-left: 50px;"><input type="text" class="dateSearch" id="datePicker_0"> <a style="float: left;">-</a> <input type="text" class="dateSearch" id="datePicker_1"></div>');

				$("#datePicker_0").datepicker({
					format: "yyyy-mm-dd",
					todayHightlight :true
				});

			
				$("#datePicker_1").datepicker({
					format: "yyyy-mm-dd",
					todayHightlight :true
				});
			}else{
				$("#date").replaceWith('<div id="select" style="float: left; margin-left: 50px;"><input type="text" name="searchSel" id="searchSel"></div>');
				
				
			}
			
		});

	//	enterSelect('searchSel');
		
		$(document).on("keypress", "#searchSel", function(e) {
			if (e.keyCode == 13) {

				if (key == 'idate') {
					var startDate = $("#datePicker_0").val();
					var endDate = $("#datePicker_1").val();

					if (value === "") {
						alert("검색할 내용을 입력하세요!");
						$("#searchSel").focus();
						return;
					}

					$('#chat_data').empty();
					var cParam = { "key" : key, "startdate" : startDate, "enddate" : endDate};
					var test = chatAjax('/chatselect', cParam ,'post');
					console.log(test);

					whenSuccess(test);	
				}else{
					var value = $.trim($("#searchSel").val());	
					
					if (value === "") {
						alert("검색할 내용을 입력하세요!");
						$("#searchSel").focus();
						return;
					}

					$('#chat_data').empty();
					var cParam = { "key" : key, "value" : value};
					var test = chatAjax('/chatselect', cParam ,'post');
					console.log(test);

					whenSuccess(test);		
				}				
			}
		});
		

		$("#search_btn").off().click( function(e){		
			if (key == 'idate') {
				var startDate = $("#datePicker_0").val();
				var endDate = $("#datePicker_1").val();
				console.log(startDate);
				console.log(endDate);

				if (value === "") {
					alert("검색할 내용을 입력하세요!");
					$("#searchSel").focus();
					return;
				}

				$('#chat_data').empty();
				var cParam = { "key" : key, "startdate" : startDate, "enddate" : endDate};
				var test = chatAjax('/chatselect', cParam ,'post');
				console.log(test);

				whenSuccess(test);	
			}else{
				var value = $.trim($("#searchSel").val());	
				
				if (value === "") {
					alert("검색할 내용을 입력하세요!");
					$("#searchSel").focus();
					return;
				}

				$('#chat_data').empty();
				var cParam = { "key" : key, "value" : value};
				var test = chatAjax('/chatselect', cParam ,'post');
				console.log(test);

				whenSuccess(test);		
			}				
			
		});
	}

	function whenSuccess( fnTest ) {
		var html = '';
		$.each( fnTest, function(key, value) {
		    html += "<tr>";
			html += "<td>"+value.SEQ+"</td>";
			html += "<td><a onclick='chatPopup("+'"'+value.SEQ+'"'+','+'"'+value.SUBJECT+'"'+','+'"'+value.USERID+'"'+")'>"+value.SUBJECT+"</a></td>";
			html += "<td>"+value.USERID+"</td>";
			html += "<td>"+value.IDATE+"</td>";
			html += "</tr>";
		});
		$('#chat_data').append(html);
	};

	function chatPopup (fnSeq, fnTname, fnUserid) {
		if (sessionId != 'null') {
			var windowCheck;
			var options = 'top=100, left=1400, width=594, height=610, location=no resizable=no';
		
			windowCheck = window.open('/chatpop?seq='+fnSeq+'&'+'subject='+fnTname+'&'+'userid='+fnUserid, 'chatpop', options);

			console.log("seq ::::: " + fnSeq + "\n" + "userid :::::: " + sessionId);
			param = {"chatseq" : fnSeq, "guestid" : sessionId}
			chatAjax("/userChattable", param, 'post');
		}else{
			alert("로그인 하세요");
		}
		
	};	

</script>

</head>
<style>
/* .exitselect{
	display: block;
	color: red;
}
.initSelect:hover{
	display: none;
	color: black; */
}

table.type08 {
  border-collapse: collapse;
  text-align: left;
  line-height: 1.5;
  border-left: 1px solid #ccc;
  margin: 20px 10px;
}

table.type08 thead th {
  padding: 10px;
  font-weight: bold;
  border-top: 1px solid #999696;
  border-right: 1px solid #999696;
  background: #bccae7;
}
table.type08 tbody th {
  width: 150px;
  padding: 10px;
  font-weight: bold;
  vertical-align: top;
  border-right: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
  background: #ececec;
}
table.type08 td {
  width: 350px;
  padding: 10px;
  vertical-align: top;
  border-right: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
}

.dateSearch {
	float: left;
	display: block;
	height: 19px;
}

</style>
<body>
<div class="section" style="width:50%, float:left;">
<!-- 	<form id="chat" name="chat" action="/chat" method="get"> -->
	<table class="type08" width="700" style="table-layout:fixed; text-align:center;">
		<thead>
			<tr>
				<th colspan="4">채팅방 검색</th>
			</tr>
			<tr>
				<th style="background: #eee;" colspan="4">
					<div style="float:left; margin-left: 50px"> 
					<select id="selectBox">
						<option value="seq" selected>번호</option>
						<option value="subject">제목</option>
						<option value="userid">방장</option>
						<option value="idate">날짜</option>
					</select>
					<!-- 
					<div id="date">
						<input type="text" id="datePicker_0">
						-
						<input type="text" id="datePicker_1">
					</div>
					 -->	
					</div>
					<div id="select" style="float: left; margin-left: 50px;">
						<input type="text" name="searchSel" id="searchSel">
					</div>
					<div>
						<input type="button" name="search_btn" id="search_btn" value="검색">
					</div>
					<div>
						
					</div>
				</th>
			</tr>
			<tr>
				<th style="background: #eee;">번호</th>
				<th style="background: #eee;">제목</th>
				<th style="background: #eee;">방장</th>
				<th style="background: #eee;">입력/수정 날짜</th>
			</tr>
		</thead>
		<tbody>
			<tbody id="chat_data">
			</tbody>
		</tbody>
	</table>
<!--  	</form>-->
</div>

</body>
</html>