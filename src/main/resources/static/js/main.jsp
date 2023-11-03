<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script>
document.querySelector("#btn-login").addEventListener("click", async function () {
	if (!document.querySelector("#userid").value) {
        alert("아이디 입력!!");
        return;
      } else if (!document.querySelector("#userpwd").value) {
        alert("비밀번호 입력!!");
        return;
      } else {
    	  
        const userid = document.querySelector("#userid").value;
        const userpwd = document.querySelector("#userpwd").value;
        
/*         // Hash the password using bcrypt
        const saltRounds = 10; // Number of salt rounds
        const userpwd = await bcrypt.hash(srcpwd, saltRounds); */
        
        const response = await fetch("${root}/member?action=login", {
      	  method: "post",
      	  body:"userid="+userid+"&userpwd="+userpwd,
      	  headers: {
      		  'Content-Type': 'application/x-www-form-urlencoded'
      	  }
        })
        
        const json = await response.json();
        console.log(json);
        if(!json.result){
      	  alert(json.msg);
        }else{ 
      	  location.href="${root}/index.jsp";
        }
      }
});

document.querySelector("#btn-logout").addEventListener("click", async function () {
	const response = await fetch("${root}/member?action=logout", {
    	  method: "post",
      })
   	location.href="${root}/index.jsp";
});


function switchSessionBtn() {

}

</script>