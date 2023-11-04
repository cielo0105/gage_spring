<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:set value="${pageContext.servletContext.contextPath }" var="root"></c:set>
<script>
document.querySelector("#btn-update").addEventListener("click", async function () {
	if (!document.querySelector("#id").value) {
        alert("아이디 입력!!");
        return;
      } else if (!document.querySelector("#password").value) {
        alert("비밀번호 입력!!");
        return;
      } else if (!document.querySelector("#checkpassword").value) {
          alert("비밀번호 확인 입력!!");
          return;
      } else if (!document.querySelector("#name").value) {
          alert("이름 입력!!");
          return;
      } else {
    	  
        const userid = document.querySelector("#id").value;
        const userpwd = document.querySelector("#password").value;
        const username = document.querySelector("#name").value;
        
        const data = {
        	    userId: userid,
        	    userPass: userpwd,
        	    userName: username
        };
        
        const response = await fetch("member/edit", {
      	  method: "put",
      	  //body:"userId="+userid+"&userPass="+userpwd+"&userName="+username,
      	  body: JSON.stringify(data),
      	  headers: {
      		  'Content-Type': 'application/json'
      	  }
        })
        
        const json = await response.json();
        console.log(json);
        
      	 location.href="/info";
        
      }
});

document.querySelector("#btn-delete").addEventListener("click", async function () {
	if (!document.querySelector("#id").value) {
        alert("아이디 입력!!");
        return;
      } else if (!document.querySelector("#password").value) {
        alert("비밀번호 입력!!");
        return;
      } else if (!document.querySelector("#checkpassword").value) {
          alert("비밀번호 확인 입력!!");
          return;
      } else if (!document.querySelector("#name").value) {
          alert("이름 입력!!");
          return;
      } else {
    	  
        const userid = document.querySelector("#id").value;
        
        const response = await fetch("/member/"+userid, {
      	  method: "delete",
      	 // body:"userid="+userid,
      	  headers: {
      		 // 'Content-Type': 'application/x-www-form-urlencoded'
      		  'Content-Type': 'application/json'
      	  }
        })
        
        const json = await response.json();
        console.log(json);
        
      	  location.href="/";
        
      }
});
</script>