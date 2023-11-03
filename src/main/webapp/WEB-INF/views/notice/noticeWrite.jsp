<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>

<div class="container row content align-items-center w-70">
	<div class="row justify-content-center">
		<div class="col-lg-8 col-md-10 col-sm-12">
			<h1 class="fw-bold border-bottom pb-3">공지사항</h1>
		</div>

		<div class="col-lg-8 col-md-10 col-sm-12">
			<form id="form-register" method="POST"
				action="${root }/board?action=write" onsubmit="return checkValid()">
				<div class="mb-3">
					<input
						type="hidden" class="form-control" id="userid" name="userid"
						value="${empty user ? 'ssafy':user.userId}" placeholder="작성자ID..." />

				</div>
				<div class="mb-3">
					<label for="subject" class="form-label">제목 : </label> <input
						type="text" class="form-control" id="subject" name="subject"
						placeholder="제목..." />
				</div>
				<div class="mb-3">
					<label for="content" class="form-label">내용 : </label>
					<textarea class="form-control" id="content" name="content" rows="7"></textarea>
				</div>
				<div class="d-flex justify-content-end">
					<input type="hidden" name="action" value="registBoard">
					<button type="submit" id="btn-register"
						class="btn btn-outline-primary mb-3">글작성</button>
					<button type="reset" class="btn btn-outline-danger mb-3 ms-1">초기화</button>
				</div>
			</form>
		</div>
	</div>
</div>

<script>
	function checkValid(e) {
		if (!document.querySelector("#subject").value) {
			alert("제목 입력!!");
			return false;
		} else if (!document.querySelector("#content").value) {
			alert("내용 입력!!");
			return false;
		} else {
			return true;
		}
	}
</script>
</body>
<link rel="stylesheet" href="${root }/css/notice.css" type="text/css" />
</html>
