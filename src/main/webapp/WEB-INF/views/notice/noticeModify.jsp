<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>

<div class="container row content align-items-center w-70">
	<div class="row justify-content-center">
		<div class="col-lg-8 col-md-10 col-sm-12">
			<h1 class="fw-bold border-bottom pb-3">공지사항</h1>
		</div>
		
		<div class="col-lg-8 col-md-10 col-sm-12">
			<form id="form-modify" method="POST" action="">
				<input type="hidden" name="action" value="modify"> <input
					type="hidden" name="articleNo" value="${board.articleNo}">
				<div class="mb-3">
				<input
						type="hidden" class="form-control" id="userid"
						value="${board.userId}" readonly />
				</div>
				<div class="mb-3">
					<label for="subject" class="form-label">제목 : </label> <input
						type="text" class="form-control" name="subject" id="subject"
						value="${board.subject}" />
				</div>
				<div class="mb-3">
					<label for="content" class="form-label">내용 : </label>
					<textarea class="form-control" name="content" id="content" rows="7">${board.content}</textarea>
				</div>
				<div class="d-flex justify-content-end">
					<button type="button" id="btn-modify"
						class="btn btn-outline-success mb-3">글수정</button>
					<button type="button" id="btn-list"
						class="btn btn-outline-primary mb-3 ms-1">글목록</button>
				</div>
			</form>
		</div>
	</div>
</div>

<script>
	document.querySelector("#btn-modify").addEventListener("click", function() {
		if (!document.querySelector("#subject").value) {
			alert("제목 입력!!");
			return;
		} else if (!document.querySelector("#content").value) {
			alert("내용 입력!!");
			return;
		} else {
			let form = document.querySelector("#form-modify");
			form.setAttribute("action", "${root}/board");
			form.submit();
		}
	});
	document.querySelector("#btn-list").addEventListener("click", function() {
		location.href = "${root }/board?action=list";
	});
</script>

</body>
<link rel="stylesheet" href="${root }/css/notice.css" type="text/css" />
</html>




