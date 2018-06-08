package com.piterpan.sipr.RestCont;

import com.piterpan.sipr.Interface.MatakuliahInter;

import com.piterpan.sipr.Interface.RuangInter;
import com.piterpan.sipr.Model.Matakuliah;
import com.piterpan.sipr.Model.Ruang;
import com.piterpan.sipr.Service.Biro1Service;
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
    RuangInter ruangInter;
    Biro1Service biro1Service;

//    @Autowired
//    RuangInter ruangInter;

    //get ruang aktif
    @GetMapping("/get-ruangan-available")
    public List<Ruang> getAllMk(){
        return biro1Service.getRuangByStatus("Active");
    }
}
