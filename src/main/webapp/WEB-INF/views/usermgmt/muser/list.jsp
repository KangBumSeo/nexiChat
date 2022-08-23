<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<%@ include file="/WEB-INF/views/jspf/sysmgmt_header.jsp" %>
<%@ include file="/WEB-INF/views/jspf/pagination.jsp" %>

<script type="text/javascript">
var lastRow, lastCell;

$(function(){$('#list').jqGrid({
	datatype: 'json',
	jsonReader : {
	  page: "page",
	  total: "total",
	  records: "records",
	  root: "rows", 
	  repeatitems: false
    },
    mtype: 'POST',
	   colNames:[ 'mname1', 'mbid', 'mbpass', 'mbemail', 'mbaddress' ],//,'mbemail_agree', 'mblike' ],
	   colModel:[
	       { name: 'mname', index: 'mname', width: 180, classes:"grid-col" },
	       { name: 'mbid', index: 'mbid', width: 150, classes:"grid-col" },
	       { name: 'mbpass', index: 'mbpass', width: 180, classes:"grid-col" },
	       { name: 'mbemail', index: 'mbemail', width: 150, classes:"grid-col" },
	       { name: 'mbaddress', index: 'mbaddress', width: 180, classes:"grid-col" }
	//       { name: 'mbemail_agree', index: 'mbemail_agree', width: 100 },
	//       { name: 'mblike', index: 'mblike', width: 180 }
	   ],
		sortname: 'mbid',
		sortorder: 'asc',
		rowNum: 5,
		loadonce: true,
		gridComplete : function(data){  
            initPage("list", "pager", false, "TOT");
            
            resize_width("pageTbl",parent.document.body.clientWidth - 270);
        }

	});  
	initPage("list", "pager", false, "TOT");
	

	resize_width("pageTbl",parent.document.body.clientWidth - 270);
	resize_jqgrid("list",parent.document.body.clientWidth - 270, parent.document.body.clientHeight - 305);
});

$(window).resize(function() {
	resize_width("pageTbl",parent.document.body.clientWidth - 270);
	resize_jqgrid("list",parent.document.body.clientWidth - 270, parent.document.body.clientHeight - 305);
});
	

	function fnInquiry(){
		jQuery("#list").clearGridData();

		var searchValue = {
				company_code : "",
				code_class   : "",
				use_flag     : "",
				code_name    : ""
		    };
	    
		jQuery("#list").jqGrid('setGridParam',{postData:searchValue});
		jQuery("#list").jqGrid('setGridParam',{url:"<spring:url value="/user/list"/>"}).trigger('reloadGrid');
	}
</script>
</head>
<html xmlns="http://www.w3.org/1999/xhtml">
<body>
<div id="wrapper">
	<%@ include file="/WEB-INF/views/jspf/sysmgmt_menu.jsp" %>
	<div id="container">
		<%@ include file="/WEB-INF/views/jspf/sysmgmt_top.jsp" %>
		<div class="location">
			<div class="inr"><a href="#"><img src="${img_url}main/ico_home.png" alt="home"></a> &gt; <spring:message code="MNU_4000"/> &gt; <strong><spring:message code="MNU_4200"/></strong></div>
		</div>

		<div id="contents">
			<div class="section">
				<div class="customSearchwrap">
					<span class="customSearchwrapLeft">
						<span href="#" class="customButton" >생성</span>
					</span>
					
					<span class="customSearchwrapRight">
						<select id="selectSearchFilter" class="customSelect">
							<!-- Select End -->							
						</select>
						<input type="text" id="selectSearchContent" class="customText" value="" onkeypress="if(event.keyCode==13) {fnInquiry();}" />
						<span id="buttonSearch" onclick="javascript:fnInquiry();" class="customButton">검색</span>
					</span>
				</div>
				<div class="customDummyReline"><br>&nbsp;</div>
				<table>
					<tr>
			        	<td align='left'>
			          		<table id="list"><tr><td/></tr></table> 
			          		<br/>
							<div class='paging' id="pager"></div>
			        	</td>
			      	</tr>
				</table>

			</div>

		</div>
	</div>
</div>
</body>
</html>
