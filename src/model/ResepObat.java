package model;

public class ResepObat {

    private int idResep;

    private Pemeriksaan pemeriksaan;

    private Obat obat;

    private int jumlah;

    private String dosis;

    private String keterangan;

    // Untuk ditampilkan di TableView
    private String namaPasien;

    private String namaObat;

    public ResepObat() {
    }

    public int getIdResep() {
        return idResep;
    }

    public void setIdResep(int idResep) {
        this.idResep = idResep;
    }

    public Pemeriksaan getPemeriksaan() {
        return pemeriksaan;
    }

    public void setPemeriksaan(Pemeriksaan pemeriksaan) {
        this.pemeriksaan = pemeriksaan;
    }

    public Obat getObat() {
        return obat;
    }

    public void setObat(Obat obat) {
        this.obat = obat;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getNamaPasien() {
        return namaPasien;
    }

    public void setNamaPasien(String namaPasien) {
        this.namaPasien = namaPasien;
    }

    public String getNamaObat() {
        return namaObat;
    }

    public void setNamaObat(String namaObat) {
        this.namaObat = namaObat;
    }

    @Override
    public String toString() {

        return "ID Resep : "
                + idResep
                + " | Pasien : "
                + namaPasien;

    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof ResepObat))
            return false;

        ResepObat r = (ResepObat) obj;

        return idResep == r.idResep;

    }

    @Override
    public int hashCode() {

        return Integer.hashCode(idResep);

    }

}