package com.piterpan.sipr.Service;

import com.piterpan.sipr.Interface.MatakuliahInter;
import com.piterpan.sipr.Model.Matakuliah;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MkService {

    private MatakuliahInter mkInter;

    @Autowired
    public MkService(MatakuliahInter mkInter){
        this.mkInter = mkInter;
    }

    public List<Matakuliah> findMkByIdKaprodi(int idKaprodi) {
        return mkInter.findMatakuliahsByIdKaprodi(idKaprodi);
    }
}
