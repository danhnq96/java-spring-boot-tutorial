var modal = {
    openOneModalMessage: function (content) {
        $('#modal_message_content').html(content);
        $('#modal_message').modal('show');
    },
    closeOneModalMessage: function () {
        $('#modal_message').modal('hide');
    },

    openTwoModalMessage: function (content) {
        $('#modal_message_content2').html(content);
        $('#modal_message2').modal('show');
    },
    closeTwoModalMessage: function () {
        $('#modal_message2').modal('hide');
    },

    validateField: function (field, value) {
        if (value == "" || value == null || value == undefined || (value != null && typeof value == "object" && !Object.keys(value).length)) {
            $('#modal_message_content').html(field + '을(를) 확인해 주세요');
            $('#modal_message').modal('show');
        } else {
            return false
        }
    }
};