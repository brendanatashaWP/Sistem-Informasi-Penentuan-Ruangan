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
    private int idMk;

    @Column(nullable = false)
    private String namaMk;

    @Column(nullable = false)
    private int sks;

    public int getKodeGrupFk() {
        return kodeGrupFk;
    }

    public void setKodeGrupFk(int kodeGrupFk) {
        this.kodeGrupFk = kodeGrupFk;
    }

    @Column(nullable = false)
    private int kodeGrupFk;

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

    public int getJamSelesai() {
        return jamSelesai;
    }

    public void setJamSelesai(int jamSelesai) {
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
    private int jamSelesai;
    @Column(nullable = false)
    private String statusMk;
}
