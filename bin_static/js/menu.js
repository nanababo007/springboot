//사이트 메뉴
var menuList = {
	info:{
		//상단 메뉴 인덱스
		topIdx:-1
		//좌측 메뉴 인덱스
		,leftIdx:-1
	}
	,list:[
		//메인 대시보드
		{
			id:'main'
			,title:'대시보드'
			,pageTitle:'메인 페이지'
			,topYn:'Y'
			,dispYn:'Y'
			,url:'/main/dash.do'
			,urlTarget:'_self'
		}
		//회원 관리
		,{
			id:'memb'
			,title:'회원관리'
			,pageTitle:'회원 관리'
			,topYn:'Y'
			,dispYn:'Y'
			,url:''
			,urlTarget:'_self'
			,subList:[
				//회원 목록
				{
					id:'membList'
					,title:'회원목록'
					,pageTitle:'회원 리스트 관리'
					,topYn:'Y'
					,dispYn:'Y'
					,url:'/memb/list.do'
					,urlTarget:'_self'
				}
			]
		}
		//페이지 관리
		,{
			id:'page'
			,title:'페이지관리'
			,pageTitle:'페이지 관리'
			,topYn:'Y'
			,dispYn:'Y'
			,url:''
			,urlTarget:'_self'
			,subList:[
				//페이지 목록
				{
					id:'pageList'
					,title:'페이지목록'
					,pageTitle:'페이지 리스트 관리'
					,topYn:'Y'
					,dispYn:'Y'
					,url:'/page/list.do'
					,urlTarget:'_self'
				}
			]
		}
		//게시판 관리
		,{
			id:'bord'
			,title:'게시판관리'
			,pageTitle:'게시판 관리'
			,topYn:'Y'
			,dispYn:'Y'
			,url:''
			,urlTarget:'_self'
			,subList:[
				//공지사항
				{
					id:'bordNoti'
					,title:'공지사항'
					,pageTitle:'공지사항 리스트 관리'
					,topYn:'Y'
					,dispYn:'Y'
					,url:'/bord/noti.do'
					,urlTarget:'_self'
				}
				//FAQ
				,{
					id:'bordFaq'
					,title:'FAQ'
					,pageTitle:'FAQ 리스트 관리'
					,topYn:'Y'
					,dispYn:'Y'
					,url:'/bord/faq.do'
					,urlTarget:'_self'
				}
				//QnA
				,{
					id:'bordQna'
					,title:'QnA'
					,pageTitle:'QnA 리스트 관리'
					,topYn:'Y'
					,dispYn:'Y'
					,url:'/bord/qna.do'
					,urlTarget:'_self'
				}
				//제품소개
				,{
					id:'bordProd'
					,title:'제품소개'
					,pageTitle:'제품소개 리스트 관리'
					,topYn:'Y'
					,dispYn:'Y'
					,url:'/bord/img.do'
					,urlTarget:'_self'
				}
			]
		}
	]
};
//현재 메뉴 정보
var curMenu1Info = null; //depth1
var curMenu2Info = null; //depth2
//메뉴 렌더링
function fnRenderMenu(){
	var menuContainer = $('#container div.lcontent > nav > ul');

	//메뉴 목록 루프.
	$(menuList.list).each(function(idx,el){
		//활성메뉴 여부 (1depth)
		var isActiveMenu1 = (curMenu1Info && curMenu1Info.url && curMenu1Info.url===el.url) ? true : false;
		var activeMenuClassName1 = isActiveMenu1 ? ' on ' : '';
		//하위 메뉴 항목 유무 여부
		var hasSub = el.subList && el.subList.length ? true : false;
		//하위 메뉴 목록
		var menuSubList = hasSub ? el.subList : [];
		//상위 메뉴 html
		var html = [];
		html.push('<li class="'+(hasSub ? 'has_sub' : '')+'">');
		if(hasSub){
			html.push('<a href="javascript: void(0);" class="'+activeMenuClassName1+'"><span>'+el.title+'</span></a>');
		}else{
			html.push('<a href="'+el.url+'" target="'+el.urlTarget+'" class="'+activeMenuClassName1+'"><span>'+el.title+'</span></a>');
		}
		html.push('</li>');
		//상위 메뉴 추가 처리
		menuContainer.append(html.join(''));
		var menuUp = menuContainer.find('> li:last');
		//하위메뉴 추가 처리
		if(hasSub){
			//하위 메뉴를 추가할 ul 태그 추가
			menuUp.append('<ul></ul>');
			var menuSub = menuUp.find('> ul');
			$(menuSubList).each(function(idx2,el2){
				//활성메뉴 여부 (2depth)
				var isActiveMenu2 = (curMenu2Info && curMenu2Info.url && curMenu2Info.url===el2.url) ? true : false;
				var activeMenuClassName2 = isActiveMenu2 ? ' on ' : '';
				//활성메뉴 여부 (1depth)
				isActiveMenu1 = isActiveMenu2 ? true : isActiveMenu1;
				activeMenuClassName1 = isActiveMenu1 ? ' on ' : '';
				//하위 메뉴 html 설정
				html = [];
				html.push('<li><a href="'+el2.url+'" target="'+el2.urlTarget+'" class="'+activeMenuClassName2+'">'+el2.title+'</a></li>');
				//하위 메뉴 추가 처리
				menuSub.append(html.join(''));
			});
		}
		//상위 메뉴 활성 처리
		if(isActiveMenu1 && !menuUp.find('> a').hasClass(activeMenuClassName1)){
			menuUp.find('> a').addClass(activeMenuClassName1);
		}
	});
}
//현재 메뉴 설정 (curMenu1Info,curMenu2Info)
function fnSetCurMenu(){
	//현재 메뉴 정보 변수 초기화.
	curMenu1Info = null; //depth1
	curMenu2Info = null; //depth2

	//현재 브라우져 경로값(/memb/list.do, curUrlPathname) 으로 전체 메뉴에서, 해당 메뉴 찾기.
	$.each(menuList.list,function(idx,el){
		var hasSubMenu = el.subList && el.subList.length ? true : false;

		//현재 페이지 url과 메뉴 url 정보가 일치하면 현재 메뉴 설정.
		if(el.url===curUrlPathname){
			curMenu1Info = el; //depth1
			curMenu2Info = null; //depth2
			return false; //break;
		}

		//하위메뉴 찾기.
		if(hasSubMenu){
			$.each(el.subList,function(idx2,el2){
				//현재 페이지 url과 메뉴 url 정보가 일치하면 현재 메뉴 설정.
				if(el2.url===curUrlPathname){
					curMenu1Info = el; //depth1
					curMenu2Info = el2; //depth2
					return false; //break;
				}
			});
		}

		//메뉴를 찾았으면, 루프 종료.
		if(curMenu1Info!=null){
			return false; //break;
		}
	});

	//활성 메뉴 히스토리 정보 저장
	fnSaveActiveMenuHist();

	//활성 메뉴를 못찾았으면, 이전 활성 메뉴 히스토리 정보에서, 최신 메뉴 정보를 가져옴.
	if(curMenu1Info == null){
		//마지막 최근 활성 메뉴 항목
		var lastActiveMenu = fnGetActiveMenuHist('last');
		if(lastActiveMenu){
			curMenu1Info = lastActiveMenu.menu1Info; //depth1
			curMenu2Info = lastActiveMenu.menu2Info; //depth2
		}
	}
}
//메뉴 네비게이션 설정
function fnSetMenuNavi(){
	var html = [];
	var path = $('#container .rcontent .page_tits .path');
	if(curMenu1Info!=null){
		html.push(' <span>'+curMenu1Info.pageTitle+'</span> ');
	}
	if(curMenu2Info!=null){
		html.push(' <span>'+curMenu2Info.pageTitle+'</span> ');
	}
	path.append(html);
}
//활성 메뉴 히스토리 정보 가져오기
//fnGetActiveMenuHist('last') : 마지막 최근 항목
//fnGetActiveMenuHist(-1) : 마지막 최근 항목 이전항목, 음수는 마지막에서부터 이전 항목 반환
//fnGetActiveMenuHist(1) : 0 이상의 숫자는, 배열 인덱스, 숫자가 작을수록 과거 데이터.
function fnGetActiveMenuHist(idx){
	var activeMenuHist = JSON.parse(localStorage.getItem(cstActiveMenuHistSaveKey));
	activeMenuHist = activeMenuHist ? activeMenuHist : [];
	var activeMenuHistLen = activeMenuHist.length;

	if(idx==='last'){
		return activeMenuHistLen > 0 ? activeMenuHist[activeMenuHistLen - 1] : null;
	}else if(typeof idx==='number' && idx < 0){
		var idx2 = activeMenuHistLen - 1 + idx;
		return idx2 >= 0 ? activeMenuHist[idx2] : null;
	}else if(typeof idx==='number' && idx >= 0){
		return activeMenuHist[idx];
	}else{
		return activeMenuHist;
	}
}
//활성 메뉴 히스토리 정보 저장
//배열에 메뉴 객체 정보 추가, {menu1Info:curMenu1Info,menu2Info:curMenu2Info}
function fnSaveActiveMenuHist(){
	if(curMenu1Info){
		var activeMenuHist = fnGetActiveMenuHist();
		//현재 메뉴 정보를 히스토리에 추가
		activeMenuHist.push({menu1Info:curMenu1Info,menu2Info:curMenu2Info});
		//히스토리 배열 사이즈가 10을 넘으면, 제일 과거 항목 한개 제거
		if(activeMenuHist.length > 10){
			activeMenuHist.remove(0);
		}
		//스토리지 저장
		localStorage.setItem(cstActiveMenuHistSaveKey,JSON.stringify(activeMenuHist));
	}
}