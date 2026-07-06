package model;

import java.sql.Time;

public class Jadwal {

    private int idJadwal;
    private Dokter dokter;
    private String hari;
    private Time jamMulai;
    private Time jamSelesai;

    public Jadwal() {

    }

    public Jadwal(int idJadwal,
                  Dokter dokter,
                  String hari,
                  Time jamMulai,
                  Time jamSelesai) {

        this.idJadwal = idJadwal;
        this.dokter = dokter;
        this.hari = hari;
        this.jamMulai = jamMulai;
        this.jamSelesai = jamSelesai;
    }

    public int getIdJadwal() {
        return idJadwal;
    }

    public void setIdJadwal(int idJadwal) {
        this.idJadwal = idJadwal;
    }

    public Dokter getDokter() {
        return dokter;
    }

    public void setDokter(Dokter dokter) {
        this.dokter = dokter;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public Time getJamMulai() {
        return jamMulai;
    }

    public void setJamMulai(Time jamMulai) {
        this.jamMulai = jamMulai;
    }

    public Time getJamSelesai() {
        return jamSelesai;
    }

    public void setJamSelesai(Time jamSelesai) {
        this.jamSelesai = jamSelesai;
    }

    public String getNamaDokter() {
        return dokter != null ? dokter.getNama() : "";
    }

    @Override
    public String toString() {
        return getNamaDokter()
                + " - "
                + hari;
    }
}