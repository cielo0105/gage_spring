<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>

<div class="container row content align-items-center w-70">
	<div class="row justify-content-center">
		<div class="col-lg-8 col-md-10 col-sm-12">
			<h1 class="fw-bold border-bottom pb-3">공지사항</h1>
		</div>

		<div class="col-lg-8 col-md-10 col-sm-12">
			<div class="card mt-2 mb-3">
				<div class="card-body ">
					<h5 class="card-title mb-3">${board.subject}</h5>
					<h6 class="card-subtitle mb-4 text-body-secondary border-bottom">
						<img
							class="avatar me-2 float-md-start bg-light p-2 rounded-circle"
							src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg" />
						${board.userId}
					</h6>
					<p class="card-text">${board.content}</p>
				</div>
				<div class="card-footer text-body-secondary">
					<small class="text-body-secondary">${board.registerTime}</small>
				</div>
			</div>
			<div class="d-flex justify-content-end">
				<button type="button" id="btn-list"
					class="btn btn-outline-primary mb-3">글목록</button>
				<button type="button" id="btn-mv-modify"
					class="btn btn-outline-success mb-3 ms-1">글수정</button>
				<button type="button" id="btn-delete"
					class="btn btn-outline-danger mb-3 ms-1">글삭제</button>
			</div>
		</div>

		<%-- <div class="col-lg-8 col-md-10 col-sm-12">
			<div class="row my-2">
				<h2 class="text-secondary px-5">${board.subject}</h2>
			</div>
			<div class="row">
				<div class="col-md-8">
					<div class="clearfix align-content-center">
						<img class="avatar me-2 float-md-start bg-light p-2"
							src="https://raw.githubusercontent.com/twbs/icons/main/icons/person-fill.svg" />
						<p>
							<span class="fw-bold">${board.userId}</span> <br /> <span
								class="text-secondary fw-light"> ${board.registerTime} 조회
								: ${board.hit}</span>
						</p>
					</div>
				</div>
				<div class="col-md-4 align-self-center text-end">댓글 : 17</div>
				<div class="divider mb-3"></div>
				<div class="text-secondary">${board.content}</div>
				<div class="divider mt-3 mb-3"></div>
				<div class="d-flex justify-content-end">
					<button type="button" id="btn-list"
						class="btn btn-outline-primary mb-3">글목록</button>
					<button type="button" id="btn-mv-modify"
						class="btn btn-outline-success mb-3 ms-1">글수정</button>
					<button type="button" id="btn-delete"
						class="btn btn-outline-danger mb-3 ms-1">글삭제</button>
				</div>
			</div>
		</div> --%>
	</div>
</div>

<script>
	document.querySelector("#btn-list").addEventListener("click", function() {
		location.href = "${root }/board?action=list";
	});

	document
			.querySelector("#btn-mv-modify")
			.addEventListener(
					"click",
					function() {
						location.href = "${root }/board?action=modifyInfo&articleNo=${board.articleNo}";
					});

	document
			.querySelector("#btn-delete")
			.addEventListener(
					"click",
					function() {
						location.href = "${root}/board?action=delete&articleNo=${board.articleNo}";
					});
</script>

</body>
<link rel="stylesheet" href="${root }/css/notice.css" type="text/css" />
</html>