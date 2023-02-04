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

function updateRow(restaurantId) {
    form.find(":input").val("");
    $.get(ctx.ajaxUrl + restaurantId, function (vote) {
        form.find("input[id=id]").val(vote.id);
        form.find("select[id=restaurantId]").prop("value", vote.restaurant.id);
        $('#editRow').modal();
    });
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