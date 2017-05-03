$(function () {
    $("#table1").hpaging({ "limit": 2 });
});

$("#btnApply").click(function () {
    var lmt = $("#pglmt").val();
    $("#table1").hpaging("newLimit", lmt);
});