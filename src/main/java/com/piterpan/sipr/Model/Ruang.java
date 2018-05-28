package com.piterpan.sipr.Model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Component
@Entity
@Table(name = "ruang")
@EntityListeners(AuditingEntityListener.class)
public class Ruang implements Serializable {
    @Id
    private int idRuang;
    @Column(nullable = false)
    private String namaRuang, statusRuang;
    @Column(nullable = false)
    private int kapasitasRuang;

    public int getIdRuang() {
        return idRuang;
    }

    public void setIdRuang(int idRuang) {
        this.idRuang = idRuang;
    }

    public String getNamaRuang() {
        return namaRuang;
    }

    public void setNamaRuang(String namaRuang) {
        this.namaRuang = namaRuang;
    }

    public String getStatusRuang() {
        return statusRuang;
    }

    public void setStatusRuang(String statusRuang) {
        this.statusRuang = statusRuang;
    }

    public int getKapasitasRuang() {
        return kapasitasRuang;
    }

    public void setKapasitasRuang(int kapasitasRuang) {
        this.kapasitasRuang = kapasitasRuang;
    }
}
