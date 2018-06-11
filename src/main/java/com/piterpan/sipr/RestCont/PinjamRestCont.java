package com.piterpan.sipr.RestCont;

import com.piterpan.sipr.Interface.PinjamInter;
import com.piterpan.sipr.Model.Peminjaman;
import com.piterpan.sipr.Service.PinjamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import sun.security.util.Pem;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PinjamRestCont {
    @Autowired
    PinjamInter pinjamInter;
//
//    @Autowired
//    PinjamService pinjamService;

    //Post yg dipinjam
    @PostMapping("/post-pinjam")
    public Peminjaman bookRuang(@Valid @RequestBody Peminjaman peminjaman){
//        if (peminjaman.get)
        return pinjamInter.save(peminjaman);
    }

    @GetMapping("/get-all-pinjam")
    public List<Peminjaman> getAllPinjam(){
        return pinjamInter.findAll();
    }

//    @GetMapping("/get-jam")
//    public List<Peminjaman> getAllUser() {
//        List<Peminjaman> a = getAllPinjam();
//        return pinjamService.getJam();
//    }

}
