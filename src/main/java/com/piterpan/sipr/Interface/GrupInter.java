package com.piterpan.sipr.Interface;

import com.piterpan.sipr.Model.Grup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupInter extends JpaRepository<Grup, Integer> {
}
