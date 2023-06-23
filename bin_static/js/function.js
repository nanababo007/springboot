Array.prototype.remove=function(idx)
{
	var resultArray = this;
	if(this==null || this==undefined || idx==null || idx==undefined){
	}else{
		this.splice(idx, 1);
	}
	return resultArray;
}

/**
 * 문자열의 마지막(끝) 문자의 종성 포함 여부 확인
 * @param value - Target String
 * @returns 종성 포함 여부
 */
function hasCoda(value) {
    return ((value.charCodeAt(value.length - 1) - 0xAC00) % 28) > 0;
}

/**
 * 필드(Elemenet) 유효성 검사
 * @param target - 검사 대상 Element
 * @param fieldName - 필드명
 * @param focusTarget - 포커스 대상 Element
 * @returns 필드 입력(선택) 여부
 */
function isValid(target, fieldName, focusTarget) {
    if (target.value.trim()) {
        return true;
    }

    const particle = (hasCoda(fieldName)) ? '을' : '를'; // 조사
    const elementType = (target.type === 'text' || target.type === 'password' || target.type === 'search' || target.type === 'textarea') ? '입력' : '선택';
    alert( `${fieldName + particle} ${elementType}해 주세요.` );

    target.value = '';
    ( !focusTarget ? target : focusTarget).focus();
    throw new Error(`"${target.id}" is required...`)
}

/**
 * 브라우저의 현재 url 정보 구하기
 * @param divStr - url 정보 구분 문자열, 안넘기면 전체 url 반환 (/memb/list.do?par1=1&par2=222)
 * @returns divStr url 정보 구분 문자열에 따른 해당 값
 *
 * (참조) - divStr 끝에 문자열
 * document.location.href : http://localhost:8060/memb/list.do?par1=1&par2=222#11
 * document.location.pathname : /memb/list.do
 * document.location.search : ?par1=1&par2=222
 * document.location.hash : #11
 * document.location.host : localhost:8060
 * document.location.hostname : localhost
 * document.location.protocol : http:
 * hostfull : http://localhost:8060
 * null or undefined : /memb/list.do?par1=1&par2=222
 */
function fnGetCurUrl(divStr) {
	var result = document.location.pathname+document.location.search;
	if(divStr==='href' || divStr==='1'){
		result = document.location.href;
	}else if(divStr==='pathname' || divStr==='2'){
		result = document.location.pathname;
	}else if(divStr==='search' || divStr==='3'){
		result = document.location.search;
	}else if(divStr==='hash' || divStr==='4'){
		result = document.location.hash;
	}else if(divStr==='host' || divStr==='5'){
		result = document.location.host;
	}else if(divStr==='hostname' || divStr==='6'){
		result = document.location.hostname;
	}else if(divStr==='protocol' || divStr==='7'){
		result = document.location.protocol;
	}else if(divStr==='hostfull' || divStr==='8'){
		result = document.location.protocol+'//'+document.location.host;
	}
	return result;
}