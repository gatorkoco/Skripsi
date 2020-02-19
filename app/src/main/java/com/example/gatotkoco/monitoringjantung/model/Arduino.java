package com.example.gatotkoco.monitoringjantung.model;

/**
 * Created by GATOT KOCO on 08/07/2019.
 */

public class Arduino {
    private Integer counter;
    private Integer dataOksigen;
    private Float dataJantung;
    private String created_at;

    public Arduino() {
    }

    public Arduino(Integer counter, Integer dataOksigen, Float dataJantung, String created_at) {
        this.counter = counter;
        this.dataOksigen = dataOksigen;
        this.dataJantung = dataJantung;
        this.created_at = created_at;
    }

    public Integer getCounter() {

        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public Integer getDataOksigen() {
        return dataOksigen;
    }

    public void setDataOksigen(Integer dataOksigen) {
        this.dataOksigen = dataOksigen;
    }

    public Float getDataJantung() {
        return dataJantung;
    }

    public void setDataJantung(Float dataJantung) {
        this.dataJantung = dataJantung;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }
}
