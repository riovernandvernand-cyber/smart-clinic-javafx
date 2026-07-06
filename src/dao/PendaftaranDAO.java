package dao;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.sql.*;

public class PendaftaranDAO {

    Connection conn = DBConnection.connect();

    // GET ALL
    public ObservableList<Pendaftaran> getAllPendaftaran() {

        ObservableList<Pendaftaran> list =
                FXCollections.observableArrayList();

        try {

            String sql =
                    "SELECT p.*, " +
                    "ps.nama AS nama_pasien, " +
                    "d.nama AS nama_dokter " +
                    "FROM pendaftaran p " +
                    "JOIN pasien ps ON p.id_pasien = ps.id_pasien " +
                    "JOIN dokter d ON p.id_dokter = d.id_dokter";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()) {

                Pasien pasien = new Pasien();
                pasien.setIdPasien(
                        rs.getInt("id_pasien"));
                pasien.setNama(
                        rs.getString("nama_pasien"));

                Dokter dokter = new Dokter();
                dokter.setIdDokter(
                        rs.getInt("id_dokter"));
                dokter.setNama(
                        rs.getString("nama_dokter"));

                list.add(
                        new Pendaftaran(
                                rs.getInt("id_daftar"),
                                rs.getDate("tanggal"),
                                rs.getString("keluhan"),
                                pasien,
                                dokter
                        )
                );
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // INSERT
    public void insertPendaftaran(
            Pendaftaran p) {

        try {

            String sql =
                    "INSERT INTO pendaftaran " +
                    "(tanggal, keluhan, id_pasien, id_dokter) " +
                    "VALUES (?,?,?,?)";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setDate(
                    1,
                    new java.sql.Date(
                            p.getTanggal().getTime()));

            ps.setString(
                    2,
                    p.getKeluhan());

            ps.setInt(
                    3,
                    p.getPasien().getIdPasien());

            ps.setInt(
                    4,
                    p.getDokter().getIdDokter());

            ps.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // UPDATE
    public void updatePendaftaran(
            Pendaftaran p) {

        try {

            String sql =
                    "UPDATE pendaftaran " +
                    "SET tanggal=?, keluhan=?, " +
                    "id_pasien=?, id_dokter=? " +
                    "WHERE id_daftar=?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setDate(
                    1,
                    new java.sql.Date(
                            p.getTanggal().getTime()));

            ps.setString(
                    2,
                    p.getKeluhan());

            ps.setInt(
                    3,
                    p.getPasien().getIdPasien());

            ps.setInt(
                    4,
                    p.getDokter().getIdDokter());

            ps.setInt(
                    5,
                    p.getIdDaftar());

            ps.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE
    public void deletePendaftaran(
            int id) {

        try {

            String sql =
                    "DELETE FROM pendaftaran " +
                    "WHERE id_daftar=?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}