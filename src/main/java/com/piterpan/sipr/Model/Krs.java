package com.piterpan.sipr.Model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "krs")
@EntityListeners(AuditingEntityListener.class)
public class Krs {
    @Id
    private int idRekapRuang;

    @Column
    private int idHari, idJamMulai, idRuang, kodeGrup;

    public int getIdRekapRuang() {
        return idRekapRuang;
    }

    public void setIdRekapRuang(int idRekapRuang) {
        this.idRekapRuang = idRekapRuang;
    }

    public int getIdHari() {
        return idHari;
    }

    public void setIdHari(int idHari) {
        this.idHari = idHari;
    }

    public int getIdJamMulai() {
        return idJamMulai;
    }

    public void setIdJamMulai(int idJamMulai) {
        this.idJamMulai = idJamMulai;
    }

    public int getIdRuang() {
        return idRuang;
    }

    public void setIdRuang(int idRuang) {
        this.idRuang = idRuang;
    }

    public int getKodeGrup() {
        return kodeGrup;
    }

    public void setKodeGrup(int kodeGrup) {
        this.kodeGrup = kodeGrup;
    }

    public String getKodeMk() {
        return kodeMk;
    }

    public void setKodeMk(String kodeMk) {
        this.kodeMk = kodeMk;
    }

    @Column
    private String kodeMk;
}
