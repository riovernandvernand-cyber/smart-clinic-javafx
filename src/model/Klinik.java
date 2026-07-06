package model;

import java.util.ArrayList;

public class Klinik {

    private String namaKlinik;
    private String alamat;

    private ArrayList<Pasien> daftarPasien;
    private ArrayList<Dokter> daftarDokter;

    public Klinik(String namaKlinik,
                  String alamat) {

        this.namaKlinik = namaKlinik;
        this.alamat = alamat;

        daftarPasien = new ArrayList<>();
        daftarDokter = new ArrayList<>();
    }
    public String getNamaKlinik() {
    return namaKlinik;
    }

    public void setNamaKlinik(String namaKlinik) {
    this.namaKlinik = namaKlinik;
    }

    public String getAlamat() {
    return alamat;
    }

    public void setAlamat(String alamat) {
    this.alamat = alamat;
    }

    public void tambahPasien(Pasien p) {

        daftarPasien.add(p);
    }

    public void tambahDokter(Dokter d) {

        daftarDokter.add(d);
    }

    public void tampilData() {

        System.out.println(
                "=== DATA PASIEN ===");

        for(Pasien p : daftarPasien){

            p.tampilInfo();
            System.out.println();
        }
    }
}