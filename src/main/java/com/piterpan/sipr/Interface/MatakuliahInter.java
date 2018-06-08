package com.piterpan.sipr.Interface;

import com.piterpan.sipr.Model.Matakuliah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatakuliahInter extends JpaRepository<Matakuliah, Integer> {
    public List<Matakuliah> findMatakuliahsByIdKaprodi(int idKaprodi);
}
