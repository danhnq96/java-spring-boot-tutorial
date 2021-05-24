$(document).ready(function () {

    var key = getCookie("key");
    $("#name").val(key);

    if ($("#name").val() != "") {
        $("#idSaveCheck").attr("checked", true);
    }

    $("#idSaveCheck").change(function () {
        if ($("#idSaveCheck").is(":checked")) {
            setCookie("key", $("#name").val(), 7);
        } else {
            deleteCookie("key");
        }
    });


    $("#name").keyup(function () {
        if ($("#idSaveCheck").is(":checked")) {
            setCookie("key", $("#name").val(), 7);
        }
    });
});

function setCookie(cookieName, value, exdays) {
    var exdate = new Date();
    exdate.setDate(exdate.getDate() + exdays);
    var cookieValue = escape(value) + ((exdays == null) ? "" : "; expires=" + exdate.toGMTString());
    document.cookie = cookieName + "=" + cookieValue;
}

function deleteCookie(cookieName) {
    var expireDate = new Date();
    expireDate.setDate(expireDate.getDate() - 1);
    document.cookie = cookieName + "= " + "; expires=" + expireDate.toGMTString();
}


function getCookie(cookieName) {
    cookieName = cookieName + '=';
    var cookieData = document.cookie;
    var start = cookieData.indexOf(cookieName);
    var cookieValue = '';
    if (start != -1) {
        start += cookieName.length;
        var end = cookieData.indexOf(';', start);
        if (end == -1) end = cookieData.length;
        cookieValue = cookieData.substring(start, end);
    }
    return unescape(cookieValue);
}

function actionLoginMember() {
    if ($("#name").val() == "") {
        $("#idEmpty").modal('show');
        return false;
    } else if ($("#password").val() == "") {
        $("#passwordEmpty").modal('show');
        return false;
    } else {
        var url = "/adminLogin";
        var user = {
            "name": document.loginForm.name.value,
            "password": document.loginForm.password.value,
        };
        $.ajax({
            type: "POST",
            url: url,
            contentType: 'application/json',
            async: false,
            data: JSON.stringify(user),
            success: function (result) {
                localStorage.setItem("jwt", result);
                location.href = "/admin/home";
            },
            error: function (xhr) {
                console.log("Login failed :" + xhr.responseText);
                $("#loginError").modal('show');
            }
        });
    }
}

function enterLogin() {
    if (event.keyCode == 13) {
        actionLoginMember();
    }
}