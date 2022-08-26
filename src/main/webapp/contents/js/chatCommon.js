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
			    html += "<tr>";			
				html += "<td id='seq_"+value.SEQ+"'>"+value.SEQ+"</td>";
				html += "<td id='subject_"+value.SEQ+"'>"+value.SUBJECT+"</td>";
				html += "<td id='userid_"+value.SEQ+"'>"+value.USERID+"</td>";
				html += "<td id='idate_"+value.SEQ+"'>"+value.IDATE+"</td>";
			    if (ynMap.update && ynMap.delete) {
				    if (ynMap.update ==='Y' && ynMap.delete ==='Y'){
						html+='<td><input type="button" name="'+value.SEQ+'" id="upBtn" value="수정"></td>';
						html+='<td><input type="button" id="comBtn" value="완료"></td>'
						html+='<td><input type="button" id="delBtn" value="삭제"></td>';
					}
				}else{
			   		alert("확인하세요");
				}
				html += "</tr>";
			});
			$('#chat_data').append(html);

		};
		
		
		function input_change( fn_event ){
			console.log("input_change");
			console.log(fn_event);
			
			
			var returnBool = false;
			var inputChk = $("a[name=seq]");
			//var inputChk = $('#chat_data').find('a');
			//console.log(inputChk);
			console.log();
			if( inputChk.length > 0 ){
				console.log(inputChk.length);
				console.log("inputChk if 문 ");
				
				
				// fn_event
				// fn_event 을 이용하여 input을 <td></td> text 방식으로 변경 소스 작업
				$.each(fn_event[0].childNodes, function(i, v){
					var inputVal = v.innerHTML;
					console.log(inputVal);
					console.log(inputVal.value);
						if(inputVal.split('button').length === 1) {							
							if(inputVal.split('input').length === 1) {
								this.innerHTML = "<td id='subject_'>"+document.getElementsByName('seq')[0].innerHTML+"</td>";	
							}else{
								this.innerHTML = "<td id='subject_'>"+document.getElementsByName('val')[0].value+"</td>";
							}
						} 
						
						/*
						if (i != 0) {
							this.innerHTML = "<td id='subject_"+value.SEQ+"'>"+value.SUBJECT+"</td>";
						}else{
							this.innerHTML = "<td id='seq_"+inputVal+"'>"+inputVal+"</td>";
						}
						*/	
					
				});
				
				return true;
			}
			else{
				
				return false;
			}
	
		}
		
	
		function updateChk() {
			$(document).on("click", "#upBtn", function(e){
				//기존에 input을 text로 변경 작업 
				var inputVal = $('#chat_data').find('a').parent().parent();
			//	var inputVal = $("a[name=seq]");
				console.log(inputVal);
				var changeVal = $(e.target.parentElement.parentElement);
				input_change(inputVal );
				
				var arrTemp = [];
				var thVal = $('#data_table > thead > tr > th ');
				var id = $(e.target.parentElement.parentElement);//tr
	
				//console.log(id[0].childNodes);//td
				//console.log(id.parent());
			//	console.log(thVal);
			//	console.log(thVal[0].id);
				//console.log($('#data_table > thead > tr > th ')[0].innerHTML);
				
				//4
				$.each(thVal, function(i, v){
					var thValue = v.id
				//	console.log(thValue);
					arrTemp.push(thValue); 
					
				});
				//console.log("arrTemp >>> : " + arrTemp);
				//5
				console.log(id[0].childNodes)
				var uVal = [];
					for (i=0; i<id[0].childNodes.length; i++) {
						uVal.push(id[0].childNodes[i].id);
					} 
					console.log(uVal);
	
	
				var chkButton = $(e.target)[0].name;
				
				console.log(chkButton);
				console.log(id[0].childNodes)
				
	
				$.each(id[0].childNodes ,function(i,v){
					var tempValue = v.innerHTML;
					console.log(tempValue);
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
												
						if (i != 0  ) {
							this.innerHTML = '<input style="width:80%;" name="val" value="'+tempValue+'" />';
						}else{
							this.innerHTML = '<a style="width:80%;" name="seq">'+tempValue+'</a>';
						}
					}
						
				}); //foreach 끝					
			//	console.log($("a[name=seq]").length);
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
				var dParam = {'seq':seq};
				var con = confirm("삭제하시겠습니까?");
				if (!con) {
					return false;
				}
				var deleteOk = chatAjax('/chatdelete', dParam, 'post');
				if(deleteOk) {
					var tdParam = {"tableName":tableName}
					chatAjax('/chatTableDrop', tdParam, 'post');
				}
				reload_data();
			});
		}
		
		
	