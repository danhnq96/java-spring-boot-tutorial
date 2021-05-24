"use strict";

(function ($) {
    $(function () {
    });

    $(window).load(function () {
        var $_body = $("body"),
            $_window = $(this),
            currentScrollTop = 0;

        function init() {
            disableClick();
            getRateElement();
        }

        init();

        function isIe() {
            var pattern = /Trident\/[0-9]+\.[0-9]+/;

            return pattern.test(navigator.userAgent);
        }

        function isEdge() {
            var pattern = /Edge\/[0-9]+\.[0-9]+/;

            return pattern.test(navigator.userAgent);
        }

        function disableClick() {
            $(".noclick").on("click", function () {
                return false;
            });
        }

        function scrollToPosition(pos, second) {
            $("html, body").animate({
                scrollTop: pos
            }, second * 1000);
        }

        // Resize Height
        function setRateElement(objects, x, y) {
            var w = x || 5;
            var h = y || 3;
            // objects.height(objects.width() * h / w);
            objects.each(function () {
                $(this).height($(this).outerWidth() * h / w);
            });
        }

        function getRateElement() {
            setRateElement($(".imgResize-9-8"), 9, 8);
            setRateElement($(".imgResize-16-9"), 16, 9);
        }

        // Upload file
        $(".custom-file-input").on("change", function () {
            var fileName = $(this).val().split("\\").pop();
            $(this).siblings(".custom-file-label").addClass("selected").html(fileName);
        });

        // Check validate form Search
        $("#formSearch").validate({
            errorPlacement: function errorPlacement(error, element) {
                $("#searchEmpty").modal("show");
            }
        });

        // const ps = new PerfectScrollbar(".results");
        $(".formSearchProduct").each(function () {
            var ps = new PerfectScrollbar(".results", {
                wheelPropagation: true,
                minScrollbarLength: 20
            });
        });

        // Check validate form user list password
        $("#formUserEdit,#formUserAdd").validate({
            rules: {
                password: {
                    required: true,
                    minlength: 8,
                    maxlength: 15
                },
                confirmpassword: {
                    equalTo: "#password"
                }
            },
            messages: {
                password: {
                    required: "텍스트를 입력해주세요.",
                    minlength: "8~15 자리의 문자, 숫자, 특수문자를 포함해 주세요.",
                    maxlength: "8~15 자리의 문자, 숫자, 특수문자를 포함해 주세요."
                }
            }
        });

        // Check validate form confirm_password
        $(".formUserEdit,.formUserAdd").each(function () {
            $("#password,#confirm_password").on("keyup", function () {
                var passW = $("#password").val().length;
                if (passW >= 8) {
                    $('button[type="submit"]').click(function () {
                        if ($("#password").val() == $("#confirm_password").val()) {
                        } else {
                            $("#popupNotSamePW").modal("show");
                        }
                    });
                }
            });
        });

        // Check validate form resgiter FAQ
        $("#resFaq").validate({
            rules: {
                theme: {
                    required: true
                },
                detail: {
                    required: true
                }
            },
            errorPlacement: function errorPlacement(error, element) {
                $("#formEmpty").modal("show");
            }
        });
        // Check validate form resgiter registration
        $("#request-registration").validate({
            rules: {
                theme: {
                    required: true
                },
                content: {
                    required: true
                }
            },
            errorPlacement: function errorPlacement(error, element) {
                $("#checkEmpty").modal("show");
            }
        });

        // Check validate form resgiter Event
        $("#resEvent").validate({
            rules: {
                theme: {
                    required: true
                },
                dateStart: {
                    required: true
                },
                dateEnd: {
                    required: true
                },
                detail: {
                    required: true
                }
            }
        });

        $("#resEvent").submit(function () {
            var eventTitle = $("#event_Title").val().length,
                eventDstart = $("#date-start").val().length,
                eventContent = $("#input_content").val().length;

            if ($('#date-end').length) {
                var eventDend = $("#date-end").val().length;
            }

            if (eventTitle == 0) {
                $("#formEmpty").modal("show");
            } else if (eventDstart == 0) {
                $("#datepickerEmpty").modal("show");
            } else if (eventDend == 0) {
                $("#datepickerEmpty").modal("show");
            } else if (eventContent == 0) {
                $("#formEmpty").modal("show");
            }
        });

        if ($('#unlimited-check').length) {
            var $_get_enddate = document.getElementById("unlimited-check");
            var $html_enddate = $_get_enddate.outerHTML;

            $("#dateCheck").on("change", function () {
                if (this.checked) {
                    $('#unlimited-check').html('');
                } else {
                    $('#unlimited-check').html($html_enddate);
                }
            });
        }

        // Datetimepicker with starting from any date
        $("input[id^='startDate']").each(function () {
            var _thisStart = this.id;

            $('#' + _thisStart).datetimepicker({
                format: "YYYY-MM-DD",
                useCurrent: false
            });

            // $('#'+_this).on("dp.change", function (e) {
            // 	$("#endDate")
            // 		.data("DateTimePicker")
            // 		.minDate(e.date);
            // });

            // $("#startDate").on("dp.change", function (e) {
            // 	$("#endDate")
            // 		.data("DateTimePicker")
            // 		.minDate(e.date);
            // });
        });

        $("input[id^='endDate']").each(function () {
            var _thisEnd = this.id;

            $('#' + _thisEnd).datetimepicker({
                format: "YYYY-MM-DD",
                useCurrent: false
            });
        });

        // Datetimepicker with starting from the current date
        $("#date-start").datetimepicker({
            format: "YYYY-MM-DD",
            useCurrent: false,
            minDate: moment()
        });

        $("#date-end").datetimepicker({
            format: "YYYY-MM-DD",
            useCurrent: false,
            minDate: moment()
        });

        $("#date-start").on("dp.change", function (e) {
            var incrementDay = moment(new Date(e.date));
            incrementDay.add(1, "days");
            $("#date-end").data("DateTimePicker").minDate(incrementDay);
            $(this).data("DateTimePicker").hide();
        });

        $("#date-end").datetimepicker().on("dp.change", function (e) {
            var decrementDay = moment(new Date(e.date));
            decrementDay.subtract(1, "days");
            $("#date-start").data("DateTimePicker").maxDate(decrementDay);
            $(this).data("DateTimePicker").hide();
        });

        // Detail Setting
        var detail_stt = $(".detail-edit .detail-setting"),
            detail_thumb = $(".detail-edit .thumbnail"),
            detail_thumb_btn = $(".detail-edit .thumbnail button"),
            detail_thumb_cls = $(".detail-edit .thumbnail .btn-close"),
            detail_ct = $(".detail-edit .detail-ct"),
            detail_btnback = $(".detail-edit .post-detail__btn-back"),
            detail_btn = $(".detail-edit .post-detail__confirm");

        detail_thumb_btn.hide();
        detail_thumb_cls.hide();
        detail_ct.attr("disabled", true);
        detail_btn.hide();
        $(".detail-setting").on("change", function () {
            var val = $(this).val();
            if (val == "del") {
                $("#deleteEmpty").modal("show");
            } else if (val == "edit") {
                $("#editEmpty").modal("show");
            } else {
                $("#repEmpty").modal("show");
            }
        });

        $("#editEmpty .btn-confirm").on("click", function () {
            detail_stt.attr("disabled", true);
            detail_thumb.addClass("enable-edit");
            detail_thumb_btn.show();
            detail_thumb_cls.show();
            detail_ct.attr("disabled", false);
            detail_btnback.hide();
            detail_btn.show();
        });

        $(window).resize(function () {
            getRateElement();
        });
    });
})(jQuery);
//# sourceMappingURL=main.js.map
