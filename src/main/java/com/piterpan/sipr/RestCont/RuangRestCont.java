package com.piterpan.sipr.RestCont;

import com.piterpan.sipr.Exception.ResourceNotFoundException2;
import com.piterpan.sipr.Interface.RuangInter;
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

