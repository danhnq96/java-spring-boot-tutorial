$(".submit_btn").on("click", function () {
    var pageNum = $(this).attr("value");
    $("#page").val(pageNum);
    var f = $("#formBtn");
    f.submit();
});