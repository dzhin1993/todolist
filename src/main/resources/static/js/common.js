let ajaxUrl = "ajax/todoList/";
let context;
let isCompleted;

function makeEditable(ctx) {
    context = ctx;

    // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
    $.ajaxSetup({cache: false});
}

$(function () {
    makeEditable({
            datatableApi: $("#todoTable").DataTable({
                "ajax": {
                    "url": ajaxUrl,
                    "dataSrc": ""
                },
                "info": false,
                "columns": [
                    {
                        "data": "name"
                    },
                    {
                        "data": "dateTime",
                        "render": renderDateTime
                    },
                    {
                        "data": "completed",
                        "render": renderCheckBox
                    },
                    {
                        "render": renderDeleteBtn,
                        "defaultContent": "",
                        "orderable": false
                    },
                    {
                        "render": renderEditBtn,
                        "defaultContent": "",
                        "orderable": false
                    }
                ],
                "order": [
                    [
                        0,
                        "asc"
                    ]
                ],
            }),
            updateTable: updateFiltered
        }
    );
});

function updateFiltered() {
    if (isCompleted == null) {
        $.get(ajaxUrl, updateTableByData);
    } else {
        $.ajax({
            type: "GET",
            url: ajaxUrl + "by-completed",
            data: {completed: isCompleted}
        }).done(updateTableByData);
    }
}

function complete(chkbox, id) {
    let enabled = chkbox.is(":checked");
    $.ajax({
        type: 'POST',
        url: ajaxUrl + 'complete',
        data: {id: id, completed: enabled}
    }).done(function () {
        context.updateTable();
    });
}

function save() {
    $.ajax({
        type: 'POST',
        url: ajaxUrl,
        data: $('#detailsForm').serialize()
    }).done(function () {
        context.updateTable();
        $('#modal').modal('toggle');
    });
}

function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: "DELETE"
    }).done(function () {
        context.updateTable();
    });
}

function updateRow(id) {
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            $('#detailsForm').find("input[name='" + key + "']").val(value);
        });
        $('#modal').modal();
    });
}

function add() {
    $("#modal").modal();
}

function updateTableByData(data) {
    context.datatableApi.clear().rows.add(data).draw();
}

function filter(filter) {
    if (filter === 'All') {
        isCompleted = null;
    } else isCompleted = filter !== "Active";
    context.updateTable();
}

function renderEditBtn(data, type, row) {
    if (type === "display") {
        return "<button class='btn btn-primary' onclick='updateRow(" + row.id + ");'>Update</button>";
    }
}

function renderDeleteBtn(data, type, row) {
    if (type === "display") {
        return "<button class='btn btn-danger' onclick='deleteRow(" + row.id + ");'>Delete</button>";
    }
}

function renderCheckBox(data, type, row) {
    if (type === "display") {
        return "<input type='checkbox' " + (data ? "checked" : "") + " onclick='complete($(this)," + row.id + ");'/>";
    }
    return data;
}

function renderDateTime(date, type) {
    if (type === 'display') {
        return date.replace('T', ' ').substr(0, 16);
    }
    return date;
}