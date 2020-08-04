let ajaxUrl = "ajax/todoList/";
let context;

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
                        "data": "dateTime"
                    },
                    {
                        "data": "completed",
                        "render": function (data, type, row) {
                            if (type === "display") {
                                return "<input type='checkbox' " + (data ? "checked" : "") + " onclick='complete($(this)," + row.id + ");'/>";
                            }
                            return data;
                        }
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
            updateTable: function () {
                $.get(ajaxUrl, updateTableByData);
            }
        }
    );
});

function complete(chkbox, id) {
    let enabled = chkbox.is(":checked");
    $.ajax({
        type: 'POST',
        url: ajaxUrl + 'complete',
        data: {id: id, completed: enabled}
    }).done(function () {
        chkbox.closest("tr").attr("data-todoCompleted", enabled);
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