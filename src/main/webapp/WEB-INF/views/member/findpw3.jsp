<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>

<div class="container row content align-items-center">
	<div class="mb-3">
		<h1 class="fw-bold border-bottom pb-3">비밀번호 찾기</h1>
	</div>

	<div class="row text-end fs-6 fw-light">
		<p>
			01. 아이디 입력 > 02. 본인 확인 > <b class="fw-bold">03. 비밀번호 재설정</b>
		</p>
	</div>

	<div class="w-50 mx-auto">
		<div class="mb-3">
			<label for="password" class="form-label">새로운 비밀번호</label>
			<input type="password" id="passwrod" class="form-control" aria-describedby="passwordHelpBlock" />
			<div class="alert alert-danger text-danger mt-2" id="passwordHelpBlock" role="alert">
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-exclamation-triangle-fill" viewBox="0 0 16 16">
            <path
						d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z" />
          </svg>
				비밀번호는 문자, 숫자의 조합·구성에 따라 최소 8자리 이상 최대 20자리 이하로 구성되어야 합니다.
			</div>
		</div>

		<div class="mb-3">
			<label for="checkpassword" class="form-label">비밀번호 확인</label>
			<input type="password" id="checkpassword" class="form-control" aria-describedby="passwordHelpBlock" />
			<div class="alert alert-danger text-danger mt-2" id="checkPasswordHelpBlock" role="alert">
				<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-exclamation-triangle-fill" viewBox="0 0 16 16">
            <path
						d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z" />
          </svg>
				비밀번호가 일치하지 않습니다.
			</div>
		</div>
	</div>

	<div class="mt-4 mb-3 text-center">
		<button type="button" class="btn btn-secondary btn-lg py-2 px-5">재설정</button>
	</div>
</div>

</body>

</html>