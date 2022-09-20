<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>chat_main</title>
<script type="text/javascript" src="/contents/js/nCommon.js" ></script>
<script type="text/javascript" src="/contents/js/chatCommon.js" charset="EUC-KR"></script>
<script type="text/javascript">

	//$(document).on(function(){
	$(function(){
		$(document).off("click");
		var param = {'':''};
		var test0 = chatAjax("/chatselectAll", param, 'post');
		// 기본 화면 로딩
		//whenSuccess(test0);
		
		//var ynMap = {"update":"Y", "delete":"Y"};
		//whenSuccess_1(test0, updateYn, ynMap);
		//mainClick();
		
		var oldTableName = "";
		updateChk();
		//updateOk();
		deleteChk();
		reload_data("/chatselectAll");

	});

	function updateOk(fnOldTableName) {
		$(document).on("click", "#comBtn", function(e){
			console.log(fnOldTableName);
			var con = confirm("수정하시겠습니까?");
			if (!con) {
				return false;
			};

			var thVal = $('#data_table > thead > tr > th ');
			console.log($('#data_table > thead > tr > th '));
			//console.log($('#data_table > thead > tr > th ')[0].innerHTML);
			var arrTemp = [];

			$.each(thVal, function(i, v){
				var thValue = v.id
				console.log(thValue);
				arrTemp.push(thValue); 
				
			});
			console.log("arrTemp >>> : " + arrTemp);

			
			console.log(e.target);
			var uTarget = $(e.target.parentElement.parentElement);
			//var test = document.getElementById("seq_0").value;
			var uVal = [];
			//for (i= leng or 0 or 1 ; i<uTarget[0].childNodes.length or 0 ; i--) {
			  for(i=0; i < uTarget[0].childNodes.length-3; i++) {
				uVal.push(uTarget[0].childNodes[i].id);
			console.log(uVal);
			} 
			/*
			$.each(uTarget[0].childNodes ,function(i,v){

				return ;
				
			});
			*/

			/*
			if(t === "a"){
				return "<th>seq</th><th>seq</th><th>seq</th>"
			}
			else if (t === "b"){
				return "<th>seq</th><th>seq</th><th>seq</th>"
			}
			
			switch (t) {
			  case "a":
				return "<th>seq</th><th>seq</th><th>seq</th>"
			    break;
			  case "b" :
				return "<th>seq</th><th>seq</th><th>seq</th>"  
			}
			*/
				
			console.log(uTarget[0].childNodes[0]);
			console.log(document.getElementById(uVal[1]).childNodes[0].value);
			console.log(document.getElementById(uVal[2]).childNodes[0].value);
		//	console.log(test);
			//console.log( $(cTarget[0].childNodes[1]).find("input")[0].value );
			var uParam = {};
			for (i=1; i<uVal.length-1; i++) {
				var updateKey = arrTemp[i]
				var updateVal = document.getElementById(uVal[i]).childNodes[0].value;
			//	console.log("updateVal >>> : " + updateVal);
			//	var updateVal = arrTemp[i]+'_'+
				console.log("updateKey >>> : " + updateKey + "\n" +  "updateVal >>> : " + updateVal);
				//uParam.set(updateKey, updateVal);
				uParam[updateKey]=updateVal;
				//updateKey 
				//uParam.updateKey = updateVal;

				}

			var seq = $('#data_table > thead > tr > th ')[0].id;
			//	$('seq_'+index+' > input').val();
				var seqVal = document.getElementById(uVal[0]).childNodes[0].text;
				var newTableName = uParam.subject + uParam.userid;
			//	var seqVal = $('#seq_44 > a')[0].text;
			//	console.log(seq);
			//	console.log(seqVal_1);
		/* 	 	var idate = $('#data_table > thead > tr > th ')[3].id;
			 	var date = new Date();
			 	var year = date.getFullYear();
			 	var month = ('0'+(date.getMonth()+1)).slice(-2);
			 	var date = ('0'+date.getDate()).slice(-2);
				var idateVal = year+'-'+month+'-'+date;
				uParam[idate] = idateVal; */
				uParam[seq] = seqVal;
				uParam["otName"]=fnOldTableName;
				uParam["ntName"]=newTableName;




				
			// 채팅방 수정 중복체크
			var dParam = {'key': 'userid', 'value' : uParam.userid};
			var dResult = chatAjax('/chatupdateDup', {"id" : uParam.userid}, 'post');

			if (dResult == 0) {
				alert("존재하지 않는 사용자 입니다.");
				return false;

			}else {
				dResult = chatAjax('/chatselect', dParam, 'post');
				//console.log(dResult.get(0).get("subject"));
				console.log("듑 else 절 result>>>>>>>.");
				console.log(dResult);
				console.log(dResult[0].USERID);
				console.log(dResult[0].SUBJECT);
				var dSubject = dResult[0].SUBJECT;
				if (dSubject == uParam.subject) {
					alert("해당 유저는 채팅방이 이미 존재합니다.");
					return false;
				}

				dResult = chatAjax('/chatupdateok', uParam, 'post');
				
				if(dResult === 0 ){
					alert('fail');
					return;
				}
				else{
					alert("success")
				reload_data("/chatselectAll");	
				}
			};
			

			
			
			});
			
		};

</script>

</head>
<style>
table.type77 {
  border-collapse: collapse;
  text-align: left;
  line-height: 1.5;
  border-left: 1px solid #ccc;
  margin: 20px 10px;
}

table.type77 thead th {
  padding: 10px;
  font-weight: bold;
  border-top: 1px solid #ccc;
  border-right: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
  background: #eee;
}
table.type77 tbody th {
  width: 150px;
  padding: 10px;
  font-weight: bold;
  vertical-align: top;
  border-right: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
  background: #ececec;
}
table.type77 td {
  width: 350px;
  padding: 10px;
  vertical-align: top;
  border-right: 1px solid #ccc;
  border-bottom: 1px solid #ccc;
}
</style>
<body>
<div class="section" style="width:50%, float:left;">
	<table class="type77" width="1200" style="table-layout:fixed; text-align:center;" id="data_table">
		<thead>
			<tr>
				<td colspan="7" style="background: #bccae7; border-top: 1px solid #ccc; font-weight: bold; border-bottom: 1px solid #999696;">채팅방 목록</td>
			</tr>
			<tr>
				<th id="seq">번호</th>
				<th id="subject" style="border-bottom: 2px solid #c00; ">제목</th>
				<th id="userid" style="border-bottom: 2px solid #c00; ">방장</th>
				<th id="idate" >입력/수정 날짜</th>
				<td colspan="2" style="background: #eee; font-weight: bold;">수정</td>
				<td style="background: #eee; font-weight: bold;">삭제</td>
			</tr>
		</thead>
		<tbody id="chat_data">
		</tbody>
	</table>
</div>
</body>
</html>
