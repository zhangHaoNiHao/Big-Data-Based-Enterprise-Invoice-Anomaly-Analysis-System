<%@ page language="java" import="db.connDb,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% ArrayList<String> list = connDb.index();%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>企业异常信息分析系统</title>
<link href="${pageContext.request.contextPath}/css/style.css" type='text/css' rel="stylesheet"/>
<script src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
</head>
<body>
	<div class='header'>
        <p>企业异常预测</p>
    </div>
    <div class="content">
        <div class="nav">
            <ul>
                <li class="current"><a href="#">总览</a></li>
                
                <li class="current"><a href="#">异常企业购销金额对比</a></li>
                <li><a href="${pageContext.request.contextPath}/select.jsp">企业查询</a></li>
            </ul>
        </div>
        <div class="container">
            <div class="title">异常企业总览饼图</div>
            <div class="show">
                <div id="main"></div>
            </div>
        </div>
    </div>
<script>
//基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));
// 指定图表的配置项和数据




// 使用刚指定的配置项和数据显示图表。
myChart.setOption(option);

myChart.on("click", function (param){    
    //alert(param.dataIndex);
	window.location.href="${pageContext.request.contextPath}/Servlet?method=list&i="+param.dataIndex+"&name="+option.series[0].data[param.dataIndex].name;
});
</script>
</body>
</html>