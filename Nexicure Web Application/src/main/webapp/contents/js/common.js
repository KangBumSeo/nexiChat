$.sidebarMenu = function(menu) {
	var animationSpeed = 300,
		subMenuSelector = '.sidebar-submenu';

	$(menu).on('click', 'li a', function(e) {
		var $this = $(this);
		var checkElement = $this.next();

		if (checkElement.is(subMenuSelector) && checkElement.is(':visible')) {
			checkElement.slideUp(animationSpeed, function() {
			checkElement.removeClass('menu-open');
		});
			checkElement.parent("li").removeClass("active");
			checkElement.parent("li").removeClass("on");
		}
	
		//If the menu is not visible
		else if ((checkElement.is(subMenuSelector)) && (!checkElement.is(':visible'))) {
			//Get the parent menu
			var parent = $this.parents('ul').first();
			//Close all open menus within the parent
			var ul = parent.find('ul:visible').slideUp(animationSpeed);	// 1 => 주석처리시 자동 닫힘 해제 
			//Remove the menu-open class from the parent
			ul.removeClass('menu-open');								// 2
			//Get the parent li
			var parent_li = $this.parent("li");
	
			//Open the target menu and add the menu-open class
			checkElement.slideDown(animationSpeed, function() {
				//Add the class active to the parent li
				checkElement.addClass('menu-open');
				parent.find('li.active').removeClass('active');			// 3
				parent.find('li.on').removeClass('on');					// 4
				parent_li.addClass('active');
				parent_li.addClass('on');
			});
		}
		//if this isn't a link, prevent the page from being redirected
		if (checkElement.is(subMenuSelector)) {
			e.preventDefault();
		}
	});
}

jQuery.fn.serializeObject = function() {
	var obj = null;
	try {// this[0].tagName이 form tag일 경우
		if(this[0].tagName && this[0].tagName.toUpperCase() == "FORM" ) {
			var arr = this.serializeArray();
			if(arr){
				obj = {};
				jQuery.each(arr, function() {// obj의 key값은 arr의 name, obj의 value는 value값
					obj[this.name] = this.value;
				});
			}
		}
	} catch(e) {alert(e.message);
	} finally {}
	return obj;
};
jQuery.fn.setJsonObjectWithDiable = function(d_json) {
	try {// this[0].tagName이 form tag일 경우
		var frm = this[0];
		if(frm.tagName && frm.tagName.toUpperCase() == "FORM" ) {
			var arr = this.serializeArray();
			if(arr){
				jQuery.each(arr, function() {
					if($("#"+ frm.id +" input[name="+ this.name +"]").length > 0 ) {
						$("#"+ frm.id +" input[name="+ this.name +"]").val(d_json[this.name]);
						$("#"+ frm.id +" input[name="+ this.name +"]").prop('readonly', true);
					} else if($("#"+ frm.id +" select[name="+ this.name +"]").length > 0 ) {
						$("#"+ frm.id +" select[name="+ this.name +"]").val(d_json[this.name]);
						$("#"+ frm.id +" select[name="+ this.name +"]").attr('disabled', 'disabled');
					}
				});
			}
		}
	} catch(e) {alert(e.message);
	} finally {}
};

jQuery.fn.setJsonObject = function(d_json) {
	try {// this[0].tagName이 form tag일 경우
		var frm = this[0];
		if(frm.tagName && frm.tagName.toUpperCase() == "FORM" ) {
			var arr = this.serializeArray();
			if(arr){
				jQuery.each(arr, function() {
					if($("#"+ frm.id +" input[name="+ this.name +"]").length > 0 ) {
						$("#"+ frm.id +" input[name="+ this.name +"]").val(d_json[this.name]);
						$("#"+ frm.id +" input[name="+ this.name +"]").prop('readonly', false);
					} else if($("#"+ frm.id +" select[name="+ this.name +"]").length > 0 ) {
						$("#"+ frm.id +" select[name="+ this.name +"]").val(d_json[this.name]);
					}
				});
				$('select:disabled').removeAttr("disabled"); //	anyOther removeAttr is fail!!
			}
		}
	} catch(e) {alert(e.message);
	} finally {}
};

jQuery.fn.setClearField = function() {
	try {// this[0].tagName이 form tag일 경우
		var frm = this[0];
		if(frm.tagName && frm.tagName.toUpperCase() == "FORM" ) {
			var arr = this.serializeArray();
			if(arr){
				jQuery.each(arr, function() {
					if($("#"+ frm.id +" input[name="+ this.name +"]").length > 0 ) {
						$("#"+ frm.id +" input[name="+ this.name +"]").val("");
						$("#"+ frm.id +" input[name="+ this.name +"]").prop('readonly', false);
					} else if($("#"+ frm.id +" select[name="+ this.name +"]").length > 0 ) {
						$("#"+ frm.id +" select[name="+ this.name +"] ").val("");
					}
				});
				$('select:disabled').removeAttr("disabled"); //	anyOther removeAttr is fail!!
			}
		}
	} catch(e) {alert(e.message);
	} finally {}
};



function resize_width(obj, width){
	if(width >= 924 ){  // min-width = ( '#wrapper min-width' - '#left width' - 'some padding-left' )
		$("#"+obj).css('width',width);	
	}
}
function resize_jqgrid(obj, width, height){
	$("#"+obj).setGridHeight(height);
	if(width >= 924 ){  // min-width = ( '#wrapper min-width' - '#left width' - 'some padding-left' )
		$("#"+obj).setGridWidth(width);
	}
}

function resize_jqgrid_default(num){
	resize_width("pageTbl",parent.document.body.clientWidth - 270);
	resize_jqgrid("list",parent.document.body.clientWidth - 270, parent.document.body.clientHeight - 272 - (num*35)); //1:307 2:342
}