<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js" type="text/javascript"></script>
<script>

$(function() {
	getList(1);
	$("#btn-submit-category").click(function() {
		var name = $("#category-name").val();
		var info = $("#category-info").val();
		if(name=="" || info=="") {
			alert("입력해주세요~");
			return;
		}
		var category = JSON.stringify({
			"name": name,
			"info": info
		});
		
		$.ajax({
			url: "${pageContext.servletContext.contextPath }/api/category/add",
			type: "post",
			dataType: "json",
			data: category,
			contentType:"application/json;charset=UTF-8",
			success: function(data, response) {
				if(response.result == "fail") {
					console.error(response.message);
					return;
				}
				
				$("#category-name").val("");
				$("#category-info").val("");
				$("#category-name").focus();
				getList(1);
			},
			error: function(xhr, error) {
				console.error("error:"+error);
			}
		});
	});
	function getList(pageNum) {
		$("tr[target='_blank']").remove();
		$("#pager").empty();
		$.ajax({
			url: "${pageContext.servletContext.contextPath }/api/category/getList?curPage="+pageNum,
			type: "get",
			dataType: "json",
			data: "",
			success: function(data, response) {
				if(response.result == "fail") {
					console.error(response.message);
					return;
				}
				var list = "";
				var pagination = data.pagination;
				$.each(data.category, function(key, category) {
					list += "<tr target='_blank'>";
					list += "<td>"+(pagination.listCnt-((pagination.curPage-1)*pagination.pageSize)-key)+"</td>";
					list += "<td>"+category.name+"</td>";
					list += "<td>"+category.countPost+"</td>";
					list += "<td>"+category.info+"</td>";
					list += "<td><button data-page='"+pageNum+"' data-value='"+category.no+"'><img src='${pageContext.request.contextPath}/assets/images/delete.jpg'></button></td>";
					list += "</tr>";
				});
				$("#tbl-category").append(list);
				paging(pagination);
			},
			error: function(xhr, error) {
				console.error("error:"+error);
			}
		});
	}
	
	$("#tbl-category").on("click", "button", function(event) {
		//event.preventDefault();
		var value = $(this).data("value");
		var pageNum = $(this).data("page");
		var data = JSON.stringify({
			"categoryNo": value
		});
		
		$.ajax({
			url: "${pageContext.servletContext.contextPath }/api/category/delete",
			type: "post",
			dataType: "json",
			data: data,
			contentType:"application/json;charset=UTF-8",
			success: function(data, response) {
				if(response.result == "fail") {
					console.error(response.message);
					return;
				}
				
				getList(pageNum);
			},
			error: function(xhr, error) {
				console.error("error:"+error);
			}
		});
	});
	
	function paging(pagination) {
		
		var curRange = pagination.curRange;
		var curPage = pagination.curPage;
		var prevRange = pagination.prevRange;
		var startPage = pagination.startPage;
		var endPage = pagination.endPage;
		var rangeCnt = pagination.rangeCnt;
		var nextRange = pagination.nextRange;
		var pageCnt = pagination.pageCnt;
		var page = "";
		
		page += "<ul>";
		if(curRange != 1) {
			page += "<li><a href='#' data-page='1'>[◀◀]</a></li>";
			page += "<li><a href='#' data-page='"+prevRange+"'>[◀]</a></li>";
		}
		
		for (var pageNum = startPage; pageNum <= endPage; pageNum++) {
			if(pageNum == curPage) {
				page += "<li>"+pageNum+"</li>"
			} else {
				page += "<li><a href='#' data-page='"+pageNum+"'>"+pageNum+"</a></li>";
			}
			
		}
		if(curRange != rangeCnt && rangeCnt > 1) {
			page += "<li><a href='#' data-page='"+nextRange+"'>[▶]</a></li>";
		}
		if(curRange != rangeCnt && rangeCnt > 0) {
			page += "<li><a href='#' data-page='"+pageCnt+"'>[▶▶]</a></li>";
		}
		page += "</ul>";
		
		$("#pager").append(page);
		
	}
	
	$("#pager").on("click", "a", function(event) {
		var pageNum = $(this).data("page");
		
		getList(pageNum);
	});
});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header-blog.jsp" />
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/admin-menu.jsp" />
		      	<table class="admin-cat" id="tbl-category">
		      		<tr>
		      			<th>번호</th>
		      			<th>카테고리명</th>
		      			<th>포스트 수</th>
		      			<th>설명</th>
		      			<th>삭제</th>      			
		      		</tr>
		      		
				</table>
      			<div id="pager" class="pager">

      			</div>
      			<h4 class="n-c">새로운 카테고리 추가</h4>
		      	<table id="admin-cat-add">
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input id="category-name" type="text" name="name"></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input id="category-info" type="text" name="info"></td>
		      		</tr>
		      		<tr>
		      			<td class="s">&nbsp;</td>
		      			<td><input id="btn-submit-category" type="submit" value="카테고리 추가"></td>
		      		</tr>      		      		
		      	</table> 
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>