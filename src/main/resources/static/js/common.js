function complete(id, completed) {
    $.ajax({
        type: 'POST',
        url: '/todoList/complete',
        data: { id: id, completed : completed}
    }).done(function () {
        location.reload();
    });
}

function add() {
    $("#modalLoginForm").modal();
}