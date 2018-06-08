package com.piterpan.sipr.Interface;

import com.piterpan.sipr.Model.Ruang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RuangInter extends JpaRepository<Ruang, Integer>{
    public List<Ruang> findRuangsByStatusRuangIsAndKapasitasRuangIsGreaterThanEqual(String status, int kap);
}
