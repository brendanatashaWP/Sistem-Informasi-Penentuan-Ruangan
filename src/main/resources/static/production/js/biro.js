$(document).ready(function(){
    var idKaprodi;
    // var ambil;
    // var kapasitasRuang ;
    var a = true;
    $('#selectProdi').change(function(){
        var idne = $("#selectProdi").val();
        a = false;
        console.log(idne);
        getMk(idne);
    });
    $('#selectRuang').change(function(){
        alert($('#selectRuang').val());
        getKapasitas($('#selectRuang').val());
        // bandinginKapasitas();
    });
    var table = $('#datatable5').DataTable( {
        ajax: {
            url: baseURL + '/get-all-mk/' + getIdKaprodi() ,
            dataSrc: ''
        },
        columns: [
            { data: 'kodeMk'},
            { data: 'namaMk'},
            { data: 'kapasitasMk' },
            { data: 'kodeGrupFk'},
            { data: 'hariMk' },
            { data: 'jamMulai' },
            { data: 'jamSelesai' },
            {
                "defaultContent": "<button data-toggle='modal' data-target='#modalAtur' id='btnAtur'>Edit</button>"
            }
        ],
        "columnDefs": [
            {
                "targets": [ 0 ],
                "visible": false,
                "searchable": false
            }
        ]
    });
    function getMk(idKaprodi) {
        console.log(idKaprodi);
        this.idKaprodi = idKaprodi;
    }
    function getIdKaprodi() {
        if (a === true){
            idKaprodi = $("#selectProdi").val();
        } else
            idKaprodi;
        return idKaprodi;
    }
    //ambil data user ke form atur
    $('body').on('click', '#btnAtur', function(){
        //to get currently clicked row object
        var row  = $(this).parents('tr')[0];
        //for row data
        ambil = table.row( row ).data();
        $("#kodeMatakuliahAtur").val(ambil.kodeMk);
        $("#namaMatakuliahAtur").val(ambil.namaMk);
        $("#kapasitasAtur").val(ambil.kapasitasMk);
        $('#hariAtur').val(ambil.hariMk);
        $("#groupAtur").val(ambil.kodeGrupFk);
        $("#jamMulaiAtur").val(ambil.jamMulai);
        $("#jamSelesaiAtur").val(ambil.jamSelesai);
    });
    $('#savePinjam').click(function () {
        preparePostPinjam(ambil);
    })
});
var baseURL = 'http://localhost:8090/api';

function preparePostPinjam(data) {
    event.preventDefault();
    var hariPinjam = $('#hariAtur');
    // var jamPinjam = data.idxJamTerpakai;
    var kodeMk = $('#kodeMatakuliahAtur');
    var namaRuang = $('#selectRuang');
    var kapasitasMk = data.kapasitasMk;
    var data = {};
        data['hariPinjam'] = hariPinjam.val();
        // data['jamPinjam'] = jamPinjam;
        data['kodeMk'] = kodeMk.val();
        data['namaRuang'] = namaRuang.val();
        data['kapasitasMk'] = kapasitasMk;
        hariPinjam.val('');
        kodeMk.val('');
        namaRuang.val('');
        $('#modalAtur').modal('toggle');
        aturRuang(data);
}
function aturRuang(data) {
    console.log(data.idUser);
    $.ajax({
        url: baseURL + '/post-pinjam/',
        type: 'post',
        contentType: 'application/json',
        data: JSON.stringify(data),
        dataType: 'json',
        success: function (data) {
            alert("Atur ruang Success!");
            {window.location.reload();}
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function getKapasitas(namaRuang) {
    $.ajax({
        url: baseURL + '/get-kapasitas-ruang/' + namaRuang,
        type: 'get',
        contentType: 'application/json',
        data: JSON.stringify(namaRuang),
        dataType: 'json',
        success: function (data) {
            // alert("ambil kapasitas Success!");
            // alert(data.kapasitasRuang);
            this.kapasitasRuang = data.kapasitasRuang;
            if (ambil.kapasitasMk <= this.kapasitasRuang){
                alert("Bisa")
            } else {
                alert("Tidak Bisa");
                // document.getElementById("#savePinjam").disabled = true;
            }
        },
        error: function (err) {
            console.log(err);
        }
    });
}

function bandinginKapasitas() {
    alert(ambil.kapasitasMk);
    alert(this.kapasitasRuang);

}
