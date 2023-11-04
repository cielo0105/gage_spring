<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

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

<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=8980521e66956686ad980618b70271ab&libraries=services"></script>
<%@ include file="/WEB-INF/views/js/history.jsp"%>


<<script type="text/javascript">
const changeMoney = (value) => {
	  if (!value) return;
	  value = Number(value.toString().replace(/,/g, ''));
	  const numbers = [numbering(value % 100000000, 10000), numbering(value % 10000, 1000)];

	  return setUnitText(numbers)
	    .filter((number) => !!number)
	    .join(' ');
	};

	const numbering = (value, division) => {
	  const result = Math.floor(value / division);
	  return result === 0 ? null : result % division;
	};

	function setUnitText(numbers) {
	  const unit = ['천', '억'];
	  return numbers.map((number, index) =>
	    !!number ? numberFormat(number) + unit[unit.length - 1 - index] : number
	  );
	}

	const NUMBER_FORMAT_REGX = /\B(?=(\d{3})+(?!\d))/g;

	const numberFormat = (value) => {
	  return value.toString().replace(NUMBER_FORMAT_REGX, ',');
	};

	//이전 지역 코드를 저장
	let prevCode = 0;

	var container = document.getElementById('map'); // 지도를 담을 영역의 DOM 레퍼런스
	var options = {
	  // 지도의 중심좌표.
	  center: new kakao.maps.LatLng(37.502, 127.026581),
	  level: 3 // 지도의 레벨(확대, 축소 정도)
	};

	var map = new kakao.maps.Map(container, options); // 지도 생성 및 객체 리턴
	var geocoder = new kakao.maps.services.Geocoder();

	// 현재 지도 위에 찍혀있는 마커를 저장하는 배열
	var markers = [];

	navigator.geolocation.getCurrentPosition((p) => {
	  let lat = p.coords.latitude;
	  let lng = p.coords.longitude;

	  map.setCenter(new kakao.maps.LatLng(lat, lng));
	});

	window.onload = function () {
	  getAddr(map.getCenter());
	};

	kakao.maps.event.addListener(map, 'tilesloaded', function () {
	  getAddr(map.getCenter());
	});

	// 중심 좌표 옮겼을 때 주소 정보 표출하는 함수
	function getAddr(coord) {
	  geocoder.coord2RegionCode(coord.getLng(), coord.getLat(), (e) => {
	    let code = e[0].code.substring(0, 5);
	    // if (prevCode != code) {
	    markers.map((m) => m.setMap(null));
	    // prevCode = code;
	    loadData(e[0].code);
	    // }
	  });
	}

	// 아파트 정보를 불러오는 함수
	function loadData(code) {
	  getApt(code);
	}

	// 아파트 정보를 토대로 맵에 마커를 찍는 함수
	async function createMarker(data) {
	  var content = document.createElement('div');
	  content.className = 'overlaybox';
	  content.setAttribute('onclick', 'showDetail(this)');

	  content.innerHTML = `
	        <input type="hidden" name="clickLat" value=${data.lat}>
	        <input type="hidden" name="clickLng" value=${data.lng}>
	        <input type="hidden" name="clickCode" value=${data.aptCode}>
	        <div class="price">\${changeMoney(data.dealAmount)}</div>
	        <div class="date">\${data.buildYear}</div>`;

	  // position은 아파트의 좌표를 가지고 맵 위에 위치 객체를 생성
	  var position = new kakao.maps.LatLng(data.lat, data.lng);

	  // 맵 위에 마커를 커스텀 이미지로 변경하기
	  var customOverlay = new kakao.maps.CustomOverlay({
	    position: position,
	    content: content,
	    xAnchor: 0.3,
	    yAnchor: 0.91
	  });

	  customOverlay.setMap(map);
	  markers.push(customOverlay);
	}

	// 오버레이 클릭 했을 때 왼쪽 박스에 디테일 보여주는 함수
	async function showDetail(e) {
	  let lat = e.children[0].value;

	  let lng = e.children[1].value;
	  let code = e.children[2].value;
	  await infoPrint(lat, lng, code);
	}

	function addEventHandle(target, type, callback) {
	  if (target.addEventListener) {
	    target.addEventListener(type, callback);
	  }
	}

	//------------------- 좌표로 주소 찾기 ------------------------------------------

	//주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();

	var marker = new kakao.maps.Marker(), // 클릭한 위치를 표시할 마커입니다
	  infowindow = new kakao.maps.InfoWindow({ zindex: 1 }); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다

	let curLat = 0;
	let curLon = 0;

	let starLoc = new Map();

	//아파트를 클릭했을때
	function infoPrint(lat, lon, code) {
	  curLat = lat;
	  curLon = lon;

	  searchDetailAddrFromCoords(lat, lon, function (result, status) {
	    if (status === kakao.maps.services.Status.OK) {
	      var addressDetail = !!result[0].road_address ? result[0].road_address.address_name : ' ';
	      var address = result[0].address.address_name;

	      document.getElementById('address').innerText = address;
	      document.getElementById('address-detail').innerHTML =
	        addressDetail != ' '
	          ? ` <span class="badge bg-secondary">도로명주소</span> ` + addressDetail
	          : '';
	      openInfo();

	      let table = document.getElementById('table-history');
	      table.innerHTML = `
	         <thead class="table-light"> 
	             <td>실거래가</td>
	             <td>거래일자</td>
	         </thead>`;

	      // 최근 거래가 초기화
	      document.getElementById('curCost').innerHTML = '';

	      fetch('${root}/house/dealinfo&aptNo=' + code)
	        .then((response) => response.json())
	        .then((data) => {
	          let infoList = data.data;
	          let table = document.getElementById('table-history');

	          for (let i = 0; i < infoList.length; i++) {
	            let element = infoList[i];
	            let item = `<tr>
		                        	<td>\${changeMoney(element.dealAmount)}</td>
		                            <td>\${element.dealYear}년 ${element.dealMonth}월 ${element.dealDay}일</td>
		                        </tr>
		                     `;
	            table.innerHTML += item;

	            if (document.getElementById('curCost').innerHTML == '') {
	              document.getElementById('curCost').innerHTML = changeMoney(element.dealAmount);
	            }
	          }
	        });

	      document.getElementById('btn-star').classList.remove('selected');
	      if (starLoc.has(address)) {
	        document.getElementById('btn-star').classList.add('selected');
	      }
	    }
	  });
	}

	function searchDetailAddrFromCoords(coordsLat, coordsLon, callback) {
	  // 좌표로 법정동 상세 주소 정보를 요청합니다
	  geocoder.coord2Address(coordsLon, coordsLat, callback);
	}

	function closeInfo() {
	  document.getElementById('information').style.display = 'none';
	}

	function openInfo() {
	  closeStar();
	  document.getElementById('information').style.display = 'block';
	}

	function openStar() {
	  closeInfo();
	  document.getElementById('starList').style.display = 'block';
	}

	function closeStar() {
	  document.getElementById('starList').style.display = 'none';
	}

	function changeStar() {
	  let starBtn = document.getElementById('btn-star');

	  if (starBtn.classList.length < 3) {
	    document.getElementById('btn-star').classList.add('selected');
	    starLoc.set(
	      document.getElementById('address').innerText,
	      new kakao.maps.LatLng(curLat, curLon)
	    );
	  } else {
	    if (starLoc.has(document.getElementById('address').innerText)) {
	      starLoc.delete(document.getElementById('address').innerText);
	    }
	    document.getElementById('btn-star').classList.remove('selected');
	  }
	  makeStarTable();
	}

	function makeStarTable() {
	  let i = 1;
	  let body = document.getElementById('starListBody');
	  body.innerHTML = '';
	  starLoc.forEach((value, key) => {
	    let item = ` 
	 <tr>
	   <th scope="row">i++</th>
	   <td onclick="changeMapUsingStar(this)">${key}</td>
	 </tr>`;
	    body.innerHTML += item;
	  });
	}

	function changeMapUsingStar(e) {
	  viewMap(e.innerHTML);
	}

	///////////////////////// select box 설정 (지역) /////////////////////////

	//브라우저가 열리면 시도정보 얻기.
	sendRequest('sido', '*00000000');

	//시도가 바뀌면 구군정보 얻기.
	document.querySelector('#sido').addEventListener('change', function () {
	  if (this[this.selectedIndex].value) {
	    let regcode = this[this.selectedIndex].value.substr(0, 2) + '*00000';
	    sendRequest('gugun', regcode);
	    initOption('dong');
	  } else {
	    initOption('gugun');
	    initOption('dong');
	  }
	});

	//구군이 바뀌면 동정보 얻기.
	document.querySelector('#gugun').addEventListener('change', function () {
	  if (this[this.selectedIndex].value) {
	    let regcode = this[this.selectedIndex].value.substr(0, 5) + '*';
	    sendRequest('dong', regcode);
	  } else {
	    initOption('dong');
	  }
	});

	//동이 바뀌면 지도 위치 변경하기.
	document.querySelector('#dong').addEventListener('change', function () {
	  if (this[this.selectedIndex].value) {
	    let sidoSeleted =
	      document.querySelector('#sido')[document.querySelector('#sido').selectedIndex].innerHTML;
	    let gugunSeleted =
	      document.querySelector('#gugun')[document.querySelector('#gugun').selectedIndex].innerHTML;
	    let address = `${sidoSeleted} ${gugunSeleted} \${this[this.selectedIndex].innerHTML}`;
	    viewMap(address);
	  } else {
	    initOption('dong');
	  }
	});

	function sendRequest(selid, regcode) {
		console.log("sendRequest가 호출되었습니다.");
	  const url = 'https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes';
	  let params = 'regcode_pattern=' + regcode + '&is_ignore_zero=true';
	  fetch(`\${url}?\${params}`)
	    .then((response) => response.json())
	    .then((data) => addOption(selid, data));
	}

	function addOption(selid, data) {
	  let opt = ``;
	  initOption(selid);
	  switch (selid) {
	    case 'sido':
	      opt += "<option>시도선택</option>";
	      data.regcodes.forEach(function (regcode) {
	        opt += `<option value="\${regcode.code}">\${regcode.name}</option>`;
	      });
	      break;
	    case 'gugun':
	      opt += `<option value="">구군선택</option>`;
	      for (let i = 0; i < data.regcodes.length; i++) {
	        if (i != data.regcodes.length - 1) {
	          if (
	            data.regcodes[i].name.split(' ')[1] == data.regcodes[i + 1].name.split(' ')[1] &&
	            data.regcodes[i].name.split(' ').length != data.regcodes[i + 1].name.split(' ').length
	          ) {
	            data.regcodes.splice(i, 1);
	            i--;
	          }
	        }
	      }
	      let name = '';
	      data.regcodes.forEach(function (regcode) {
	        if (regcode.name.split(' ').length == 2) name = regcode.name.split(' ')[1];
	        else name = regcode.name.split(' ')[1] + ' ' + regcode.name.split(' ')[2];
	        opt += `<option value="\${regcode.code}">\${name}</option>`;
	      });
	      break;
	    case 'dong':
	      opt += `<option value="">동선택</option>`;
	      let idx = 2;
	      data.regcodes.forEach(function (regcode) {
	        if (regcode.name.split(' ').length != 3) idx = 3;
	        opt += `<option value="\${regcode.code}">\${regcode.name.split(' ')[idx]}</option>`;
	      });
	  }
	  document.querySelector(`#\${selid}`).innerHTML = opt;
	}

	//선택지에 따라서 전체 옵션을 지운다 (default는 남기기)
	function initOption(selid) {
	  let options = document.querySelector(`#\${selid}`);
	  let len = options.length;
	  for (let i = len - 1; i > 0; i--) {
	    options.remove(i);
	  }
	}

	//주소지를 기준으로 맵을 이동
	function viewMap(address) {
	  if (marker != null && infowindow != null) {
	    marker.setMap(null);
	    infowindow.close();
	  }
	  // 주소-좌표 변환 객체를 생성합니다
	  var geocoder = new kakao.maps.services.Geocoder();

	  // 주소로 좌표를 검색합니다
	  geocoder.addressSearch(address, function (result, status) {
	    // 정상적으로 검색이 완료됐으면
	    if (status === kakao.maps.services.Status.OK) {
	      var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

	      // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
	      map.setCenter(coords);
	    }
	  });
	}

	function initTable() {
	  let tbody = document.querySelector('#aptlist');
	  let len = tbody.rows.length;
	  for (let i = len - 1; i >= 0; i--) {
	    tbody.deleteRow(i);
	  }
	}

</script>


</body>

</html>