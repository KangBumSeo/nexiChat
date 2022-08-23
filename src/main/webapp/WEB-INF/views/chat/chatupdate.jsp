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

	$(document).ready(function(){
		var param = {'':''};
		var test0 = chatAjax("/chatselectAll", param, 'post');
		console.log(test0);
		// 기본 화면 로딩
		//whenSuccess(test0);
		
		var updateYn = "";
		var ynMap = {"update":"Y", "delete":"Y"};
		whenSuccess_1(test0, updateYn, ynMap);
		//mainClick();
		updateChk();
		updateOk();
		reload_data("/chatselectAll");
		deleteChk();
		
	

	});

	function updateOk() {
		$("#completeBtn").off().click(function(e){

			var thVal = $('#data_table > thead > tr > th ');
			console.log($('#data_table > thead > tr > th '));
			//console.log($('#data_table > thead > tr > th ')[0].innerHTML);
			var arrTemp = [];
			//4
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
			for (i=1; i<uVal.length; i++) {
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
		//	var seqVal = $('#seq_44 > a')[0].text;
		//	console.log(seq);
		//	console.log(seqVal_1);
			
			uParam[seq] = seqVal;
			console.log(uParam);
			
			chatAjax('/chatupdateok', uParam, 'post');
			reload_data("/chatselectAll");	
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
  background: #dcdcd1;
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
				<td colspan="7" style="border-top: 1px solid #ccc;">채팅방 목록</td>
			</tr>
			<tr>
				<th id="seq">번호</th>
				<th id="subject" style="border-bottom: 2px solid #c00; ">제목</th>
				<th id="userid" style="border-bottom: 2px solid #c00; ">방장</th>
				<th id="idate" >입력/수정 날짜</th>
				<td colspan="2">수정</td>
				<td>삭제</td>
			</tr>
		</thead>
		<tbody id="chat_data">
		</tbody>
	</table>
</div>
</body>
</html>