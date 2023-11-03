<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- <%@ include file="/common/header.jsp"%> --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container row content align-items-center w-70">
	<div class="mb-3 row  border-bottom pb-3">
		<div class="col-8">
			<h1 class="fw-bold">공지사항</h1>
		</div>
		<div class="col-4" style="text-align: right;">
			<a class="btn btn-secondary" href="${root }/board?action=writeInfo">글쓰기</a>
		</div>
	</div>
	
	<table class="table table-hover align-middle">
		<thead>
			<tr>
				<th class="col-1" scope="col">글번호</th>
				<th class="col-7" scope="col">제목</th>
				<th class="col-4" scope="col">날짜</th>
			</tr>
		</thead>
		<tbody>

			<c:forEach var="item" items="${list}">
				<tr
					onclick="location.href='${root}/board?action=view&no=${item.articleNo}'">
					<th scope="row">${item.articleNo}</th>
					<td>${item.subject}</td>
					<td>${item.registerTime}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	
	<div class="row my-1 col-8 mx-auto">
		<form class="d-flex" id="form-search" action="">
		<input type="hidden" name="action" value="list"/>
        <input type="hidden" name="pgno" value="1"/>
        
		<div class="input-group mb-3 w-25">
			<select class="form-select" id="key" name="key" aria-label="검색조건">
				<option value="" selected>검색조건</option>
				<option value="article_no">글번호</option>
				<option value="subject">제목</option>
			</select>
		</div>
		<div class="input-group mb-3 w-75">
			<input type="text" name="word" id="word" class="form-control">
			<button class="btn btn-outline-secondary" type="button" id="btn-search">검색</button>
		</div>
		</form>
	</div>

<c:set var="currentPage" value="${navigation.currentPage}" />
<c:set var="startPage" value="${navigation.startPage}" />
<c:set var="endPage" value="${navigation.endPage}" />
<c:set var="startRange" value="${navigation.startRange}" />
<c:set var="endRange" value="${navigation.endRange}" />
<c:set var="totalPageCnt" value="${navigation.totalPageCount}" />

<nav aria-label="Page navigation example">
	<ul class="pagination justify-content-center">
		<li class="page-item <c:if test="${startRange}">disabled</c:if>" data-pg="1">
			<a class="page-link text-reset" href="#" aria-label="Previous"> <span
				aria-hidden="true">&laquo;</span>
		</a>
		</li>
		<li class="page-item <c:if test="${currentPage == 1}">disabled</c:if>" data-pg="${currentPage - 1}"><a
			class="page-link text-reset" href="#">이전</a></li>

		<c:forEach var="pageNum" begin="${startPage}" end="${endPage}"
			step="1">
			<li
				class="page-item <c:if test="${currentPage == pageNum}">fw-bold</c:if>" data-pg="${pageNum}"><a
				class="page-link text-reset" href="#">${pageNum}</a></li>
		</c:forEach>

		<li
			class="page-item <c:if test="${currentPage == totalPageCnt}">disabled</c:if>" data-pg="${currentPage + 1}"><a
			class="page-link text-reset" href="#">다음</a></li>
		<li
			class="page-item <c:if test="${endRange}">disabled</c:if>" data-pg="${totalPageCnt}">
			<a class="page-link text-reset" href="#" aria-label="Next"> <span
				aria-hidden="true">&raquo;</span>
		</a>
		</li>
	</ul>
</nav>
</div>

	<form id="form-param" method="get" action="">
      <input type="hidden" id="p-action" name="action" value="">
      <input type="hidden" id="p-pgno" name="pgno" value="">
      <input type="hidden" id="p-key" name="key" value="">
      <input type="hidden" id="p-word" name="word" value="">
	</form>

</body>
<link rel="stylesheet" href="${root }/css/notice.css" type="text/css" />
<script>
document.querySelector("#btn-search").addEventListener("click", function () {
	  let form = document.querySelector("#form-search");
    form.setAttribute("action", "${root}/board");
    form.submit();
});


let pages = document.querySelectorAll(".page-link");
pages.forEach(function (page) {
  page.addEventListener("click", function () {
    console.log(this.parentNode.getAttribute("data-pg"));
    document.querySelector("#p-action").value = "list";
 	document.querySelector("#p-pgno").value = this.parentNode.getAttribute("data-pg");
 	document.querySelector("#p-key").value = "${param.key}";
 	document.querySelector("#p-word").value = "${param.word}";
    document.querySelector("#form-param").submit();
  });
});
</script>
</html>