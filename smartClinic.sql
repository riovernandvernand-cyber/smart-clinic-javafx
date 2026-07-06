CREATE DATABASE IF NOT EXISTS smart_clinic;
USE smart_clinic;
CREATE TABLE IF NOT EXISTS dokter (
    id_dokter INT AUTO_INCREMENT PRIMARY KEY,
    nama VARCHAR(100) NOT NULL,
    spesialis VARCHAR(100),
    no_hp VARCHAR(20)
);

CREATE TABLE IF NOT EXISTS pasien (
    id_pasien INT AUTO_INCREMENT PRIMARY KEY,
    nama VARCHAR(100) NOT NULL,
    umur INT,
    gender VARCHAR(20),
    alamat TEXT,
    no_hp VARCHAR(20),
    tekanan_darah DOUBLE,
    gula_darah DOUBLE
);
CREATE TABLE IF NOT EXISTS pendaftaran (
    id_daftar INT AUTO_INCREMENT PRIMARY KEY,
    tanggal DATE,
    keluhan TEXT,

    id_pasien INT,
    id_dokter INT,

    FOREIGN KEY (id_pasien)
    REFERENCES pasien(id_pasien),

    FOREIGN KEY (id_dokter)
    REFERENCES dokter(id_dokter)
);
CREATE TABLE  IF NOT EXISTS prediksi (
    id_prediksi INT AUTO_INCREMENT PRIMARY KEY,

    hasil_prediksi VARCHAR(100),
    probabilitas DOUBLE,
    tanggal_prediksi DATE,

    id_pasien INT,

    FOREIGN KEY (id_pasien)
    REFERENCES pasien(id_pasien)
);
INSERT INTO dokter(nama, spesialis, no_hp)
VALUES
('Dr. Andi', 'Penyakit Dalam', '081111111'),
('Dr. Budi', 'Umum', '082222222');
INSERT INTO pasien
(nama, umur, gender, alamat, no_hp,
tekanan_darah, gula_darah)

VALUES
('Siti', 25, 'Perempuan',
'Semarang', '081234567',
120, 90),

('Ahmad', 40, 'Laki-laki',
'Kendal', '089999999',
150, 250);

INSERT INTO pendaftaran
(tanggal, keluhan, id_pasien, id_dokter)

VALUES
('2026-05-08',
'Sakit kepala',
1,
1);

INSERT INTO prediksi
(hasil_prediksi,
probabilitas,
tanggal_prediksi,
id_pasien)

VALUES
(
'Risiko Diabetes Tinggi',
0.89,
'2026-05-08',
2
);

SELECT
p.nama,
d.nama,
pd.keluhan

FROM pendaftaran pd

JOIN pasien p
ON pd.id_pasien = p.id_pasien

JOIN dokter d
ON pd.id_dokter = d.id_dokter;

SELECT
p.nama,
pr.hasil_prediksi,
pr.probabilitas

FROM prediksi pr

JOIN pasien p
ON pr.id_pasien = p.id_pasien;

CREATE TABLE IF NOT EXISTS roles (
    id_role INT AUTO_INCREMENT PRIMARY KEY,
    nama_role VARCHAR(50)
);
CREATE TABLE IF NOT EXISTS users (
    id_user INT AUTO_INCREMENT PRIMARY KEY,

    nama VARCHAR(100),
    username VARCHAR(50),
    password VARCHAR(255),

    id_role INT,
    id_dokter INT NULL,

    FOREIGN KEY (id_role)
        REFERENCES roles(id_role),

    FOREIGN KEY (id_dokter)
        REFERENCES dokter(id_dokter)
);

INSERT INTO roles(nama_role)
VALUES
('Admin'),
('Petugas'),
('Dokter');

INSERT INTO users
(nama, username, password, id_role)
VALUES
('Administrator', 'admin', '123', 1),
('Petugas Klinik', 'petugas', '123', 2);


-- =========================
-- TABEL OBAT
-- =========================
CREATE TABLE IF NOT EXISTS obat (
    id_obat INT AUTO_INCREMENT PRIMARY KEY,
    nama_obat VARCHAR(100),
    stok INT,
    harga DOUBLE,
    aturan_pakai VARCHAR(100),
    kode_kfa INT
);

-- =========================
-- TABEL PEMERIKSAAN
-- =========================
CREATE TABLE IF NOT EXISTS pemeriksaan (
    id_periksa INT AUTO_INCREMENT PRIMARY KEY,

    id_daftar INT,

    tanggal_periksa DATE,

    diagnosa TEXT,
    tekanan_darah DOUBLE,
    gula_darah DOUBLE,
    suhu DOUBLE,
    berat_badan DOUBLE,

    catatan TEXT,

    hasil_prediksi VARCHAR(100),
    tingkat_resiko VARCHAR(50),

    FOREIGN KEY (id_daftar)
        REFERENCES pendaftaran(id_daftar)
        ON DELETE CASCADE
);

-- =========================
-- TABEL REKAM MEDIS
-- =========================
CREATE TABLE IF NOT EXISTS rekam_medis (
    id_rekam INT AUTO_INCREMENT PRIMARY KEY,
    id_periksa INT,
    tanggal DATE,
    ringkasan TEXT,
    FOREIGN KEY (id_periksa)
        REFERENCES pemeriksaan(id_periksa)
        ON DELETE CASCADE
);

-- =========================
-- TABEL RESEP OBAT
-- =========================
CREATE TABLE IF NOT EXISTS resep_obat (
    id_resep INT AUTO_INCREMENT PRIMARY KEY,

    id_periksa INT,
    id_obat INT,

    jumlah INT,
    dosis VARCHAR(100),
    keterangan TEXT,

    FOREIGN KEY (id_periksa)
        REFERENCES pemeriksaan(id_periksa)
        ON DELETE CASCADE,

    FOREIGN KEY (id_obat)
        REFERENCES obat(id_obat)
        ON DELETE CASCADE
);

INSERT INTO obat(nama_obat, stok, harga, aturan_pakai)
VALUES
('Paracetamol', 100, 5000, '3x1'),
('Antasida', 50, 7000, '2x1');

INSERT INTO obat
(nama_obat, stok, harga)
VALUES
('Amoxicillin', 50, 12000),
('Vitamin C', 80, 3000);

-- =========================
-- DATA PEMERIKSAAN
-- =========================
INSERT INTO pemeriksaan
(id_daftar,
 tanggal_periksa,
 diagnosa,
 tekanan_darah,
 gula_darah,
 suhu,
 berat_badan,
 catatan,
 hasil_prediksi,
 tingkat_resiko)

VALUES
(
1,
'2026-06-15',
'Hipertensi',
150,
250,
37.0,
70,
'Kontrol rutin setiap minggu',
'Risiko Diabetes',
'Tinggi'
);

-- =========================
-- DATA REKAM MEDIS
-- =========================
INSERT INTO rekam_medis
(id_periksa,
 tanggal,
 ringkasan)

VALUES
(
1,
'2026-06-15',
'Pasien mengalami hipertensi dan perlu pemantauan gula darah secara berkala.'
);

-- =========================
-- DATA RESEP OBAT
-- =========================
INSERT INTO resep_obat
(id_periksa,
 id_obat,
 jumlah,
 dosis,
 keterangan)

VALUES
(
1,
1,
10,
'3x1',
'Diminum setelah makan'
);

-- =========================
-- DATA PREDIKSI TAMBAHAN
-- =========================
INSERT INTO prediksi
(hasil_prediksi,
 probabilitas,
 tanggal_prediksi,
 id_pasien)

VALUES
(
'Risiko Hipertensi Tinggi',
0.92,
'2026-06-15',
1
);