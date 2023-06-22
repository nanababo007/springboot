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
			,pageTitle:''
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
			,topYn:'Y'
			,dispYn:'Y'
			,url:''
			,urlTarget:'_self'
			,subList:[
				//페이지 등록
				{
					id:'pageReg'
					,title:'페이지등록'
					,topYn:'Y'
					,dispYn:'Y'
					,url:'/page/reg.do'
					,urlTarget:'_self'
				}
			]
		}
		//게시판 관리
		,{
			id:'bord'
			,title:'게시판관리'
			,topYn:'Y'
			,dispYn:'Y'
			,url:''
			,urlTarget:'_self'
			,subList:[
				//공지사항
				{
					id:'bordNoti'
					,title:'공지사항'
					,topYn:'Y'
					,dispYn:'Y'
					,url:'/bord/noti.do'
					,urlTarget:'_self'
				}
				//FAQ
				,{
					id:'bordFaq'
					,title:'FAQ'
					,topYn:'Y'
					,dispYn:'Y'
					,url:'/bord/faq.do'
					,urlTarget:'_self'
				}
				//QnA
				,{
					id:'bordQna'
					,title:'QnA'
					,topYn:'Y'
					,dispYn:'Y'
					,url:'/bord/qna.do'
					,urlTarget:'_self'
				}
				//제품소개
				,{
					id:'bordProd'
					,title:'제품소개'
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
		//하위 메뉴 항목 유무 여부
		var hasSub = el.subList && el.subList.length ? true : false;
		//하위 메뉴 목록
		var menuSubList = hasSub ? el.subList : [];
		//상위 메뉴 html
		var html = [];
		html.push('<li class="'+(hasSub ? 'has_sub' : '')+'">');
		if(hasSub){
			html.push('<a href="javascript: void(0);" class=""><span>'+el.title+'</span></a>');
		}else{
			html.push('<a href="'+el.url+'" target="'+el.urlTarget+'" class=""><span>'+el.title+'</span></a>');
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
				//하위 메뉴 html 설정
				html = [];
				html.push('<li><a href="'+el2.url+'" target="'+el2.urlTarget+'" class="">'+el2.title+'</a></li>');
				//하위 메뉴 추가 처리
				menuSub.append(html.join(''));
			});
		}
	});
}
//현재 메뉴 설정
function fnSetCurMenu(){

}
//메뉴 네비게이션 설정
function fnSetMenuNavi(){

}
//현재 메뉴 활성화
function fnActiveMenu(){

}