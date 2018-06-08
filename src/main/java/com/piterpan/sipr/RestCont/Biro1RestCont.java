package com.piterpan.sipr.RestCont;

import com.piterpan.sipr.Interface.MatakuliahInter;

import com.piterpan.sipr.Model.Matakuliah;
import com.piterpan.sipr.Model.Ruang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class Biro1RestCont {
    @Autowired
    MatakuliahInter matakuliahInter;

//    @Autowired
//    RuangInter ruangInter;

    //get ruang aktif
//    @GetMapping("/get-ruangan-available")
//    public List<Ruang> getAllMk(@PathVariable(value = "idKaprodi") int id){
////        return ruangInter.findMatakuliahsByIdKaprodi(id);
//    }
}
