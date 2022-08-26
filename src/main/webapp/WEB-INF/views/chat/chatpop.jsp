<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.js"></script> 
<script type="text/javascript" src="/contents/js/nCommon.js"></script>
<%
	String sessionName = (String)session.getAttribute("username");
	String sessionId = (String)session.getAttribute("userid");
	System.out.println("jsp sessionId >>> : " + sessionId);
%>
<script type="text/javascript">
//location.pathname = "/";
	//history.replaceState({}, null, location.pathname);
	history.pushState(null, null, '/');

	var chatSeq = '${chatSeq}';
	var chatTname = '${chatTname}';
	var chatUserid = '${chatUserid}'
	var tableName = chatTname+chatUserid
	var sessionId = '<%= sessionId%>';

	$(document).ready(function(){
		var sParam = {"tableName":tableName}
		var returnConv = chatAjax('/chatConvSel', sParam, 'post');
		conData(returnConv);

		$(document).on("keyup", "#userconv", function(key){
			if(key.keyCode == 13) {
				var chat_conv = $("#userconv").val();
				var param = {"tableName":tableName, "userid":sessionId, "usercs":chat_conv};
				var convResult = chatAjax('/chatConvIn', param, 'post');

				if (convResult) {
					var returnConv = chatAjax('/chatConvSel', sParam, 'post');
					console.log(returnConv);
					conData(returnConv);
				};	
				fnReset();		
			}
		});
		

		
		$("#chat_submit").off().click(function(){
			var chat_conv = $("#userconv").val();
			var param = {"tableName":tableName, "userid":sessionId, "usercs":chat_conv};
			var convResult = chatAjax('/chatConvIn', param, 'post');

			console.log(convResult);
			if (convResult) {
				var returnConv = chatAjax('/chatConvSel', sParam, 'post');
				console.log(returnConv);
				conData(returnConv);
			};	

			fnReset();							
			/*
			var html = 	'<div class="main_text">';
				html +=	'	<div class="userid" id="dd">';
			//	html += 	chat_id;
				html +=	'	</div>';
				html +=	'</div>';

			$('#chat_main').append(html);
			*/
				
		});
	});


	function conData(fnCon) {
		$('#container > #chat_main > #main_text').remove();
			html = 	'<div class="main_text" id="main_text">';
			for(i = fnCon.length; i > 0; i--) {
			}
		$($(fnCon).get().reverse()).each(function(i, v){
			var conv = v.USERCS;
			var id = v.USERID;		
			
	//		console.log(chatUserid);	
	//		console.log(id);
			console.log(sessionId);

			if (id == sessionId) {
				html +=	'	<div class="userdiv">';
				html +=	'		<div class="userid" id="id">';
				html += 		id;
				html +=	'		</div>';
				html += '		<div class="usercon" id="id">';
				html += 		conv;
				html +=	'		</div>';
				html +=	'	</div>';
			}else{
				html +=	'	<div class="otherdiv">';
				html +=	'		<div class="otherid" id="id">';
				html += 		id;
				html +=	'		</div>';
				html += '		<div class="othercon" id="id">';			
				html += 			conv;
				html +=	'		</div>';
				html +=	'	</div>';
			}				
		});
		
		html +=	'</div>';

		$('#chat_main').append(html);		
	}

	function fnReset(e){
		$("#chat_main").scrollTop($("#chat_main")[0].scrollHeight);
		$("#userconv").val("");
		//$("#userconv").focusout();
		//blur
		//$("#userconv").focus();
	}
	
</script>
</head>

<style>

.container{
	margin : auto;
	border: 1px solid;
}


.chat_header {
	padding: 0px;
	margin: 0px;
	height: 50px;
	border-bottom: 1px solid;
	text-align:center;
	background: #eee;
}

.chat_main {
	width:100%;
	height: 440px;
	overflow-y: scroll
}

.main_text{
    width: auto;
    min-height: 380px;
	margin: 10px 20px ;
	color: #323232;
}

.userdiv{
	float:right;  
	display:block; 
	margin: 5px 0px 15px 0px;
	max-width: 277px;
}

.userid{
	width:93%;
	min-height: 20px;
	text-align: left;
	padding: 0px 10px;
	word-break: break-all;

}

.usercon{
	width:265px;
	min-height: 10px;
	background-color: #fac66e;
	text-align: left;
	color: white;
	padding: 10px;
	border-radius: 5px;
	margin-bottom: 8px;
    height: 100%;
    display: block;
    word-break: break-all;
	
}

.otherdiv{
	float:left;  
	display:block; 
	margin: 5px 0px 15px 0px;
	max-width: 277px;
}

.otherid{
	width:93%;
	min-height: 20px;
	text-align: left;
	padding: 0px 10px;
	word-break: break-all;
}

.othercon{
	width:265px;
	min-height: 10px;
	background-color: #4479e7;
	text-align: left;
	color: white;
	padding: 10px;
	border-radius: 5px;
	margin-bottom: 8px;
    height: 100%;
    display: block;
    word-break: break-all;
}


.chat_input {
	width:100%;
	height: 100px;
	border-top: 1px solid;
	background: #eeeeee;
}

.input_name {
	margin: 10px 20px ;

}

.input_conv{
	margin: 10px 20px;
}
</style>


<body>
<div class="container" id="container">
	<div class="chat_header" style="background: #eeeeee;">
		<div style="padding: 10px;">${chatTname}</div>
	</div>
	<div class="chat_main" id="chat_main">
	</div>
	<div class="chat_input">
		<div class="input_name">
			<input type="text" id="userid" style="background: #f7f7f7; color: #515170;" value="<%=sessionId%>" disabled>
			<input type="hidden" id="tableName" value="${chatTname}">
			<input type="button" id="chat_submit" value="입력" 
					style="float:right; margin-right: 20px; background: #a1a5ab; color: #fff; border: 1px #939292 solid;">
		</div>
		<div class="input_conv">
		<textarea rows="2" cols="70" id="userconv"></textarea>
		</div>
	</div>
</div>
</body>
</html>