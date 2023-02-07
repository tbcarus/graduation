const userAjaxUrl = "ui/admin/users/";
// https://stackoverflow.com/a/5064235/548473
const ctx = {
    ajaxUrl: userAjaxUrl,
    updateTable: function () {
        $.ajax({
            type: "GET",
            url: userAjaxUrl,
        }).done(updateTableByData);
    }
}

function enable(chkbox, id) {
    var enabled = chkbox.is(":checked");
//  https://stackoverflow.com/a/22213543/548473
    $.ajax({
        url: userAjaxUrl + id,
        type: "POST",
        data: "enabled=" + enabled
    }).done(function () {
        chkbox.closest("tr").attr("data-user-enabled", enabled);
        successNoty(enabled ? "Enabled" : "Disabled");
    }).fail(function () {
        $(chkbox).prop("checked", !enabled);
    });
}

function updateRow(id) {
    form.find(":input").val("");
    $.get(ctx.ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[id='" + key + "']").val(value);
            if (key === "email") {
                form.find("input[id=email]").prop('readonly', true);
            }
            if (key === "enabled") {
                form.find("input[id=enabled]").prop("checked", value);
            }
            if (key === "roles") {
                form.find("input[id=userRole]").prop("checked", value.includes("USER"));
                form.find("input[id=adminRole]").prop("checked", value.includes("ADMIN"));
            }
        });
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
                    "data": "name"
                },
                {
                    "data": "email"
                },
                {
                    "data": "enabled"
                },
                {
                    "data": "registered"
                },
                {
                    "data": "roles"
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