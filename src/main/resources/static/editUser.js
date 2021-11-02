function editUser() {

    let form = window.formEditUser.editRoles;
    let new_Roles = "";

    let rolesList = document.createElement('ul');

    for (var i = 0; i < form.length; i++) {
        var option = form.options[i];
        let role = document.createElement('li');
        if (option.selected) {
            new_Roles = new_Roles.concat(option.value + (i != (form.length - 1) ? "," : ""));

            role.textContent = option.value + " ";
            rolesList.appendChild(role);
        }
    }

    // let row_num = 0;
    // var phrase = window.formEditUser.editID.value;
    // var table = document.getElementById("tBody");
    // var regPhrase = new RegExp(phrase, 'i');
    // for (var i = 0; i < table.rows.length; i++) {
    //     if (regPhrase.test(table.rows[i].cells[0].innerHTML)) {
    //         row_num = i;
    //         break;
    //     }
    // }

    let id = window.formEditUser.editID.value;

    fetch('http://localhost:8080/update', {
        method: 'PUT',
        body: JSON.stringify({
            id: window.formEditUser.editID.value,
            name: window.formEditUser.editName.value,
            lastName: window.formEditUser.editLastName.value,
            age: window.formEditUser.editAge.value,
            email: window.formEditUser.editEmail.value,
            password: window.formEditUser.editPassword.value,
            roles: new_Roles
        }),
        headers: {"Content-type": "application/json; charset=UTF-8"}
    })
        .then(response => {
//            showAllUsers();
//            $("#tBody tr:" + id + "td:eq(0)").html(cell1);
//              $(this).parent().siblings("td:first").
            $('#' + id).replaceWith('<tr id=' + id + '>' +
                '<td>' + id + '</td>' +
                '<td>' + window.formEditUser.editName.value + '</td>' +
                '<td>' + window.formEditUser.editLastName.value + '</td>' +
                '<td>' + window.formEditUser.editAge.value + '</td>' +
                '<td>' + window.formEditUser.editEmail.value + '</td>' +
                '<td>' + rolesList.textContent + '</td>' +
                '<td> <button type="button" onclick="getModalEdit(' + id + ')" class="btn btn-primary btn-sm">Edit</button> </td>' +
                '<td> <button type="button" onclick="getModalDelete(' + id + ')" class="btn btn-danger btn-sm">Delete</button> </td>' +
                '</tr>');


            // let row = document.getElementById("tBody").rows[row_num];
            // row.cells[0].innerHTML = window.formEditUser.editID.value;
            // row.cells[1].innerHTML = window.formEditUser.editName.value;
            // row.cells[2].innerHTML = window.formEditUser.editLastName.value;
            // row.cells[3].innerHTML = window.formEditUser.editAge.value;
            // row.cells[4].innerHTML = window.formEditUser.editEmail.value;
            // row.cells[5].innerHTML = rolesList.textContent;
        });
}