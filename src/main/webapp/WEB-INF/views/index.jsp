<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<%@ include file="/WEB-INF/views/jspf/sysmgmt_header.jsp" %>
<%@ include file="/WEB-INF/views/jspf/pagination.jsp" %>
<script src="http://code.highcharts.com/highcharts.js"></script>
<script type="text/javascript" src="/resources/contents/js/jqgrid/jquery.jqGrid.js"></script>
<script type="text/javascript">

	function pageLoad(fnButton, fnUrl){
		$(fnButton).click(function(e){

			var userid = '<%= (String)session.getAttribute("userid") %>';
			if( (userid != '' && userid != 'null') || fnUrl === '/login' ){
				$.ajax({
					url:fnUrl,
					type:"get",
					data: {  '':'' },
					contentType: "application/json",
					success: function(result){			
						$('#container > #contents').remove();
						console.log($('#container > #contents'))
						var returnHtml = '<div id="contents" style="height: 440px;">';
							returnHtml += result;
							returnHtml += '</div>';
						$('#container').append(returnHtml);
						return;
					},
					error: function(){
						alert("에러");
					}
				});//2
			} // end if session
			else{
				alert(" Login is Required");
				$('#chatlogin').click();
			}
			///1
		});
	}

/* 	function pageTest(){
		$('#chat_select').click(function(e){
			$.ajax({
				url:"/chatmain",
				type:"get",
				data: {  '':'' },
				contentType: "application/json",
				success: function(result){
					
					$('#container > #contents').remove();
					console.log($('#container > #contents'))
					var returnHtml = '<div id="contents" style="height: 440px;">';
						returnHtml += result;
						returnHtml += '</div>';
						
					//$('#container').html(returnHtml);
					$('#container').append(returnHtml);
					return;
				},
				error: function(){
					alert("에러");
				}
			});//2

			///1
		});
	}

	function chatInsert() {
		$("#chat_insert").click(function(e){
			$.ajax({
				url:"/chatinsert",
				type:"get",
				data: {  '':'' },
				contentType: "application/json",
				success: function(result){
					
					$('#container > #contents').remove();
					console.log($('#container > #contents'))
					var returnHtml = '<div id="contents" style="height: 440px;">';
						returnHtml += result;
						returnHtml += '</div>';
						
					//$('#container').html(returnHtml);
					$('#container').append(returnHtml);

					
					return;
				},
				error: function(){
					alert("에러");
				}
			});
		});
	}


	function chatUpdate() {
		$("#chat_update").click(function(e){
			console.log("chatupdate");
			$.ajax({
				url:"/chatupdate",
				type:"get",
				data: {  '':'' },
				contentType: "application/json",
				success: function(result){
					
					$('#container > #contents').remove();
					console.log($('#container > #contents'))
					var returnHtml = '<div id="contents" style="height: 440px;">';
						returnHtml += result;
						returnHtml += '</div>';
						
					//$('#container').html(returnHtml);
					$('#container').append(returnHtml);

					
					return;
				},
				error: function(){
					alert("에러");
				}
			});
		});
	}


	function chatLogin() {
		$("#chatlogin").click(function(e){
			$.ajax({
				url:"/login",
				type:"get",
				data: {  '':'' },
				contentType: "application/json",
				success: function(result){
					
					$('#container > #contents').remove();
					var returnHtml = '<div id="contents" style="height: 440px;">';
						returnHtml += result;
						returnHtml += '</div>';
					$('#container').append(returnHtml);
					return;
				},
				error: function(){
					alert("에러");
				}
			});
		});
	} */
	
</script>
<script>
$(document).ready(function() {
		pageLoad('#chat_select', '/chatmain'); //main
		pageLoad('#chat_insert', '/chatinsert'); //insert
		pageLoad('#chat_update', '/chatupdate'); //update
		pageLoad('#chatlogin', '/login'); //login
		pageLoad('#chat_in', '/chatting'); //chatin
/* 
	pageTest();
	chatInsert();
	chatUpdate();
	chatLogin();
 */
	/*
   var chart = {
      type: 'column',
      width:'650',
      height:'400'
   };
   var title = {
      text: '계정 현황'
   };
   var subtitle = {
      text: ''
   };
   var xAxis = {
      categories: ['AIX','Linux','HP-UX','Solaris','Windows'],
      crosshair: true
   };
   var yAxis = {
      min: 0,
      title: {
         text: '계정 수'
      }
   };

   var tooltip = {
      headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
      pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
         '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
      footerFormat: '</table>',
      shared: true,
      useHTML: true
   };

   var plotOptions = {
      column: {
         pointPadding: 0.2,
         borderWidth: 0
      }
   };
   var credits = {
      enabled: false
   };

   var series= [{
        name: '전체',
            data: [49.9, 71.5, 106.4, 129.2, 144.0]
        }, {
            name: '활성',
            data: [83.6, 78.8, 98.5, 93.4, 106.0]
        }, {
            name: '장기미접속',
            data: [48.9, 38.8, 39.3, 41.4, 47.0]
        }, {
            name: 'PW변경 미준수',
            data: [42.4, 33.2, 34.5, 39.7, 52.6]
  		}, {
            name: '고아',
            data: [42.4, 33.2, 34.5, 39.7, 52.6]
   }];

   var json = {};
   json.chart = chart;
   json.title = title;
   json.subtitle = subtitle;
//   json.tooltip = tooltip;
   json.xAxis = xAxis;
   json.yAxis = yAxis;
   json.series = series;
   json.plotOptions = plotOptions;
   json.credits = credits;
   $('#nxc1').highcharts(json);
	 */
});
</script>
<script language="JavaScript">
/*
$(document).ready(function() {
   var chart = {
      type: 'column',
      width:'650',
      height:'400'
   };
   var title = {
      text: '일일 처리 현황'
   };
   var subtitle = {
      text: ''
   };
   var xAxis = {
      categories: ['AIX','Linux','HP-UX','Solaris','Windows'],
      crosshair: true
   };
   var yAxis = {
      min: 0,
      title: {
         text: '처리 수'
      }
   };

   var tooltip = {
      headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
      pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
         '<td style="padding:0"><b>{point.y:.1f} mm</b></td></tr>',
      footerFormat: '</table>',
      shared: true,
      useHTML: true
   };

   var plotOptions = {
      column: {
         pointPadding: 0.2,
         borderWidth: 0
      }
   };
   var credits = {
      enabled: false
   };

   var series= [{
        	name: '전체',
            data: [148.5, 216.4, 194.1, 95.6, 54.4]
        }, {
        	name: '계정생성',
            data: [148.5, 216.4, 194.1, 95.6, 54.4]
        }, {
            name: '계정 변경',
            data: [104.3, 91.2, 83.5, 106.6, 92.3]
        }, {
            name: '계정 회수',
            data: [59.6, 52.4, 65.2, 59.3, 51.2]
        }, {
            name: '패스워드 변경',
            data: [60.4, 47.6, 39.1, 46.8, 51.1]
   }];
   var json = {};
   json.chart = chart;
   json.title = title;
   json.subtitle = subtitle;
 //  json.tooltip = tooltip;
   json.xAxis = xAxis;
   json.yAxis = yAxis;
   json.series = series;
   json.plotOptions = plotOptions;
   json.credits = credits;
   $('#nxc2').highcharts(json);

});

*/
</script>
<script type="text/javascript">
	$(function(){
	});


	$(window).resize(function() {
		
	});


	$(document).ready(function() {
	//	fnInitOrgChart();
	//	$.post('<spring:url value="/commons/codedesc/orgchart"/>',{},function(data){fnOrgCallback(data);});

	//	fnMakeSelectBox();
	});

</script>
<style>

input[type="button"]{
	cursor : pointer;
}

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
  background: #dcdcd1;
}
table.type08 tbody th {
  width: 150px;
  padding: 10px;
  font-weight: bold;
  vertical-align: top;
  border-right: 1px solid #ccc;

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
</head>
<html xmlns="http://www.w3.org/1999/xhtml">
<body>
<div id="wrapper">
	<%@ include file="/WEB-INF/views/jspf/sysmgmt_menu.jsp" %>
	<div id="container" >
		<%@ include file="/WEB-INF/views/jspf/sysmgmt_top.jsp" %>
		<div class="location">
			<div class="inr">
				<a href="/">
				<img src="${img_url}main/ico_home.png" alt="home">
				</a><!-- &gt;
				<spring:message code="MNU_4000"/> &gt; <strong><spring:message code="MNU_4100"/></strong-->
			</div>
		</div>
		<div id="contents" style="height: 440px;">
			<div class="section" style="width:50%;float:left;">
				<div id="nxc1" style="width: 750px; height: 550px; margin: 0 auto"></div>
			</div>
			<div class="section" style="width:50%;float:right;">
				<div id="nxc2" style="width: 750px; height: 550px; margin: 0 auto"></div>
			</div>
		</div>

		<div id="contents">
			<div class="section" style="width:50%;float:left;">
			<%-- 
			<!-- div style=" text-align: center;font-size: 16;">계정 현황</div -->
				<table class="type08" width=700 style="table-layout: fixed">
					<thead>
					<tr>
						<th scope="cols" style="width: 135px;"></th>
						<th scope="cols" style="text-align: center;">AIX</th>
						<th scope="cols" style="text-align: center;">Linux</th>
						<th scope="cols" style="text-align: center;">HP-UX</th>
						<th scope="cols" style="text-align: center;">Solaris</th>
						<th scope="cols" style="text-align: center;">Windows</th>
					</tr>
					</thead>
					<tbody>
					<tr>
						<th scope="row">전체</th>
						<td style="text-align: right;">2313</td>
						<td style="text-align: right;">22545</td>
						<td style="text-align: right;">243</td>
						<td style="text-align: right;">2543</td>
						<td style="text-align: right;">243</td>
					</tr>
					<tr>
						<th scope="row">활성</th>
						<td style="text-align: right;">3345</td>
						<td style="text-align: right;">3545</td>
						<td style="text-align: right;">3454</td>
						<td style="text-align: right;">354</td>
						<td style="text-align: right;">75</td>
					</tr>
					<tr>
						<th scope="row">장기미접속</th>
						<td style="text-align: right;">3774</td>
						<td style="text-align: right;">564</td>
						<td style="text-align: right;">6546</td>
						<td style="text-align: right;">777</td>
						<td style="text-align: right;">65</td>
					</tr>
					<tr>
						<th scope="row">PW변경 미준수</th>
						<td style="text-align: right;">745</td>
						<td style="text-align: right;">85845</td>
						<td style="text-align: right;">58568</td>
						<td style="text-align: right;">435</td>
						<td style="text-align: right;">4535</td>
					</tr>
					<tr>
						<th scope="row">고아</th>
						<td style="text-align: right;">47</td>
						<td style="text-align: right;">6</td>
						<td style="text-align: right;">24</td>
						<td style="text-align: right;">86</td>
						<td style="text-align: right;">44</td>
					</tr>
					</tbody>
				</table>
				--%>
			</div>
			<div class="section" style="width:50%;float:right;">
			<%-- 
			<!--div style=" text-align: center;font-size: 16;">일일 처리 현황</div -->
				<table class="type08" width=700 style="table-layout: fixed">
					<thead>
					<tr>
						<th scope="cols" style="width: 135px;"></th>
						<th scope="cols" style="text-align: center;">AIX</th>
						<th scope="cols" style="text-align: center;">Linux</th>
						<th scope="cols" style="text-align: center;">HP-UX</th>
						<th scope="cols" style="text-align: center;">Solaris</th>
						<th scope="cols" style="text-align: center;">Windows</th>
					</tr>
					</thead>
					<tbody>
					<tr>
						<th scope="row">전체</th>
						<td style="text-align: right;">2313</td>
						<td style="text-align: right;">22545</td>
						<td style="text-align: right;">243</td>
						<td style="text-align: right;">2543</td>
						<td style="text-align: right;">243</td>
					</tr>
					<tr>
						<th scope="row">계정 생성</th>
						<td style="text-align: right;">3345</td>
						<td style="text-align: right;">3545</td>
						<td style="text-align: right;">3454</td>
						<td style="text-align: right;">354</td>
						<td style="text-align: right;">75</td>
					</tr>
					<tr>
						<th scope="row">계정 변경</th>
						<td style="text-align: right;">3774</td>
						<td style="text-align: right;">564</td>
						<td style="text-align: right;">6546</td>
						<td style="text-align: right;">777</td>
						<td style="text-align: right;">65</td>
					</tr>
					<tr>
						<th scope="row">계정 회수</th>
						<td style="text-align: right;">745</td>
						<td style="text-align: right;">85845</td>
						<td style="text-align: right;">58568</td>
						<td style="text-align: right;">435</td>
						<td style="text-align: right;">4535</td>
					</tr>
					<tr>
						<th scope="row">패스워드 변경</th>
						<td style="text-align: right;">47</td>
						<td style="text-align: right;">6</td>
						<td style="text-align: right;">24</td>
						<td style="text-align: right;">86</td>
						<td style="text-align: right;">44</td>
					</tr>
					</tbody>
				</table>
				--%>
			</div>
		</div>
	</div>
</div>
</body>
</html>
