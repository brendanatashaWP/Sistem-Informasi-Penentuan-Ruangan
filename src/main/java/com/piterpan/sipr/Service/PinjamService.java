package com.piterpan.sipr.Service;

import com.piterpan.sipr.Interface.PinjamInter;
import com.piterpan.sipr.Model.Peminjaman;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PinjamService {
    PinjamInter pinjamInter;

    @Autowired
    public PinjamService (PinjamInter userInter){
        this.pinjamInter = userInter;
    }

//    public List<Peminjaman> getJam (int[] jam


}
