let form;

function makeEditable(datatableApi) {
    ctx.datatableApi = datatableApi;
    form = $('#detailsForm');
}

function add() {
    form.find(":input").each(function () {
        this.value = "";
        this.removeAttribute('disabled');
    });
    form.find(":input:checkbox").each(function () {
        this.checked = false;
    });
    $("#editRow").modal();
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
    document.getElementById("email").setAttribute('disabled', 'disabled');
    document.getElementById("password").value = password;
    document.getElementById("userRole").checked = roles.includes("USER");
    document.getElementById("adminRole").checked = roles.includes("ADMIN");

    modalWindow.modal();
}

function deleteRow(id) {
    if (confirm('Are you sure?')) {
        $.ajax({
            url: ctx.ajaxUrl + id,
            type: "DELETE"
        }).done(function () {
            ctx.updateTable();
        });
    }
}

function updateTableByData(data) {
    ctx.datatableApi.clear().rows.add(data).draw();
}

function save() {
    $.ajax({
        type: "POST",
        url: ctx.ajaxUrl,
        data: form.serialize()
    }).done(function () {
        $("#editRow").modal("hide");
        ctx.updateTable();
    });
}