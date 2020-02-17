<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">

	<title>Insert title here</title>
	
	<!-- 2 -->
	<!-- charts -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.6.0/Chart.js"></script>
	
	<!-- Jquery -->
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<!-- 2 -->
	
	<script type="text/javascript">
		$(document).ready(function() {
		       
			// 받아올 데이터를 저장할 배열 선언
	        var chartLabels = []; 
	        var chartData_all = []; 
	        var chartData_notall = []; 
	                 
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
	 	 	        
	        
	        //버튼을 클릭하면 차트가 그려진다. createChart()함수를 안에다 선언해야지 차트값을 받더라...
	        $('#btn').click(function(){
	            
				chartLabels = [];
				chartData_all = []; 
		        chartData_notall = []; 
	                                      	            
	            $.ajax({
	            	url:'/board/visitor_visitor_regdate',
	            	type: 'post',
	            	success : function(data){           		
	           			
	            		chartLabels = data[0];
	            		chartData_all = data[1];
	            		chartData_notall = data[2];
	            		
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
		                                data : chartData_all },
									{
		                                label : "날짜별 방문자수 [중복처리O]",
		                                backgroundColor:"#bfdaf9",
		                                borderColor: "#187ff5",
		                                pointBorderColor: "#187ff5",
		                                pointBackgroundColor: "#187ff5",
		                                pointHoverBackgroundColor: "#187ff5",
		                                pointHoverBorderColor: "#187ff5",
		                                fill: false,
		                                borderWidth: 4,
		                                data : chartData_notall }
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
			<button id="btn">방문자 통계</button>
 		</div>
	</div>


 
	<div id="graph" style="width: 60%; margin: 30px;">
		<div>
    		<canvas id="canvas" height="300px" width="500px"></canvas>
		</div>
	</div>
	
</body>
</html>