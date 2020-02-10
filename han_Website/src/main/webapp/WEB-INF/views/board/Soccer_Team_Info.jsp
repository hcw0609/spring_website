<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<!-- Jquery -->
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<!-- Custom -->
<link rel="stylesheet" type="text/css" href="/resources/css/Style_Soccer_Team_Info.css"/>

<script type="text/javascript">

	var team_image = "/Image/team_image/"+"${Team_Info['정식 명칭']}"+".png";
	console.log(team_image);

	$(document).ready(function(){	
		$("#team_image").attr("src",team_image);
		
	})
	

	
</script>

</head>
<body>

<div style="width:350px; text-align:center; position:absolute; margin-top:50px; margin-left:120px; border: 2px solid #787777;">
	<div>
		<img id="team_image" style="width:200px; height:200px;">			
	</div>
	
	</br>
	
	<div class="text_title" style="color:#ffffff; background-color:#000000; border: 1.5px solid #787777;">${Team_Info["정식 명칭"]}</div>
	

	<table style="border: 1.5px solid #787777; width:350px; "> 
		<colgroup>
			<col style="width:30%; "/>
			<col style="width:70%; "/>	
		</colgroup>
	
		<c:forEach var="sti" items="${Team_Info}" varStatus="status">
			<tr> 
	 			<td class="text_title" style="color:#ffffff; background-color:#000000;">${sti.key}</td>
	  			<td class="text_subtitle1 text-left" style="border: 1.5px solid #787777;">${sti.value}</td>
			</tr> 
		</c:forEach>
	</table>
</div>


<div style="width:700px; text-align:center; position:absolute; margin-top:50px; margin-bottom:50px; margin-left:600px; border: 2px solid #787777 ">
	<div>
		<img src="/Image/LALIGA/Real_Madrid_1.png" style="width:550px; height:350px;">
	</div>
	
	</br>
	<table>
		<colgroup>
			<col style="width:10%;"/>
			<col style="width:10%;"/>	
			<col style="width:25%;"/>
			<col style="width:20%;"/>	
			<col style="width:20%;"/>
			<col style="width:15%;"/>
		</colgroup>
		
		
		<tr class="text_title" style="background-color: #000000; color:#ffffff">
			<c:forEach items="${Player_Info[0]}" var="list"	>
				<td style="border: 1.5px solid #787777;" >${list.key}</td>
			</c:forEach>		
		</tr>
				
		
		<c:forEach items="${Player_Info}" var="list">
			<tr>
				<c:forEach items="${list}" var="map">					
					<td class="text_subtitle1" style="border: 1.5px solid #787777;" >${map.value}</td>
				</c:forEach>
			</tr>	
		</c:forEach>
	</table>	
</div>





</body>
</html>