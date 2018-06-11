package com.piterpan.sipr.Model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "peminjaman")
@EntityListeners(AuditingEntityListener.class)
public class Peminjaman {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPinjam;

    @Column(nullable = false)
    private String namaRuang;

    @Column(unique = true, nullable = false)
    private String kodeMk;

    @Column(nullable = false)
    private String hariPinjam;

    @Column(nullable = false)
    private String kapasitasMk;


//    @Column( nullable = false)
//    private int[] jamPinjam;

//    public int[] getJamPinjam() {
//        return jamPinjam;
//    }
//
//    public void setJamPinjam(int[] jamPinjam) {
//        this.jamPinjam = jamPinjam;
//    }

    public String getHariPinjam() {
        return hariPinjam;
    }

    public void setHariPinjam(String hariPinjam) {
        this.hariPinjam = hariPinjam;
    }

    public String getKodeMk() {
        return kodeMk;
    }

    public void setKodeMk(String kodeMk) {
        this.kodeMk = kodeMk;
    }

    public String getNamaRuang() {
        return namaRuang;
    }

    public void setNamaRuang(String namaRuang) {
        this.namaRuang = namaRuang;
    }

    public int getIdPinjam() {
        return idPinjam;
    }

    public void setIdPinjam(int idPinjam) {
        this.idPinjam = idPinjam;
    }

    public String getKapasitasMk() {
        return kapasitasMk;
    }

    public void setKapasitasMk(String kapasitasMk) {
        this.kapasitasMk = kapasitasMk;
    }
}

