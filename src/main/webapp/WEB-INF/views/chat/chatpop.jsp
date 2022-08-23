<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.js"></script> 
<script type="text/javascript" src="/contents/js/nCommon.js"></script>
<script type="text/javascript">
//location.pathname = "/";
	//history.replaceState({}, null, location.pathname);
	history.pushState(null, null, '/');

	var chatSeq = '${chatSeq}';
	var chatTname = '${chatTname}';
	var chatUserid = '${chatUserid}'
	var tableName = chatTname+chatUserid
	console.log(chatSeq);
	console.log(chatTname);
	console.log(tableName);

	$(document).ready(function(){
		var sParam = {"tableName":tableName}
		var returnConv = chatAjax('/chatConvSel', sParam, 'post');
		conData(returnConv);

		$(document).on("keypress", "#userconv", function(key){
			if(key.keyCode == 13) {
				//alert("엔터");
				var chat_id = $("#userid").val();
				var chat_conv = $("#userconv").val();
				console.log("chat_id >>> : " + chat_id);
				console.log("chat_conv >>> : " + chat_conv);

				var param = {"tableName":tableName, "hostid":chat_id, "hostcs":chat_conv};
				var convResult = chatAjax('/chatConvIn', param, 'post');
				console.log(convResult);

				if (convResult) {
					var returnConv = chatAjax('/chatConvSel', sParam, 'post');
					console.log(returnConv);
					conData(returnConv);
				};	

				fnReset();
							
			}
		});

		
		$("#chat_submit").off().click(function(){
			var chat_id = $("#userid").val();
			var chat_conv = $("#userconv").val();
			console.log("chat_id >>> : " + chat_id);
			console.log("chat_conv >>> : " + chat_conv);

			var param = {"tableName":chatTname, "hostid":chat_id, "hostcs":chat_conv};
			var convResult = chatAjax('/chatConvIn', param, 'post');
			console.log(convResult);

			if (convResult) {
				var sParam = {"tableName":chatTname}
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
			var conv = v.HOSTCS;
			var id = v.HOSTID;	
			console.log(chatUserid);	
			console.log(id);
			if (id == chatUserid) {
				html +=	'	<div style="float:right; display:block; margin: 5px 0px 15px 0px; ">';
				html +=	'		<div class="userid" id="id">';
				html += 		id;
				html +=	'		</div>';
				html += '		<div class="usercon" id="id">';
				html += 		conv;
				html +=	'		</div>';
				html +=	'	</div>';
			}else{
				html +=	'	<div style="float:left;  display:block; margin: 5px 0px 15px 0px; ">';
				html +=	'		<div class="otherid" id="id">';
				html += 		id;
				html +=	'		</div>';
				html += '		<div class="othercon" id="id">';
				html += 		conv;
				html +=	'		</div>';
				html +=	'	</div>';
			}				
		});
		
		html +=	'</div>';

		$('#chat_main').append(html);		
	}

	function fnReset(e){
		$("#chat_main").scrollTop($("#chat_main")[0].scrollHeight);
		var chat_id = $("#userid").val('');
		var chat_conv = $("#userconv").val('');
		$("#userid").focus();
	}
	
</script>
</head>

<style>

.container{
	margin : auto;
}


.chat_header {
	width:100%;
	height: 50px;
	border-color: yellow;
	border: solid;
	text-align:center;
}

.chat_main {
	width:100%;
	height: 400px;
}

.main_text{
    width: auto;
    min-height: 380px;
	margin: 10px 20px ;
}

.userid{
	width:265px;
	min-height: 30px;
	background-color: black;

}

.usercon{
	width:265px;
	min-height: 30px;
	background-color: red;
	
}

.otherid{
	width:265px;
	min-height: 30px;
	background-color: white;
	text-align: left;
}

.othercon{
	width:265px;
	min-height: 30px;
	background-color: blue;
	text-align: left;
}



.chat_input {
	width:100%;
	height: 100px;
	border-color: yellow;
	border: solid;
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
	<div class="chat_header">
		<div style="margin: 10px;">${chatTname}</div>
	</div>
	<div class="chat_main" id="chat_main" style="overflow-x: scroll; background: yellow;">
	<!-- 
		<div class="main_text">
		</div>
	 -->
	</div>
	<div class="chat_input">
		<div class="input_name">
			<input type="text" id="userid">
			<input type="hidden" id="tableName" value="${chatTname}">
			<input type="button" id="chat_submit" value="입력" 
					style="float:right; margin-right: 20px;">
		</div>
		<div class="input_conv">
		<textarea rows="2" cols="70" id="userconv"></textarea>
		</div>
	</div>
</div>
</body>
</html>