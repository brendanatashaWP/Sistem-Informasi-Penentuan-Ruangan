$(document).ready(function(){
    var ambil;
    //post user baru
    $("#coba").click(function(){
        submitFormAdd();
    });
    //get all users
    var table = $('#datatable1').DataTable( {
        ajax: {
            url: baseURL + '/get-users',
            dataSrc: ''
        },
        columns: [
            { data: 'idUser' },
            { data: 'username' },
            { data: 'namaUser' },
            { data: 'role' },
            { data: 'statusUser'},
            {
                "defaultContent": "<button data-toggle='modal' data-target='#editModal' id='btnEdit'>Edit</button><button id='btnHapus'>Ganti Status</button>"
            }
        ],
        "columnDefs": [
            {
                "targets": [ 0 ],
                "visible": false,
                "searchable": false
            }
        ]
    } );
    //delete user
    $('body').on('click', '#btnHapus', function(){
        //to get currently clicked row object
        var row  = $(this).parents('tr')[0];
        //for row data
        ambil = table.row( row ).data();
        // console.log(ambil.idUser);
        deleteUserPrepare(ambil);
    });
    $('body').on('click', '#btnEdit', function(){
        //to get currently clicked row object
        var row  = $(this).parents('tr')[0];
        //for row data
        ambil = table.row( row ).data();
        // alert("jalan");
        // alert(ambil.idUser);
        // var ambilUsername = document.getElementById("#usernameEdit");
        // ambilUsername.value = ambil.username;
        $("#usernameEdit").val(ambil.username);
        $("#namaPenggunaEdit").val(ambil.namaUser);
        $("#passEdit").val(ambil.password);
        $("#dropdownRoleEdit").val(ambil.role);
    });
    $("#saveEdit").click(function () {
        submitFormEdit(ambil);
    })
});
var page = 1;
var pageSize = 10;
var sort = 1;
var nameSort = 1;

var baseURL = 'http://localhost:8090/api';
var userCount;
var roleData;
var usersFilterBy = 0;
var numRow = 0;
var url;

//add User
function addUser(data) {
    $.ajax({
        url: baseURL + '/post-user',
        type: 'post',
        contentType: 'application/json',
        data: JSON.stringify(data),
        dataType: 'json',
        success: function (data) {
            if(!alert('Tambah user baru SUCCESS!'))
            {window.location.reload();}
        },
        error: function (err) {
            console.log(err);
        }
    });
};

function deleteUserPrepare(user) {
    event.preventDefault();
    var data = {};
    data['idUser'] = user.idUser;
    data['username'] = user.username;
    data['namaUser'] = user.namaUser;
    data['password'] = user.password;
    data['role'] = user.role;
    data['statusUser'] = user.statusUser;
    deleteUser(data);
}

function submitFormAdd() {
    event.preventDefault();
    var userName = $('#newUsername');
    var namaPengguna = $('#namaPengguna');
    var pass = $('#passw');
    var repass = $('#repassw');
    var role = $('#dropdownRole');
    var data = {};
    console.log(pass.val());
    console.log(repass.val());
    if (pass.val() === repass.val()) {
        data['username'] = userName.val();
        data['namaUser'] = namaPengguna.val();
        data['password'] = pass.val();
        data['role'] = role.val();
        data['statusUser'] = 'Active';
        userName.val('');
        namaPengguna.val('');
        pass.val('');
        repass.val('');
        role.val('Biro 1');
        $('#myModal').modal('toggle');
        console.log(userName.val());
        addUser(data);
    } else {
        alert('Password tidak benar!');
    };
}

function submitFormEdit (data){
    event.preventDefault();
    console.log(data.idUser);
    var userName = $('#usernameEdit');
    var namaPengguna = $('#namaPenggunaEdit');
    var pass = $('#passEdit');
    // var repass = $('#repassEdit');
    var role = $('#dropdownRoleEdit');
    var data2 = {};
    // if (pass === repass) {
    data2['idUser'] = data.idUser;
    data2['username'] = userName.val();
    data2['namaUser'] = namaPengguna.val();
    data2['password'] = pass.val();
    data2['role'] = role.val();
    data2['statusUser'] = 'Active';
        userName.val('');
        namaPengguna.val('');
        pass.val('');
        // repass.val('');
        role.val('Biro 1');
        $('#editModal').modal('toggle');
    // } else {
    //     alert('Password tidak benar!');
    // };
    updateUser(data2);
}

function updateUser(data){
    console.log(data.idUser);
    $.ajax({
        url: baseURL + '/put-user/' + data.idUser,
        type: 'put',
        contentType: 'application/json',
        data: JSON.stringify(data),
        dataType: 'json',
        success: function (data) {
            alert("Edit User Success!");
            {window.location.reload();}
        },
        error: function (err) {
            console.log(err);
        }
    });
}

// function getUsersBiro1() {
//         $.ajax({
//             url: baseURL + '/get-users',
//             type: 'get',
//             success: function (data) {
//                 var tableBody = $('#tabel-users-body');
//                 // console.log(data)
//                 for (var i = 0; i < data.length; i++) {
//                     tableBody.append(
//                         '<tr>' +
//                         '<td>' + data[i].idUser + '</td>' +
//                         '<td>' + data[i].username + '</td>' +
//                         '<td>' + data[i].namaUser + '</td>' +
//                         '<td>' + data[i].role + '</td>' +
//                         '<td>' + data[i].statusUser + '</td>' +
//                         '<td>' +
//                         '<div class="btn-grup">' +
//                         '<button class="btn btn-warning" data-toggle="modal" data-target="#editModal-'+data[i].idUser + '">Edit</button>' +
//                         '<button class="btn btn-danger" onclick="deleteUser(' + data[i].idUser + ')">Delete</button>' +
//
//                         '<div class="modal fade" id="editModal-' + data[i].idUser + '" role="dialog">' +
//                             '<div class="modal-dialog modal-md">'+
//                                 '<div class="modal-content">' +
//                                     '<div class="modal-header">' +
//                                         '<button type="button" class="close" data-dismiss="modal">&times;</button>' +
//                                         '<h4 class="modal-title">Edit User</h4>' +
//                                 '</div>'+
//                             '<div class="modal-body">' +
//                             '<form id="formEditUser-' + data[i].idUser +'"  onsubmit="setId(' + data[i].idUser + ')">' +
//                                 '<div>' +
//                                     '<label class="col-sm-3">Username </label>'+
//                                         '<div class="col-sm-9">' +
//                                         '<input type="text" id="username-' + data[i].idUser + '" class="form-control" placeholder="Username" required="" value="'+ data[i].username+'" />'+
//                                         '</div>'+
//                                 '</div>'+
//                                 '<br>' + '<br>'+
//                                 '<div>' +
//                                     '<label class="col-sm-3">Nama Pengguna </label>'+
//                                         '<div class="col-sm-9">'+
//                                         '<input type="text" id="namaPengguna-' + data[i].idUser + '" class="form-control" placeholder="Nama Pengguna" required="" value="'+ data[i].namaUser+'" />'+
//                                         '</div>' +
//                                 '</div>' +
//                         '<br><br>'+
//                                 '<div>'+
//                         '<label class="col-sm-3">Password </label>'+
//                         '<div class="col-sm-9">'+
//                         '<input type="password" id="pass-' + data[i].idUser + '" class="form-control" placeholder="Password" required="" value="'+data[i].password + '" />'+
//                         '</div>'+
//                         '</div>'+
//                         '<br>'+
//                         '<br>'+
//                         '<div>'+
//                         '<label class="col-sm-3">Repassword </label>'+
//                         '<div class="col-sm-9">'+
//                         '<input type="password" id="repass-' + data[i].idUser + '" class="form-control" placeholder="Repassword" required="" />'+
//                         '</div>'+
//                         '</div>'+
//                         '<br>' +
//                         '<br>'+
//                         '<div>'+
//                         '<label class="col-sm-3">Role </label>'+
//                         '<div class="col-sm-9">'+
//                         '<select class="form-control" id="dropdownRole-' + data[i].idUser + '" data-value="'+ data[i].role+'">'+
//                         '<option>Biro 1</option>'+
//                     '<option>KA Prodi</option>'+
//                     '</select>'+
//                     '</div>'+
//                     '</div>'+
//                     '</form>'+
//                     '</div>'+
//                     '<div class="modal-footer">'+
//                         '<button type="submit" id="saveEdit-' + data[i].idUser + '" class="btn btn-info btn-sm" data-dismiss="modal">Save changes</button>'+
//                         '<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>'+
//                         '</div>'+
//                         '</div>'+
//                         '</div>'+
//                         '</div>'
//                 )
//                 }
//             }
//         })
//     };

function deleteUser(data) {
    $.ajax({
        url: baseURL + '/delete-user/' + data.idUser,
        type: 'put',
        contentType: 'application/json',
        data: JSON.stringify(data),
        dataType: 'json',
        success: function (data) {
            if(!alert('Ganti status user SUCCESS!'))
            {window.location.reload();}
        },
        error: function (err) {
            console.log(err);
        }
    });
}
