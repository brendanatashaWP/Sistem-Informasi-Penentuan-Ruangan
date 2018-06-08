$(document).ready(function() {
    $("#btnLogin").click(function(){
        console.log('hai');
        login();
    });
});

var baseUrl = 'http://localhost:8090/';
// var isUsernameOk = false;
// var validateUsername = function (username) { // Fungsi untuk mendeteksi apakah email yang diinputkan sesuai dengan kriteria email
//     var reg = /^([A-Za-z0-9_\-\.])$/;
//     isUsernameOk = reg.test(username.value);

//     $('#btnLogin').prop('disabled', !isUsernameOk);
// };


  function login() {
    event.preventDefault();

    var data = {};

    data['username'] = $('#username').val(); // Mengambil value dari input user-email
    data['password'] = $('#password').val(); // Mengambil value dari input user-password

    $('#btnLogin').prop('disabled', true);

    /**
     * Fungsi ajax
     */
    $.ajax({
        url: baseUrl + 'login', //yang diget diatas dibawa ke /login di controller
        type: 'POST',
        contentType: 'application/json',
        data: JSON.stringify(data),
        dataType: 'json',
        success: function (data) {
            alert(data.message);

            if (data.data !== null)
                location.assign(baseUrl);
            else
                $('#btnLogin').prop('disabled', false);
        },
        error: function (error) {
            console.log(error);
            $('#btnLogin').prop('disabled', false);
        }
    });
};