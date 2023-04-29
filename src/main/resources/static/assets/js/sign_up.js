
//给log out函数连接方法
const logoutButton = document.getElementById("Logout");
  logoutButton.onclick = function () {
    window.location.href = "/appointment-system/logout";
  };

  const user = document.getElementById('user').value;
  var buttonprofile = document.getElementById("Profile");
  var buttonappoint = document.getElementById("Appointment");
  if(user=="") {
    document.getElementById("Login").style.display = "block";
    document.getElementById("Logout").style.display = "none";
    buttonprofile.onclick= start;
    buttonappoint.onclick= start;

  }
  else {
    document.getElementById("Login").style.display = "none";
    document.getElementById("Logout").style.display = "block";
    buttonprofile.onclick= Profile;
    buttonappoint.onclick = Appoint;
  }

function start() {
      document.getElementById('signform').style.display=""
    }

function Profile(){
  window.location.href="/appointment-system/profile";
}

function Appoint(){
  window.location.href="/appointment-system/appoint";
}

window.onload = function() {
    const message = document.getElementById("message").innerText;
    const error = document.getElementById("error").innerText;
    if (message) {
        alert(message);
        window.location.href = "/appointment-system";
    } else if (error) {
        alert(error);
        window.location.href = "/appointment-system";
    }
}







var span = document.getElementsByClassName("close_sign")[0];
span.onclick = function () {
  document.getElementById('signform').style.display="none"
  document.getElementById('signupform').style.display="none"
}
var span1 = document.getElementsByClassName("close_sign")[1];
span1.onclick = function () {
  document.getElementById('signform').style.display="none"
  document.getElementById('signupform').style.display="none"
}
function start() {
  document.getElementById('signform').style.display=""
}

function loading() {
  document.getElementById('registerloading').style.display=""
}

function signre() {
  if(document.getElementById('signform').style.display=="" && document.getElementById('signupform').style.display=="none"){
    document.getElementById('signform').style.display="none"
    document.getElementById('signupform').style.display=""
  }
}

function resign() {
  if(document.getElementById('signupform').style.display=="" && document.getElementById('signform').style.display=="none"){
    document.getElementById('signform').style.display=""
    document.getElementById('signupform').style.display="none"
  }
}


// sign up 的 constraints
//获取页面实体（输入框、表单）---------------------------------------
const form = document.getElementById("Registerform");
const formLog = document.getElementById("signform");
const uname = document.getElementById("uname");
const upwd = document.getElementById("upwd");
const username = document.getElementById("username");
const email = document.getElementById("email");
const passowrd = document.getElementById("password");
const password2 = document.getElementById("password2");
const phone = document.getElementById("phone");


username.addEventListener("blur",checkUsername)
email.addEventListener("blur",checkEmail)
passowrd.addEventListener("blur",checkPassword)
password2.addEventListener("blur",checkPassword2)
phone.addEventListener("blur",checkPhone)

function checkPhone(){
  const phoneValue = phone.value.trim();
  const cPhone = /^1[3-9]\d{9}$/;
  //手机验证
  if (phoneValue==="") {
    setErrorFor(phone, "Phone number can not be empty");
  } 
  else if(!cPhone.test(phoneValue))
  {
    setErrorFor(phone, "Invalid mobile phone");
  } 
  else{
    setSuccessFor(phone);
  }
}

function checkUsername() {
        const usernameValue = username.value.trim();
        // 用户名验证
        if (usernameValue === "") {
            setErrorFor(username, "Username can not be empty");
        } else {
            // 检查用户名是否唯一
            fetch(`/appointment-system/check-unique?value=${usernameValue}`)
                .then(response => {
                    if (response.status === 200) {
                        setSuccessFor(username);
                    } else if (response.status === 409) {
                        setErrorFor(username, "Username already exists");
                    } else {
                        setErrorFor(username, "Error checking username");
                    }
                })
                .catch(error => {
                    console.error('Error fetching data:', error);
                });
        }
    }


function checkEmail() {
        const emailValue = email.value.trim();

        // 邮箱验证
        if (emailValue === "") {
            setErrorFor(email, "Email can not be empty");
        } else if (!valiEmail(emailValue)) {
            setErrorFor(email, "Email format is incorrect, please re-enter");
        } else {
            // 检查邮箱是否唯一
            fetch(`/appointment-system/check-uniqueem?value=${emailValue}`)
                .then(response => {
                    if (response.status === 200) {
                        setSuccessFor(email);
                    } else if (response.status === 409) {
                        setErrorFor(email, "Email already exists");
                    } else {
                        setErrorFor(email, "Error checking email");
                    }
                })
                .catch(error => {
                    console.error('Error fetching data:', error);
                });
        }
    }


function checkPassword(){
  const passwordValue = passowrd.value.trim();
  const cPassword = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$/;
  //密码验证
  if(passwordValue===""){
    setErrorFor(password,"Password can not be empty");}

  else if(!cPassword.test(passwordValue)){
    setErrorFor(passowrd,"Password must contain letters and numbers, between 6-18 characters");
  }
  else{
    setSuccessFor(passowrd)
  }
}
function checkPassword2(){
  const passwordValue = passowrd.value.trim();
  const password2Value = password2.value.trim();
  //重复密码验证
  if(password2Value===""){
    setErrorFor(password2,"Password can not be empty");
  }else if(passwordValue!==password2Value){
    setErrorFor(password2,"The passwords entered twice are inconsistent, please re-enter")
  }else{
    setSuccessFor(password2)
  }
}



//form表单提交事件-----------------------------------
form.addEventListener('submit', (e) => {
// 如果 checkInputs 返回 false（即输入不符合要求），阻止表单提交
const checkvalue=checkInputs();
if (checkvalue!=0) {
e.preventDefault();
}
})

//Log form 表单提交事件
formLog.addEventListener('submit', (e) => {
// 如果 checkInputs 返回 false（即输入不符合要求），阻止表单提交
const checkvalue=checkInputslog();
if (checkvalue!=0) {
e.preventDefault();
}
})

function checkInputslog(){
  let test=0;

  const usernameValue = uname.value.trim();
  const passwordValue = upwd .value.trim();
  if (usernameValue === "") {
            setErrorFor(uname, "Username can not be empty");
            test++;
        } 
   //密码验证
   if(passwordValue===""){
    setErrorFor(upwd,"Password can not be empty");
    test++;

}
return test;
}

//总验证方法-------------------------------------------
function checkInputs() {
  //获取输入框实体的输入值
  let test=0;
  const usernameValue = username.value.trim();
  const emailValue = email.value.trim();
  const passwordValue = passowrd.value.trim();
  const password2Value = password2.value.trim();
  const phoneValue = phone.value.trim();
  const cPassword = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$/;
  const cPhone = /^1[3-9]\d{9}$/;
  //用户名验证
  if (usernameValue === "") {
            setErrorFor(username, "Username can not be empty");
            test++;
        } 
        else {
            // 检查用户名是否唯一
            fetch(`/appointment-system/check-unique?value=${usernameValue}`)
                .then(response => {
                    if (response.status === 200) {
                        setSuccessFor(username);
                    } else if (response.status === 409) {
                        setErrorFor(username, "Username already exists");
                        test++;
                    } else {
                        setErrorFor(username, "Error checking username");
                        test++;
                    }
                })
                .catch(error => {
                    console.error('Error fetching data:', error);
                    test++;
                });
        }

  if (emailValue === "") {
            setErrorFor(email, "Email can not be empty");
            test++;
        } else if (!valiEmail(emailValue)) {
            setErrorFor(email, "Email format is incorrect, please re-enter");
            test++;
        } else {
            // 检查邮箱是否唯一
            fetch(`/appointment-system/check-uniqueem?value=${emailValue}`)
                .then(response => {
                    if (response.status === 200) {
                        setSuccessFor(email);
                    } else if (response.status === 409) {
                        setErrorFor(email, "Email already exists");
                        test++;
                    } else {
                        setErrorFor(email, "Error checking email");
                        test++;
                    }
                })
                .catch(error => {
                    console.error('Error fetching data:', error);
                    test++;
                });
        }
  //密码验证
  if(passwordValue===""){
    setErrorFor(password,"Password can not be empty");
    test++;
  }else if(!cPassword.test(passwordValue)){
    setErrorFor(passowrd,"Password must contain letters and numbers, between 6-18 characters");
    test++;

  }
  else{
    setSuccessFor(passowrd)
  }
  //手机验证
  if (phoneValue==="") {
    setErrorFor(phone, "Phone number can not be empty");
  } 
  else if(!cPhone.test(phoneValue))
  {
    setErrorFor(phone, "Phone number must be 11 digits");
  } 
  else{
    setSuccessFor(phone);
  }

  //重复密码验证
  if(password2Value===""){
    setErrorFor(password2,"Password can not be empty");
    test++;
  
  }
    
  else if(passwordValue!==password2Value){
    setErrorFor(password2,"The passwords entered twice are inconsistent, please re-enter")
    test++;
  }else{
    setSuccessFor(password2)
  }
  return test;
  
}

//简单封装验证成功和失败的方法-------------------------------
//验证原理：输入框的父组件上添加成功或失败的样式，并且将验证错误信息动态添加到<small>标签中

//验证失败
function setErrorFor(input, message) {
  //input即为组件名（在这里因只有输入框所以写成input），message是错误信息（在总验证方法中传回错误信息message）
  const formControl = input.parentElement;//所验证实体（输入框）的父组件
  const small = formControl.querySelector('small');
  small.textContent = message;
  //此处须注意细节，如果只添加错误样式，输入错误信息后再次输入正确的信息，两个样式相互重叠，造成偏差，
  //所以之前应先去除已出现的样式，没明白的话，可以将添加success的代码注释掉，看看效果
  formControl.classList.remove('success');
  formControl.classList.add('error');
}
//验证成功
function setSuccessFor(input) {
  const formControl = input.parentElement;
  //同理
  formControl.classList.remove('error');
  formControl.classList.add('success');
}


//邮箱验证特殊情况：邮箱格式不正确----------------------------
function valiEmail(email){
  const regEmail = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
  //将传过来的email值与定义的邮箱范围相比较
  return regEmail.test(email);//返回true或false
}