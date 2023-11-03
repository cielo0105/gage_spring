<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script>
	let markerList = new Map();
	
	// 아파트매매 실거래 상세 자료
	async function getApt(code) {
		let infoList = [];
		
		await fetch("${root}/house/aptinfo?code="+code)
			.then(response => response.json())
			.then(async (data) => {
				console.log(data);
				infoList = data.data;

				for(let i = 0; i < infoList.length; i++){					
					createMarker(infoList[i]);
				}
			});
		}
	
	
	function makeApt(data) {
	  let parser = new DOMParser();
	  const xml = parser.parseFromString(data, "application/xml");
	  let apts = xml.querySelectorAll("item");
	  let list = [];
	
	  apts.forEach((apt) => {
	    let aptInfo = {};
	    aptInfo["amount"] = apt.querySelector("거래금액").textContent.trim(); // 거래금액
	    aptInfo["buildYear"] = apt.querySelector("건축년도").textContent; // 건축년도
	    aptInfo["roadAddress"] =
	      apt.querySelector("중개사소재지").textContent +
	      " " +
	      apt.querySelector("도로명").textContent; // 도로명주소
	    aptInfo["aptName"] = apt.querySelector("아파트").textContent; // 아파트
	    aptInfo["address"] =
	      apt.querySelector("중개사소재지").textContent +
	      apt.querySelector("법정동").textContent +
	      " " +
	      apt.querySelector("지번").textContent; // 법정동
	    aptInfo["transactionDate"] =
	      apt.querySelector("년").textContent +
	      "." + // 거래 년
	      apt.querySelector("월").textContent +
	      "." + // 거래 월
	      apt.querySelector("일").textContent; // 거래 일
	    aptInfo["area"] = apt.querySelector("전용면적").textContent; // 전용면적
	    aptInfo["floor"] = apt.querySelector("층").textContent; // 층수
	    list.push(aptInfo);
	  });
	  return list;
	}

</script>