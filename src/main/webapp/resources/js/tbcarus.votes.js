const voteAjaxUrl = "ui/admin/votes/";
// https://stackoverflow.com/a/5064235/548473
const ctx = {
    ajaxUrl: voteAjaxUrl,
    updateTable: function () {
        $.ajax({
            type: "GET",
            url: voteAjaxUrl,
        }).done(updateTableByData);
    }
}

function edit(restaurantId) {
    let modalWindow = $("#editRow");
    let element = document.getElementById(restaurantId);

    let id = element.getAttribute("data-id");

    document.getElementById("id").value = id;
    $('select').val(restaurantId);

    modalWindow.modal();
}

// $(document).ready(function () {
$(function () {
    makeEditable(
        $("#datatable").DataTable({
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": "date"
                },
                {
                    "data": "user.name"
                },
                {
                    "data": "restaurant.name"
                },
                {
                    "defaultContent": "Edit",
                    "orderable": false
                },
                {
                    "defaultContent": "Delete",
                    "orderable": false
                }
            ],
            "order": [
                [
                    0,
                    "asc"
                ]
            ]
        })
    );
});