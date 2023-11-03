let geocoder = new kakao.maps.services.Geocoder();

let lat = 0,
  lng = 0,
  code = "";

navigator.geolocation.getCurrentPosition((p) => {
  lat = p.coords.latitude;
  lng = p.coords.longitude;
});

window.onload = () => {
  initAnalysis();
};

function initAnalysis() {
  console.log(lat, lng);
  if (lat && lng) {
    geocoder.coord2RegionCode(lng, lat, (e) => {
      code = e[0].code.substring(0, 5);
      getNationAnalysis(code);
    });
  } else {
    getNationAnalysis("11680");
  }
}

function getNationAnalysis(code) {
  fetch("house?action=nationAnalysis&code=" + code)
    .then((response) => response.json())
    .then((data) => {
      const guList = data.infoList;
      let html = "";
      for (let i = 0; i < 3; i++) {
        html += `
<li
	class="list-group-item d-flex justify-content-between align-items-center py-3">
    <div class="ms-2 me-auto">
	<div class="fw-bold">
        	${guList[i].sidoName} ${guList[i].gugunName}
    </div>
    </div>
    <h5 class="m-0">
        	<span class="badge bg-secondary rounded-pill">
        		${changeMoney(guList[i].dealAmountAvg)}
        	</span>
    </h5>
</li>
`;
      }
      document.querySelector("#nation-top3").innerHTML = html;
    });
}

function getLocalAnalysis(code){
	  fetch("house?action=localAnalysis&code=" + code)
	  .then((response) => response.json())
	  .then((data) => {
	    const guList = data.monthlyInfoList;
	    console.log("guList==", guList);
	    let html="";
	    
	    html += `
<table class="table table-hover align-middle">
	    	<thead>
	    		<tr>
	    			<th class="col-3" scope="col">년</th>
					<th class="col-2" scope="col">월</th>
					<th class="col-7" scope="col">금액</th>
	    		</tr>
			</thead>
			<tbody>			
`;
	    
	    for(let i=0; i<guList.length; i++){
	    	
	    	html += `
<tr>
	    		<td>${guList[i].dealYear}</td>
	    		<td>${guList[i].dealMonth}</td>
				<th scope="row">${changeMoney(guList[i].dealAmountAvg)}</th>
</tr>
`;
	    }
	    
	    html += `
			</tbody>
</table>	
`;
	    console.log(html);
	    document.querySelector("#monthly-table").innerHTML = html;
	  });
}


// 시도 선택 드롭박스
sendRequest("sido", "*00000000");

document.querySelector("#sido").addEventListener("change", function() {changeSido(this[this.selectedIndex].value)});

function changeSido(sidoCode){
	if (sidoCode) {
	    let regcode = sidoCode.substr(0, 2) + "*00000";
	    sendRequest("gugun", regcode);
	} else {
	    initOption("gugun");
	}
}

	// 구군이 바뀌면 fetch 하기
	document.querySelector("#gugun").addEventListener("change", function () {
	  if (this[this.selectedIndex].value) {
		    let code = document.querySelector("#gugun").value.substr(0,5);
		    document.querySelector("#monthly-table").innerHTML = `
		  	  <div class="d-flex align-items-center">
		  						<strong role="status">불러오는 중입니다...</strong>
		  						<div class="spinner-border ms-auto" aria-hidden="true"></div>
		  	</div>`;
		    getLocalAnalysis(code);
	  } else {
	    initOption("gugun");
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