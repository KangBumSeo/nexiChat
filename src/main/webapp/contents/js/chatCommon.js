/**
 * 한글 깨질시 charset=EUC-KR 변경 필요
 * 파일 호출시 js 내부파일 charset을 EUC-KR로 변경작업 필요 ( 현재 파일은 변경 완료 )
 */


		function reload_data(rUrl){
			$('#chat_data').html("");
			var param = {'':''};
			var test0 = chatAjax(rUrl, param, 'post');
			var updateYn = "";
			var ynMap = {"update":"Y", "delete":"Y"};
			whenSuccess(test0, updateYn, ynMap);
		}

		function whenSuccess( fnTest , updateYn , ynMap ) {
			var html = '';
			updateYn = "1"
				
			$.each( fnTest, function(key, value) {
				
				var chatType = value.STATUS
				console.log(chatType);
				var subject_htmlValStr = '';
				var userid_htmlValStr = '';
				var htmlSumVal = '';
				
				if(chatType === 'C') {
					subject_htmlValStr = '비공개';
					userid_htmlValStr = '비공개';
				}else if (chatType === 'O') {
					subject_htmlValStr = value.SUBJECT;
					userid_htmlValStr = value.USERID;
					htmlSumVal += '	<input type="button" style="margin: 0px 10px;" name="'+value.SEQ+'" id="upBtn" value="수정">';
					htmlSumVal += '	<input type="button" style="margin: 0px 10px;" id="comBtn" value="완료">';
				}
					htmlSumVal += '<input type="button" style="margin: 0px 10px;" id="delBtn" value="삭제">';
										
				html += "<tr>";	
				html += "<td id='seq_"+value.SEQ+"'>"+value.SEQ+"</td>";
				html += "<td id='subject_"+value.SEQ+"'>"+subject_htmlValStr+"</td>";
				html += "<td id='userid_"+value.SEQ+"'>"+userid_htmlValStr+"</td>";
				html += "<td id='idate_"+value.SEQ+"'>"+value.IDATE+"</td>";
				
			
		   		
		    if (ynMap.update && ynMap.delete) {
			    if (ynMap.update ==='Y' && ynMap.delete ==='Y'){
					html+='<td>';
					html+=htmlSumVal;
					html+='</td>';
				}
			}else{
		   		alert("확인하세요");
			}
			html += "</tr>";
			
			});
			$('#chat_data').append(html);

		};
		
		
		function input_change( fn_event ){
			
			// fn_event
			// fn_event 을 이용하여 input을 <td></td> text 방식으로 변경 소스 작업
			if(fn_event.length != 0){
				$.each(fn_event[0].childNodes, function(i, v){
					var inputVal = v.innerHTML;
					if(inputVal.split('button').length === 1) {							
						console.log(inputVal);
							if(inputVal.split('input').length != 1) {
								this.innerHTML = "<td id=''>"+document.getElementsByName("val")[0].value+"</td>";	
							}
							else if(inputVal.split('detailSeq').length != 1) {
								this.innerHTML = "<td id=''>"+$("#detailSeq").text()+"</td>";
							} 
							else if(inputVal.split('detailDate').length != 1) {
								this.innerHTML = "<td id=''>"+$("#detailDate").text()+"</td>";
							}
						} 
				});
			}
		}
		
	
		function updateChk() {
			
			$(document).on("click", "#upBtn", function(e){
				var arrTemp = [];
				var inputVal;
				var thVal = $('#data_table > thead > tr > th ');
				var id = $(e.target.parentElement.parentElement);//tr
				//oldTableName
				var tNameVal_1 = id[0].childNodes[1].innerHTML;
				var tNameVal_2 = id[0].childNodes[2].innerHTML;
				oldTableName = tNameVal_1+tNameVal_2
				
			//기존에 input을 text로 변경 작업 
				var findInput = $('#chat_data').find('input');
				
				inputVal = $('#chat_data').find('a[id="detailSeq"]').parent().parent();
			//	var inputVal = $("a[name=seq]");
				var changeVal = $(e.target.parentElement.parentElement);
				console.log(inputVal);
				input_change(inputVal );			
				//4
				$.each(thVal, function(i, v){
					var thValue = v.id
				//	console.log(thValue);
					arrTemp.push(thValue); 
					
				});
				//5
				var uVal = [];
					for (i=0; i<id[0].childNodes.length; i++) {
						uVal.push(id[0].childNodes[i].id);
					} 
	
	
				var chkButton = $(e.target)[0].name;
				
	
				$.each(id[0].childNodes ,function(i,v){
					var tempValue = v.innerHTML;
					/*
					var test = ['1','2','3','99','33'];
					console.log(test.indexOf('99'));
					console.log( test[test.indexOf('99')] )
					console.log(tempValue.indexOf('input'))
					console.log(tempValue.indexOf('button'))
					// 확인 작업 
					*/	

					if( tempValue.indexOf('button') === -1 //tempValue.split('button').length === 1
					 && tempValue.indexOf('input') === -1
				
						){
						//arrTemp[i]
					//this.innerHTML = '<input style="width:80%;" value="'+tempValue+'" id="'+uVal[i]+'" />';
												
						if (i != 0 && i != 3  ) {
							this.innerHTML = '<input style="width:80%;" name="val" value="'+tempValue+'" />';
						}else if(i == 3){
							this.innerHTML = '<a style="width:80%;" id="detailDate">'+tempValue+'</a>';
						}else{
							this.innerHTML = '<a style="width:80%;" id="detailSeq">'+tempValue+'</a>';
						}
					}
						
				}); //foreach 끝			
					
			updateOk(oldTableName);	
				
			});
		};
		
		
		function deleteChk() {
			$(document).on("click", "#delBtn", function(e){
				console.log(e);
				console.log(e.target);
				console.log(e.target.parentElement);
				var delTarget = $(e.target.parentElement.parentElement);
				var seq = delTarget[0].childNodes[0].innerHTML;
				var tname = delTarget[0].childNodes[1].innerHTML;
				var uid = delTarget[0].childNodes[2].innerHTML;
				var tableName = tname + uid
				var dParam = {'seq':seq , "tableName":tableName, "fileTableName" : tableName+'_file'};
				var con = confirm("삭제하시겠습니까?");
				if (!con) {
					return false;
				}
				var deleteOk = chatAjax('/chatdelete', dParam, 'post');
				/*if(deleteOk) {
					var tdParam = {"tableName":tableName, "fileTableName" : tableName+'_file'}
					chatAjax('/chatTableDrop', tdParam, 'post');
				}*/
				reload_data();
			});
		}
		
		
	