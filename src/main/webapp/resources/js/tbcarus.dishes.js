const dishAjaxUrl = "ui/admin/dishes/";
// https://stackoverflow.com/a/5064235/548473
const ctx = {
    ajaxUrl: dishAjaxUrl,
    updateTable: function () {
        $.ajax({
            type: "GET",
            url: dishAjaxUrl+"?restaurantId="+document.getElementById("restaurantId").value
        }).done(updateTableByData);
    }
}

function edit(dishId) {
    let modalWindow = $("#editRow");
    let element = document.getElementById(dishId);

    let restaurantId = element.getAttribute("data-restaurantId");
    let name = element.getAttribute("data-name");
    let price = element.getAttribute("data-price");
    let date = element.getAttribute("data-date");

    document.getElementById("id").value = dishId;
    document.getElementById("name").value = name;
    document.getElementById("price").value = price;
    document.getElementById("date").value = date;
    document.getElementById("date").setAttribute('readonly', true);
    $('select').val(restaurantId);
    document.getElementById("restaurantId").setAttribute('style', "pointer-events: none;");

    modalWindow.modal();
}

function updateRow(id, restaurantId) {
    form.find(":input").val("");
    $.get(ctx.ajaxUrl + id, function (dish) {
        $.each(dish, function (key, value) {
            form.find("input[id='" + key + "']").val(value);
            if(key === "inputDate"){
                // form.find("input[id=inputDate]").prop('readonly', true);
            }
        });
        // $("#inputDate").prop("readonly", true);
        form.find("input[id=inputDate]").prop('readonly', true);
        form.find("select[id=restaurantId]").prop('readonly', true);
        $("#restaurantId").val(restaurantId);
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
                    "data": "price"
                },
                {
                    "data": "inputDate"
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