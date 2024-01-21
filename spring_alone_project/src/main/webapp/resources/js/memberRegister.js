console.log("member register in~~!!");

let checkEmailSuccess = 0;
let checkPwdSuccess = 0;

document.getElementById('checkEmail').addEventListener('click' , ()=>{
    let email = document.getElementById('e');
    let checkEmail = email.value;
    console.log(checkEmail);
    let emailCheckTextBox = document.getElementById('emailCheckText');
    if(checkEmail == null || checkEmail == ''){
        emailCheckTextBox.style.color = 'red';
        emailCheckTextBox.innerText = "이메일을 입력해주세요.";
        checkEmailSuccess = 0;
        checkSuccess();
    }
    checkEmailToServer(checkEmail).then(result => {
        if(result == '1'){
            emailCheckTextBox.style.color = 'red';
            emailCheckTextBox.innerText = "중복된 이메일입니다.";
            checkEmailSuccess = 0;
            checkSuccess();
        }else{
            emailCheckTextBox.style.color = 'green';
            emailCheckTextBox.innerText = "등록 가능한 이메일입니다.";
            checkEmailSuccess = 1;
            checkSuccess();
        }
    })
})

document.getElementById('p').addEventListener('keyup', ()=>{
    checkPwd();
    checkSuccess();
})

document.getElementById('p2').addEventListener('keyup', ()=>{
    checkPwd();
    checkSuccess();
})

function checkPwd(){
    let originalPwd = document.getElementById('p').value;
    let checkPwd = document.getElementById('p2').value;

    let pwdCheckTextBox = document.getElementById('pwdCheckText');
    if(originalPwd != checkPwd){
        pwdCheckTextBox.style.color = 'red';
        pwdCheckTextBox.innerText = "비밀번호가 다릅니다.";
        checkPwdSuccess = 0;
    }else{
        pwdCheckTextBox.style.color = 'green';
        pwdCheckTextBox.innerText = "비밀번호가 같습니다.";
        checkPwdSuccess = 1;
        
    }
}

async function checkEmailToServer(email){
    try {
        const resp = await fetch("/member/checkEmail/" + email);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

function checkSuccess(){
    let signUp = document.getElementById('signUp');
    if(checkEmailSuccess == 1 && checkPwdSuccess == 1){
        signUp.disabled = false;
    }else{
        signUp.disabled = true;
    }
}