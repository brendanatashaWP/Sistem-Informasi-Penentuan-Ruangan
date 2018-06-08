package com.piterpan.sipr.Service;

import com.piterpan.sipr.Interface.RuangInter;
import com.piterpan.sipr.Model.Ruang;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Biro1Service {
    private RuangInter ruangInter;

    @Autowired
    public Biro1Service (RuangInter ruangInter){
        this.ruangInter = ruangInter;
    }

    public List<Ruang> getRuangByStatus(String stat){
        return ruangInter.findRuangsByStatusRuangIsLike(stat);
    }
}
