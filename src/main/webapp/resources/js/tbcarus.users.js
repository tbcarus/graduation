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

function edit(email) {
    let modalWindow = $("#editRow");
    let element = document.getElementById(email);

    let id = element.getAttribute("data-id");
    let name = element.getAttribute("data-name");
    let password = element.getAttribute("data-password");
    let roles = element.getAttribute("data-roles");

    document.getElementById("id").value = id;
    document.getElementById("name").value = name;
    document.getElementById("email").value = email;
    document.getElementById("email").setAttribute('readonly', true);
    document.getElementById("password").value = password;
    document.getElementById("userRole").checked = roles.includes("USER");
    document.getElementById("adminRole").checked = roles.includes("ADMIN");

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