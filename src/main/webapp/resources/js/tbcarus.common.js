let form;

function makeEditable(datatableApi) {
    ctx.datatableApi = datatableApi;
    form = $('#detailsForm');

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(jqXHR);
    });

    // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
    $.ajaxSetup({cache: false});
}

function add() {
    form.find(":input").val("");
    form.find('input[type=text], [type=number], [type=email], [type=date]').each(function () {
        this.value = "";
        this.removeAttribute('readonly');
    });
    form.find(":input:checkbox").each(function () {
        this.checked = false;
    });
    // form.find("select").each(function () {
    //     this.setAttribute('style', "pointer-events: auto;");
    // });
    form.find("select").prop('disabled', false);

    let now = new Date();
    let day = ("0" + now.getDate()).slice(-2);
    let month = ("0" + (now.getMonth() + 1)).slice(-2);
    let dateStr = now.getFullYear() + "-" + month + "-" + day;
    form.find('input[type=date]').val(dateStr);

    $("#editRow").modal();
}

// function updateRow(id) {
//     form.find(":input").val("");
//     $.get(ctx.ajaxUrl + id, function (data) {
//         $.each(data, function (key, value) {
//             form.find("input[id='" + key + "']").val(value);
//             if (key === "roles") {
//                 form.find("input[id=userRole]").prop("checked", value.includes("USER"));
//                 form.find("input[id=adminRole]").prop("checked", value.includes("ADMIN"));
//             }
//             if (key === "restaurant") {
//                 form.find("select[name=restaurantId]").prop("value", value.id);
//             }
//             if (key === "inputDate") {
//                 form.find("input[id=inputDate]").prop('readonly', true);
//             }
//         });
//         $('#editRow').modal();
//     });
// }

function deleteRow(id) {
    if (confirm('Are you sure?')) {
        $.ajax({
            url: ctx.ajaxUrl + id,
            type: "DELETE"
        }).done(function () {
            ctx.updateTable();
            successNoty("Deleted");
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
        successNoty("Saved");
    });
}

let failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(text) {
    closeNoty();
    new Noty({
        text: "<span class='fa fa-lg fa-check'></span> &nbsp;" + text,
        type: 'success',
        layout: "bottomRight",
        timeout: 2000
    }).show();
}

function failNoty(jqXHR) {
    closeNoty();
    failedNote = new Noty({
        text: "<span class='fa fa-lg fa-exclamation-circle'></span> &nbsp;Error status: " + jqXHR.status,
        type: "error",
        layout: "bottomRight"
    });
    failedNote.show()
}