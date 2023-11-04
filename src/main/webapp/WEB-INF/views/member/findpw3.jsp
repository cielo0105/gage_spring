<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp"%>

<div class="container row content align-items-center">
	<div class="mb-3">
		<h1 class="fw-bold border-bottom pb-3">비밀번호 찾기</h1>
	</div>

	<div class="row text-end fs-6 fw-light">
		<p>
			01. 아이디 입력 ><b class="fw-bold">02. 비밀번호 재설정</b>
		</p>
	</div>

	<div class="w-50 mx-auto">
		<div class="mb-3">
			<label for="password" class="form-label">새로운 비밀번호</label> <input
				type="password" id="password" class="form-control"
				aria-describedby="passwordHelpBlock" />
		</div>

		<div class="mb-3">
			<label for="checkpassword" class="form-label">비밀번호 확인</label> <input
				type="password" id="checkpassword" class="form-control"
				aria-describedby="passwordHelpBlock" />
		</div>
	</div>

	<div class="mt-4 mb-3 text-center">
		<button type="button" class="btn btn-secondary btn-lg py-2 px-5"
			id="set">재설정</button>
	</div>
</div>

</body>
<script type="text/javascript">

document.querySelector("#set").addEventListener("click", async()=>{
	let password = document.querySelector("#password").value;
	
	const response = await fetch("/member/findpw3", {
    	  method: "post",
    	  body:"userPass="+password,
    	  headers: {
    		  'Content-Type': 'application/x-www-form-urlencoded'
    	  }
      })
      
      const json = await response.json();
      console.log(json);
    location.href="/";
})
</script>
</html>