<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div id="header-blog">
	<h1><a href="${pageContext.servletContext.contextPath }/${authUser.id}">${blogVo.title }</a></h1>
	<ul>
		<li><a href="${pageContext.servletContext.contextPath }/${authUser.id}/admin/basic">블로그 관리</a><li>
		<li><a href="${pageContext.servletContext.contextPath }/user/logout">로그아웃</a><li>
	</ul>
</div>

