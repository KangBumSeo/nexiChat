<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<%@ include file="/WEB-INF/views/jspf/sysmgmt_header.jsp" %>
<%@ include file="/WEB-INF/views/jspf/pagination.jsp" %>
<script src="http://code.highcharts.com/highcharts.js"></script>
<script type="text/javascript" src="/resources/contents/js/jqgrid/jquery.jqGrid.js"></script>
<script type="text/javascript" src="/resources/contents/js/nCommon.js"></script>
<script type="text/javascript">
$(function(){
	clickEvent();
}); //end ready funcion

function clickEvent(){


	$('#loginSubmit').click(function(e){
			var id = $('#loginId').val();
			var pw = $('#loginPw').val();

			if( id === '' || pw === '' ){
				alert( 'Please Enter ID,PASSWORD' );
				return;
			}

			var loginParam = {
				  'id' : id 
				, 'password' : pw
			};
			var loginResult = runAjax('/loginChat',loginParam,'post');
			if(loginResult.length === 1){
				let loginId = loginResult[0].USERID;
				let loginName = loginResult[0].USERNAME;
				let top = $('#topUserDetail');

				let topHtml = '<spring:message code="MSG_0000" arguments='###'/>';
					topHtml = topHtml.replace('###',loginName);

				top.html(topHtml);
			}
			else{
				alert('Please Check The ID AND PW');
			}
	}); 
	//end click loginSubmit 

	$('#signUp').click(function(e){
		$('#signUpPop').remove();

		var divObject = $('#signUpDiv');
		var htmlData = signUpHtml();
		divObject.append(htmlData);
		signChangeEvent();

		$('#signUpClose').click(function(e){
			$('#signUpPop').remove();
		});

		$('#idDupChk').click(function(e){
			let signId = $('#signId').val();
								
			if( signId === '' ){
				alert( "Please Enter ID" );
				return;
			}
			else if( signId.length < 5 ) {
				alert('Please Enter At Least 4 Characters of ID');
				return;
			}			

			let loginChkParam = {
					'id' : signId
			}
			let loginResult = runAjax( '/signUpIdChk' , loginChkParam , 'post' );
			
			if( loginResult === 0 ){
			//	$('#signUpSubmit').removeAttr('disabled');
				$('#signUpSubmit').removeAttr('disabled');
			}
			else{
				$('#signUpSubmit').prop('disabled',true);
			}
		});

		$('#signUpSubmit').click(function(e){
			let signId = $('#signId').val();
			let signPw = $('#signPw').val();
			let signName = $('#signName').val();
			let signBirth = $('#signBirth').val();
		
			if( 
					signId === '' 
				||  signPw === ''
				||  signName === ''
				||  signBirth === ''
			   ){
				alert( "You Have to fill up the empty space" );
				return;
			}

			let signParam = {
				  "id" : signId
				, "password" : signPw
				, "name" : signName
				, "birth" : signBirth
			};

			let confirmBool = confirm('Are You Done Writing the Contents?');

			if( confirmBool === true ){
				var result = runAjax('/signUpInsert',signParam , 'post' );
				if(result === 1){
					alert('Congratulations on Your Membership');
					$('#signUpPop').remove();
				}
				else{
					alert('Membership Registration Failed');
				}
			}
		});
		//end click signUpSubmit
	});
	//end click signUp

}

function signChangeEvent(){
	let targetPw = $('#signPw');
	let targetPwChk = $('#signPwChk');

	targetPw.change(function(e){
			signPwView( targetPw,targetPwChk );
	});
	//end change targetPw

	targetPw.keyup(function(e){
			if(signPwCnt != 0){
				signPwView( targetPw,targetPwChk );
			}
	});
	//end keydown targetPw

	targetPwChk.change(function(e){
			signPwView( targetPw,targetPwChk );
	});
	//end change targetPwChk

	targetPwChk.keyup(function(e){
			if(signPwCnt != 0){
				signPwView( targetPw,targetPwChk );
			}
	});
	//end keydown targetPwChk
}

var signPwCnt = 0;
function signPwView( fnPw , fnPwChk  ){
	let pw = fnPw.val();
	let pwChk = fnPwChk.val();
	let pwBool = false;

	if( signPwCnt === 0 ){
		signPwCnt++;
	}
	else if( pw.length < 5 ){
		pwBool = 'password';
	}
	else if( pw === pwChk && pw != '' && pwChk != '' ){
		pwBool = true;
	}else{ pwBool = false; }
	
	signPwChkHtml(pwBool);
}

function signPwChkHtml(	fnPwBool ){
	let pwChkViewHtml = '';
	switch (fnPwBool){
		case true : 
			pwChkViewHtml = '<a class="chkStrTrue"> Password Matches. </a>';
			break;
		case false : 
			pwChkViewHtml = '<a class="chkStrFalse"> Password Does not Match. </a>';
			break;
		case 'password':
			pwChkViewHtml = '<a class="chkStrFalse"> Password must be at least 5 characters long </a>';
			break;
	};
	$('#signPwChkView').html(pwChkViewHtml);
}

function signUpHtml(){
	
	var returnHtml = '<div class="loginLayout" id="signUpPop" >';
		returnHtml += '	<div class="loginHardLayout">';
		returnHtml += '		<a class="loginTitle"> sign up init list </a> ';
		returnHtml += '	</div>';
		returnHtml += '	<div class="loginBodyLayout">';
		returnHtml += '		<div id=""> <a> id : </a> <input id="signId" type="text"> <input type="button" value="ID Duplicate Check" id="idDupChk"> </div>';
		returnHtml += '		<div id=""> <a> pw : </a> <input id="signPw" type="password"> </div>';
		returnHtml += '		<div id=""> <a> pwChk : </a> <input id="signPwChk" type="password"> </div>';
		returnHtml += '		<a id="signPwChkView"></a>';
		returnHtml += '		<div id=""> <a> name :  </a> <input id="signName" type="text"> </div>';
		returnHtml += '		<div id=""> <a> birth : </a> <input id="signBirth" type="text"> </div>';
		returnHtml += '		<div id="loginBottomLayout"> <input type="button" value="Sign" id="signUpSubmit"> <input type="button" value="close" id="signUpClose"> </div>';
		returnHtml += ' </div>';
		returnHtml += '</div>';
	 return returnHtml;	
}

</script>

 <style>
#signPwChkView{
	display:block;
	height:15px;
	width:100%;
	color:#000000;
	margin-top:7px;
	padding-left:40px;
}
.chkStrTrue{
	color:green;
	font-size:20px;
}
.chkStrFalse{
	color:red;
	font-size:20px;
}
#signUpSubmit{
	float:right;
	margin-right:30px;
}
.loginLayOut{
	position:fixed;
	top:10%;
	left:10%;
	height:500px;
	width:800px;
}
.loginHardLayout{
	width:100%;
	height:10%;
	background:#5d5d5d; 
	display:block;
}
.loginBodyLayout{
	width:100%;
	height:90%;
	background:#333333;
	display:block;
}
.loginBodyLayout > div > a{
	color:#000000;
	margin-left:30px;
	padding-top:20px;
	display:block;
	float:left;
}
.loginBodyLayout > div > input{
	float:left;
	margin-top:20px;
	margin-left:5px;
	height:15px;
	display:block;
}
.loginBodyLayout > div{
	display:block;
	height:10%;
}
.loginTitle{
	font-size:25px;
	color:#252525;
	width:85%;
	height:100%;
	margin: 0% 0% 0% 40%;
	padding-top: 7px;
	display:block;
}
</style>
</head>
<html xmlns="http://www.w3.org/1999/xhtml">
<body>
<div id="loginDiv">
	<a>ID : </a><input type="text" id="loginId"     onKeypress="if(event.keyCode==13){$('#loginSubmit').click()}"/>
	<a>PW : </a><input type="password" id="loginPw" onKeypress="if(event.keyCode==13){$('#loginSubmit').click()}"/>
	<input type="button" id="loginSubmit" value="submit"/>
	<input type="button" id="signUp" value="signUp"/>
</div>
<div id="signUpDiv">
</div>
</body>
</html>
