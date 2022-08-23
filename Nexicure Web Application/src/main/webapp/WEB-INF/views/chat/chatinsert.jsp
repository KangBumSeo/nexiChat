<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script type="text/javascript" src="/contents/js/nCommon.js"></script>
<script type="text/javascript">

	$(document).ready(function(){			

		//$("#submit_btn").prop('disabled', true);
		
		$("#userCheck").off().click(function(e){
		
			userCheck();		
			
		});

		$("#submit_btn").off().click( function (event) {
			//console.log(e)
			
			if ( $("#idCheck").val() === 'y') {

				var subject = $.trim($("#subject").val());
				var userid = $.trim($("#userid").val());
				var tableName = subject + userid			
				
				console.log( typeof subject);
				console.log( subject*1 );
				console.log( isNaN(subject) )
				console.log(tableName);
				
				if (subject === "") {
					alert("채팅방 제목을 입력하세요");
					
					$("#subject").focus();
					return;
				}; 
				
				if($.isNumeric(subject)){
					alert("문자+숫자 조합으로 입력하세요");
					return;
				};
				
				/*
				if( !isNaN(subject*1) ){
					alert("문자+숫자 조합으로 입력하세요");
					return;
				}
				*/

				if (userid === '') {
					alert("채팅 방장을 입력하세요");
				}
				
				var iParam = {
						'subject':subject,
						'userid':userid,
						'tableName':tableName
					}

					var insertResult = runAjax('/chatinsertdata', iParam, 'post');
					console.log(insertResult)
					/*
					if (insertResult) {
						var tableName = $('#subject').val();
						var tParam = {'tableName':tableName};
						console.log(tParam);
						chatAjax('/chatTableInsert', tParam, 'post');
					}
					*/
					alert("채팅방이 생성되었습니다.");
					var subject = $("#subject").val('');
					var userid = $("#userid").val('');	
					$("#idCheck").val('');
			}else{
				alert("채팅 방장 중복 확인이 필요합니다.");
				$("#idCheck").focus();
			}		
			
		});		
	});


	function userCheck(e) {	
		var subject = $.trim($("#subject").val());
		var userid = $.trim($("#userid").val());
		console.log("subject >>> : " + subject);
		console.log("userid >>> : " + userid);
		var cParam = {'key':'userid', 'value':userid}
		var uCheck = chatAjax('/chatUserCheck', cParam, 'post');

		if ( uCheck === 1) {
			
			alert("이미 채팅방이 존재합니다.");
			return false;			
		}else{
			if(userid === '') {
				alert("채팅 방장을 입력하세요");
				return false;
			}
			alert("채팅방 생성이 가능합니다.");
			$("#idCheck").val('y');
			/*
			console.log($("input[name=idCheck]")[0].value);
			console.log( document.getElementsByName("idCheck") ) 
			var test = document.getElementsByName("idCheck")
			$.each(test, function(i,v){
			});
			*/
		}
		
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
	<table class="type08" width="700" style="table-layout:fixed;">
		<thead>
			<tr>
				<th colspan="2">채팅방 등록</th>
			</tr>
		</thead>
		<tbody>
		
			<!-- 
			<tr>
				<td>시퀀스</td>
				<td>
				<input type="text" name="seq" id="seq">
				</td>
			</tr>
			 -->
			<tr>
				<td>채팅방 제목</td>
				<td>
				<input type="text" name="subject" id="subject" placeholder="문자+숫자 조합으로 입력하세요"
						style="width:200px;">
				</td>
			</tr>
			<tr>
				<td>채팅 방장</td>
				<td>
				<input type="text" name="userid" id="userid" style="width:200px;">
				<input type="button" name="userCheck" id="userCheck" value="중복 확인">
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:center">
				<input type="button" id="submit_btn" value="제출">
				<input type="hidden" name="idCheck" id="idCheck" value="">
				</td>
			</tr>
		</tbody>
	</table>
</div>
</body>
</html>