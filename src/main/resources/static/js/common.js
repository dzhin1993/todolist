var context;

function makeEditable(ctx) {
    context = ctx;

    // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
    $.ajaxSetup({cache: false});
}

$(function () {
    makeEditable({
            ajaxUrl: "/todoList",
            datatableApi: $("#todoTable").DataTable({
                "ajax": {
                    "url": "/todoList/all",
                    "dataSrc": ""
                },
                "paging": false,
                "info": true,
                "columns": [
                    {
                        "data": "name"
                    },
                    {
                        "data": "dateTime",
                    },
                    {
                        "data": "completed"
                    },
                    {
                        "orderable": false,
                        "defaultContent": "Delete",
                    },
                    {
                        "orderable": false,
                        "defaultContent": "Update",
                    }
                ],
                "order": [
                    [
                        0,
                        "asc"
                    ]
                ],
            }),
        }
    );
});

function complete(id, completed) {
    $.ajax({
        type: 'POST',
        url: '/todoList/complete',
        data: {id: id, completed: completed}
    }).done(function () {
        location.reload();
    });
}

function save() {
    $.ajax({
        type: 'POST',
        url: '/todoList',
        data: $('#detailsForm').serialize()
    }).done(function () {
        location.reload();
    });
}

function update(id) {
    $.get('/todoList/' + id, function (data) {
        $.each(data, function (key, value) {
            $('#detailsForm').find("input[name='" + key + "']").val(value);
        });
        $('#modal').modal();
    });
}

function deleteRow(id) {
        $.ajax({
            url: '/todoList/' + id,
            type: "DELETE"
        }).done(function () {
            location.reload();
        });
}

function add() {
    $("#modal").modal();
}