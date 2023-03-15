let form;

function makeEditable(datatableApi) {
    ctx.datatableApi = datatableApi;
    form = $('#detailsForm');

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(jqXHR);
    });

    // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
    $.ajaxSetup({cache: false});

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
}

function add() {
    $("#modalTitle").html(i18n["addTitle"]);
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

function successNoty(key) {
    closeNoty();
    new Noty({
        text: "<span class='fa fa-lg fa-check'></span> &nbsp;" + i18n[key],
        type: 'success',
        layout: "bottomRight",
        timeout: 2000
    }).show();
}

function renderEditBtn(data, type, row) {
    if (type === "display") {
        return "<a style='cursor: pointer' onclick='updateRow(" + row.id + ")'><span class='fa fa-pencil'></span>" + i18n["common.update"] + " modal</a>" +
            "<br>" +
            "<a style='cursor: pointer' href='" + window.location.pathname + "/update?id=" + row.id + "'><span class='fa fa-pencil'>" + i18n["common.update"] + " JSP</a>";
    }
}

function renderDeleteBtn(data, type, row) {
    if (type === "display") {
        return "<a style='cursor: pointer' onclick='deleteRow(" + row.id + ")'><span class='fa fa-remove'></span>" + i18n["common.delete"] + " modal</a>" +
            "<br>" +
            "<a style='cursor: pointer' href='" + window.location.pathname + "/delete?id=" + row.id + "'><span class='fa fa-remove'></span>" + i18n["common.delete"] + " JSP</a>";
    }
}

function failNoty(jqXHR) {
    closeNoty();
    var errorInfo = jqXHR.responseJSON;
    failedNote = new Noty({
        text: "<span class='fa fa-lg fa-exclamation-circle'></span> &nbsp;" + i18n["common.errorStatus"] + ": " + jqXHR.status +
            "<br>" + errorInfo.type + "<br>" + errorInfo.detail,
        type: "error",
        layout: "bottomRight"
    });
    failedNote.show()
}