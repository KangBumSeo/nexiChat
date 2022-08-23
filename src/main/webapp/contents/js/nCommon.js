/*
 * 송정흔
 * 20220811 
*/

function chatAjax(aUrl, aParam, aType) {
	var successResult;
	console.log(aParam);
	$.ajax({
		url: aUrl,
		type: aType,
		data: JSON.stringify(aParam),
		dataType: 'json',
		async : false , // 동기 비동기 설정 안하면 왜 순서가 바뀌지....?
		contentType: 'application/json; charset=UTF-8',
		success: function(result){
			console.log("success");
			console.log(result);
			successResult = result;
		},
		error: function(request, status, error){
			console.log("status : " + request.status + "\n" + "message : " + request.responseText + "\n" + "error : " + error);
		}
	});
	
	return successResult;
}



/** ICM2팀 강범서
 * 20220711 
 */

// runAjax 전역변수 
let setAsync = false;
let setDataType = 'json';
let setClickTarget = '';
let setHeader = '';
let setToken = '';
let setLoadingTarget = '';
let setLoadingCss = '';

/** ajax 커스텀마이징
 * @param fnUrl : 요청Url
 * @param fnParam : 요청시 필요데이터
 * @param fnType : post, get 방식중 어느것인지 
 * @returns result : 요청데이터 
 */
function runAjax( fnUrl , fnParam , fnType ){
	if( fnType === '' || !fnType ){ fnType = 'post'};
	var returnVal;
	$.ajax({
        url: fnUrl ,
        type: fnType ,
        data: JSON.stringify(fnParam) ,
        dataType : setDataType ,
        async : setAsync ,
        contentType: "application/json",
        beforeSend : ( xhr ) => {
        	if( fnUrl === ''   || 	fnType === '' ){
        		xhr.abort();
        		throw new Error ( '호출시 문제가 발생 했습니다.' );
        		return ;
        	}
        	//ajax 연속클릭으로 인한 중복호출 방지 
        	// submit ID값으로 강제 설정 
        	if(setClickTarget !='' ){ $('#'+setClickTarget).unbind('click'); }
        	
        	/** 토큰을 이용하여 호출시 사용 
        	 * setHeader : 토큰 헤더값
        	 * setToken : 토큰값
        	 */
        	if( setHeader != '' && setToken != '' ){
        		xhr.setRequestHeader( setHeader, setToken );
        	}
        	// 로딩 CSS 추가 가능
        	if( setLoadingTarget != '' && setLoadingTarget ){ $('#'+setLoadingTarget).addClass(setLoadingCss); }
        },
        complete : ( result ) => {
        	// 로딩 CSS 추가 가능
        	if( setLoadingTarget != '' && setLoadingTarget ){ $('#'+setLoadingTarget).removeClass(setLoadingCss); }
        	return result;
        },
        success: (result) => {
        		//whenSuccess(result);
                if(setClickTarget !='' ){ $('#'+setClickTarget).bind('click'); }
                console.log( result);
                returnVal = result;
        },
        error:  ( request , status , error) => {
        	throw new Error( request.responseText )
        }
    });
	// 초기화 작업 
	//setAsync = true;
	setDataType = 'json';
	setClickTarget = '';
	return returnVal;
}

/** ajax 요청시 로딩화면 필요시 셋팅 함수
 * @param fnLoadingTarget : 로딩 CSS 가 올라와야하는 타겟 ( ID값으로 고정 )
 * @param fnLoadingCss : 로딩 CSS 클래스명 
 * @returns void
 */
runAjax.setLoding = ( fnLoadingTarget , fnLoadingCss ) => {
	setLoadingTarget = fnLoadingTarget;
	setLoadingCss = fnLoadingCss;
}

/** ajax 요청시 토큰셋팅 필요시 호출 함수
 * @param fnHeader : 전달 헤더값
 * @param fnToken :  토큰값
 * @returns void
 */
runAjax.setToken = ( fnHeader , fnToken ) => {
	setHeader = fnHeader;
	setToken = fnToken;
}

/** 연속적 호출을 막기 위한 호출함수
 * @param fnTarget : 연속호출 막아야할 클릭 대상 ( ID값으로 고정 )
 * @returns void
 */
runAjax.setClickTarget = (fnTarget) =>{
	setClickTarget = fnTartget;
}

/** 동기식 , 비동기식 선택 함수 ( ajax 호출 후 true로 재셋팅 )
 * @returns void
 */
runAjax.setAsync = ( fnData ) => {
	setAsync = false;
}

/** ajax 호출시 dataType 셋팅 함수 ( 기본 json ) 
 * 서버에서 요청 후 반환값을 설정 
 * fnDataType : xml, html , json , script , text
 * @returns void
 */
runAjax.setDataType = ( fnDataType ) =>{
	setDataType = fnDataType;
}

/** 세션스토리지 선언 함수
 * 선언시 만약의 경우를 대비해 이전 스토리지 기록 확인하여 제거 작업
 * @returnss void
 */
function sess(fnData){
	var sessLong = sessionStorage.length;
	if(sessLong != 0){
		sessionStorage.clear(); 
	}
}

/** 세션스토리지 전체삭제
 * 세션스토리지가 존재시에 삭제
 * @returnss void
 */
sess.cls = (fnData) => {
	var sessLong = sessionStorage.length;
	if(sessLong != 0){
		sessionStorage.clear(); 
	}
}

/** 세션스토리지 셋팅 작업. 
 * 세션이 연결 종료시 데이터는 삭제 된다.
 * 데이터는 보안에 위배되지 않는 데이터 삽입필요
 * 
 *  fn_data ( Map || Arr ) 두개의 데이터 삽입 가능
 * 
 *  Arr의 경우 전체 데이터를 문자열화 시켜 데이터적재
 *	셋팅 키값 : fnArrSetKey
 *
 *  Map의 경우 데이터를 반복문을 이용하여 Key,Value 값 저장 
 * 
 * @returns void
 */
sess.add = ( fn_data , fnArrSetKey ) => {
	try{
		if( Array.isArray(fn_data)){
			if( fnArrSetKey != "" && fnArrSetKey ){
				sessionStorage.setItem( fnArrSetKey , JSON.stringify(fn_data))
			}
			else{
				throw new Error('Key 값이 없습니다.');
			}
		}
		else if( typeof fn_data === 'object' ){
			if( fnArrSetKey != "" && fnArrSetKey ){
				throw new Error('Key 셋팅이 잘 못 되었습니다.');
			}
			else{
				$.each( fn_data , (i,v) => {
					sessionStorage.setItem( String(i) , String(v) );
				}); // end each
				
				
				$.each( fn_data , function ( index, value){
					
				});
			}
		}
		else{
			throw new Error('세팅 재확인 부탁드립니다.');
		}
	}
	catch(e){
		console.log(e)
	}
}

/** 세션스토리지 전체데이터 호출 
 * 
 * fnKey : 특정 데이터 추출시 
 * 
 * @returns String and Map
 */
sess.get = ( fnKey ) => {
	try{
		var reData;
		if( fnKey != "" && fnKey ){
			reData = sessionStorage.getItem( fnKey );
			if( reData === '' ){return '';}
		}
		else{
			var sessList = sessionStorage;
			reData = {};
			if(sessList.length != 0){
				$.each( sessList , (i,v) => {
					if( !sess.basicKeyEx(i) ){
						reData[i] = sessionStorage.getItem( i );					
					}
				});
			}
			else{
				return '';
			}
		}
		return reData ;
	}catch(e){
		console.log(e);
	}
}

/** 세션스토리지 삭제 함수
 * fnDelKey 를 기준으로 삭제진행
 * @returns void 
 */
sess.del = ( fnDelKey ) => {
	try{
		if(fnDelKey && fnDelKey != ""){
			sessionStorage.removeItem(fnDelKey)
		}
		else{
			throw new Error('Not Target Key');
		}
	}catch(e){
		console.log(e);
	}
}

/** 세션 기본 키 값 제외함수
 * @returns boolean 
 */
sess.basicKeyEx = ( fnData ) => {
	var bool = false; 
	if( 
		fnData === "clear"		||
		fnData === "getItem"	||
		fnData === "key"		||
		fnData === "length"		||
		fnData === "removeItem"	||
		fnData === "setItem"
	){		bool = true ; 		}
	else{		bool = false;		}
	return bool;
}

/** 검색 입력값 분류 함수 
 * 입력값을 한글만 입력 또는 영문 입력 등 분류하기 위한 함수
 * en , kr , all , phon , s ( 공백 ), ss( 특수기호 )
 * cs ( 함수에 전달된 값 /내용/ 중 '/'문자를 제외한 내용만 )
 * 
 *  글자를 변경할 가능성도 생각 필요 
 *  @param fnInit : 입력값 
 *  @param fnSet : 정규식 분류값 ( en,kr ... )
 *  @param fnAlertMess : 정규식에 해당 했을시 경고창 
 * @returns boolean
 */
function initDataChk( fnInit , fnSet , fnAlertMess){
	
	if( fnSet === '' || !fnSet ){ return true; };
	if( fnAlertMess != '' && fnAlertMess ){  alert( String(fnAlertMess) ) };
	
	const regEx = regularExpression(fnSet);
	return regEx.test( fnInit );
}

// 날짜 구하기위한 함수 선언부분
function dateSet(){}

/** 금일 날짜 구하기 위한 함수
 *  fnDateFormet : 형식별 -,/ 등 추가 가능
 *  @returns String
 */
dateSet.getNowDate = ( fnDateFormet ) => {
	let today = new Date();   

	let year = today.getFullYear(); // 년도
	let month = today.getMonth() + 1;  // 월
	let date = today.getDate();  // 날짜
	let returnDate = '';

	if( fnDateFormet != '' && fnDateFormet ){
		returnDate = year + fnDateFormet + month + fnDateFormet + date;
	}
	else{ returnDate = year + month + date }
}

/** 이전 날짜 구하는 함수
 *  fnSettingDate : y -> 년 , m -> 월 , d -> 일
 * 	fnSettingDateNum : 얼만큼 이전인지 구하는 값 ( 기본 셋팅 값은 1이다 ) 
 * 
 *  fnDateFormet : 형식별 -,/ 등 추가 가능
 *  @returns String
 */
dateSet.getBeforeDate = ( fnSettingDate , fnSettingDateNum , fnDateFormet ) => {
	if( fnSettingDateNum === '' || !fnSettingDateNum ){ fnSettingDateNum = 1; }
	let today = new Date();   

	let year =  fnSettingDate === 'y' ? today.getFullYear() - fnSettingDateNum : today.getFullYear() ; // 년도
	let month = fnSettingDate === 'm' ? today.getMonth() + 1 - fnSettingDateNum : today.getMonth() + 1 ;  // 월
	let date =  fnSettingDate === 'd' ? today.getDate() - fnSettingDateNum : today.getDate() ;  // 날짜
	let returnDate = '';

	if( fnDateFormet != '' && fnDateFormet ){
		returnDate = year + fnDateFormet + month + fnDateFormet + date;
	}
	else{ returnDate = year + month + date }
}

/** 이후 날짜 구하는 함수
 *  fnSettingDate : y -> 년 , m -> 월 , d -> 일
 * 	fnSettingDateNum : 얼만큼 이후인지 구하는 값 ( 기본 셋팅 값은 1이다 ) 
 * 
 *  fnDateFormet : 형식별 -,/ 등 추가 가능
 *  @returns String
 */
dateSet.getNextDate = ( fnSettingDate , fnSettingDateNum , fnDateFormet ) => {
	if( fnSettingDateNum === '' || !fnSettingDateNum ){ fnSettingDateNum = 1; }
	let today = new Date();   

	let year =  fnSettingDate === 'y' ? today.getFullYear() +  fnSettingDateNum : today.getFullYear() ; // 년도
	let month = fnSettingDate === 'm' ? today.getMonth() + 1 + fnSettingDateNum : today.getMonth() + 1 ;  // 월
	let date =  fnSettingDate === 'd' ? today.getDate() +      fnSettingDateNum : today.getDate() ;  // 날짜
	let returnDate = '';

	if( fnDateFormet != '' && fnDateFormet ){
		returnDate = year + fnDateFormet + month + fnDateFormet + date;
	}
	else{ returnDate = year + month + date }
}

let viewName = '';
let urlData;
let urlHtmlData;
function refreshView( fnUrlMapSelect ){
	
	// 정상 호출 아닐시 settingChk 함수에서 멈춘다.
	refreshView.settingChk( urlData , fnUrlMapSelect );
	refreshView.settingChk( urlHtmlData , fnUrlMapSelect );
	
	//체크 필요 부분 차후 추가
	
	// .load( html ) 추가하여 진행 가능 
	// 전페이지 location.reload();  location.reload(false) 기본값 :캐쉬로 리플래쉬 location.reload(true) 서버에서 리플래쉬  
	// 10초마다 리로드  setTimeout('location.reload()',10000); 
	// 특정 영역 : $("#div의 id").load(window.location.href + "#div의 id");
	// history.state : url 정보 및 이전 정보 떙겨 올수 있을거같음 
	if( $('#'+viewName).length > 0 ){
		if( Array.isArray(urlData) ){
			urlData.each( (v,i) => {
				
			});
		}
	}
	else if( $('.'+viewName).length > 0 ){
		
	}
}

refreshView.setUrlMap = ( fnUrlMap ) => {
	urlData = fnUrlMap;
};

refreshView.setUrlArr = ( fnUrlArr )=>{
	urlData = fnUrlArr;
}

refreshView.setUrlStr = ( fnUrlStr ) => {
	urlData = fnUrlStr;
}

refreshView.setUrlHtmlMap = ( fnUrlHtmlMap ) => {
	urlHtmlData = fnUrlHtmlMap;
}

refreshView.setUrlHtmlArr = ( fnUrlHtmlArr ) => {
	urlHtmlData = fnUrlHtmlArr;
}

refreshView.setUrlHtmlStr = ( fnUrlHtmlStr ) => {
	urlHtmlData = fnUrlHtmlStr;
}

/** 리플래쉬 함수 호출시 정상데이터 호출인지 체크함수
 * @param fnUrlSetData : URL 셋팅 데이터 입니다.
 * @param fnUrlSelect : 리플래쉬해야하는 URL 선택한 데이터입니다.
 * @return void
 */
refreshView.settingChk = ( fnUrlSetData , fnUrlSelect ) => {
	const urlParam = typeof fnUrlSetData;
	const urlArr = Array.isArray(fnUrlSetData);
	
	if( urlParam === 'string' && fnUrlSelect != '' ){
		throw new Erorr('리로드 시 URL 셋팅 부분 확인 필요합니다. ( Code : 01 )');
		return;
	}
	else if( urlParam === 'object' && !urlArr && !/0-9/gi.test( Number(fnUrlSelect) ) ){
		throw new Erorr('리로드 시 URL 셋팅 부분 확인 필요합니다. ( Code : 02 )');
		return;
	}
	else if( urlParam === 'object' && urlArr && /0-9/gi.test( Number(fnUrlSelect) ) ){
		throw new Erorr('리로드 시 URL 셋팅 부분 확 인 필요합니다. ( Code : 03 )');
		return;
	}
}