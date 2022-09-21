
function mainClick(){

		var key = $("#selectBox option:selected").val();
		console.log("key >>> : "  + key)
		$("#selectBox").change(function(){
			key = $("#selectBox option:selected").val();
			console.log("key >>> : " + key);
			if (key == 'idate'){
				$("#select").replaceWith('<div id="date" style="float: left; margin-left: 50px;"><input type="text" class="dateSearch" id="datePicker_0"> <a style="float: left;">-</a> <input type="text" class="dateSearch" id="datePicker_1"></div>');

				$("#datePicker_0").datepicker({
					format: "yyyy-mm-dd",
					todayHightlight :true
				});

			
				$("#datePicker_1").datepicker({
					format: "yyyy-mm-dd",
					todayHightlight :true
				});
			}else{
				$("#date").replaceWith('<div id="select" style="float: left; margin-left: 50px;"><input type="text" name="searchSel" id="searchSel"></div>');
				
				
			}
			
		});
});


function enterSelect (searchText) {
	
	$(document).on("keypress", "#"+searchText, function(e) {
		if (e.keyCode == 13) {

			if (key == 'idate') {
				var startDate = $("#datePicker_0").val();
				var endDate = $("#datePicker_1").val();

				if (value === "") {
					alert("검색할 내용을 입력하세요!");
					$("#searchSel").focus();
					return;
				}

				$('#chat_data').empty();
				var cParam = { "key" : key, "startdate" : startDate, "enddate" : endDate};
				var test = chatAjax('/chatselect', cParam ,'post');
				console.log(test);

				whenSuccess(test);	
			}else{
				var value = $.trim($("#"+searchText).val());	
				
				if (value === "") {
					alert("검색할 내용을 입력하세요!");
					$("#"+searchText).focus();
					return;
				}

				$('#chat_data').empty();
				var cParam = { "key" : key, "value" : value};
				var test = chatAjax('/chatselect', cParam ,'post');
				console.log(test);

				whenSuccess(test);		
			}				
		}
	});
	
}