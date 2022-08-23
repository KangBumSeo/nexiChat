<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>chat_main</title>
<script type="text/javascript" src="/contents/js/nCommon.js"></script>
<script type="text/javascript">
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
		});

		
		$(document).on("keypress", "#searchSel", function(e) {
			if (e.keyCode == 13) {
				var value = $.trim($("#searchSel").val());	
				//console.log("value >>>>> : " + value);
				
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
		

		$("#search_btn").off().click( function(e){		
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
			
		});
	}

	function whenSuccess( fnTest ) {
		var html = '';
		$.each( fnTest, function(key, value) {
		    html += "<tr>";
			//var html = "<tr align='center'>";
			html += "<td>"+value.SEQ+"</td>";
			html += "<td><a onclick='chatPopup("+'"'+value.SEQ+'"'+','+'"'+value.SUBJECT+'"'+','+'"'+value.USERID+'"'+")'>"+value.SUBJECT+"</a></td>";
			html += "<td>"+value.USERID+"</td>";
			html += "<td>"+value.IDATE+"</td>";
			html += "</tr>";
		});
		//$('#chat_data').empty();
		$('#chat_data').append(html);

		//var test = '<table id="sddd">';
	};

	function chatPopup (fnSeq, fnTname, fnUserid) {
		var windowCheck;
		var options = 'top=100, left=1400, width=594, height=685, location=no';
	
		windowCheck = window.open('/chatpop?seq='+fnSeq+'&'+'subject='+fnTname+'&'+'userid='+fnUserid, 'chatpop', options);

	};	

</script>

</head>
<style>
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
  border-top: 1px solid #ccc;
  border-right: 1px solid #ccc;
  border-bottom: 2px solid #c00;
  background: #dcdcd1;
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
</style>
<body>
<div class="section" style="width:50%, float:left;">
<!-- 	<form id="chat" name="chat" action="/chat" method="get"> -->
	<table class="type08" width="700" style="table-layout:fixed; text-align:center;">
		<thead>
			<tr>
				<th colspan="2">채팅방 검색</th>
			</tr>
			<tr>
				<th>
				<div style="float:left; margin-left: 50px"> 
				<select id="selectBox">
					<option value="seq" selected>번호</option>
					<option value="subject">제목</option>
					<option value="userid">방장</option>
					<option value="idate">날짜</option>
					
				</select>
				</div>
			
				<div >
					<input type="text" name="searchSel" id="searchSel">
				</div>
				</th>
				<th>
					<input type="button" name="search_btn" id="search_btn" value="검색">
				</th>
			</tr>
		</thead>
		<tbody>
			<tr>
			
			</tr>
		</tbody>
	</table>
<!--  	</form>-->
</div>
<div class="section" style="width:50%, float:left;">
	<table class="type08" width="700" style="table-layout:fixed; text-align:center;"">
		<thead>
			<tr>
				<th colspan="4">채팅방 목록</th>
			</tr>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>방장</th>
				<th>입력/수정 날짜</th>
			</tr>
		</thead>
		<tbody id="chat_data">
		</tbody>
	</table>
</div>
</body>
</html>