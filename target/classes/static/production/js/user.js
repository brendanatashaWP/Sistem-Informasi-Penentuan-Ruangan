$(document).ready(function(){
    var ambil;
    //post user baru
    $("#coba").click(function(){
        submitFormAdd();
    });
    $("#btnLogout2").click(function(){
        logout();
    });
    //get all users
    var table = $('#datatable1').DataTable( {
        ajax: {
            url: baseURL + '/get-user-biro1',
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
    //ambil data user ke form edit user
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
    //edit user
    $("#saveEdit").click(function () {
        submitFormEdit(ambil);
    })
});
var page = 1;
var pageSize = 10;
var sort = 1;

var baseURL = 'http://localhost:8090/api';
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
}
//ganti status user
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
//submit form add persiapan lempar data2
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
//submit form edit, persiapan lempar data2
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
//edit user
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
//delete user
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
function logout() {
    $.ajax({
        url: 'http://localhost:8090' + '/logout',
        type: 'POST', // Tipe pengaksesan url
        success: function (data) {
            alert(data.message);

            if (data.data === 1) {
                location.assign(baseUrl);
            }
        },
        error: function (error) {
            console.log(error);
        }
    });
}