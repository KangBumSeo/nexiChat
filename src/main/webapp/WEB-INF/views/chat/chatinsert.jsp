<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
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
  border-top: 1px solid #999696;
  border-right: 1px solid #999696;
  background: #bccae7;
  text-align: center;
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



.content_body{
    background: #f7f7f7;
    padding: 0px 60px;
    height: 500px;
}

.content_body_main{
    height: auto;
    text-align: center;
    width: 45%;
    float: left;

}

.content_body_line{
	height: 43.5px;
}

.content_body_detail{
    float: left;
    width: 50%;
	height: 100%;
    padding: 10px;

}

.chk{
	width: 45%;
    height: 100%;
    padding: 10px;
}


.chat_info{
    height: 100%;
    float: left;
    width: 40%;
    padding: 10px;
    /* border: 1px solid; */
}




</style>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script type="text/javascript" src="/contents/js/nCommon.js"></script>
<script type="text/javascript" src="/contents/js/chatInsert.js"></script>
<%
	String sessionName = (String)session.getAttribute("username");
	String sessionId = (String)session.getAttribute("userid");
	System.out.println("jsp sessionId >>> : " + sessionId);
%>
<script type="text/javascript">
	var sessionId = '<%=sessionId%>';
	
	$(document).ready(function(){		
		
		console.log(sessionId);	

		chatType(sessionId);

		//$("#submit_btn").prop('disabled', true);
		

		
	});

	function userCheck(userId) {	

		$("#userCheck").off().click(function(e){
		var subject = $.trim($("#subject").val());
		var userid = userId;
		console.log("subject >>> : " + subject);
		console.log("userid >>> : " + userid);

		if (subject === "") {
			alert("채팅방 제목을 입력하세요");
			
			$("#subject").focus();
			return;
		}; 

		if($.isNumeric(subject)){
			alert("문자+숫자 조합으로 입력하세요");
			return;
		};
		
	
		var cParam = {'key':'tablechk', 'userid':userid, 'subject' :subject}
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
	
	/* 		console.log($("input[name=idCheck]")[0].value);
			console.log( document.getElementsByName("idCheck") ) 
			var test = document.getElementsByName("idCheck")
			$.each(test, function(i,v){
			}); */
					
		}
		
	});
} 

 	function chatType(sessionId) {

		$("#optionSelect").change(function(){
		//	console.log($(this).val());
			var selOption = $(this).val();

			$("#pbHtml").remove();
			$("#pvHtml").remove();
			

			console.log("selOption >>>>> : " + selOption);

			console.log("sessioinId :::::" + sessionId);
			var param = {'id' : sessionId}

			var userReturn = chatAjax('/userSelect', param, 'post');
			console.log(userReturn);


	 		if(selOption === 'public') {	 	 		
				chatTypeHtml(sessionId, chat, 'Y', userReturn, 'O');
	 		 }else if(selOption === 'private'){
	 			chatTypeHtml(sessionId, chat, 'N', userReturn, 'C');
		 	}else{
		 		chatTypeHtml(sessionId, chat, "", userReturn);
			}		

			userCheck(sessionId);	
	 	//	chatSubmit();	
	 			
	 		 
		});

	
 	}



 	function userChoose(index, htmlId, chatType){
	
		var clicktarget ="chkUser_"+index;  
		var userSeltarget = "userSel_"+index;
		console.log(clicktarget)
		console.log(userSeltarget);
		console.log("chatType >>>> : " + chatType);
		
		$("#"+clicktarget ).click(function(e){
		//	console.log(e.target.id)
			var target_index = (e.target.id).split('_')[1];
			var userid = $('#userId_'+target_index).text();
			var delYn = $('#delYN_'+target_index).text();
			
			console.log(userid);
			console.log(delYn);
			console.log($("#pvbodyChk").find("#userId_"+target_index).length);
			
			if ($("#pvbodyChk").find('#userId_'+target_index).length === 0){
			//var chk = e;

			userSelHtml(htmlId, userid, delYn, 'N', index);
				
			}else{
			$("#pvbodyChk > #"+userSeltarget ).remove();
			}
			
		});
			buttonSel(chatType);

	/* 	$("#userSubmit").off().click(function(e){
			console.log("userSubmit 클릭 ==================");

	 		var subject = $.trim($("#subject").val());
			var userid = sessionId;
			var tableName = subject + userid	
			var status = 'O';
			
			console.log(subject);
			var chkUser = $(':checkbox:checked');
			if(chkUser.length === 0) {
				alert("추가할 사용자를 선택하세요");
				return;
			}
			
			$.each(chkUser, function(i,v){
				var chkNum = v.id.split('_')[1];
				var chkUser = $("#userId_"+chkNum)[0].innerText;
				guestid.push(chkUser);
			});
			guestid.push(sessionId);
			console.log(guestid);

			var iParam = {
					'subject':subject,
					'userid':userid,
					'tableName':tableName,
					'fileTableName':tableName+'_file',
					'guestid':guestid,
					'status': status
			}

			var insertResult = chatAjax('/chatinsertdata', iParam, 'post');

			
			//alert("채팅방이 생성되었습니다.");
			console.log(insertResult);
			
		}); */

 	}



</script>
</head>

<body>


<div class="chatting">
		
	<div class="chatInTable" id="chat">
		<div class="chat content_header">채팅방 등록</div>
		<div class="chat content_sub_header" id="aaa" style="padding:10px;">
			<div class="selectDiv" style="text-align: center;"> 
				<select id="optionSelect">
					<option value="">선택</option>
					<option value="public">공개 채팅방</option>
					<option value="private">비공개 채팅방</option>
				</select>
			</div>	
		</div> 
	</div>
</div>


 <%-- <div class="section" style="width:50%, float:left;">
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
				<td style="background: #eee; text-align: center; font-weight: bold;">채팅방 제목</td>
				<td>
				<input type="text" name="subject" id="subject" placeholder="문자+숫자 조합으로 입력하세요"
						style="width:200px;">
				</td>
			</tr>
			<tr>
				<td style="background: #eee; text-align: center; font-weight: bold;">채팅 방장</td>
				<td>
				<input type="text" name="userid" id="userid" style="width:200px;" placeholder="<%=sessionId%>" disabled>
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
</div> --%>
</body>
</html>