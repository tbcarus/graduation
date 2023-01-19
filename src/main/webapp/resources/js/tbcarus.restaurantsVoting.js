const voteAjaxUrl = "ui/admin/votes/";
// https://stackoverflow.com/a/5064235/548473
const ctx = {
    ajaxUrl: voteAjaxUrl,
    updateTable: function () {
        $.ajax({
            type: "GET",
            url: voteAjaxUrl
        }).done(updateTableByData);
    }
}

function edit(restaurantId) {
    let modalWindow = $("#editRow");
    document.getElementById("id").value = restaurantId;
    modalWindow.modal();
}

function vote(restaurantId, voteId) {


    $.ajax({
        type: "POST",
        url: ctx.ajaxUrl,
        data: "id="+voteId+"&restaurantId="+restaurantId
    }).done(function () {
        ctx.updateTable();
    });

    if (window.location.href.split("?").length > 1) {
        let newPath = window.location.href.split("?")[0];
        window.location.replace(newPath);
    }

}

// $(document).ready(function () {
$(function () {
    makeEditable(
        $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "restaurant.name"
                },
                {
                    "defaultContent": "Can revote?",
                    "orderable": false
                },
                {
                    "data": "date"
                }
            ]
        })
    );
});