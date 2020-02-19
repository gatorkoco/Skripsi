package com.example.gatotkoco.monitoringjantung.model;

/**
 * Created by GATOT KOCO on 17/07/2019.
 */

public class Notification {
    private String key;
    private String judul;
    private String keterangan;
    private String pasien;

    public Notification() {
    }

    public Notification(String key, String judul, String keterangan, String pasien) {
        this.key = key;
        this.judul = judul;
        this.keterangan = keterangan;
        this.pasien = pasien;
    }

    public String getKey() {

        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getPasien() {
        return pasien;
    }

    public void setPasien(String pasien) {
        this.pasien = pasien;
    }
}
