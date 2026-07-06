package model;

import java.util.Date;

public class Pemeriksaan {

    private int idPeriksa;
    private Pendaftaran pendaftaran;
    private Date tanggalPeriksa;
    private String diagnosa;

    private double tekananDarah;
    private double gulaDarah;
    private double suhu;
    private double beratBadan;

    private String catatan;
    private String hasilPrediksi;
    private String tingkatResiko;

    private String namaPasien;

    public Pemeriksaan() {
    }

    public int getIdPeriksa() {
        return idPeriksa;
    }

    public void setIdPeriksa(int idPeriksa) {
        this.idPeriksa = idPeriksa;
    }

    public Pendaftaran getPendaftaran() {
        return pendaftaran;
    }

    public void setPendaftaran(Pendaftaran pendaftaran) {
        this.pendaftaran = pendaftaran;
    }

    public Date getTanggalPeriksa() {
        return tanggalPeriksa;
    }

    public void setTanggalPeriksa(Date tanggalPeriksa) {
        this.tanggalPeriksa = tanggalPeriksa;
    }

    public String getDiagnosa() {
        return diagnosa;
    }

    public void setDiagnosa(String diagnosa) {
        this.diagnosa = diagnosa;
    }

    public double getTekananDarah() {
        return tekananDarah;
    }

    public void setTekananDarah(double tekananDarah) {
        this.tekananDarah = tekananDarah;
    }

    public double getGulaDarah() {
        return gulaDarah;
    }

    public void setGulaDarah(double gulaDarah) {
        this.gulaDarah = gulaDarah;
    }

    public double getSuhu() {
        return suhu;
    }

    public void setSuhu(double suhu) {
        this.suhu = suhu;
    }

    public double getBeratBadan() {
        return beratBadan;
    }

    public void setBeratBadan(double beratBadan) {
        this.beratBadan = beratBadan;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getHasilPrediksi() {
        return hasilPrediksi;
    }

    public void setHasilPrediksi(String hasilPrediksi) {
        this.hasilPrediksi = hasilPrediksi;
    }

    public String getTingkatResiko() {
        return tingkatResiko;
    }

    public void setTingkatResiko(String tingkatResiko) {
        this.tingkatResiko = tingkatResiko;
    }

    public String getNamaPasien() {
    return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
    this.namaPasien = namaPasien;
    }
    @Override
    public String toString() {

    return "ID Periksa : "
            + idPeriksa
            + " | Pasien : "
            + getNamaPasien();
    }
    @Override
    public boolean equals(Object obj) {

    if (this == obj) return true;

    if (obj == null || getClass() != obj.getClass())
        return false;

    Pemeriksaan p = (Pemeriksaan) obj;

    return idPeriksa == p.idPeriksa;
    }

    @Override
    public int hashCode() {

    return Integer.hashCode(idPeriksa);
    }
}