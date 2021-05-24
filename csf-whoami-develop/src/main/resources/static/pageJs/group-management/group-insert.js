function insertAppVersion() {
    var url = "/admin/group-mng/register";

    var param = {
        "group_name": $("#group-name").val(),
        "group_url": $("#group-prefix").val() + $("#group-url").val(),
        "is_private": $("input[name=private-link]:checked").val(),
        "is_publish": $("input[name=publish-link]:checked").val(),
        "group_description": $("#group-description").val()
    };

    /*
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
        modal.openOneModalMessage("Please check the update date.");
        return false;
    }
    */

    $.ajax({
        type: "POST",
        url: url,
        contentType: 'application/json;',
        async: false,
        data: JSON.stringify(param),
        success: function (data) {
            console.log("data: " + data);
            modal.openOneModalMessage("It has been successfully registered.");
            $("#oneBtn").click(function () {
                window.location.href = "list";
            });
        },
        error: function (xhr) {
            console.log("Login failed :" + xhr.responseText);
            modal.openOneModalMessage("ERROR");
        }
    });
}