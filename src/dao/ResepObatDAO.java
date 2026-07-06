package dao;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.sql.*;

public class ResepObatDAO {

    Connection conn = DBConnection.connect();

    // ==========================
    // GET ALL
    // ==========================
    public ObservableList<ResepObat> getAllResep() {

        ObservableList<ResepObat> list =
                FXCollections.observableArrayList();

        try {

            String sql =
                    "SELECT r.*, " +
                    "ps.nama AS nama_pasien, " +
                    "o.nama_obat " +
                    "FROM resep_obat r " +
                    "JOIN pemeriksaan pm ON r.id_periksa = pm.id_periksa " +
                    "JOIN pendaftaran pd ON pm.id_daftar = pd.id_daftar " +
                    "JOIN pasien ps ON pd.id_pasien = ps.id_pasien " +
                    "JOIN obat o ON r.id_obat = o.id_obat";

            Statement st =
                    conn.createStatement();

            ResultSet rs =
                    st.executeQuery(sql);

            while (rs.next()) {

                ResepObat r =
                        new ResepObat();

                r.setIdResep(
                        rs.getInt("id_resep"));

                r.setJumlah(
                        rs.getInt("jumlah"));

                r.setDosis(
                        rs.getString("dosis"));

                r.setKeterangan(
                        rs.getString("keterangan"));

                r.setNamaPasien(
                        rs.getString("nama_pasien"));

                r.setNamaObat(
                        rs.getString("nama_obat"));

                Pemeriksaan p =
                        new Pemeriksaan();

                p.setIdPeriksa(
                        rs.getInt("id_periksa"));

                r.setPemeriksaan(p);

                Obat obat =
                        new Obat();

                obat.setIdObat(
                        rs.getInt("id_obat"));

                obat.setNamaObat(
                        rs.getString("nama_obat"));

                r.setObat(obat);

                list.add(r);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return list;

    }

    // ==========================
    // INSERT
    // ==========================
    public void insertResep(
            ResepObat r) {

        try {

            String sql =
                    "INSERT INTO resep_obat(id_periksa,id_obat,jumlah,dosis,keterangan) VALUES(?,?,?,?,?)";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setInt(
                    1,
                    r.getPemeriksaan()
                     .getIdPeriksa());

            ps.setInt(
                    2,
                    r.getObat()
                     .getIdObat());

            ps.setInt(
                    3,
                    r.getJumlah());

            ps.setString(
                    4,
                    r.getDosis());

            ps.setString(
                    5,
                    r.getKeterangan());

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    // ==========================
    // UPDATE
    // ==========================
    public void updateResep(
            ResepObat r) {

        try {

            String sql =
                    "UPDATE resep_obat SET " +
                    "id_periksa=?, " +
                    "id_obat=?, " +
                    "jumlah=?, " +
                    "dosis=?, " +
                    "keterangan=? " +
                    "WHERE id_resep=?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setInt(
                    1,
                    r.getPemeriksaan()
                     .getIdPeriksa());

            ps.setInt(
                    2,
                    r.getObat()
                     .getIdObat());

            ps.setInt(
                    3,
                    r.getJumlah());

            ps.setString(
                    4,
                    r.getDosis());

            ps.setString(
                    5,
                    r.getKeterangan());

            ps.setInt(
                    6,
                    r.getIdResep());

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    // ==========================
    // DELETE
    // ==========================
    public void deleteResep(
            int id) {

        try {

            String sql =
                    "DELETE FROM resep_obat WHERE id_resep=?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}