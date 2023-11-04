<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<div class="container row content align-items-center">
	<div class="mb-3">
		<h1 class="fw-bold border-bottom pb-3">비밀번호 찾기</h1>
	</div>

	<div class="row text-end fs-6 fw-light">
		<p>
			<b class="fw-bold">01. 아이디 입력</b>> 02. 비밀번호 재설정
		</p>
	</div>

	<div class="w-50 mx-auto">
		<div class="my-3">
			<label for="id" class="form-label">비밀번호를 찾고자하는 아이디를 입력해주세요.</label> <input
				class="form-control" id="id" />
		</div>
	</div>

	<div class="mt-4 mb-3 text-center">
		<button type="submit" class="btn btn-secondary btn-lg py-2 px-5" id="next">다음</button>
	</div>
</div>
</body>
<script>
	document.querySelector("#next").addEventListener("click", async() => {
		let id = document.querySelector("#id").value;
		
		const response = await fetch("/member/findpw1", {
	      	  method: "post",
	      	  body:"userId="+id,
	      	  headers: {
	      		  'Content-Type': 'application/x-www-form-urlencoded'
	      	  }
	        })
	        
	        const json = await response.json();
	        console.log(json);
	        if(!json.success){
	      	  alert("로그인에 실패했습니다. 다시 시도해주세요.");
	        }else{ 
	      	  location.href="/findpw3";
	        }
	})
</script>
</html>