function loginUser() {
  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;

  fetch("http://localhost:8081/member/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({ email, password })
  })
  .then(res => {
    if ( res.status!=200 ) throw new Error("登入失敗，請檢查帳號密碼");
    return res.json();
  })
  .then(data => {
    localStorage.setItem("accessToken", data.token);
    localStorage.setItem("username",data.user);
    document.getElementById("alertBox").innerHTML = `
      <div class="alert alert-success" role="alert">
        ✅ 登入成功！歡迎 ${data.user}
      </div>
    `;
    document.getElementById("result").innerHTML = `
      <p><strong>用戶帳號：</strong> ${data.user}</p>
      <p><strong>JWT Token：</strong> ${data.token}</p>
    `;
    setTimeout(function(){
    location.href="home.html";
    },2000)

  })
  .catch(err => {
    document.getElementById("alertBox").innerHTML = `
      <div class="alert alert-danger" role="alert">
        ❌ ${err.message}
      </div>
    `;
    document.getElementById("result").innerHTML = "";
  });
}