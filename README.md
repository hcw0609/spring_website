# Spring Website  
http://hcw0609.cafe24.com  
하나의 커뮤니티 사이트에서 파싱으로 가져온 데이터나 api를 이용하여 사이트의 이동 없이 축구정보를 파악할 수가 있다. 

## 주요기능
1. 게시판[CURD] 
2. 게시글 페이징  
3. 게시글 검색  
4. Ckeditor을 이용한 이미지 업로드
5. 파일 업로드  
6. 파일 다운로드  
7. 회원가입  
    (1) 유효성 검사 [ 정규식 이용 ]   
    (2) E-mail을 이용한 인증  
8. 댓글    
9. 글에 첨부파일 or 이미지가 or 댓글이 포함되어 있을때 글제목에 표시   
10. 섬네일  
11. 접근권한이 없는 유저가 주소로 직접 접속하는 것을 차단  
    ex) 주소창에 http://hcw0609.cafe24.com/board/modify?dno=4 를 쳐서 접근하는 형태
12. Google Map Api 활용 하여 지도 검색  
13. 파싱  
    (1) 네이버 스포츠 : 리그 순위 가져오기  
    (2) 나무위키 : 팀정보 가져오기  
14. Chart.js를 이용한 방문자, 날짜별 게시글, 리플 통계 
15. 완벽하진 않지만 어느정도의 모바일호환  

## 사용 기술 
1. Fornt-End  
    (1) Spring   
    (2) HTML  
    (3) Javascript    
    (4) CSS    
    (5) Jquery    
    (6) Ajax    
2. Back-End  
    (1) Java
3. DataBase  
    (1) OracleDB  
    (2) MariaDB  
    
## 사용 버전  
Eclipse:4.13  
JDK:1.8  
Tomcat:8.5  
MariaDB:10.1.13  
Spring:4.3.8  
Mybatis:3.4.1  
Maven:2.9  
Bootstrap:4.3.1  
Jquery:3.4 [항상 최신버전 유지]  


## 전반적인 구조  
![Untitled Diagram](https://user-images.githubusercontent.com/60380909/75225560-33bbcc80-57ee-11ea-9217-0ab283cb4ba9.jpg)



## 일자
2020-01-14 : Eclipse, JDK, Tomcat, OracleDB설치  
2020-01-15 : Eclipse에 Spring 설치 후 Tomcat, MariaDB 연동  
2020-01-16 : 기본적인 CURD 게시판 구현  
2020-01-17 : Ajax, Session 를 이용한 로그인, 회원가입 구현  
2020-01-18 : ID중복체크, 유효성검사, 이메일 인증 구현  
2020-01-19 : 글 페이징,검색 구현  
2020-01-20 : Ckedtior를 이용한 이미지업로드 구현  
2020-01-21 : 파일업로드 구현  
2020-01-22 : 파일업로드 구현, 파일다운로드 구현  
2020-01-23 : 파일다운로드 구현  
2020-01-24 : 리플 작성구현  
2020-01-25 : 리플 작성구현, 리플 수정구현  
2020-01-26 : 리플 수정구현  
2020-01-27 : OracleDB -> MariaDB로 변경, cafe24를 이용한 웹 호스팅  
2020-01-28 : 권한이 없는 접근 처리  
2020-01-15 ~ 2020-01-28 : 일자는 Github 시작전  
2020-01-29 : Interceptor를 이용한 권한 없는 접근 처리  
2020-01-30 : Interceptor 방법 사용 x, 원래 사용 중이던 권한 체크를 함수로 만들어서 코드 정리  
2020-01-30 : 리플 수정 시 내용 가져오기, 리플의 유효성검사  
2020-01-30 : 코드에 주석 달기, 검색 실패 시 카테고리 값을 잃어버리는 현상 수정  
2020-01-31 : Google Map Api으로 지도 출력 성공, 그러나 검색결과를 가지고와서 지도 출력 실패  
2020-02-01 : 사용자의 검색결과를 가져와서 주소 -> 좌표값으로 변경하여 지도 출력 성공    
2020-02-02 : 사용자의 검색결과를 가져와서 주소 -> 좌표값으로 변경하여 스트릿뷰 출력 성공  
2020-02-03 : 글에 댓글의 갯수 표시  
2020-02-04 : 네이버 스포츠의 순위표를 파싱하여 홈페이지에 적용  
2020-02-05 : 회원가입 시 ID, PASSSWORD, EMAIL 유효성 검사를 정규식을 이용하는 것으로 변경  
2020-02-09 : 전반적인 디자인 변경, 나무위키에서 축구클럽 데이터 파싱  
2020-02-10 : 모바일 화면을 고려 하여 css 변경  
2020-02-11 : 나무위키에서 파싱한 데이터 홈페이지에 적용  
2020-02-12 : 나무위키에서 파싱한 데이터와 Google Map Api 연동  
2020-02-13 : 글 쓰기,읽기,수정 이미지 사이즈 모바일에 맞게 자동으로 조절 + 그외 모바일 편의성 작업  
2020-02-13 : 사파리에서 글 쓰기,수정시 사이즈가 모바일에 맞게 변하지 않음  
2020-02-14 : 글에 첨부파일 or 이미지가 or 댓글이 포함되어 있을때 글제목에 표시  
2020-02-15 : SessionListener활용하여 방문자의 날짜, ip를 db에 저장   
2020-02-17 : Chart.js를 이용하여 방문자 수의 그래프화  + 최근 일주일 만 보여준다.  
2020-02-19 : Chart.js를 이용하여 날짜별 게시글, 리플수 + 최근 일주일 만 보여준다.  
2020-02-20 : 불필요한 코드, 전반적인 디자인 다듬기  

