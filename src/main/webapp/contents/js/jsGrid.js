let selectObject = [];
let returnJsGridData = [];

jsGrid.setColumSetting = function( fnAjaxData , fnSelectObject ){  

	let ajaxDataKay = objectKeys(fnAjaxData);

	$.each( ajaxDataKay , function(i,v){
		var ajaxObject;

		if( v.type === "select" ){
			ajaxObject = { 
							name : v.name , type : v.type , width : v.width
						 , items : selectObject[ v.selectItems ]
						 //, valueField : "Id" , textField : "Name"
			  }

			returnJsGridData.push(ajaxObject);
		}
		else {
			ajaxObject = { Name : v.name , type : v.type , width : v.width } ;

			returnJsGridData.push(ajaxObject);
		}
	});

	return returnJsGridData;
}

jsGrid.selectBox = function( fnSelectData ){
	selectObject = fnSelectData;
}
// end function selectBox

let filter			= false;
jsGrid.filter		= function() { filter = true; }
let edit			= false;
jsGrid.edit			= function() { edit   = true; }
let sort			= false;
jsGrid.sort			= function() { sort   = true; }
let page			= false;
jsGrid.page			= function() { page	   = true; }
let autoLoad		= false;
jsGrid.autoLoad		= function() { autoLoad = true; }
let pageSize		= 15;
jsGrid.pageSize		= function(fnSize) { pageSize = fnSize; }
let pageBtnLeng		= 5;
jsGrid.pageBtnLeng  = function(fnPageBtnLeng) { pageBtnLeng = fnPageBtnLeng; }
let delConfirm		= "";
jsGrid.delConfirm	= function(fnDelConfirm) { delConfirm = fnDelConfirm; }

jsGrid.optionReset	= function(){ 
	filter = false; edit = false; sort = false; page = false; autoLoad = false; 
	pageSize = false; pageBtnLeng = false; delConfirm = false; 
}

function jsGrid( fnId , fnSize ,fnDefaultCtlUrl ){

	var defaultUrlData = jsGrid.defaultSetData(function( fnSelectMap ){
			return fnSelectMap;
	});

	var jsGridData = runAjax( fnDefaultCtlUrl , defaultUrlData , 'post'  );

	$('#'+fnId ).jsGrid({
		height			: fnSize.height ,
		width			: fnSize.width ,
		
		filtering		: filter ,
		editing			: edit ,
		sorting			: sort ,
		paging			: page , 
		autoload		: autoLoad ,

		pageSize		: pageSize ,
		pageButtonCount : pageBtnLeng ,

		deleteConfirm   : delConfirm ,
		controller : {
			loadData : function(){
				return data
			}
		},
		fields : returnJsGridData
	});
}
