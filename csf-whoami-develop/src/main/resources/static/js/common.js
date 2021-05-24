"use strict";

(function ($) {
    $(function () {
        $(document).ready(function ($) {
            $("tbody .table-row").click(function () {
                var $_datahref = $(this).data("href");
                if (typeof $_datahref !== "undefined") {
                    window.document.location = $(this).data("href");
                }
            });
        });
    });
})(jQuery);


$.ajaxSetup({
    headers: {
        'Authorization': localStorage.getItem("jwt")
    }
});
//# sourceMappingURL=common.js.map
