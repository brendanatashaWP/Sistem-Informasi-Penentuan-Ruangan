package com.piterpan.sipr.Model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Component
@Entity
@Table(name = "hari")
@EntityListeners(AuditingEntityListener.class)
public class Hari implements Serializable {
    public int getIdHari() {
        return idHari;
    }

    public void setIdHari(int idHari) {
        this.idHari = idHari;
    }

    public String getNamaHari() {
        return namaHari;
    }

    public void setNamaHari(String namaHari) {
        this.namaHari = namaHari;
    }

    @Id
    private int idHari;
    @Column(nullable = false)
    private String namaHari;
}
