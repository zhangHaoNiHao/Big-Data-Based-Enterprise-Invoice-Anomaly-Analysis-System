<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- <%
ArrayList<String[]> list = connDb.index_1();
%>  --%>   
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
                <li class="current"><a href="./index.jsp">总览</a></li>
                <li><a href="#">企业查询</a></li>
            </ul>
        </div>
        <div class="container">
            <div class="title">企业信息查询</div>
        </div> 
        <div class="row-fluid">
			<form method="post" action="">
				请输入纳税人ID：<input type="text">
				<button type="submit" class='btn btn-primary btn-block'>确定</button>
			</form>
		</div>
            
      
    </div>

</body>
</html>