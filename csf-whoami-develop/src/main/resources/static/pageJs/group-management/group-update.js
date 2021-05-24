$("#deploy-management").addClass("open");
$("#deploy").addClass("active");

function deployUpdate(id) {
    var url = "/admin/deploy/update";

    var param = {
        "id": id,
        "tbOsId": $("input[name=os-link]:checked").val(),
        "type": $("input[name=deploy-link]:checked").val(),
        "version": $("#version").val(),
        "link": $("#link").val(),
        "content": $("#content").val(),
        "tbAdminId": $("#tbAdminId").val(),
        "versionUpdatedAt": $("input[name=versionUpdatedAt]").val(),
    };

    if ($("input[name=os-link]:checked").val() == null || $("input[name=os-link]:checked").val() == "") {
        modal.openOneModalMessage("OS를 확인해주세요.");
        return false;
    }

    if ($("input[name=deploy-link]:checked").val() == null || $("input[name=deploy-link]:checked").val() == "") {
        modal.openOneModalMessage("구분을 확인해주세요.");
        return false;
    }

    if ($("#version").val() == null || $("#version").val() == "") {
        modal.openOneModalMessage("버전을 확인해주세요.");
        return false;
    }

    if ($("#link").val() == null || $("#link").val() == "") {
        modal.openOneModalMessage("앱 다운 링크를 확인해주세요.");
        return false;
    }

    if ($("#content").val() == null || $("#content").val() == "") {
        modal.openOneModalMessage("이슈를 확인해주세요.");
        return false;
    }

    if ($("input[name=versionUpdatedAt]").val() == null || $("input[name=versionUpdatedAt]").val() == "") {
        modal.openOneModalMessage("업데이를 날짜를 확인해주세요.");
        return false;
    }


    $.ajax({
        type: "POST",
        url: url,
        contentType: 'application/json;',
        async: false,
        data: JSON.stringify(param),
        success: function (result) {
            modal.openOneModalMessage("정상적으로 수정 되었습니다.");
            $("#oneBtn").click(function () {
                window.location.href = "/admin/deploy/detail/" + id;
            });
        },
        error: function (xhr) {
            console.log("Login failed :" + xhr.responseText);
            modal.openOneModalMessage("ERROR");
        }
    });

}