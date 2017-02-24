var email;
var email1;
function check()
{

    if ((email) && (email1))
    {

        $("#fieldInputValidationp").text("");
        return true;
    } else
    {

        $("#fieldInputValidationp").text("Đăng Nhập Không Thành Công!");
        return false;
    }
}

function check1()
{

    if ((email) && (email1))
    {
        return true;
    } else
    {
        return false;
    }
}


function validateEmail() {
    var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
    email = $("#testForm\\:emailInput").val();
    if ((emailReg.test(email)) && (email.length > 1)) {
        $("#emailInputValidationc").text("! Email chinh xac!");
        $("#emailInputValidationo").text("");
        email = true;
    } else {
        $("#emailInputValidationo").text("! Email không hợp lệ!");
        $("#emailInputValidationc").text(" ");
        email = false;
    }
}


function validateContent() {
    email1 = $("#testForm\\:nonNullInput").val();
    if (email1.length < 8) {
        $("#fieldInputValidationo").text("! Mật Khẩu Phải ít Nhất 8 Kí tự");
        $("#fieldInputValidationc").text(" ");
        email1 = false;
    } else {
        $("#fieldInputValidationc").text("! Correct");
        $("#fieldInputValidationo").text(" ");
        email1 = true;
    }
}