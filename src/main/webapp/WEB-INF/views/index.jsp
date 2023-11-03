<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/common/header.jsp"%>

<div id="information" class="information scroll">
	<div class="brt-6 p-2 gray-700">
		<button class="text-white border-0 gray-700" onclick="closeInfo()">X</button>
	</div>

	<div class="p-3 border-bottom">
		<h4 id="aptname" class="fw-bold">hello</h4>
		<p class="fw-bold" id="address">서울특별시 강남구 역삼동</p>
		<button id="btn-star" class="border-0 rounded-2" onclick="changeStar()">★</button>
		<p id="address-detail">서울특별시 강남구 역삼동 718-5</p>
	</div>

	<div class="p-3 border-bottom d-flex">
		<div>
			<p>최근 거래 금액</p>
			<h4 id="curCost" class="fw-bold"></h4>
		</div>
		<div class="mx-auto">
			<p>주변 평균 거래 금액</p>
			<h4 id="avgCost" class="fw-bold"></h4>
		</div>
	</div>

	<div class="p-3">
		<p>거래내역</p>
		<table id="table-history" class="font- table table-bordered"></table>
	</div>
</div>

<div id="starList" class="starList scroll">
	<div class="brt-6 p-2 gray-700">
		<button class="text-white border-0 gray-700" onclick="closeStar()">X</button>
	</div>
	<div class="p-3">
		<table class="table table-hover">
			<thead>
				<tr>
					<th scope="col">#</th>
					<th class="text-center" scope="col">주소</th>
				</tr>
			</thead>
			<tbody id="starListBody">
			</tbody>
		</table>
	</div>
</div>

<div id="search" class="search">
	<div class="rounded-3 align-items-center p-2 search-box">
		<div class="row p-1 justify-content-center">
			<div class="form-group col-md-4">
				<select class="form-select bg-white" id="sido">
					<option value="">시도선택</option>
				</select>
			</div>
			<div class="form-group col-md-4">
				<select class="form-select bg-white" id="gugun">
					<option value="">구군선택</option>
				</select>
			</div>
			<div class="form-group col-md-4">
				<select class="form-select bg-white" id="dong">
					<option value="">동선택</option>
				</select>
			</div>
		</div>
	</div>
	<div class="text-center star-btn">
		<button class="btn btn-light" onclick="openStar()">관심지역</button>
	</div>
</div>


<article class="map">
	<!-- 매장 위치 정보 kakaomap start -->
	<div id="map" style="width: 100%; height: 100%"></div>
	<!-- 매장 위치 정보 kakaomap end -->
</article>

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=6aa08865bc20d80f0d5bfb3661b2e72b&libraries=services"></script>
<%@ include file="./js/history.jsp"%>


<script src="./js/utils.js"></script>
<script src="./js/kakao.js"></script>
<script src="./js/infomation.js"></script>


</body>

</html>