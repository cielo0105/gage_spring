// ------------------- 좌표로 주소 찾기 ------------------------------------------

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new kakao.maps.services.Geocoder();

var marker = new kakao.maps.Marker(), // 클릭한 위치를 표시할 마커입니다
  infowindow = new kakao.maps.InfoWindow({ zindex: 1 }); // 클릭한 위치에 대한 주소를 표시할 인포윈도우입니다

let curLat = 0;
let curLon = 0;

let starLoc = new Map();

// 아파트를 클릭했을때
function infoPrint(lat, lon, code) {
	console.log(lat, lon, code);
  curLat = lat;
  curLon = lon;

  searchDetailAddrFromCoords(lat, lon, function (result, status) {
    if (status === kakao.maps.services.Status.OK) {
      var addressDetail = !!result[0].road_address
        ? result[0].road_address.address_name
        : " ";
      var address = result[0].address.address_name;

      document.getElementById("address").innerText = address;
      document.getElementById("address-detail").innerHTML =
        addressDetail != " "
          ? ` <span class="badge bg-secondary">도로명주소</span> ` +
            addressDetail
          : "";
      openInfo();

      let table = document.getElementById("table-history");
      table.innerHTML = `
            <thead class="table-light"> 
                <td>실거래가</td>
                <td>거래일자</td>
            </thead>`;

      // 최근 거래가 초기화
      document.getElementById("curCost").innerHTML = "";

      fetch("/house/dealinfo?aptNo="+code)
		.then(response => response.json())
		.then((data) => {
			let infoList = data.data;
			let table = document.getElementById("table-history");
			
			for(let i = 0; i < infoList.length; i++){
				let element = infoList[i];
	            let item = `<tr>
	                        	<td>${changeMoney(element.dealAmount)}</td>
	                            <td>${element.dealYear}년 ${element.dealMonth}월 ${element.dealDay}일</td>
	                        </tr>
	                     `;
	            table.innerHTML += item;

	            if (document.getElementById("curCost").innerHTML == "") {
	              document.getElementById("curCost").innerHTML = changeMoney(
	                element.dealAmount
	              );
	            }
			}
	  });
      
      document.getElementById("btn-star").classList.remove("selected");
      if(starLoc.has(address)){
        document.getElementById("btn-star").classList.add("selected");
      }
    }
  });
}

function searchDetailAddrFromCoords(coordsLat, coordsLon, callback) {
  // 좌표로 법정동 상세 주소 정보를 요청합니다
  geocoder.coord2Address(coordsLon, coordsLat, callback);
}

function closeInfo() {
  document.getElementById("information").style.display = "none";
}

function openInfo() {
  closeStar();
  document.getElementById("information").style.display = "block";
}

function openStar() {
  closeInfo();
  document.getElementById("starList").style.display = "block";
}


function closeStar(){
  document.getElementById("starList").style.display = "none";
}

function changeStar() {
  let starBtn = document.getElementById("btn-star");

  if (starBtn.classList.length < 3) {
    document.getElementById("btn-star").classList.add("selected");
    starLoc.set(document.getElementById("address").innerText, new kakao.maps.LatLng(curLat, curLon));
  } else {
    if (starLoc.has(document.getElementById("address").innerText)) {
      starLoc.delete(document.getElementById("address").innerText);
    }
    document.getElementById("btn-star").classList.remove("selected");
  }
  makeStarTable();
}

function makeStarTable(){
  let i = 1;
  let body = document.getElementById("starListBody");
  body.innerHTML = "";
  starLoc.forEach((value, key) => {
    let item = ` 
    <tr>
      <th scope="row">${i++}</th>
      <td onclick="changeMapUsingStar(this)">${key}</td>
    </tr>`;
    body.innerHTML += item;
  })
}

function changeMapUsingStar(e){
  viewMap(e.innerHTML);
}

///////////////////////// select box 설정 (지역) /////////////////////////

// 브라우저가 열리면 시도정보 얻기.
sendRequest("sido", "*00000000");

// 시도가 바뀌면 구군정보 얻기.
document.querySelector("#sido").addEventListener("change", function () {
  if (this[this.selectedIndex].value) {
    let regcode = this[this.selectedIndex].value.substr(0, 2) + "*00000";
    sendRequest("gugun", regcode);
    initOption("dong");
  } else {
    initOption("gugun");
    initOption("dong");
  }

});

// 구군이 바뀌면 동정보 얻기.
document.querySelector("#gugun").addEventListener("change", function () {
  if (this[this.selectedIndex].value) {
    let regcode = this[this.selectedIndex].value.substr(0, 5) + "*";
    sendRequest("dong", regcode);
  } else {
    initOption("dong");
  }
});

// 동이 바뀌면 지도 위치 변경하기.
document.querySelector("#dong").addEventListener("change", function () {
  if (this[this.selectedIndex].value) {
    let sidoSeleted = document.querySelector("#sido")[document.querySelector("#sido").selectedIndex].innerHTML;
    let gugunSeleted = document.querySelector("#gugun")[document.querySelector("#gugun").selectedIndex].innerHTML;
    let address = `${sidoSeleted} ${gugunSeleted} ${this[this.selectedIndex].innerHTML}`;
    viewMap(address);

  } else {
    initOption("dong");
  }
});



function sendRequest(selid, regcode) {
  const url = "https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes";
  let params = "regcode_pattern=" + regcode + "&is_ignore_zero=true";
  fetch(`${url}?${params}`)
    .then((response) => response.json())
    .then((data) => addOption(selid, data));
}

function addOption(selid, data) {
  let opt = ``;
  initOption(selid);
  switch (selid) {
    case "sido":
      opt += `<option value="">시도선택</option>`;
      data.regcodes.forEach(function (regcode) {
        opt += `<option value="${regcode.code}">${regcode.name}</option>`;
      });
      break;
    case "gugun":
      opt += `<option value="">구군선택</option>`;
      for (let i = 0; i < data.regcodes.length; i++) {
        if (i != data.regcodes.length - 1) {
          if (
            data.regcodes[i].name.split(" ")[1] == data.regcodes[i + 1].name.split(" ")[1] &&
            data.regcodes[i].name.split(" ").length !=
            data.regcodes[i + 1].name.split(" ").length
          ) {
            data.regcodes.splice(i, 1);
            i--;
          }
        }
      }
      let name = "";
      data.regcodes.forEach(function (regcode) {
        if (regcode.name.split(" ").length == 2) name = regcode.name.split(" ")[1];
        else name = regcode.name.split(" ")[1] + " " + regcode.name.split(" ")[2];
        opt += `<option value="${regcode.code}">${name}</option>`;
      });
      break;
    case "dong":
      opt += `<option value="">동선택</option>`;
      let idx = 2;
      data.regcodes.forEach(function (regcode) {
        if (regcode.name.split(" ").length != 3) idx = 3;
        opt += `<option value="${regcode.code}">${regcode.name.split(" ")[idx]}</option>`;
      });
  }
  document.querySelector(`#${selid}`).innerHTML = opt;
}

// 선택지에 따라서 전체 옵션을 지운다 (default는 남기기)
function initOption(selid) {
  let options = document.querySelector(`#${selid}`);
  let len = options.length;
  for (let i = len - 1; i > 0; i--) {
    options.remove(i);
  }
}

// 주소지를 기준으로 맵을 이동
function viewMap( address) {
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
  let tbody = document.querySelector("#aptlist");
  let len = tbody.rows.length;
  for (let i = len - 1; i >= 0; i--) {
    tbody.deleteRow(i);
  }
}