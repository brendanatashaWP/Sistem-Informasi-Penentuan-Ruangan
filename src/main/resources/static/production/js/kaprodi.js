$(document).ready(function(){
    var ambil;
    //post user baru
    $("#btnSaveMk").click(function(){
        submitFormAdd();
    });
    $("#btnLogout3").click(function(){
        logout();
    });
    $('#jamMulai').change(function(){
        var jamMulai= $(this).val();
        var sks = $('#sks').val();
        hitungJamSelesai(jamMulai, sks);
    });
    $('#jamMulaiEdit').change(function(){
        var jamMulai= $(this).val();
        var sks = $('#sksEdit').val();
        hitungJamSelesai(jamMulai, sks);
    });
    //get all mk
    var table = $('#datatable3').DataTable( {
        ajax: {
            url: baseURL + '/get-all-mk/' + $('#idUser').val(),
            dataSrc: ''
        },
        columns: [
            { data: 'idMk' },
            { data: 'idKaprodi' },
            { data: 'kodeMk'},
            { data: 'namaMk'},
            { data: 'sks'},
            { data: 'kapasitasMk' },
            { data: 'hariMk' },
            { data: 'kodeGrupFk'},
            { data: 'jamMulai' },
            { data: 'jamSelesai' },
            { data: 'statusMk'},
            {
                "defaultContent": "<button data-toggle='modal' data-target='#myModalEdit' id='btnEdit'>Edit</button><button id='btnHapusMk'>Ganti Status</button>"
            }
        ],
        "columnDefs": [
            {
                "targets": [ 0, 1 ],
                "visible": false,
                "searchable": false
            }
        ]
    } );
    //delete mk
    $('body').on('click', '#btnHapusMk', function(){
        //to get currently clicked row object
        var row2  = $(this).parents('tr')[0];
        //for row data
        ambil2 = table.row( row2 ).data();
        console.log(ambil2.idMk);
        deleteMkPrepare(ambil2);
    });
    //ambil data user ke form edit user
    $('body').on('click', '#btnEdit', function(){
        //to get currently clicked row object
        var row  = $(this).parents('tr')[0];
        //for row data
        ambil = table.row( row ).data();
        $("#idMk").val(ambil.idMk);
        $("#kodeMkEdit").val(ambil.kodeMk);
        $("#namaMkEdit").val(ambil.namaMk);
        $("#sksEdit").val(ambil.sks);
        $("#hari").val(ambil.hariMk);
        $("#kapasitasMkEdit").val(ambil.kapasitasMk);
        $("#kodeGrupMkEdit").val(ambil.kodeGrupFk);
        $("#jamMulaiEdit").val(ambil.jamMulai);
    });
    //edit user
    $("#btnSaveMkEdit").click(function () {
        submitFormEdit(ambil);
    })

});
var baseURL = 'http://localhost:8090/api';

//add Mk
function addMk(data) {
    $.ajax({
        url: baseURL + '/post-mk',
        type: 'post',
        contentType: 'application/json',
        data: JSON.stringify(data),
        dataType: 'json',
        success: function (data) {
            if(!alert('Tambah Matakuliah baru SUCCESS!'))
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
    var idKaprodi = $('#idUser'); //baru samPE SINIIII
    // var idMk = $('#namaPengguna');
    var jamSelesai = $('#jamSelesai');
    var jamMulai = $('#jamMulai');
    var kapasitasMk = $('#kapasitasMk');
    var kodeGrup = $('#kodeGrupMk');
    var kodeMk = $('#kodeMk');
    var namaMk = $('#namaMk');
    var sks = $('#sks');
    var hari = $('#hari');

    var data = {};
    data['idKaprodi'] = idKaprodi.val();
    data['jamSelesai'] = jamSelesai.val();
    data['jamMulai'] = jamMulai.val();
    data['kapasitasMk'] = kapasitasMk.val();
    data['kodeGrupFk'] = kodeGrup.val();
    data['kodeMk'] = kodeMk.val();
    data['namaMk'] = namaMk.val();
    data['sks'] = sks.val();
    data['hariMk'] = hari.val();
    data['statusMk'] = 'Active';
    idKaprodi.val('');
    jamMulai.val('');
    jamSelesai.val('');
    kapasitasMk.val('');
    kodeGrup.val('');
    kodeMk.val('');
    namaMk.val('');
    sks.val('');
    console.log(data);
    $('#myModal').modal('toggle');
    addMk(data);
}
//hitung jamSelesai
function hitungJamSelesai(jamMulai, sks) {
    var totalWaktuMenit = (sks * 50) + 10; //nyari total menit sesuai jumlah sks
    var menit = totalWaktuMenit % 60; //nyari menit
    var jam = Math.floor(totalWaktuMenit / 60); //nyari jam dibulatkan kebawah
    var mulai = jamMulai.split('.');
    var totalMenit = parseInt(mulai[1]) + parseInt(menit);
    var finalMenit, finalJam;
    if (totalMenit > 60){
        finalMenit = totalMenit % 60;
        finalJam = parseInt(mulai[0]) + jam + (Math.floor(totalMenit / 60));
    } else {
        finalMenit = totalMenit;
        finalJam =parseInt(mulai[0])+ parseInt(jam);
    }
    if (finalMenit.length === 1){
        var nol = 0;
        finalMenit += nol.toString();
    }
    // console.log("totalMenit " + totalMenit);
    // console.log("jam selesai: " + finalJam + "." + finalMenit);
    // return finalJam + "," + finalMenit;
    document.getElementById("jamSelesai").value = finalJam + "." + finalMenit;
    document.getElementById("jamSelesaiEdit").value = finalJam + "." + finalMenit;
}
//submit form add persiapan lempar data2
function submitFormEdit() {
    event.preventDefault();
    var idKaprodi = $('#idUser'); //baru samPE SINIIII
    var idMk = $('#idMk');
    var jamSelesai = $('#jamSelesaiEdit');
    var jamMulai = $('#jamMulaiEdit');
    var kapasitasMk = $('#kapasitasMkEdit');
    var kodeGrup = $('#kodeGrupMkEdit');
    var kodeMk = $('#kodeMkEdit');
    var namaMk = $('#namaMkEdit');
    var sks = $('#sksEdit');
    var hari = $('#hari');

    var data = {};
    data['idMk'] = idMk.val();
    data['idKaprodi'] = idKaprodi.val();
    data['jamSelesai'] = jamSelesai.val();
    data['jamMulai'] = jamMulai.val();
    data['kapasitasMk'] = kapasitasMk.val();
    data['kodeGrupFk'] = kodeGrup.val();
    data['kodeMk'] = kodeMk.val();
    data['namaMk'] = namaMk.val();
    data['sks'] = sks.val();
    data['hariMk'] = hari.val();
    data['statusMk'] = 'Active';
    idKaprodi.val('');
    jamMulai.val('');
    jamSelesai.val('');
    kapasitasMk.val('');
    kodeGrup.val('');
    kodeMk.val('');
    namaMk.val('');
    sks.val('');
    console.log(data);
    $('#myModalEdit').modal('toggle');
    updateUser(data);
}
//edit mk
function updateUser(data){
    $.ajax({
        url: baseURL + '/put-mk/' + data.idMk,
        type: 'put',
        contentType: 'application/json',
        data: JSON.stringify(data),
        dataType: 'json',
        success: function (data) {
            alert("Edit Matakuliah Success!");
            {window.location.reload();}
        },
        error: function (err) {
            console.log(err);
        }
    });
}
//delete mk
function deleteMkPrepare(mk) {
    event.preventDefault();
    console.log('dlete prepare');
    var data = {};
    data['idMk'] = mk.idMk;
    data['idKaprodi'] = mk.idKaprodi;
    data['jamSelesai'] = mk.jamSelesai;
    data['jamMulai'] = mk.jamMulai;
    data['kapasitasMk'] = mk.kapasitasMk;
    data['kodeGrupFk'] = mk.kodeGrupFk;
    data['kodeMk'] = mk.kodeMk;
    data['namaMk'] = mk.namaMk;
    data['sks'] = mk.sks;
    data['statusMk'] = mk.statusMk;
    data['hariMk'] = mk.hari;
    console.log(data['idMk']);
    deleteMk(data);
}
//delete mk
function deleteMk(data) {
    $.ajax({
        url: baseURL + '/delete-mk/' + data.idMk,
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