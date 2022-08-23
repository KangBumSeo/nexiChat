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
					alert("ä�ù� ������ �Է��ϼ���");
					
					$("#subject").focus();
					return;
				}; 
				
				if($.isNumeric(subject)){
					alert("����+���� �������� �Է��ϼ���");
					return;
				};
				
				/*
				if( !isNaN(subject*1) ){
					alert("����+���� �������� �Է��ϼ���");
					return;
				}
				*/

				if (userid === '') {
					alert("ä�� ������ �Է��ϼ���");
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
					alert("ä�ù��� �����Ǿ����ϴ�.");
					var subject = $("#subject").val('');
					var userid = $("#userid").val('');	
					$("#idCheck").val('');
			}else{
				alert("ä�� ���� �ߺ� Ȯ���� �ʿ��մϴ�.");
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
			
			alert("�̹� ä�ù��� �����մϴ�.");
			return false;			
		}else{
			if(userid === '') {
				alert("ä�� ������ �Է��ϼ���");
				return false;
			}
			alert("ä�ù� ������ �����մϴ�.");
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
				<th colspan="2">ä�ù� ���</th>
			</tr>
		</thead>
		<tbody>
		
			<!-- 
			<tr>
				<td>������</td>
				<td>
				<input type="text" name="seq" id="seq">
				</td>
			</tr>
			 -->
			<tr>
				<td>ä�ù� ����</td>
				<td>
				<input type="text" name="subject" id="subject" placeholder="����+���� �������� �Է��ϼ���"
						style="width:200px;">
				</td>
			</tr>
			<tr>
				<td>ä�� ����</td>
				<td>
				<input type="text" name="userid" id="userid" style="width:200px;">
				<input type="button" name="userCheck" id="userCheck" value="�ߺ� Ȯ��">
				</td>
			</tr>
			<tr>
				<td colspan="2" style="text-align:center">
				<input type="button" id="submit_btn" value="����">
				<input type="hidden" name="idCheck" id="idCheck" value="">
				</td>
			</tr>
		</tbody>
	</table>
</div>
</body>
</html>