package com.piterpan.sipr.Model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Component
@Entity
@Table(name = "matakuliah")
@EntityListeners(AuditingEntityListener.class)
public class Matakuliah implements Serializable{

    @Column(unique = true, nullable = false)
    private String kodeMk;

    public int getIdMk() {
        return idMk;
    }

    public void setIdMk(int idMk) {
        this.idMk = idMk;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMk;

    @Column(nullable = false)
    private String namaMk;

    @Column(nullable = false)
    private int idKaprodi;

    @Column(nullable = false)
    private int sks;

    public char getKodeGrupFk() {
        return kodeGrupFk;
    }

    public void setKodeGrupFk(char kodeGrupFk) {
        this.kodeGrupFk = kodeGrupFk;
    }

    @Column(nullable = false)
    private char kodeGrupFk;

    public String getKodeMk() {
        return kodeMk;
    }

    public void setKodeMk(String kodeMk) {
        this.kodeMk = kodeMk;
    }

    public String getNamaMk() {
        return namaMk;
    }

    public void setNamaMk(String namaMk) {
        this.namaMk = namaMk;
    }

    public int getSks() {
        return sks;
    }

    public void setSks(int sks) {
        this.sks = sks;
    }

    public int getKapasitasMk() {
        return kapasitasMk;
    }

    public void setKapasitasMk(int kapasitasMk) {
        this.kapasitasMk = kapasitasMk;
    }

    public String getJamSelesai() {
        return String.format("%.2f", jamSelesai);
    }

    public void setJamSelesai(double jamSelesai) {
        this.jamSelesai = jamSelesai;
    }

    public String getStatusMk() {
        return statusMk;
    }

    public void setStatusMk(String statusMk) {
        this.statusMk = statusMk;
    }

    @Column(nullable = false)
    private int kapasitasMk;
    @Column(nullable = false)
    private double jamSelesai;
    @Column(nullable = false)
    private double jamMulai;
    @Column(nullable = false)
    private String statusMk;
    @Column(nullable = false)
    private String hariMk;
//    @Column(nullable = false)
//    private int[] idxJamTerpakai;

    public int getIdKaprodi() {
        return idKaprodi;
    }

    public void setIdKaprodi(int idKaprodi) {
        this.idKaprodi = idKaprodi;
    }

    public String getJamMulai() {
        return String.format("%.2f", jamMulai) ;
    }

    public void setJamMulai(double jamMulai) {
        this.jamMulai = jamMulai;
    }

    public String getHariMk() {
        return hariMk;
    }

    public void setHariMk(String hariMk) {
        this.hariMk = hariMk;
    }

//    public int[] getIdxJamTerpakai() {
//        return idxJamTerpakai;
//    }
//
//    public void setIdxJamTerpakai(int[] idxJamTerpakai) {
//        this.idxJamTerpakai = idxJamTerpakai;
//    }
}
