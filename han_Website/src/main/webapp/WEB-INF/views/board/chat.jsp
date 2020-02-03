<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>웹 소켓 통신</title>

<!-- MenuBar -->
<link rel="stylesheet" type="text/css" href="/resources/css/MenuBar.css"/>

<!-- Bootstrap -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">

<!-- Custom -->
<link rel="stylesheet" type="text/css" href="/resources/css/style.css"/>

<!-- Jquery -->
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<script type="text/javascript">
var ws;
var messages = $('#messageList');

	function openSocket(){
	    if(ws!==undefined && ws.readyState!==WebSocket.CLOSED)
	    {
	        writeResponse("WebSocket is already opend.");
	        return;
	    } 
	    
	    //웹소켓 객체 만드는 코드
	    ws = new WebSocket('ws://localhost:9090/board/echo-ws');
	    
	    ws.onopen=function(event){
	        if(event.data===undefined) return;
	        writeResponse(event.data);
	    };
	    ws.onmessage=function(event){
	        writeResponse(event.data);
	    };
	    ws.onclose=function(event){
	        writeResponse("Connection closed");
	    }
	}
	
	function send(){
	    var text = $("#messageWrite").val();
	    ws.send(text);	    
	    text="";	
	    
	    $("#messageWrite").val("");
	}
	
	function closeSocket(){
	    ws.close();
	}
	
	function writeResponse(text){
		$('#messageList').append(text+'\n');
	}
	
</script>
</head>
<body>

<div class="container">
	<div>
		<c:if test="${loginInfo.ID == null}">
		<span class="text_etc">"비로그인"님 어서오세요.</span>
		<input type="button" class="btn-Logout btn-danger" value="로그인하기" onclick="location.href='/board/login'"></input>
		</c:if>
		<c:if test="${loginInfo.ID != null}">
		<span class="text_etc">"${loginInfo.ID}"님 어서오세요.</span>
		<input type="button" class="btn-Logout btn-danger" value="로그아웃" onclick="location.href='/board/logout'"></input>
		</c:if>
		<input type="hidden" id="loginInfoID" name="${loginInfo.ID}" value="${loginInfo.ID}">
	</div>
	
	<!-- 상단 메뉴바 -->
	<div id="topMenu" >
		<ul>
			<li class="topMenuLi">
                <a class="menuLink" href="/board/list">FOOTBALL</a>
                <ul class="submenu4">
                    <li><a href="/board/Board_List/soccer?category=해외축구" class="submenuLink">해외축구</a></li>
                    <li><a href="/board/Board_List/soccer?category=국내축구" class="submenuLink">국내축구</a></li>
                    <li><a href="/board/Board_List/soccer?category=축구 소식통" class="submenuLink">축구 소식통</a></li>
                    <li><a href="/board/Board_List/soccer?category=자유 게시판" class="submenuLink">자유 게시판</a></li>
                </ul>
            </li>
			<li>|</li>
            <li class="topMenuLi">
                <a class="menuLink" href="1">BASKETBALL</a>
                <ul class="submenu3">
                    <li><a href="" class="submenuLink">미구현</a></li>
                    <li><a href="" class="submenuLink">미구현</a></li>
                    <li><a href="" class="submenuLink">미구현</a></li>                    
                </ul>
            </li>
			<li>|</li>
            <li class="topMenuLi">
                <a class="menuLink" href="1">LANGUAGE</a>
                <ul class="submenu4">
                    <li><a href="" class="submenuLink">미구현</a></li>
                    <li><a href="" class="submenuLink">미구현</a></li>
                    <li><a href="" class="submenuLink">미구현</a></li> 
                    <li><a href="" class="submenuLink">미구현</a></li>    
                </ul>
            </li>
			<li>|</li>
            <li class="topMenuLi">
                <a class="menuLink" href="/board/chat">Chat</a>
            </li>
			<li>|</li>
            <li class="topMenuLi">
                <a class="menuLink" onclick="window.open('/board/map','window_name','width=1200,height=700,location=no,status=no,scrollbars=yes');">Map</a>
            </li>
		</ul>
	</div>
	<!-- 상단 메뉴바 -->
	
	</br></br>
	
	
	
	<textarea id="messageList" rows="10" cols="10" style="width:100%; height:500px;" readonly></textarea>
	
	<div>
        <input type="text" style="width:100%;" id="messageWrite">
    </div>
    
    <div>
        <button onclick="openSocket();">Open</button>
        <button onclick="send();">Send</button>
        <button onclick="closeSocket();">close</button>
    </div>
    
   
</div>
</body>

</html>
