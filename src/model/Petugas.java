package model;

public class Petugas extends Person {

    private int idPetugas;
    private String jabatan;
    private String noHP;

    public Petugas() {
    }

    public Petugas(int idPetugas,
                   String nama,
                   String jabatan,
                   String noHP) {

        super(nama);

        this.idPetugas = idPetugas;
        this.jabatan = jabatan;
        this.noHP = noHP;
    }

    public int getIdPetugas() {
        return idPetugas;
    }

    public void setIdPetugas(int idPetugas) {
        this.idPetugas = idPetugas;
    }

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getNoHP() {
        return noHP;
    }

    public void setNoHP(String noHP) {
        this.noHP = noHP;
    }

    @Override
    public void tampilInfo() {

        System.out.println("Petugas : " + nama);
        System.out.println("Jabatan : " + jabatan);
    }

    @Override
    public String toString() {
        return nama;
    }
}