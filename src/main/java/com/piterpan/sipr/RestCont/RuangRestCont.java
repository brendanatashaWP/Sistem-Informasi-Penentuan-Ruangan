package com.piterpan.sipr.RestCont;

import com.piterpan.sipr.Exception.ResourceNotFoundException2;
import com.piterpan.sipr.Interface.RuangInter;
import com.piterpan.sipr.Model.Matakuliah;
import com.piterpan.sipr.Model.Ruang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RuangRestCont {
    @Autowired
    RuangInter ruangInter;

    //post ruang
    @PostMapping("/post-ruang")
    public Ruang addRuang(@Valid @RequestBody Ruang ruang) {
        return ruangInter.save(ruang);
    }

    //get all ruang
    @GetMapping("/get-ruang")
    public List<Ruang> getAllRuang(){
        return ruangInter.findAll();
    }

    //getActive ruang
    @GetMapping("/get-active-ruang")
    public List<Ruang> getActiveRuang(){
        return ruangInter.findRuangsByStatusRuangIsLike("Active");
    }

    @GetMapping("/get-kapasitas-ruang/{namaRuang}")
    public Ruang getKapasitas(@PathVariable(value = "namaRuang") String namaRuang) {
        return ruangInter.findRuangByNamaRuang(namaRuang);
    }

    int a = 0;
    @GetMapping("/get-cucok-ruang")
    public List<Ruang> getCucokRuang(Matakuliah mk){
        if (mk.getJamMulai().equals("7,30") || mk.getJamMulai().equals("07,30")){
            this.a = 0;
        }
        if (mk.getJamMulai().equals("8,30") || mk.getJamMulai().equals("08,30")){
            this.a = 1;
        }
        if (mk.getJamMulai().equals("9,30") || mk.getJamMulai().equals("09,30")){
            this.a = 2;
        }
        if (mk.getJamMulai().equals("10,30")){
            this.a = 3;
        }
        if (mk.getJamMulai().equals("11,30")){
            this.a= 4;
        }
        if (mk.getJamMulai().equals("12,30")){
            this.a= 5;
        }
        if (mk.getJamMulai().equals("13,30")){
            this.a= 6;
        }
        if (mk.getJamMulai().equals("14,30")){
            this.a= 7;
        }
        if (mk.getJamMulai().equals("15,30")){
            this.a= 8;
        }
        if (mk.getJamMulai().equals("16,30")){
            this.a= 9;
        }
        if (mk.getJamMulai().equals("17,30")){
            this.a = 10;
        }
        Boolean statKapasitas = false, statJam = false, statHari = false;
        List<Ruang> ruangs = getActiveRuang();
        Ruang[] ruangArr = new Ruang[ruangs.size()];
        for (int x = 0; x < ruangs.size(); x++) {
            if (ruangArr[x].getKapasitasRuang() >= mk.getKapasitasMk()) {
                statKapasitas = true;
            }
//            if (statKapasitas == true) {
//                if (mk.getIdxJamTerpakai() )
//            }
        }
        return ruangs;
    }

    //get 1 ruang
    @GetMapping("/get-ruang/{id}")
    public Ruang getOneRuang(@PathVariable(value = "id") Integer id) {
        return ruangInter.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException2("ruang", "idRuang", id));
    }

    //put ruang
    @PutMapping("/put-ruang/{id}")
    public Ruang updateRuang(@PathVariable(value = "id") Integer id, @Valid @RequestBody Ruang ruang){
        Ruang ruang1 = ruangInter.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException2("ruang", "idRUang", id));
        ruang1.setKapasitasRuang(ruang.getKapasitasRuang());
        ruang1.setNamaRuang(ruang.getNamaRuang());
        ruang1.setStatusRuang(ruang.getStatusRuang());
        Ruang ruangBaru = ruangInter.save(ruang1);
        return ruangBaru;
    }

    //delete ruang
    @PutMapping("/delete-ruang/{id}")
    public Ruang deleteRuang(@PathVariable(value = "id") Integer id, @Valid @RequestBody Ruang ruang){
        Ruang ruang1 = ruangInter.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException2("ruang", "idRUang", id));
        ruang1.setKapasitasRuang(ruang.getKapasitasRuang());
        ruang1.setNamaRuang(ruang.getNamaRuang());
        if (ruang.getStatusRuang().equals("Active")){
            ruang1.setStatusRuang("Non-Active");
        } else {
            ruang1.setStatusRuang("Active");
        }
        Ruang ruangBaru = ruangInter.save(ruang1);
        return ruangBaru;
    }

}

