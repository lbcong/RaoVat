var email;
var pass1;
var name;
var phone;
var pass2;
function validateContentName() {
    var name = $("#FormRegister\\:nonNullInputname").val();
    if (name.length < 8) {
        $("#fieldInputValidationnameo").text("! Tên Phải ít Nhất 8 Kí tự");
        $("#fieldInputValidationnamec").text("");
        return false;
    } else {

        $("#fieldInputValidationnamec").text("! Đúng");
        $("#fieldInputValidationnameo").text("");
        return true;
    }
}

function validateContentPhone() {
    phone = $("#FormRegister\\:nonNullInputPhone").val();
    if (phone.length < 10 || phone.length >= 11) {
        $("#fieldInputValidationphoneo").text("! Nhập sai sdt mời nhập lại");
        $("#fieldInputValidationphonec").text("");
        return false;
    } else {
        $("#fieldInputValidationphonec").text("! Đúng");
        $("#fieldInputValidationphoneo").text("");
        return true;
    }
}



function checkRegister()
{

    if (((validateContentName()) && (validateContentPhone())) && (validateEmail2()) && (validateContent2()) && (validateContentrepass()))
    {

        $("#fieldInputValidationp").text("");
        return true;
    } else
    {

        $("#fieldInputValidationp").text("Đăng Kí Không Thành Công!");
        return false;
    }
}




function validateEmail2() {
    var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
    email = $("#FormRegister\\:emailInput").val();
    if ((emailReg.test(email)) && (email.length > 1)) {
        $("#emailInputValidationc").text("! Email chinh xac!");
        $("#emailInputValidationo").text("");
        return true;
    } else {
        $("#emailInputValidationo").text("! Email không hợp lệ!");
        $("#emailInputValidationc").text(" ");
        return false;
    }
}


function validateContent2() {
    pass1 = $("#FormRegister\\:nonNullInput").val();
    if (pass1.length < 3) {
        $("#fieldInputValidationo").text("! Mật Khẩu Phải ít Nhất 8 Kí tự");
        $("#fieldInputValidationc").text(" ");
        return false;
    } else {
        $("#fieldInputValidationc").text("! Đúng");
        $("#fieldInputValidationo").text(" ");
        return true;
    }
}
function validateContentrepass()
{
    var pass1 = $("#FormRegister\\:nonNullInput").val();
    pass2 = $("#FormRegister\\:nonNullInputrepass").val();
    if (pass1 === pass)
    {
        $("#fieldInputValidationrepass").text("đúng");
        return true;
    } else {
        $("#fieldInputValidationrepass").text("! Mật Khẩu Không Khớp ");
        return false;
    }
}

      