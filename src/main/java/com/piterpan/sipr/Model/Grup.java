package com.piterpan.sipr.Model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

@Component
@Entity
@Table(name = "grup")
@EntityListeners(AuditingEntityListener.class)
public class Grup implements Serializable {
    public int getKodeGrup() {
        return kodeGrup;
    }

    public void setKodeGrup(int kodeGrup) {
        this.kodeGrup = kodeGrup;
    }

    public char getNamaGrup() {
        return namaGrup;
    }

    public void setNamaGrup(char namaGrup) {
        this.namaGrup = namaGrup;
    }

    @Id
    private int kodeGrup;
    @Column(nullable = false)
    private char namaGrup;
}
