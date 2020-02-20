<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">

	<title>Admin_Visitorcount</title>
	
	<!--  1 -->
	<!-- charts -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.js"></script>
	
	<!-- Jquery -->
 	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
 	
	<!-- Bootstrap core CSS -->
	<link href="/resources/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<!--  1 --> 

	<script type="text/javascript">
	
		$(document).ready(function() {
		       
			// 받아올 데이터를 저장할 배열 선언
	        var chartLabels = []; 
	        var chartData_1 = []; 
	        var chartData_2 = [];
	                 
	        function createChart() {
	            
	            var ctx = document.getElementById("canvas").getContext("2d");
	            LineChartDemo = Chart.Line(ctx, {
	                data : lineChartData,
	                options : {
	                    scales : {
	                        yAxes : [ {
	                            ticks : {
	                                beginAtZero : true
	                            }
	                        } ]
	                    }
	                }
	            });
	        }
	 	 	 
	        
	        // 버튼을 클릭하면 차트가 그려진다. [게시글]
	        $("#board_btn").on("click", function(e){
	        	
	        	chartLabels = [];
				chartData_1 = []; 
		        chartData_2 = [];
		        
		        $.ajax({
		        	url: '/board/board_count_day',
		        	type: 'post',
		        	success : function(data){
		        		
		        		chartLabels = data[0];
	            		chartData_1 = data[1];
	            		chartData_2 = data[2];
	            		
	            		lineChartData = {
	                            labels : chartLabels,
	                            datasets : [
	                            	{
		                                label : "날짜별 작성된 게시글",
		                                backgroundColor:"#bfdaf9",
		                                borderColor: "#80b6f4",
		                                pointBorderColor: "#80b6f4",
		                                pointBackgroundColor: "#80b6f4",
		                                pointHoverBackgroundColor: "#80b6f4",
		                                pointHoverBorderColor: "#80b6f4",
		                                fill: false,
		                                borderWidth: 4,
		                                data : chartData_1 },
									{
										label : "날짜별 작성된 리플",
										backgroundColor:"#187ff5",
										borderColor: "#187ff5",
										pointBorderColor: "#187ff5",
										pointBackgroundColor: "#187ff5",
										pointHoverBackgroundColor: "#187ff5",
										pointHoverBorderColor: "#187ff5",
										fill: false,
										borderWidth: 4,
										data : chartData_2 }
								]   
						}
	            		createChart();
		        	}
		        	
		        })
	        })
	        
	        
	        // 버튼을 클릭하면 차트가 그려진다. [방문자]
	        $('#visitor_btn').click(function(){
	            
				chartLabels = [];
				chartData_1 = []; 
		        chartData_2 = []; 
	                                      	            
	            $.ajax({
	            	url:'/board/visitor_visitor_regdate',
	            	type: 'post',
	            	success : function(data){           		
	           			
	            		chartLabels = data[0];
	            		chartData_1 = data[1];
	            		chartData_2 = data[2];
	            		
	            		lineChartData = {
	                            labels : chartLabels,
	                            datasets : [
	                            	{
		                                label : "날짜별 방문자수 [중복처리X]",
		                                backgroundColor:"#bfdaf9",
		                                borderColor: "#80b6f4",
		                                pointBorderColor: "#80b6f4",
		                                pointBackgroundColor: "#80b6f4",
		                                pointHoverBackgroundColor: "#80b6f4",
		                                pointHoverBorderColor: "#80b6f4",
		                                fill: false,
		                                borderWidth: 4,
		                                data : chartData_1 },
									{
		                                label : "날짜별 방문자수 [중복처리O]",
		                                backgroundColor:"#187ff5",
		                                borderColor: "#187ff5",
		                                pointBorderColor: "#187ff5",
		                                pointBackgroundColor: "#187ff5",
		                                pointHoverBackgroundColor: "#187ff5",
		                                pointHoverBorderColor: "#187ff5",
		                                fill: false,
		                                borderWidth: 4,
		                                data : chartData_2 }
								]   
						}
						createChart();           		
	            	},
	            	error:function(request,status,error){
	                    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
	                }
	
	            })            
	        })
		})
      
	</script>
</head>
<body>

	<div>
		<div style="margin-top:20px; margin-left:80px">                  
			<button id="visitor_btn">방문자 통계</button>
			<button id="board_btn">게시글 통계</button>
 		</div>
	</div>

 
	<div id="graph" style="width: 60%; margin: 30px;">
		<div>
    		<canvas id="canvas" height="300px" width="500px"></canvas>
		</div>
	</div>
		
</body>
</html>