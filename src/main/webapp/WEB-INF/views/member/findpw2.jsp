<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>

<div class="container row content align-items-center">
	<div class="mb-3">
		<h1 class="fw-bold border-bottom pb-3">비밀번호 찾기</h1>
	</div>

	<div class="row text-end fs-6 fw-light">
		<p>
			01. 아이디 입력 > <b class="fw-bold">02. 본인 확인</b> > 03. 비밀번호 재설정
		</p>
	</div>

	<div class="w-50 mx-auto">
		<div class="my-3">
			<label for="phone" class="form-label">본인 명의 휴대전화로 인증</label>
			<div class="input-group mb-3">
				<input type="text" class="form-control" aria-label="phone1" />
				<span class="input-group-text">-</span>
				<input type="text" class="form-control" aria-label="phone2" />
				<span class="input-group-text">-</span>
				<input type="text" class="form-control" aria-label="phone3" />
				<button class="btn btn-outline-secondary" type="button" id="sendauth">인증번호 전송</button>
			</div>

			<div class="my-3">
				<label for="authnum" class="form-label">인증번호</label>
				<div class="input-group mb-3">
					<input type="text" class="form-control" aria-label="authnum" />
					<button class="btn btn-outline-secondary" type="button" id="checkauth">확인</button>
				</div>
			</div>

			<div class="mt-4 mb-3 text-center">
				<button type="button" class="btn btn-secondary btn-lg py-2 px-5">다음</button>
			</div>
		</div>
	</div>
</div>

</body>

</html>