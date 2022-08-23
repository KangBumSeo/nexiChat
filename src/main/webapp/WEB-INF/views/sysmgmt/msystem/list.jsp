<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<%@ include file="/WEB-INF/views/jspf/sysmgmt_header.jsp" %>
<%@ include file="/WEB-INF/views/jspf/pagination.jsp" %>

<script type="text/javascript">
	var lastRow, lastCell;
	var orgchartMap, simcategoryMap, simservicegroupMap, simreconstatMAP;

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
			colNames:[ '<spring:message code="ISM_0008"/>','', '<spring:message code="ISM_0009"/>','', '<spring:message code="ISM_0010"/>',
					 '<spring:message code="ISM_0011"/>','', '<spring:message code="ISM_0012"/>', '<spring:message code="ISM_0013"/>',
					 '<spring:message code="ISM_0014"/>', '<spring:message code="ISM_0015"/>','', '<spring:message code="ISM_0016"/>',
					 '<spring:message code="ISM_0017"/>', '<spring:message code="ISM_0018"/>', '<spring:message code="ISM_0019"/>',
					 '<spring:message code="ISM_0020"/>', '<spring:message code="ISM_0021"/>', '<spring:message code="ISM_0022"/>',
					 '<spring:message code="ISM_0023"/>' ],
			colModel:[
		       { name: 'simservicegroup_nm', index: 'simservicegroup_nm', width: 180, classes:"grid-col" },
		       { name: 'simservicegroup', index: 'simservicegroup', width: 180, classes:"grid-col", hidden:true},
		       { name: 'erparent_nm', index: 'erparent_nm', width: 150, classes:"grid-col" },
		       { name: 'erparent', index: 'erparent', width: 150, classes:"grid-col", hidden:true},
		       { name: 'erservicename', index: 'erservicename', width: 180, classes:"grid-col" },
		       { name: 'simcategory_nm', index: 'simcategory_nm', width: 150, classes:"grid-col" },
		       { name: 'simcategory', index: 'simcategory', width: 150, classes:"grid-col", hidden:true},
		       { name: 'erurl', index: 'erurl', width: 180, classes:"grid-col" },
		       { name: 'description', index: 'description', width: 100 },
		       { name: 'last_recon_time', index: 'last_recon_time', width: 180, classes:"grid-col" },
		       { name: 'resource_status_nm', index: 'resource_status_nm', width: 150, classes:"grid-col" },
		       { name: 'resource_status', index: 'resource_status', width: 150, classes:"grid-col", hidden:true},
		       { name: 'hour_num', index: 'hour_num', width: 180, classes:"grid-col" },
		       { name: 'minute_num', index: 'minute_num', width: 150, classes:"grid-col" },
		       { name: 'support_data_only', index: 'support_data_only', width: 180, classes:"grid-col" },
		       { name: 'month_num', index: 'month_num', width: 100 },
		       { name: 'ownername', index: 'ownername', width: 180, classes:"grid-col" },
		       { name: 'simsystempwdvl', index: 'simsystempwdvl', width: 150, classes:"grid-col" },
		       { name: 'erserviceuid', index: 'erserviceuid', width: 100 },
		       { name: 'erpassword', index: 'erpassword', width: 180 }
			],
			autowidth: false,
			shrinkToFit:false,
			cmTemplate: {sortable: false},
			multiselect:true,
			rowNum: 2,
			gridComplete : function(data){
		    	initPage("list", "pager", false, "TOT");
	
		    	resize_width("pageTbl",parent.document.body.clientWidth - 270);
				resize_width("srhTbl",parent.document.body.clientWidth - 270);

				var ids=$("#list").getDataIDs();
				for(var i=0; i<ids.length; i++){
					var tmp = $("#list").getCell(ids[i],'erparent');
					tmp = "s" + tmp.substring(tmp.indexOf("=")+1,tmp.indexOf(","));
					try{tmp = eval("orgchartMap."+tmp)}catch(e){tmp = ''}
					$("#list").setRowData(ids[i],{erparent_nm:tmp});

					try{tmp = eval("simcategoryMap."+$("#list").getCell(ids[i],'simcategory'))}catch(e){tmp = ''}
					$("#list").setRowData(ids[i],{simcategory_nm:tmp});

					try{tmp = eval("simservicegroupMap."+$("#list").getCell(ids[i],'simservicegroup'))}catch(e){tmp = ''}
					$("#list").setRowData(ids[i],{simservicegroup_nm:tmp});

					try{tmp = eval("simreconstatMap."+$("#list").getCell(ids[i],'resource_status'))}catch(e){tmp = ''}
					$("#list").setRowData(ids[i],{resource_status_nm:tmp});
				}
		    }
	
		});  
		initPage("list", "pager", false, "TOT");
	
		resize_width("pageTbl",parent.document.body.clientWidth - 270);
		resize_width("srhTbl",parent.document.body.clientWidth - 270);
		resize_jqgrid("list",parent.document.body.clientWidth - 270, parent.document.body.clientHeight - 342); //1:307 2:342
	});

	
	$(window).resize(function() {
		resize_width("pageTbl",parent.document.body.clientWidth - 270);
		resize_width("srhTbl",parent.document.body.clientWidth - 270);
		resize_jqgrid("list",parent.document.body.clientWidth - 270, parent.document.body.clientHeight - 342); //1:307 2:342
	});


	$(document).ready(function() {
		fnInitOrgChart();
		$.post('<spring:url value="/commons/codedesc/orgchart"/>',{},function(data){fnOrgCallback(data);});

		fnMakeSelectBox();
	});

	function fnInitOrgChart() {
		$("#erparent").empty();
	
		var opt = document.createElement("option");
		opt.value = "";
		opt.text = ' == <spring:message code="ISM_0004"/> == ';
		
		$("#erparent").append(opt);
	}
	function fnOrgCallback(obj) {
		var orgchartlist = '{';
		for (var i=0;i<obj.length;i++) {
			var opt = document.createElement("option");
			opt.value = obj[i].dn;
			opt.text = obj[i].ou;
			$("#erparent").append(opt);

			orgchartlist += '"s' +obj[i].erglobalid +'":"' + obj[i].ou +'"'
			orgchartlist += (i == obj.length-1)?"}":",";
		}

		orgchartMap = JSON.parse(orgchartlist);

		$("#erparent").focus();
	}

	function fnInitSelectBox(codeList, sboxJson) {
		for(var i=0; i<codeList.length; i++){
			$("#"+eval("sboxJson."+codeList[i])).empty();
			
			var opt = document.createElement("option");
			opt.value = "";

			if(codeList[i] == "simCategoryCode")opt.text = ' == <spring:message code="ISM_0005"/> == ';
			if(codeList[i] == "simServiceGroupCode")opt.text = ' == <spring:message code="ISM_0006"/> == ';
			if(codeList[i] == "simResourceStatusCode")opt.text = ' == <spring:message code="ISM_0007"/> == ';

			if(codeList[i] != "simServiceSearchKey")
				$("#"+eval("sboxJson."+codeList[i])).append(opt);
		}
	}
	function fnMakeSelectBox() {
		var codeList = "simCategoryCode;simServiceGroupCode;simResourceStatusCode;simServiceSearchKey";
		var sboxMap = "{";
		sboxMap += '"simCategoryCode":"simcategory",';
		sboxMap += '"simServiceGroupCode":"simservicegroup",';
		sboxMap += '"simResourceStatusCode":"simreconstat",';
		sboxMap += '"simServiceSearchKey":"searchKey"}';

		var sboxJson = JSON.parse(sboxMap);

		fnInitSelectBox(codeList.split(";"), sboxJson);

		$.post('<spring:url value="/commons/codedesc/codeMap"/>',{codelist: codeList},function(data){fnSelectBoxCallback(codeList.split(";"),sboxJson,data);});
	}
	function fnSelectBoxCallback(codeList,sboxJson,obj) {

		var collector = '';
		for(var i=0; i<codeList.length; i++){
			collector = '{';
			var code  = eval("obj."+codeList[i]);
			for(var j=0; j<code.length; j++){
				var opt = document.createElement("option");
				opt.value = code[j].cn;
				opt.text = code[j].simcodename;

				$("#"+eval("sboxJson."+codeList[i])).append(opt);


				collector += '"' +code[j].cn +'":"' + code[j].simcodename +'"'
				collector += (j == code.length-1)?"}":",";

			}
			if(codeList[i] == "simCategoryCode")simcategoryMap = JSON.parse(collector);
			if(codeList[i] == "simServiceGroupCode")simservicegroupMap = JSON.parse(collector);
			if(codeList[i] == "simResourceStatusCode")simreconstatMap = JSON.parse(collector);
		}
		
	}

	function fnInquiry(){
		jQuery("#list").clearGridData();

		var searchValue = {
				orgChart     : $("#erparent").val(),
				temp1        : $("#simcategory").val(),
				serviceGroup : $("#simservicegroup").val(),
				temp3        : $("#simreconstat").val(),
				searchKey    : $("#searchKey").val(),
				searchValue  : $("#searchValue").val()
		    };
	    
		jQuery("#list").jqGrid('setGridParam',{postData:searchValue});
		jQuery("#list").jqGrid('setGridParam',{url:"<spring:url value="/sysmgmt/system/list"/>"}).trigger('reloadGrid');
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
			<div class="inr"><a href="#"><img src="${img_url}main/ico_home.png" alt="home"></a> &gt; 
				<spring:message code="MNU_4000"/> &gt; <strong><spring:message code="MNU_4100"/></strong>
			</div>
		</div>
		<div id="contents">
			<div class="section">
				<div class="customSearchwrap">
					<table id='srhTbl' class="tbl_full">
						<tr><td>
							<span class="customSearchwrapLeft">
								<select id="erparent" name="erparent" class="customSelect"></select>&nbsp;
								<select id="simcategory" name="simcategory" class="customSelect"></select>&nbsp;
								<select id="simservicegroup" name="simservicegroup" class="customSelect"></select>&nbsp;
								<select id="simreconstat" name="simreconstat" class="customSelect"></select>&nbsp;
								<select id="searchKey" name="searchKey" class="customSelect"></select>&nbsp;
							</span>
							
							<span class="customSearchwrapRight">
								<input type="text" id="searchValue" class="customText" value="" onkeypress="if(event.keyCode==13) {fnInquiry();}" />
								<span id="buttonSearch" onclick="javascript:fnInquiry();" class="customButton"><spring:message code="BTN_0001"/></span>
							</span>
						</td></tr>
						<tr style="height:5px;"><td></td></tr>
					</table>
				</div>
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
