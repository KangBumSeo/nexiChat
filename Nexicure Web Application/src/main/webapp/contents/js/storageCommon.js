/** ICM2팀 강범서
 * 웹도구를 이용한 웹스토리지 공통코드
 * 
// 키에 데이터 쓰기
localStorage.setItem("key", value);
sessionStorage.setItem("key", value);
// 키로 부터 데이터 읽기
localStorage.getItem("key");
sessionStorage.getItem("key");
// 키의 데이터 삭제
localStorage.removeItem("key");
sessionStorage.removeItem("key");
// 모든 키의 데이터 삭제
localStorage.clear();
sessionStorage.clear();
// 저장된 키/값 쌍의 개수
localStorage.length;
sessionStorage.length;
 */

function mainStorage(){}

// fnSettingItem : Map 형태 
mainStorage.setItem = function( fnSettingItem ){
	$.each( fnSettingItem , function(i,v){
		sessionStorage.setItem(i,v);
	})
}

mainStorage.getKeyList = function(){
	var storageList = sessionStorage();
	var returnKeyList = [];
	
		$.each(storageList , function(i,v){
			returnKeyList.push(i+"");
		});
		
	return returnKeyList;
}

mainStorage.getItem = function( fnKey ){
	var returnItem = sessionStorage.getItem(fnKey+"");
	
	if(typeof returnItem != 'string'){
		returnItem = Json.parse(returnItem);
	}
	return returnItem+"";
}

mainStorage.clear = function(){
	sessionStorage.clear();
}
