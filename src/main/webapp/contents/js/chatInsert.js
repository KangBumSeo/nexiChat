

function  chatTypeHtml ( sessionId, targetId, type_yn  , userReturn , chatType){
// Y 공개 , N 비공개 , "" : 선택 
/*	$("#pbHtml").remove();
	$("#pvHtml").remove();*/
	if ( type_yn != ""){	
		 
		if( type_yn === 'Y' ){
			var pbHtml = "";
				pbHtml +='<div  id="pbHtml">'
				pbHtml +='	<div class="chat content_sub_header">';
				pbHtml +='		<div class="chat_info" style="font-size: larger; text-align:center;">';
				pbHtml +='			채팅방 제목';
				pbHtml +='		</div>';
				pbHtml +='		<div class="chat_info" style="width: 60%">';
				pbHtml +='			<input type="text" name="subject" id="subject" placeholder="문자+숫자 조합으로 입력하세요" style=" height: 100%; width: 200px; margin-left: 35px;">';
				pbHtml +='			<input type="button" id="userCheck" value="중복 확인">';
				pbHtml +='		</div>';
				pbHtml +='	</div>';

				pbHtml += 	'<div class="chat content_sub_header" >';
				pbHtml +='		<div class="chat_info" style="font-size: larger; text-align:center;">';
				pbHtml +='			채팅 방장';
				pbHtml +='		</div>';
				pbHtml +='		<div class="chat_info" style="width: 60%">';
				pbHtml +='			<input type="text" name="userid" id="userid" style="width:200px; height: 100%; margin-left: 35px;" placeholder= "'+sessionId+'" disabled >';
				pbHtml +='		</div>';
				pbHtml +='	</div>';
				pbHtml +='</div>'
				$("#"+targetId).append(pbHtml);
		}
			var pvHtml = "";
				pvHtml += '<div class="chat content_body" id="pvHtml">';
				pvHtml += '<div class="pvSubmit" style="width: 100%; height: 7%;">';
				pvHtml += '<div class="pvButton" style="float: left;">';
				pvHtml += '	<input type="button" id="userReset" value="초기화" style="height: 25px; margin: 5px 0px;">';
				pvHtml += '<input type="text" style="margin-left: 50px;">';		
				pvHtml += '</div>';
				pvHtml += '<div class="pvButton" style="float: right;">';
				pvHtml += '	<input type="button" id="userSubmit" value="만들기" style="height: 25px; margin: 5px 0px;">';
				pvHtml += '</div>';				
				pvHtml += '</div>';
				pvHtml += '<div class="pvAll" style="width:100%; height: 90%;">'; //pvAll 
				pvHtml += '	<div class="chat content_body_main" id="pvbody" style="margin-right: 10%;">';
			
				pvHtml += '		<div class="content_body_line" >';
				pvHtml += '			<div class="chk" style="width: 10%; float:left;">';
				pvHtml += '			</div>';
				pvHtml += '			<div class="content_body_detail chk" id="userId">';
				pvHtml += '			유저 아이디';
				pvHtml += '			</div>';
				pvHtml += '			<div class="content_body_detail chk" id="delYN">';
				pvHtml += '			상 태';
				pvHtml += '			</div>';
				pvHtml += '		</div>';
				
				pvHtml += '	</div>';
				
				pvHtml += '	<div class="chat content_body_main"  id="pvbodyChk">';
				pvHtml += '		<div class="content_body_line">';
				pvHtml += '			<div class="content_body_detail">';
				pvHtml += '			유저 아이디';
				pvHtml += '			</div>';
				pvHtml += '			<div class="content_body_detail">';
				pvHtml += '			상 태';
				pvHtml += '			</div>';
				pvHtml += '		</div>';
				pvHtml += '	</div>';	
				
				pvHtml += '</div>';  //pvAll 
				
				pvHtml += '</div>';
				
				$("#"+targetId).append(pvHtml);
				
				$.each(userReturn, function(i, v){
					var userId = v.USERID
					var delYN = v.DELYN
					
					userSelHtml('pvbody', userId, delYN, 'Y', i);
				
					userChoose(i, 'pvbodyChk', chatType)
		
				});
			
		
	}
	
}

function userSelHtml ( htmlId, userId, delYN, chkYN, i){	
	var pvHtml = '';
	//	pvHtml = '			<input type="hidden" id="userHidden" value ="'+hiddenVal+'">';
	if (chkYN === 'Y') {
		pvHtml += '		<div class="content_body_line" id="userSel_'+i+'">';
		pvHtml += '			<div class="chk" style="width: 10%; float:left;">';
		pvHtml += '				<input type="checkbox" id="chkUser_'+i+'">';
		pvHtml += '			</div>';		
	}else {
		pvHtml += '		<div class="content_body_line userTarget" id="userSel_'+i+'">';
	}
		pvHtml += '			<div class="content_body_detail chk" id="userId_'+i+'">';
		pvHtml += 				userId;
		pvHtml += '			</div>';
		pvHtml += '			<div class="content_body_detail chk" id="delYN_'+i+'">';
		pvHtml += 				delYN;
		pvHtml += '			</div>';
		pvHtml += '		</div>';
	$("#"+htmlId).append(pvHtml);
}


function buttonSel(chatType) {
	
	$("#userReset").off().click(function(){
		
		$(':checkbox:checked').prop('checked', false);
		$(".userTarget" ).remove();

	});
	
	
	$("#userSubmit").off().click(function(e){
		console.log("userSubmit 클릭 ==================");
		console.log("guestid >>>>> : " + guestid);
		console.log("chatType >>>>> : " + chatType);

 		var subject = $.trim($("#subject").val());
		var userid = sessionId;
		var tableName = subject + userid	
		var guestid = [];
		var status = chatType;
		
		console.log("status >>>> : " + status);
		
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

		if(insertResult) {
			alert("채팅방이 생성되었습니다.");
			console.log(insertResult);
			location.href = "/";
		}else{
			alert("채팅방이 생성되지 않았습니다.");
			return;
		}
		
		
	});
}
