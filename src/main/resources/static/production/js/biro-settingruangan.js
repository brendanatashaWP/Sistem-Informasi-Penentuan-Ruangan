$(document).ready(function(){
    var ambil;
    //post user baru
    $("#btnSaveRuang").click(function(){
        submitFormAdd();
    });
    // $('#jamMulaiEdit').change(function(){
    //     var jamMulai= $(this).val();
    //     var sks = $('#sksEdit').val();
    //     hitungJamSelesai(jamMulai, sks);
    // });

    //get all ruang
    var table = $('#datatable6').DataTable( {
        ajax: {
            url: baseURL + '/get-ruang/',
            dataSrc: ''
        },
        columns: [
            { data: 'namaRuang' },
            { data: 'statusRuang'},
            { data: 'kapasitasRuang'},
            {
                "defaultContent": "<button data-toggle='modal' data-target='#modalEdit' id='btnEditRuang'>Edit</button><button id='btnHapusRuang'>Ganti Status</button>"
            }
        ]
    } );
    //delete ruang
    $('body').on('click', '#btnHapusRuang', function(){
        //to get currently clicked row object
        var row2  = $(this).parents('tr')[0];
        //for row data
        ambil2 = table.row( row2 ).data();
        console.log(ambil2.idRuang);
        deleteRuangPrepare(ambil2);
    });
    //ambil data user ke form edit user
    $('body').on('click', '#btnEditRuang', function(){
        //to get currently clicked row object
        var row  = $(this).parents('tr')[0];
        //for row data
        ambil = table.row( row ).data();
        $("#namaRuangEdit").val(ambil.namaRuang);
        $("#kapasitasEdit").val(ambil.kapasitasRuang);
        $("#statusRuangEdit").val(ambil.statusRuang);
    });
    //edit user
    $("#btnSaveRuangEdit").click(function () {
        submitFormEdit(ambil);
    })

});
var baseURL = 'http://localhost:8090/api';

//add Ruang
function addRuang(data) {
    $.ajax({
        url: baseURL + '/post-ruang',
        type: 'post',
        contentType: 'application/json',
        data: JSON.stringify(data),
        dataType: 'json',
        success: function (data) {
            if(!alert('Tambah Ruangan baru SUCCESS!'))
            {window.location.reload();}
        },
        error: function (err) {
            console.log(err);
        }
    });
}
//submit form add persiapan lempar data2
function submitFormAdd() {
    event.preventDefault();
    var kapasitasRuang = $('#kapasitasRuang');
    var namaRuang = $('#namaRuang');

    var data = {};
    data['kapasitasRuang'] = kapasitasRuang.val();
    data['namaRuang'] = namaRuang.val();
    data['statusRuang'] = 'Active';
    kapasitasRuang.val('');
    namaRuang.val('');
    console.log(data);
    $('#modalTambah').modal('toggle');
    addRuang(data);
}

//submit form add persiapan lempar data2
function submitFormEdit(ambil) {
    event.preventDefault();
    var idRUang = ambil.idRuang;
    var kapasitasRuang = $('#kapasitasEdit');
    var namaRuang = $('#namaRuangEdit');
    var statusRuang = ambil.statusRuang;

    var data = {};
    data['kapasitasRuang'] = kapasitasRuang.val();
    data['namaRuang'] = namaRuang.val();
    data['statusRuang'] = statusRuang;
    data['idRuang'] = idRUang;
    kapasitasRuang.val('');
    namaRuang.val('');
    console.log(data);
    $('#modalEdit').modal('toggle');
    updateRuang(data);
}
//edit ruang
function updateRuang(data){
    $.ajax({
        url: baseURL + '/put-ruang/' + data.idRuang,
        type: 'put',
        contentType: 'application/json',
        data: JSON.stringify(data),
        dataType: 'json',
        success: function (data) {
            alert("Edit Ruang Success!");
            // console.log(data.idRuang);
            {window.location.reload();}
        },
        error: function (err) {
            console.log(err);
        }
    });
}
//delete ruang
function deleteRuangPrepare(ruang) {
    event.preventDefault();
    console.log('dlete prepare');
    var data = {};
    data['idRuang'] = ruang.idRuang;
    data['namaRuang'] = ruang.namaRuang;
    data['kapasitasRuang'] = ruang.kapasitasRuang;
    data['statusRuang'] = ruang.statusRuang;
    console.log(data['idRuang']);
    deleteRuang(data);
}
//delete ruang
function deleteRuang(data) {
    $.ajax({
        url: baseURL + '/delete-ruang/' + data.idRuang,
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