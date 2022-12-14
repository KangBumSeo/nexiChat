<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<%@ include file="/WEB-INF/views/jspf/sysmgmt_header.jsp" %>
<%@ include file="/WEB-INF/views/jspf/pagination.jsp" %>
<script src="http://code.highcharts.com/highcharts.js"></script>
<script type="text/javascript" src="/resources/contents/js/jqgrid/jquery.jqGrid.js"></script>
<script type="text/javascript" src="/resources/contents/js/nCommon.js"></script>


 <style>
 #loginDiv{
     width: 200px;
    height: 100px;
    background: blanchedalmond;
    padding-top: 17px;
    border-radius: 10px 10px 10px 10px;
    margin: 269px;
    margin-left: 561px;
 }
 
 .idInitDiv{
 	width: 100%;
    height: 30%;
    padding-top: 5px;
    padding-left: 15px;
 }
 
 .pwInitDiv{
 	width: 100%;
    height: 30%;
    padding-left: 8px;
 }
 
 .buttonDiv{
    width: 100%;
    height: 30%;
 }
 
 #loginSubmit{
 	float: left;
    margin-left: 38px;
 }
 
 #signUp{
	float: right;
    margin-right: 30px;
 } 
#signPwChkView{
	display: block;
    width: 100%;
    color: #000000;
    margin-top: 5px;
    margin-bottom: 5px;
    padding-left: 40px;
    float: left;
    height: 5%;
}
.chkStrTrue{
	color:green;
	font-size: 15px;
    margin-left: 40px;
}
.chkStrFalse{
	color:red;
	font-size: 15px;
    margin-left: 40px;
}
#signUpSubmit{
	float: left;
	margin-right:30px;
}
.loginLayOut{
    height: 410px;
    width: 450px;
}
.loginHardLayout{
	width:100%;
	height:10%;
	background:#bccae7; 
	display:block;
	border-radius: 10px 10px 0px 0px;
}
.loginBodyLayout{
	width:100%;
	height:67%;
	background:#eee;
	display:block;
	border-radius: 0px 0px 10px 10px;
}

#idDupChk{
	background: #bccae7;
    color: #fff;
    height: 20px;
    margin-left: 10px;
}

.loginBodyLayout > div > a{
	color:#000000;
	margin-left:30px;
	padding-top:22px;
	display:block;
	float:left;
}
.loginBodyLayout > div > input{
	float: right;
    margin-top: 20px;
    margin-right: 42%;
    display: block;
    height: 20px;
}
.loginBodyLayout > div{
    display: block;
    float: left;
    width: 100%;
}
.loginTitle{
	font-size:25px;
	color:#252525;
	height:100%;
	display:block;
	text-align: center;
	padding-top: 2px;
}

.signUpDiv{
	height: 100%;
	width: 35%;
	position: fixed;
    top: 300px;
    left: 600px;
    display: none;
}

.loginBtn{
	background: #a1a5ab;
    color: #fff;
    border: 1px #939292 solid;
}

</style>

<script type="text/javascript">
$(function(){
	clickEvent();
}); //end ready funcion

function clickEvent(){


	$('#loginSubmit').click(function(e){
			var id = $('#loginId').val();
			var pw = $('#loginPw').val();

			if( id === '' || pw === '' ){
				alert( 'ID,PASSWORD??? ???????????????' );
				return;
			}

			var loginParam = {
				  'id' : id 
				, 'password' : pw
			};
			//runAjax.setDataType("String")
			var loginResult = runAjax('/loginChat',loginParam,'post');

			if(loginResult.loginStatus === 'Fail'){
				alert('ID,PASSWORD??? ???????????????');
			}
			
			/*
			if(loginResult.length === 0){
				alert('Please Check The ID AND PW');
			}
			location.reload();
			*/
			
	}); 
	//end click loginSubmit 

	$('#signUp').click(function(e){
		$('#signUpPop').remove();

		var divObject = $('#signUpDiv');
		var htmlData = signUpHtml();
		divObject.append(htmlData);
		signChangeEvent();
		
		divObject.show();

		$('#signUpClose').click(function(e){
			$('#signUpPop').remove();
			divObject.hide();
		});

		$('#idDupChk').click(function(e){
			let signId = $('#signId').val();
								
			if( signId === '' ){
				alert( "ID??? ???????????????" );
				return;
			}
			else if( signId.length < 5 ) {
				alert('ID??? 4?????? ??????????????? ?????????');
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

			let confirmBool = confirm('???????????? ???????????????????');

			if( confirmBool === true ){
				var result = runAjax('/signUpInsert',signParam , 'post' );
				if(result === 1){
					alert('??????????????? ?????????????????????!');
					$('#signUpPop').remove();
				}
				else{
					alert('??????????????? ?????????????????????');
				}
			}
			divObject.hide();
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
			pwChkViewHtml = '<a class="chkStrTrue"> ??????????????? ??????????????? </a>';
			break;
		case false : 
			pwChkViewHtml = '<a class="chkStrFalse"> ??????????????? ???????????? ???????????? </a>';
			break;
		case 'password':
			pwChkViewHtml = '<a class="chkStrFalse"> ??????????????? 5?????? ??????????????? ????????? </a>';
			break;
	};
	$('#signPwChkView').html(pwChkViewHtml);
}

function signUpHtml(){
	
	var returnHtml = '<div class="loginLayout" id="signUpPop" >';
		returnHtml += '	<div class="loginHardLayout">';
		returnHtml += '		<a class="loginTitle"> ???????????? </a> ';
		returnHtml += '	</div>';
		returnHtml += '	<div class="loginBodyLayout">';
		returnHtml += '		<div id=""> <a> ????????? : </a> ';
		
		// float : righ ??? CSS ???????????? input ?????? ?????? 
		returnHtml += '			<input type="button" value="ID ?????? ??????" id="idDupChk" style="margin-left: 7px; margin-right: 20%;"> ';
		returnHtml += '			<input id="signId" type="text" style="margin-left: 7px; margin-right: 5%;" > ';

		returnHtml += '		</div>';
		returnHtml += '		<div id=""> <a> ???????????? : </a> <input id="signPw" type="password" > </div>';
		returnHtml += '		<div id=""> <a> ???????????? ?????? : </a> <input id="signPwChk" type="password"> </div>';
		returnHtml += '		<a id="signPwChkView"></a>';
		returnHtml += '		<div id=""> <a style="padding-top:5px;"> ?????? :  </a> <input id="signName" type="text" style="margin-top: 5px;"> </div>';
		returnHtml += '		<div id=""> <a> ???????????? : </a> <input id="signBirth" type="text"> </div>';
		returnHtml += '		<div id="loginBottomLayout" style="padding-left: 22px; "> <input type="button" value="????????????" class="loginBtn" id="signUpSubmit"> <input type="button" value="??????" class="loginBtn" id="signUpClose" style="float:right; margin-right: 27px;"> </div>';
		returnHtml += ' </div>';
		returnHtml += '</div>';
	 return returnHtml;	
}

</script>

</head>
<html xmlns="http://www.w3.org/1999/xhtml">
<body>
<div id="loginDiv">
	<div class="idInitDiv">
		<a>ID : </a>
		<input type="text" id="loginId" onKeypress="if(event.keyCode==13){$('#loginSubmit').click()}"/>
	</div>
	<div class="pwInitDiv">
		<a>PW : </a>
		<input type="password" id="loginPw" onKeypress="if(event.keyCode==13){$('#loginSubmit').click()}"/>
	</div>
	<div class="buttonDiv">
		<input type="button" id="loginSubmit" value="?????????"/>
		<input type="button" id="signUp" value="????????????"/>
	</div>
<div class="signUpDiv" id="signUpDiv"></div>
</div>
</body>
</html>
