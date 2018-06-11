package com.piterpan.sipr.Interface;

import com.piterpan.sipr.Model.Peminjaman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PinjamInter extends JpaRepository<Peminjaman, Integer>{
//    public List<Peminjaman> getPeminjamanByJamPinjamContains(int[] jam);
}

