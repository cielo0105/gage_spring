<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>

<div class="container row content align-items-center w-70">
	<div class="mb-3 row  border-bottom mb-5">
		<div class="col-8">
			<h1 class="fw-bold">분석</h1>
			<p>부동산 거래 정보를 한눈에 확인해 보세요!</p>
		</div>
	</div>
	<div class="row row-eq-height">
		<div class="col-md-6 col-sm-12 mb-sm-4 px-2 h-100">
			<div class="my-card1 p-3">
				<h3 class="fw-bold">전국 매매가 TOP3</h3>
				<p class="pb-3">평균 매매가가 가장 높은 곳이에요.</p>
				<ol id="nation-top3" class="list-group list-group-numbered">
					<li
						class="list-group-item d-flex justify-content-between align-items-center py-3">

						<span class="placeholder col-11"></span>
					</li>
					<li
						class="list-group-item d-flex justify-content-between align-items-center py-3">

						<span class="placeholder col-11"></span>
					</li>
					<li
						class="list-group-item d-flex justify-content-between align-items-center py-3">

						<span class="placeholder col-11"></span>
					</li>
				</ol>
			</div>
		</div>

		<div class="col-md-6 col-sm-12 px-2 h-100">
			<div class="my-card2 p-3">
				<h3 class="fw-bold">월별 평균 매매가</h3>
				<p class="pb-1">지역을 선택해 평균 매매가를 확인해보세요.</p>

				<div class="row pe-1 ps-1 justify-content-end mb-2">
					<div class="form-group col-lg-3 col-md-6 mb-2">
						<select class="form-select bg-secondary text-light" id="sido">
							<option value="">시도선택</option>
						</select>
					</div>
					<div class="form-group col-lg-3 col-md-6 mb-2">
						<select class="form-select bg-secondary text-light" id="gugun">
							<option value="">구군선택</option>
						</select>
					</div>
				</div>
				<div id="monthly-table">
					<div class="d-flex align-items-center justify-content-center p-3">
						<strong>지역을 선택해주세요.</strong>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript"
	src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6aa08865bc20d80f0d5bfb3661b2e72b&libraries=services"></script>
<script src="./js/utils.js"></script>
<script src="./js/analysis.js"></script>
</body>
<link rel="stylesheet" href="${root }/css/analysis.css" type="text/css" />
</html>