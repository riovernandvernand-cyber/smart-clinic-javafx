package model;

import java.util.Date;

public class RekamMedis {

    private int idRekam;
    private Pemeriksaan pemeriksaan;
    private Date tanggal;
    private String ringkasan;

    public RekamMedis() {
    }

    // =========================
    // GETTER & SETTER
    // =========================

    public int getIdRekam() {
        return idRekam;
    }

    public void setIdRekam(int idRekam) {
        this.idRekam = idRekam;
    }

    public Pemeriksaan getPemeriksaan() {
        return pemeriksaan;
    }

    public void setPemeriksaan(Pemeriksaan pemeriksaan) {
        this.pemeriksaan = pemeriksaan;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getRingkasan() {
        return ringkasan;
    }

    public void setRingkasan(String ringkasan) {
        this.ringkasan = ringkasan;
    }

    // =========================
    // PROPERTY TABLE
    // =========================

    public String getNamaPasien() {

        if (pemeriksaan == null) {
            return "";
        }

        return pemeriksaan.getNamaPasien();
    }

    public String getDiagnosa() {

        if (pemeriksaan == null) {
            return "";
        }

        return pemeriksaan.getDiagnosa();
    }

    public String getPrediksi() {

        if (pemeriksaan == null) {
            return "";
        }

        return pemeriksaan.getHasilPrediksi();
    }

    public String getCatatan() {

        if (pemeriksaan == null) {
            return "";
        }

        return pemeriksaan.getCatatan();
    }

    @Override
    public String toString() {

        return "RM-" + idRekam
                + " | "
                + getNamaPasien();
    }

}