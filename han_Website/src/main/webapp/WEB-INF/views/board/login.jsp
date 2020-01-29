<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�α���</title>

<!-- Bootstrap CSS -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">

<!-- Custion CSS -->
<link rel="stylesheet" type="text/css" href="/resources/css/style1.css"/>

</head>
<body>

<div class="container">
    <div class="main">
        <div class="panel panel-success">
        
            <div class="panel-heading">
                <div class="panel-title">�α���</div>
            </div>
            
            <div class="panel-body">
                <form id="LoginForm" method="POST">

                    <div>
                        <input type="text" class="form-control" name="ID" placeholder="ID" autofocus>
                    </div>

                    <div>
                        <input type="password" class="form-control" name="PASSWORD" placeholder="PASSWORD">
                    </div>

                    <div>
                        <button type="submit" class="form-control btn btn-primary btn-dark btnmargin2">�α���</button> <br>
                        <button type="button" class="form-control btn btn-primary btn-dark btnmargin2" onclick="location.href='/board/register'">ȸ������</button>
                    </div>
                    
                </form>
            </div>
            
        </div>
    </div>
</div>

</body>
</html>