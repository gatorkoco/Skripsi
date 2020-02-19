package com.example.gatotkoco.monitoringjantung.model;

import java.io.Serializable;

/**
 * Created by GATOT KOCO on 08/07/2019.
 */

public class Biodata implements Serializable {
    private String nama;
    private String usia;
    private String jenis_kelamin;

    public Biodata() {
    }

    public Biodata(String nama, String usia, String jenis_kelamin) {
        this.nama = nama;
        this.usia = usia;
        this.jenis_kelamin = jenis_kelamin;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getUsia() {
        return usia;
    }

    public void setUsia(String usia) {
        this.usia = usia;
    }

    public String getJenis_kelamin() {
        return jenis_kelamin;
    }

    public void setJenis_kelamin(String jenis_kelamin) {
        this.jenis_kelamin = jenis_kelamin;
    }
}
