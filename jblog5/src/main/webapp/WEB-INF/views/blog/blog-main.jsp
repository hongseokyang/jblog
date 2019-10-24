<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
	pageContext.setAttribute("newLineChar", "\n");
%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header-blog.jsp" />
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${map.post.title }</h4>
					<p>
						${fn:replace(map.post.contents, newLineChar, '<br>') }
					<p>
				</div>
				<ul class="blog-list">
					<c:forEach items="${map.postList }" var="post" varStatus="status">
						<li><a href="${pageContext.request.contextPath}/${authUser.id }/${post.no }/${categoryNo }">${post.title }</a> <span>${post.writeDate }</span></li>
					</c:forEach>
				</ul>
			</div>
		</div>
		
		<div id="extra">
			<div class="blog-logo">
				<img src="${pageContext.request.contextPath}${blogVo.storedFile }">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
			<li><a href="${pageContext.request.contextPath}/${authUser.id }">전체</a></li>
			<c:forEach items="${map.categoryList }" var="category" varStatus="status">
				<li><a href="${pageContext.request.contextPath}/${authUser.id }/${category.no }">${category.name }</a></li>
			</c:forEach>
			</ul>
		</div>
		
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>