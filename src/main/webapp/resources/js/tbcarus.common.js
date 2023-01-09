let form;

function makeEditable(datatableApi) {
    ctx.datatableApi = datatableApi;
    form = $('#detailsForm');
}

function add() {
    form.find('input[type=text], [type=number]').each(function () {
        this.value = "";
        this.removeAttribute('readonly');
    });
    form.find(":input:checkbox").each(function () {
        this.checked = false;
    });
    form.find("select").each(function () {
        this.setAttribute('style', "pointer-events: auto;");
    });

    let now = new Date();
    let day = ("0" + now.getDate()).slice(-2);
    let month = ("0" + (now.getMonth() + 1)).slice(-2);
    let dateStr = now.getFullYear() + "-" + month + "-" + day;
    form.find('input[type=date]').val(dateStr);

    $("#editRow").modal();
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