package com.piterpan.sipr.Interface;

import com.piterpan.sipr.Model.Matakuliah;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatakuliahInter extends JpaRepository<Matakuliah, Integer> {
}
