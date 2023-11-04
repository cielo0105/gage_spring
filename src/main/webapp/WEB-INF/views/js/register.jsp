<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.servletContext.contextPath }" var="root"></c:set>
<script>
document.querySelector("#btn-register").addEventListener("click", async function () {
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
        
        /* // Hash the password using bcrypt
        const saltRounds = 10; // Number of salt rounds
        const userpwd = bcrypt.hash(srcpwd, saltRounds); */
        
        const response = await fetch("${root}/member/regist", {
      	  method: "post",
      	  body:"userId="+userid+"&userPass="+userpwd+"&userName="+username,
      	  headers: {
      		  'Content-Type': 'application/x-www-form-urlencoded'
      	  }
        })
        
        const json = await response.json();
        console.log(json);
        if(!json.success){
      	  alert("실패했습니다.");
        }else{ 
      	  location.href="/";
        }
      }
});
</script>