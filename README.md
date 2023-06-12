# springboot
- sample rest server api in springboot framework
- this is simple cms site for sample rest server
- my react, reactjs, angular, angularjs, vue, vuejs projects will use this rest server api common project.
  each client projects use there own folder.

# 참조 사이트
- https://congsong.tistory.com/15

# 개발환경
- 스프링부트 : 버전 3.0.7
- ui : thymeleaf
- 로그인 : 스프링 시큐리티
- 디비 서버 : mysql
- 디비 처리 : mybatis, jpa
- 소스 헬퍼 : lombok
- 배치 실행 : quartz

# 문서
- /smplcms/src/main/doc

# 페이지 주요 경로
- 로그인 페이지 : /login
- 로그아웃 페이지 : /logout
- 메인 대시보드 페이지 : /main/dash.do
- 샘플 목록 페이지 : /sample/post/list.do
- 샘플 쓰기 페이지 : /sample/post/write.do

# API 주요 경로
- 토큰 신규 발급 : /api/memb/login/token/new.do
- 토큰 재발급 : /api/memb/login/token/renew.do
- 토큰 확인 : /api/memb/login/token/valid.do
- 로그인 토큰 정보 : /api/memb/login/token/login.do
