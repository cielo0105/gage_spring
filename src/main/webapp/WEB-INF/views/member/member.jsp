<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>
<div class="container row content align-items-center">
	<div class="mb-3">
		<h1 class="fw-bold border-bottom pb-3">회원관리</h1>
	</div>

	<div class="row my-3">
		<label for="search" class="form-label">검색어</label>
		<div class="input-group mb-3 w-25">
			<select class="form-select" id="inputGroupSelect01">
				<option selected>이름</option>
				<option value="id">아이디</option>
				<option value="phone">휴대폰</option>
			</select>
		</div>
		<div class="input-group mb-3 w-75">
			<input type="text" class="form-control" aria-label="Text input with dropdown button">
			<button class="btn btn-outline-secondary" type="button" id="inputGroupFileAddon04">검색</button>
		</div>
	</div>

	<div>
		<p>
			검색결과 : <b>3</b>명 / 총 <b>3</b>명
		</p>
		<table class="table align-middle table-hover">
			<thead>
				<tr>
					<th scope="col" style="width: 5%;"><input class="form-check-input" type="checkbox" value="" id="flexCheckDefault"></th>
					<th scope="col" style="width: 20%;">아이디</th>
					<th scope="col" style="width: 15%;">이름</th>
					<th scope="col" style="width: 25%;">휴대폰</th>
					<th scope="col" style="width: 17%;">가입일</th>
					<th scope="col" style="width: 13%;">관리</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th scope="row"><input class="form-check-input" type="checkbox" value="" id="flexCheckDefault"></th>
					<td>MarkID</td>
					<td>Mark</td>
					<td>010-0000-0000</td>
					<td>2023.09.10</td>
					<td>
						<button class="btn btn-outline-secondary" type="button" id="inputGroupFileAddon04">수정</button>
						<button class="btn btn-outline-danger" type="button" id="inputGroupFileAddon04">탈퇴</button>
					</td>
				</tr>
				<tr>
					<th scope="row"><input class="form-check-input" type="checkbox" value="" id="flexCheckDefault"></th>
					<td>JacobID</td>
					<td>Jacob</td>
					<td>010-0000-0000</td>
					<td>2023.09.10</td>
					<td>
						<button class="btn btn-outline-secondary" type="button" id="inputGroupFileAddon04">수정</button>
						<button class="btn btn-outline-danger" type="button" id="inputGroupFileAddon04">탈퇴</button>
					</td>
				</tr>
				<tr>
					<th scope="row"><input class="form-check-input" type="checkbox" value="" id="flexCheckDefault"></th>
					<td>JennyID</td>
					<td>Jenny</td>
					<td>010-0000-0000</td>
					<td>2023.09.10</td>
					<td>
						<button class="btn btn-outline-secondary" type="button" id="inputGroupFileAddon04">수정</button>
						<button class="btn btn-outline-danger" type="button" id="inputGroupFileAddon04">탈퇴</button>
					</td>
				</tr>
			</tbody>
		</table>
		<button type="button" class="btn btn-danger">선택 탈퇴</button>
	</div>

	<div class="row mt-3">
		<nav aria-label="Page navigation example">
			<ul class="pagination justify-content-center">
				<li class="page-item">
					<a class="page-link text-secondary" href="#" aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
					</a>
				</li>
				<li class="page-item">
					<a class="page-link text-secondary fw-bold" href="#">1</a>
				</li>
				<li class="page-item">
					<a class="page-link text-secondary" href="#">2</a>
				</li>
				<li class="page-item">
					<a class="page-link text-secondary" href="#">3</a>
				</li>
				<li class="page-item">
					<a class="page-link text-secondary" href="#" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
					</a>
				</li>
			</ul>
		</nav>
	</div>
</div>

</body>

</html>