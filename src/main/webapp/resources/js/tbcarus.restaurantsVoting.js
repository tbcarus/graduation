const voteAjaxUrl = "ui/rest/votes/";
// https://stackoverflow.com/a/5064235/548473
const ctx = {
    ajaxUrl: voteAjaxUrl,
    updateTable: function () {
        $.ajax({
            type: "GET",
            url: voteAjaxUrl + "today/"
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
        data: "id=" + voteId + "&restaurantId=" + restaurantId
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
            "ajax": {
                "url": voteAjaxUrl + "today/",
                "dataSrc": ""
            },
            "paging": false,
            "info": true,
            "columns": [
                {
                    "data": function (data, type, row) {
                        if (type === "display") {
                            return data.id === 0 ? "Не выбрано" : data.restaurant.name;
                        }
                        return data;
                    }
                },
                {
                    "defaultContent": "Can revote?",
                    "orderable": false,
                    "render": function (data, type, row) {
                        if (type === "display") {
                            return row.canRevote;
                        }
                        return data;
                    }
                },
                {
                    "data": "date"
                }
            ]
        })
    );
});