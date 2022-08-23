<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>
<%@ include file="/WEB-INF/views/jspf/sysmgmt_header.jsp" %>
<%@ include file="/WEB-INF/views/jspf/pagination.jsp" %>

<script type="text/javascript">
	var lastRow, lastCell, mngRow, validator;
	
	$(function(){$('#list').jqGrid({
		datatype: 'json',
		jsonReader : {
		  page: "page",
		  total: "total",
		  records: "records",
		  root: "rows"
	    },
	    mtype: 'POST',
		   colNames:['<spring:message code="empno"/>', '<spring:message code="name"/>',
			    '<spring:message code="uid"/>', 
			    '<spring:message code="dept"/>', '<spring:message code="dept"/>',
			    '<spring:message code="position"/>', '<spring:message code="position"/>' 
		   ],
		   colModel:[
		       { name: 'empno', index: 'empno', width: 180, classes:"grid-col" },
		       { name: 'name', index: 'name', width: 150, classes:"grid-col" },
		       { name: 'uid', index: 'uid', width: 180, classes:"grid-col" },
		       { name: 'dept', index: 'dept', width: 150, classes:"grid-col", hidden:true },
		       { name: 'deptNm', index: 'deptNm', width: 150, classes:"grid-col" },
		       { name: 'position', index: 'position', width: 180, classes:"grid-col", hidden:true },
		       { name: 'positionNm', index: 'positionNm', width: 180, classes:"grid-col" }
		   ],
			cmTemplate: {sortable: false}, //정렬 x
			rowNum: 20,
			//multiselect: true,	// checkBox
			gridComplete : function(data){  
				var ids=$("#list").getDataIDs();
				for(var i=0; i<ids.length; i++){	// 코드기반 데이타 출력
					$("#list").setRowData(ids[i], {deptNm: ${v_data}.dept[ $("#list").getCell(ids[i],'dept') ] });
					$("#list").setRowData(ids[i], {positionNm: ${v_data}.position[ $("#list").getCell(ids[i],'position') ] });
				}
	            initPage("list", "pager", false, "TOT");						// jqgrid 커스텀 페이징
	            resize_width("pageTbl", parent.document.body.clientWidth - 270);// jqgrid 커스텀 페이징 가운데 정렬
	            
	        //},ondblClickRow : function(id,colId,val){	
			//	alert(id);
			},onSelectRow : function(rowId, iRow, iCol, e){
				mngRow = rowId	// layerPopup 관리 데이타
			//	alert(rowId);
			//},onCellSelect : function(rowId, iCol, cellcontent, e){
			//	alert(rowId);
			//	var rowdata = $('#list').getrowData(rowId);
			},
		    loadError: function (jqXHR, testStatus, errorThrown){
			// - // 화면유지 상태에서 검색 요청시 session 만료처리
			//	if('<%=session.getAttribute("auth_level")%>'=='null' || '<%=session.getAttribute("auth_level")%>'==''){	// session fail
			//		localStorage.clear();	// move to logout? login!
			//		location.href = '<spring:url value="/logout"/>'; // logout
			//	}
			}
	
		});  
		initPage("list", "pager", false, "TOT");	// jqgrid 커스텀 페이징
		resize_jqgrid_default(1);					// jqgrid resize
	});
	
	$(window).resize(function() {
		resize_jqgrid_default(1);					// jqgrid resize
	});
	
	$(document).ready(function() {
		var l_param = localStorage.getItem("<%=pathCd%>");
		if(l_param != null){
			if(confirm('<spring:message code="m_rebrowsing"/>')){ // 기능 동작을 위해 필수!! 누락시 검색 미동작
				var l_json = JSON.parse(l_param);
				$('#v_frm').setJsonObject(l_json);
				fnInquiry();
			} else {
				localStorage.removeItem("<%=pathCd%>");
			}
		}

	//	var a = 'D001'
	//	alert(${v_data}.dept[a]);
		//.post( url [, data ] [, success ] [, dataType ] )
		//$.post('<spring:url value="/commons/codedesc/codeMap"/>', {codelist: codeList}, function(data){ fnSelectBoxCallback(codeList.split(";"),sboxJson,data );} );
		//$.post('<spring:url value="/commons/codedesc/codeMapT"/>', {codelist: ''}, function(data){ fnSelectBoxCallback(codeList.split(";"),sboxJson,data );} );

		validator = $( "#pop_frm" ).validate({
			rules: {
				empno: {
					required: true
				},
				uid: {
					required: true
				},
				name: {
					required: true
				},
				dept: {
					required: true
				},
				position: {
					required: true
				}
			},
			errorPlacement: function( error, element ) {
				error.insertAfter( element.parent() );
			}
		});		
	});

	
	function fnInquiry(){
	// - // 검색 이력을 활용한 재검색 => 활성 : 주석해제 / 비활성 : 주석처리
	//	localStorage.setItem("<%=pathCd%>", JSON.stringify( $('#v_frm').serializeObject() ));
	
		$("#list").clearGridData();
		$("#list").jqGrid('setGridParam', {postData: $('#v_frm').serializeObject() });
		$("#list").jqGrid('setGridParam', {url: '<spring:url value="/base/view/list"/>' }).trigger('reloadGrid');
	}
	
	
	function layerPopupOn(obj) {
		var act = obj.innerText;
		$("#pop-act").html(act);	// title
		$("#btn-act").html(act);	// button
		
		if(act == '<spring:message code="v_add"/>'){						// 생성
			$('#pop_frm').setClearField();
			
		} else if (act == '<spring:message code="v_mod"/>'){				// 수정
			if(mngRow == undefined){
				alert('<spring:message code="m_nonData"/>');
				return;
			}
			//alert( JSON.stringify($("#list").getRowData(mngRow) ));
			$('#pop_frm').setJsonObject( $("#list").getRowData(mngRow) );
			$("#pop_frm input[name=empno]").prop('readonly', true);

		} else if (act == '<spring:message code="v_del"/>'){				// 삭제
			if(mngRow == undefined){
				alert('<spring:message code="m_nonData"/>');
				return;
			}
			//alert( JSON.stringify($("#list").getRowData(mngRow) ));
			$('#pop_frm').setJsonObjectWithDiable( $("#list").getRowData(mngRow) );
			
		}
		
		$("#layerPopup").fadeIn();
	}
	
	function layerPopupAct() {
		var act = $("#btn-act").html();
		
		if(act == '<spring:message code="v_add"/>'){						// 생성
			if($("#pop_frm").valid()){
				$("#pop_frm input[name=uid]").attr("placeholder", "아이디를 입력하세요");
				$.post('<spring:url value="/base/view/add"/>', 
					$('#pop_frm').serializeObject(), 
					function(data){ 
						if(data == "T") { 
							alert('<spring:message code="m_afterAdd"/>');
						} else {
							alert('<spring:message code="m_error"/>');
						}
					}
				);
				$("#layerPopup").fadeOut();
			}
					
		} else if (act == '<spring:message code="v_mod"/>'){				// 수정
			if($("#pop_frm").valid()){
				$.post('<spring:url value="/base/view/mod"/>', 
					$('#pop_frm').serializeObject(), 
					function(data){ 
						if(data == "T") { 
							alert('<spring:message code="m_afterMod"/>');
						} else {
							alert('<spring:message code="m_error"/>');
						}
					}
				);
				$("#layerPopup").fadeOut();
			}

		} else if (act == '<spring:message code="v_del"/>'){				// 삭제
			if(confirm('<spring:message code="m_deleteConfirm"/>')){
				$('select:disabled').removeAttr("disabled");
				
				$.post('<spring:url value="/base/view/del"/>', 
					$('#pop_frm').serializeObject(), 
					function(data){ 
						if(data == "T") { 
							alert('<spring:message code="m_afterDel"/>');
						} else {
							alert('<spring:message code="m_error"/>');
						} 
					}
				);
			}
			$("#layerPopup").fadeOut();
		}
		
	}
	
	function layerPopupOff() {
		$("#layerPopup").fadeOut();
	}


function testValid(){


	//alert(validator.check( "#pop_frm input[name=uid]" ) ); // false
	
	//alert(validator.element( "#pop_frm input[name=uid]" ) );
	//alert($.validator.methods[ 'required' ].call( validator, $( "#pop_frm input[name=uid]" ).val(), $( "#pop_frm input[name=uid]" )[0], true ))

	var form = $(  "#pop_frm" );
	form.validate();
	form.valid()
	//alert( "Valid: " + form.valid() ); // check all

	//alert(JSON.stringify(JSON.stringify(validator.errorList)))
	//alert(JSON.stringify($( "#pop_frm input[name=uid]" ).rules()));
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
			<div class="inr"><a href="#"><img src="${img_url}main/ico_home.png" alt="home"></a> &gt; ${v_submenu} &gt; <strong>${v_title}</strong></div>
		</div>

		<div id="contents">
			<div class="section">
				<div class="customSearchwrap">
					<form id="v_frm" method="POST">
						<span class="customSearchwrapLeft">
							<select name="dept" class="customSelect">
								<option value="">==<spring:message code="dept"/><spring:message code="v_selectOpt"/>==</option>
								<c:forEach var="vo" items="${dept}" varStatus="status">
									<option value="${vo.code}">${vo.codeName}</option>
								</c:forEach>
							</select>
							<select name="position" class="customSelect">
								<option value="">==<spring:message code="position"/><spring:message code="v_selectOpt"/>==</option>
								<c:forEach var="vo" items="${position}" varStatus="status">
									<option value="${vo.code}">${vo.codeName}</option>
								</c:forEach>	
							</select>
							<span onclick="javascript:layerPopupOn(this);" class="customButton"><spring:message code="v_add"/></span>
							<span onclick="javascript:layerPopupOn(this);" class="customButton"><spring:message code="v_mod"/></span>
							<span onclick="javascript:layerPopupOn(this);" class="customButton"><spring:message code="v_del"/></span>
						</span>
						
						<span class="customSearchwrapRight">
							<input type="text" name="searchValue" class="customText emphas" value="" onkeypress="if(event.keyCode==13) {fnInquiry();}" />
							<span id="buttonSearch" onclick="javascript:fnInquiry();" class="customButton"><spring:message code="v_search"/></span>
						</span>
					</form>
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


<!-- layer-popup -->
<div id="layerPopup" class="layer-wrap">
	<div class="pop-layer">
		<div class="pop-container">
			<div class="tit-l">
				<strong>${v_title} <span id="pop-act"></span></strong>
				<span onclick="javascript:layerPopupOff();" class="closeButton">X</span>
			</div>
			<form id="pop_frm" method="POST">
				<div class="fieldcontain">
					<label for="empno"><spring:message code="empno"/></label>
					<input type="text" name="empno" class="customText emphas">
				</div>
				<div class="fieldcontain">
					<label for="name"><spring:message code="name"/></label>
					<input type="text" name="name" class="customText emphas">
				</div>
				<div class="fieldcontain">
					<label for="uid"><spring:message code="uid"/></label>
					<input type="text" name="uid" class="customText emphas">
				</div>
				<div class="fieldcontain">
					<label for="dept"><spring:message code="dept"/></label>
					<select name="dept" class="customSelect">
						<option value="">==<spring:message code="dept"/><spring:message code="v_selectOpt"/>==</option>
						<c:forEach var="vo" items="${dept}" varStatus="status">
							<option value="${vo.code}">${vo.codeName}</option>
						</c:forEach>
					</select>
				</div>
				<div class="fieldcontain">
					<label class="lbl"><spring:message code="position"/></label>
					<select name="position" class="customSelect">
						<option value="">==<spring:message code="position"/><spring:message code="v_selectOpt"/>==</option>
						<c:forEach var="vo" items="${position}" varStatus="status">
							<option value="${vo.code}">${vo.codeName}</option>
						</c:forEach>
					</select>
				</div>
			</form>
			<div class="btn-r">
				<span onclick="javascript:layerPopupAct();" class="customButton" id="btn-act"><spring:message code="v_ok"/></span>
				<!--span onclick="javascript:testValid();" class="customButton">test</span-->
			</div>
        </div>
    </div>
</div>
</body>
</html>