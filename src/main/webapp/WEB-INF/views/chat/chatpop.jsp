<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<spring:url value="/resources/contents/images/" var="img_url" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://code.jquery.com/jquery-latest.js"></script> 
<script type="text/javascript" src="/contents/js/nCommon.js"></script>
<script type="text/javascript" src="/contents/js/chatInsert.js"></script>
<link rel="stylesheet" type="text/css" href="/resources/contents/css/chatpop.css" />
<%
	String sessionName = (String)session.getAttribute("username");
	String sessionId = (String)session.getAttribute("userid");
	System.out.println("jsp sessionId >>> : " + sessionId);
%>
<script type="text/javascript">
//location.pathname = "/";
	//history.replaceState({}, null, location.pathname);
//	history.pushState(null, null, '/');

	var chatSeq = '${chatSeq}';
	var chatTname = '${chatTname}';
	var chatUserid = '${chatUserid}'
	var tableName = chatTname+chatUserid
	var sessionId = '<%= sessionId%>';

	$(document).ready(function(){
		var sParam = {"tableName":tableName, "fileTableName":tableName+'_file'}
		var returnConv = chatAjax('/chatConvSel', sParam, 'post');
		//$("#chat_main").scrollTop($("#chat_main")[0].scrollHeight);
		delHtml();
		conData(returnConv);
		delCheck();
	//	delReset();
		chatExit(sessionId, chatSeq);
		layerOpen('memSelPop', chatSeq , 'sel');
		layerOpen('memInPop', chatSeq , 'in');
		$("#chat_main").scrollTop($("#chat_main")[0].scrollHeight);

		$(document).on("keyup click", "#userconv", function(key){
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
			console.log(chat_conv);
			var param = {"tableName":tableName, "userid":sessionId, "usercs":chat_conv, "fileTableName":tableName+'_file'};
			var fileparam = {}

			
			if( $("#fileUpload")[0].files.length != 0 ) {  //첨부 파일이 있을 때

				//	param[chat_conv] = "1";
					param["fileStatus"] = "Y";

				fileCheck();

				var fileMapData = fileUpload();
				console.log("------------fileMapData");
				console.log(fileMapData);
				console.log(fileMapData.length);				

			 	var paramList = [];
			 	
			 	$.each( fileMapData , function(i,v){
				 	var param2 = {};
					param2["originFileName"] = v.originFileName;
					param2["tempFileName"] = v.tempFileName;
					param2["idate"] = v.idate;
/* 					param2["tableName"] = param.tableName;
					param2["userid"] = param.userid;
					param2["usercs"] = param.usercs;
					param2["fileTableName"] = param.fileTableName; */
					 
					paramList.push(param2);
				}); 
				
			 	param.paramList = paramList;
				console.log("------------paramList");
				console.log(paramList);
			};

			if(chat_conv !=null && chat_conv != ''){
				var convResult = chatAjax('/chatConvIn', param, 'post');
				//	console.log("tempfilename_0 >>>> : " + paramList[0].tempFileName);
			}else{
				return false;
			}

	
			
			if (convResult) {
				var returnConv = chatAjax('/chatConvSel', sParam, 'post');
			//	console.log(returnConv);
				conData(returnConv);
			};	
						
			/*
			var html = 	'<div class="main_text">';
				html +=	'	<div class="userid" id="dd">';
			//	html += 	chat_id;
				html +=	'	</div>';
				html +=	'</div>';

			$('#chat_main').append(html);
			*/
			fnReset();	
		});
	});

	function conData(fnCon) {

		$('#container > div > div > #chat_main > #main_text').remove();
		var html = 	'<div class="main_text" id="main_text">';
			for(i = fnCon.length; i > 0; i--) {
			}
			console.log(fnCon);

		var beforeDiv = "";
			
		$($(fnCon).get().reverse()).each(function(i, v){
			var seq = v.SEQ;
			var conv = v.USERCS;
			var id = v.USERID;		
			var tableseq = v.TABLESEQ;
			var tempfilename = v.TEMPFILENAME;			
			
	//		console.log(chatUserid);	
	//		console.log(id);
			console.log(sessionId);
			var class_name = "";
			var idClass_name = "";
			var convClass_name = "";
			var imgClass_name = "";

			// sessionId 비교하여 클래스 다르게 주기
			if (id == sessionId) {
				class_name = "userdiv";
				idClass_name = "userid";
				convClass_name = "usercon";
				imgClass_name = "userimg";
				delConv_name = "del_userconv";
				delImg_name = "del_userimg";

			}
			else {
				class_name = "otherdiv";
				idClass_name = "otherid";
				convClass_name = "othercon";
				imgClass_name = "otherimg";
				delConv_name = "del_otherconv";
				delImg_name = "del_otherimg";
				
			}
 

			// 대화와 사진 데이터 붙이기
			if ( seq+id === beforeDiv ){
				// 같았을 때 조건
				html +=	'	<div class="'+ class_name+'">'; //cov&checkbox div
				html += '		<input type="hidden" id="image_'+tableseq+'">'
				html +=	'		<div class="chkSel" style="float: left; padding: 10px 0px;"></div>';
				html += '	<div class="'+imgClass_name+'">';
			//	html += '		<div class="'+imgClass_name+'">';
				html += '			<img src="D:/sts/work/workspace/Nexicure Web Application/src/main/webapp/contents/images/newimage/'+tempfilename+'" alt="" style="width:70px;height:70px;">'
		//		html += '		</div>';	
				html +=	'	</div>';
		//		html +=	'	<div>';
/* 				html += '		<div class="delete">'; 
				html += '		<input type="button" style="width:50px; height: 50px;">';
				html += '		</div>';	 */
		//		html += '		<input type="button" class="'+delImg_name+'">';
				html +=	'	</div>';

			}
			else if ( seq+id != beforeDiv ){
				// 이전 채팅 내용 및 seq가 다를 경우 조건 
				
				beforeDiv = seq+id;

				html +=	'	<div class="'+class_name+'">';
				html +=	'		<div class="'+idClass_name+'">';
				html += 		id;
				html +=	'		</div>';

				
				html +=	'		<div class="convDom">'; //cov&checkbox div
				html += '			<input type="hidden" id="conv_'+seq+'">'
				html +=	'			<div class="chkSel" style="float: left; padding: 10px 0px;"></div>';
				html += '			<div class="'+convClass_name+'">';
				html += 			conv;
				html +=	'			</div>';
			//	html += '		<input type="button" class="'+delConv_name+'">';
				html +=	'		</div>';  //cov&checkbox div
	/* 			html += '		<div class="delete">'; 
				html += '		<input type="button" style="width:50px; height: 50px;">';
				html += '		</div>';	 */	

				if (tempfilename != null && tempfilename != '') {
					html +=	'		<div class="convDom">'; //cov&checkbox div
					html += '		<input type="hidden" id="image_'+tableseq+'">'
					html +=	'			<div class="chkSel" style="float: left; padding: 10px 0px;"></div>';
					html += '			<div class="'+imgClass_name+'">';
				//	html += '			<div class="'+imgClass_name+'">';
					html += '				<img src="contents\\images\\newimage\\'+tempfilename+'" alt="" style="width:70px;height:70px;">'
				//	html += '			</div>';	
					html += '			</div>';
//					html += '			<div class="delete">'; 
				//	html += '			<input type="button" class="'+delImg_name+'">';
//					html += '			</div>'; 
					html +=	'		</div>'; //cov&checkbox div
				}
				html +=	'	</div>';		
			}
			
		});
		
		html +=	'</div>';

		$('#chat_main').append(html);	

	}

	function delHtml(){
		$('#delBtn').remove();
		$('#cancleBtn').remove();
		$('#delOk').remove();

		if ($('#delBtn').length == 0) {
			var delHtml = '<input type="button" value="편집"  id="delBtn" >';
			$("#delOption").append(delHtml);
		}

	}

	function delCheck() {
		//$(document).on("#delBtn", "click", function());
		$("#delBtn").on().click(function(){
			
			$('#delBtn').remove();
			
			var delHtml = '<input type="button" value="취소"  id="cancleBtn" >';
				delHtml += '<input type="button" value="삭제" id="delOk" style="float: right;">';
			$("#delOption").append(delHtml);
			

			var chatUserid = '${chatUserid}'
			var sessionId = '<%= sessionId%>';
			var userDiv = '';
			var otherDiv = '';
			
			if( chatUserid === sessionId ){		userDiv = 'userdiv'; otherDiv =	'otherdiv' }
			else {	userDiv = 'userdiv'; otherDiv = ''; };
			
			console.log("click");
			var	delHtml = 	'<input type="checkbox" id="chkbox" >';
			if( otherDiv != ""){
				var userTarget = $('.userdiv');
				var otherTarget = $('.otherdiv');

				$.each(userTarget,function(i,v){
					//console.log(v);
					//$(v).append(delHtml);
					$(v).find('.chkSel').append(delHtml);
				});

				$.each(otherTarget,function(i,v){
				//	console.log(v)
					$(v).find('.chkSel').append(delHtml);
				});
			} else{
			}
			
			//$('.'+class_name+'').append(delHtml);
		delReset();
		delChat();
		});	
	}


	function delReset(){

		$('#cancleBtn').on().click(function(){
			console.log("click");

			var sParam = {"tableName":tableName, "fileTableName":tableName+'_file'}
			var returnConv = chatAjax('/chatConvSel', sParam, 'post');

			conData(returnConv);

			delHtml();
			delCheck();
			
		});
	
	}


	
//$('input:checkbox[id=chkbox]:checked').parent().parent().find('input')[0].id;
	function delChat(){
		$("#delOk").off().click(function(){

			if ($('input:checkbox[id=chkbox]:checked').length == 0 ) {
				alert("삭제할  채팅을 선택해주세요.");
				return false;
			}

			var con = confirm("삭제하시겠습니까?");
			if( !con ) {
				return false;
			}			
			
			var delConvList = [];
			var delImgList = [];
			
			//var delId = $('input:checkbox[id=chkbox]:checked').parent().parent().find();
		/* 	$.each(delId, function(i, v){
				delId = delId[i]
			}); */
			var delVal=$('input:checkbox[id=chkbox]:checked').parent().parent().find('input[type=hidden]');
			console.log(delVal);
			$.each(delVal, function(i, v){
				var delId = delVal[i].id;
		//		var del = delId.split("_");

				if (delId.split("_")[0] === 'conv') {
					var delConvSeq = delId.split("_")[1];
					delConvList.push(delConvSeq);	
				}else{
					var delImgSeq = delId.split("_")[1];
					delImgList.push(delImgSeq);
				}
			});
			//console.log(delConvList);
			//console.log(delImgList);
			if(delImgList.length === 0){
				delImgList = null ;
			}

			var dParam = {  
					"tableName" : tableName
				, 	"fileTableName" : tableName+'_file'
				,	"tableseq" : delImgList
				, 	"seq" : delConvList
			};
			var returnVal = chatAjax("/userChatDelete", dParam, 'post');
			console.log(returnVal);	
			
		//});

		if(returnVal != 0) {
	
		var sParam = {"tableName":tableName, "fileTableName":tableName+'_file'}
		var returnConv = chatAjax('/chatConvSel', sParam, 'post');

		conData(returnConv);

		delHtml();
		delCheck();

		}

	
	});

	}
	

	function fnReset(e){
		$("#chat_main").scrollTop($("#chat_main")[0].scrollHeight);
		$("#userconv").val("");
		$("#fileUpload").val("");
		//$("#userconv").focusout();
		//blur
		//$("#userconv").focus();
	}

	
	function fileUpload(){
		// 파일 업로드
		var fileUrl = "/nexiFileUpload";
		var formData = new FormData();
		var files = $("#fileUpload")[0].files;
		var fileSize = "";
		console.log(files);	

		fileCheck();
		
		$(files).each(function (i, v) {
			formData.append("file", v)
			var fileSize = v.size;
			if(fileSize > 5242880) {
				alert("5MB 이하의 파일만 첨부 가능합니다.");
				return false;
			};
		});

		console.log(formData);
		
		for (var pair of formData.entries()) {
            console.log(pair[0]+ ', ' + pair[1]); 
        }

		var fileReturn = fileAjax(fileUrl, formData);
		console.log(fileReturn);
	
		
		return fileReturn;
	};


	function fileCheck(){
		
		if ($("#fileUpload")[0].files.length > 5) {
			alert("파일은 최대 5개까지만 첨부 가능합니다.");

			$("#userconv").val("");
			$("#fileUpload").val("");
			return false;
		};
	}


	function fileAjax (fnUrl, fnData) {

		var successResult; 
		
		$.ajax({
			url: fnUrl,
			type: 'post',
			data: fnData,
			dataType: 'json',
			processData: false,
			async : false , 
			contentType: false,
			success: function(result,textStatus){
				console.log("success");
				console.log(result);
				successResult = result;
			},
			error: function(request, status, error){
				console.log("status : " + request.status + "\n" + "message : " + request.responseText + "\n" + "error : " + error);
			}
		});
		return successResult;

	}

	function layerOpen(id, chatSeq , click_yn ) {
	      console.log("hi");
	      //$("#"+id).on("click", function(){
	      //var clickYn = 'Y';
	      /*
	      layerOpen.setClickYn = function() { clickYn = 'N'; }
	      layerOpen.clickReset = function() { clickYn = 'Y'; }
	      */
		  // click_yn : in , sel

		  if(click_yn === 'in'){
			layerOpen.in(id,chatSeq);
		  }
		  else if( click_yn === 'sel' ){
			layerOpen.sel(id,chatSeq);
		  }
	      
	      //$("#layerClose").on("click", function(){
	      /* 
	        if($("#layerPop").val() === 'Y') {
	         
	      
	      }   */
	   }
layerOpen.in = function( id , chatSeq ){
	$("#"+id).on("click", function(){
	var param = {'id' : sessionId}
	var userData = chatAjax('/userSelect', param, 'post');
	$("#SelPop").show();
	$('#SelPop').css('width','750px');
	$("#pvHtml").remove();
	chatTypeHtml( sessionId , 'memberchat' , 'N' , userData , 'U' );
	});
} 	
	
layerOpen.sel = function(id,chatSeq){
    $("#"+id).on("mouseover", function(){
        $("#userList").remove();
        $("#pvHtml").remove();
         
        $("#SelPop").show();
		$('#SelPop').css('width','800px');
        param = {"chatSeq":chatSeq}
         
         var userResult = chatAjax("/userChattable", param, 'post');
	         
         if( userResult ) {
            html  = '<div id="userList">';
            $.each(userResult, function(i,v){
               var memId = v.GUESTID;
			   var index = i+1;
			   if( memId != '' && memId != null ) {
               html += '<div id="index" style="float: left; width: 50%;">'+index+'</div>';
               html += '<div id="memId" style="float: left; width: 50%;">'+memId+'</div>';      
			   }
            });
            html += '</div>';
            $("#memberchat").append(html);
         }     

         $("#"+id).on("mouseout", function(){
	         //$("#userList").remove();
	         $("#SelPop").hide();
	         $("#layerPop").hide();
	         $("#layerPop").val("N");
	         $("#userList").remove();
	         $("#pvHtml").remove();
	      });
	          
    });
}	
	
	   
   function chatExit(sessionId, chatSeq) {
      
      $("#chatExit").on("click", function(e){
         
         var exitConfirm = confirm("채팅방을 나가시겠습니까?");
         
         if(exitConfirm) {
            // 세션아이디 찾아서 걸어야함
            console.log("chatExit");
            param = {"guestid":sessionId, "seq":chatSeq}
            console.log(param);
        
            var userResult = chatAjax("/userChattalbeDel", param, 'post');
            console.log(userResult);
            if(userResult) { window.self.close(); }            
         }else{ 
            return;
         }
         
      });
   }

</script>
</head>

<body>
<div class="container" id="container">
	<div class="chat_header" style="background: #eeeeee;">
		<div style="margin-bottom:5px;width: 35%;height: 21px;float: left;">
			 <input type="button" value="인원 확인" id="memSelPop" style="float: left;">
           <input type="button" value="인원 추가" id="memInPop" style="float: left;">
		</div>
		<div style="margin-bottom:5px;width: 40%;height: 21px; float: left;">${chatTname}</div>
		<div style="margin-bottom:5px;width: 25%;height: 21px;float: left;">
            <input type="button" value="나가기" id="chatExit" style="float: right;">  
        </div>
		<div style="text-align: left; height: 25px;" id="delOption">
		</div>
	</div>
	
	<div>
		  <div id="SelPop" class="selPopDiv" >
          <div style="margin: 10px;">채팅 멤버</div>
          <div style="margin: 10px; height: 83%;">
             <div id="memberchat" class="mamberchatDiv">      
                <input type="hidden" value="N" id="memYN">     
             </div>   
          </div>
          
          <div style=" width: 100%; height: 24.6px;">
             <input type="button" value="끄기" id="memSelClose" style="margin:10px;">
          </div>
        
	</div>
	
	<div>
		  <div id="InPop" style="background:white; border: 1px solid; position:absolute; left:20px; top:42px; width:200px; height:300px; z-index:1;display:none; text-align: center;">
          <div style="margin: 10px;">멤버 추가</div>
          <div style="margin: 10px;">
             <div id="memberInsert" style="width: 100%;height: 24.6px;">      
                <!-- <input type="hidden" value="N" id="memYN">  -->    
             </div>   
          </div>
          <div style=" width: 100%; height: 24.6px;">
             <input type="button" value="끄기" id="memInClose" style="margin:10px;">
          </div>
        
	</div>
	
	<div class="chat_main" id="chat_main">
	</div>
	<div class="chat_input">
		<div class="input_name">
			<input type="text" id="userid" style="background: #f7f7f7; color: #515170;" value="<%=sessionId%>" disabled>
			<input type="hidden" id="tableName" value="${chatTname}">
			<input type="file" id="fileUpload" multiple>
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
