$(document).ready(function(){
    var idKaprodi;
    var a = true;
    $('#selectProdi').change(function(){
        var idne = $("#selectProdi").val();
        a = false;
        console.log(idne);
        getMk(idne);
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
            { data: 'jamMulai' },
            { data: 'jamSelesai' },
            {
                "defaultContent": "<button data-toggle='modal' data-target='#modalAtur' id='btnAtur'>Edit</button>"
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
        console.log(idKaprodi);
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
        $("#groupAtur").val(ambil.kodeGrupFk);
        $("#jamMulaiAtur").val(ambil.jamMulai);
        $("#jamSelesaiAtur").val(ambil.jamSelesai);
        // bandinginKapasitas($("#kapasitasAtur").val());
        // bandinginJamdanHari();
    });
});
var baseURL = 'http://localhost:8090/api';


