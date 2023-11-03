window.onload = () => {
};

async function login() {
	if (!document.querySelector("#userid").value) {
        alert("아이디 입력!!");
        return;
      } else if (!document.querySelector("#userpwd").value) {
        alert("비밀번호 입력!!");
        return;
      } else {
/*
 * let form = document.querySelector("#form-login");
 * 
 * form.setAttribute("action", "${root }/user?action=login"); form.submit();
 */
        
        const userid = document.querySelector("#userid").value;
        const userpwd = document.querySelector("#userpwd").value;
        
        const response = await fetch("member?action=login", {
      	  method: "post",
      	  body:"userid="+userid+"&userpwd="+userpwd,
      	  headers: {
      		  'Content-Type': 'application/x-www-form-urlencoded'
      	  }
        })
        
        const json = await response.json();
        console.log(json);
        if(!json.result){
      	  alert(json.msg)
        }else{
        	console.log("${root}/");
      	  location.href="index.jsp"
        }
      }
}

async function logout() {
	const response = await fetch("member?action=logout", {
    	  method: "post",
    	  headers: {
    		  'Content-Type': 'application/x-www-form-urlencoded'
    	  }
      })
      
      const json = await response.json();
      console.log(json);
      if(!json.result){
    	  alert(json.msg)
      }else{
      	console.log("${root}/");
    	  location.href="index.jsp"
      }
    }
}

function switchSessionBtn() {

}
