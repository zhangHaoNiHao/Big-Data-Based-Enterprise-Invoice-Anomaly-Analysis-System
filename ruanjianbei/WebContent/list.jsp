<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%-- <%
ArrayList<String[]> list = connDb.index_1();
%>  --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>企业异常信息分析系统</title>
<link href="${pageContext.request.contextPath}/css/style.css"
	type='text/css' rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/bootstrap-3.3.5-dist/css/bootstrap.css" />
<script src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
<script src="${pageContext.request.contextPath}/js/fenye.js"></script>
</head>

<body onLoad="goPage(1,10);">
	<div class='header'>
		<p>企业异常预测</p>
	</div>
	<div class="content">
		<div class="nav">
			<ul>
				<li class="current"><a href="./index.jsp">总览</a></li>
				<li><a href="./select.jsp">企业查询</a></li>
			</ul>
		</div>
		
		<div class="col-md-5 ">
		</div>
		<div class="col-md-10 ">
			<div class="title">${name}名单</div>
			<table id="fenye" class="table table-striped">
				<tr>
					<td>纳税人ID</td>
					<td>发票数量</td>
					<td>发票总金额</td>
					<td>发票总税额</td>
				</tr>
			
				<!-- forEach遍历出xuqiuBeans -->
				<c:forEach items="${datas}" var="item" varStatus="status">
					<tr>
						<td><a href="${pageContext.request.contextPath}/detail.jsp?id=${item[0] }">${item[0] }</a></td>
						<td>${item[1] }</td>
						<td>${item[2] }</td>
						<td>${item[3] }</td>
					</tr>
				</c:forEach>
			</table>
			<table width="60%" align="right">
				<tr>
					<td><div id="barcon" name="barcon"></div></td>
				</tr>
			</table>
		</div>
		
	</div>
</body>
</html>