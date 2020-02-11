# Spring Website  
http://hcw0609.cafe24.com    
가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하
가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하
가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하

## 주요기능
1. 게시판[CURD] 
2. 게시글 페이징 + 검색  
    (1) 카테고리별 글 페이징 + 검색  
3. Ckeditor을 이용한 이미지 업로드
4. 파일 업로드 + 파일 다운로드 
5. 회원가입  
    (1) 유효성 검사 [ 정규식 이용 ]   
    (2) E-mail을 이용한 인증  
6. 댓글    
7. 글에 첨부파일 or 이미지가 or 댓글이 포함되어 있을때 글제목에 표시   
8. 좌측메뉴를 통한 카테고리별 글 분류  
9. 섬네일  
10. 접근권한이 없는 유저가 주소로 직접 접속하는 것을 차단  
    ex) http://hcw0609.cafe24.com/board/modify?dno=4 의 주소로 접근
11. Google Map Api 활용 하여 지도 검색  
12. 해외축구 순위표 [네이버 스포츠의 순위표를 파싱]  
13. 나무위키 [ 축구클럽 데이터 파싱 ]  

## 사용 기술  

## 사용 버전  





## 일자
2020-01-29 : Interceptor를 이용한 권한 없는 사용자를 로그인 화면으로 유도  
2020-01-30 : Interceptor 방법 사용 x, 원래 사용 중이던 권한 체크를 함수로 만들어서 코드 정리  
2020-01-30 : 리플 수정 시 기존의 내용 가져오기, 리플의 내용이 공백이면 경고 창 띄우기  
2020-01-30 : 코드에 주석 달기, 검색 실패 시 카테고리 값을 잃어버리는 현상 수정  
2020-01-31 : Google Map Api으로 미리 지정한 값으로 지도 출력 성공, 그러나 검색결과를 가지고와서 지도 출력 실패  
2020-02-01 : 사용자의 검색결과를 가져와서 주소 -> 좌표값으로 변경하여 지도 출력 성공    
2020-02-02 : 사용자의 검색결과를 가져와서 주소 -> 좌표값으로 변경하여 스트릿뷰 출력 성공  
2020-02-03 : 글에 댓글의 갯수 표시  
2020-02-04 : 네이버 스포츠의 순위표를 파싱하여 홈페이지에 적용  
2020-02-05 : 회원가입 시 ID, PASSSWORD, EMAIL 유효성 검사를 정규식을 이용하는 것으로 변경  
2020-02-09 : 전반적인 디자인 변경, 나무위키에서 축구클럽 데이터 파싱  
2020-02-10 : 모바일 화면을 고려 하여 css 변경  
2020-02-11 : 나무위키에서 파싱한 데이터 홈페이지에 적용  
