<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html class="Custom_html">
<head>

	<meta charset="EUC-KR">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	<title>Soccer Site</title>

	<!-- 2 -->
	<!-- Bootstrap CSS -->
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
	
	<!-- Custom CSS -->
	<link rel="stylesheet" type="text/css" href="/resources/css/Login_Register.css"/>
	<!-- 2 -->

</head>

<body class="Custom_body">

<div class="Custom_container">
    <div class="Custom_main">      
            <div>
                <label class="text_title" style="margin-bottom: .0rem;">로그인</label>
            </div>
            
            <div class="panel-body">
                <form id="LoginForm" method="POST" action="/board/login">

                    <div>
                        <input type="text" class="form-control" name="ID" placeholder="ID" autofocus>
                    </div>

                    <div>
                        <input type="password" class="form-control" name="PASSWORD" placeholder="PASSWORD">
                    </div>

                    <div>
                        <button type="submit" class="form-control btn btn-primary btn-dark btnmargin2">로그인 / 비로그인</button> <br>
                        <button type="button" class="form-control btn btn-primary btn-dark btnmargin2" onclick="location.href='/board/register'">회원가입</button>
                    </div>
                    
                </form>
            </div>           
    </div>
</div>

</body>
</html>