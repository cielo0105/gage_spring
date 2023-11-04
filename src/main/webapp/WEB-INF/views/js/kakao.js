// 이전 지역 코드를 저장
let prevCode = 0;

var container = document.getElementById("map"); // 지도를 담을 영역의 DOM 레퍼런스
var options = {
		// 지도의 중심좌표.
		center: new kakao.maps.LatLng(37.502, 127.026581),
		level: 3, // 지도의 레벨(확대, 축소 정도)
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


window.onload = function(){
	getAddr(map.getCenter());
};

kakao.maps.event.addListener(map, "tilesloaded", function () {
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
	var content = document.createElement("div");
	content.className = 'overlaybox';
	content.setAttribute("onclick", "showDetail(this)");
	
	console.log("data", data.lat);
	content.innerHTML = `
        <input type="hidden" name="clickLat" value=${data.lat}>
        <input type="hidden" name="clickLng" value=${data.lng}>
        <input type="hidden" name="clickCode" value=${data.aptCode}>
        <div class="price">${changeMoney(data.dealAmount)}</div>
        <div class="date">${data.buildYear}</div>`;
	
  // position은 아파트의 좌표를 가지고 맵 위에 위치 객체를 생성
  var position = new kakao.maps.LatLng(data.lat, data.lng);
  
  // 맵 위에 마커를 커스텀 이미지로 변경하기
  var customOverlay = new kakao.maps.CustomOverlay({
    position: position,
    content: content,
    xAnchor: 0.3,
    yAnchor: 0.91,
  });
  
  customOverlay.setMap(map);
  markers.push(customOverlay);
}

// 오버레이 클릭 했을 때 왼쪽 박스에 디테일 보여주는 함수
async function showDetail(e) {
	console.log("hihihihihi");
  let lat = e.children[0].value;
  let lng = e.children[1].value;
  let code = e.children[2].value;
  
  console.log("showDetail=", lat, lng, code);
  await infoPrint(lat, lng, code);
}

function addEventHandle(target, type, callback) {
    if (target.addEventListener) {
        target.addEventListener(type, callback);
    }
}
