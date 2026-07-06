package dao;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Dokter;
import model.Jadwal;

import java.sql.*;

public class JadwalDAO {

    Connection conn = DBConnection.connect();

    // =====================
    // GET ALL
    // =====================
    public ObservableList<Jadwal> getAllJadwal() {

        ObservableList<Jadwal> list =
                FXCollections.observableArrayList();

        try {

            String sql =
                    "SELECT j.*, d.nama " +
                    "FROM jadwal j " +
                    "JOIN dokter d ON j.id_dokter = d.id_dokter";

            Statement st =
                    conn.createStatement();

            ResultSet rs =
                    st.executeQuery(sql);

            while (rs.next()) {

                Dokter dokter =
                        new Dokter();

                dokter.setIdDokter(
                        rs.getInt("id_dokter"));

                dokter.setNama(
                        rs.getString("nama"));

                Jadwal jadwal =
                        new Jadwal();

                jadwal.setIdJadwal(
                        rs.getInt("id_jadwal"));

                jadwal.setDokter(dokter);

                jadwal.setHari(
                        rs.getString("hari"));

                jadwal.setJamMulai(
                        rs.getTime("jam_mulai"));

                jadwal.setJamSelesai(
                        rs.getTime("jam_selesai"));

                list.add(jadwal);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    // =====================
    // INSERT
    // =====================
    public void insertJadwal(
            Jadwal jadwal) {

        try {

            String sql =
                    "INSERT INTO jadwal(id_dokter,hari,jam_mulai,jam_selesai) VALUES(?,?,?,?)";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setInt(
                    1,
                    jadwal.getDokter()
                            .getIdDokter());

            ps.setString(
                    2,
                    jadwal.getHari());

            ps.setTime(
                    3,
                    jadwal.getJamMulai());

            ps.setTime(
                    4,
                    jadwal.getJamSelesai());

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =====================
    // UPDATE
    // =====================
    public void updateJadwal(
            Jadwal jadwal) {

        try {

            String sql =
                    "UPDATE jadwal SET id_dokter=?, hari=?, jam_mulai=?, jam_selesai=? WHERE id_jadwal=?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setInt(
                    1,
                    jadwal.getDokter()
                            .getIdDokter());

            ps.setString(
                    2,
                    jadwal.getHari());

            ps.setTime(
                    3,
                    jadwal.getJamMulai());

            ps.setTime(
                    4,
                    jadwal.getJamSelesai());

            ps.setInt(
                    5,
                    jadwal.getIdJadwal());

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =====================
    // DELETE
    // =====================
    public void deleteJadwal(
            int id) {

        try {

            String sql =
                    "DELETE FROM jadwal WHERE id_jadwal=?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =====================
    // SEARCH
    // =====================
    public ObservableList<Jadwal> searchJadwal(
            String keyword) {

        ObservableList<Jadwal> list =
                FXCollections.observableArrayList();

        try {

            String sql =
                    "SELECT j.*, d.nama " +
                    "FROM jadwal j " +
                    "JOIN dokter d ON j.id_dokter = d.id_dokter " +
                    "WHERE CAST(j.id_jadwal AS CHAR) LIKE ? " +
                    "OR d.nama LIKE ? " +
                    "OR j.hari LIKE ?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            String cari =
                    "%" + keyword + "%";

            ps.setString(1, cari);
            ps.setString(2, cari);
            ps.setString(3, cari);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                Dokter dokter =
                        new Dokter();

                dokter.setIdDokter(
                        rs.getInt("id_dokter"));

                dokter.setNama(
                        rs.getString("nama"));

                Jadwal jadwal =
                        new Jadwal();

                jadwal.setIdJadwal(
                        rs.getInt("id_jadwal"));

                jadwal.setDokter(dokter);

                jadwal.setHari(
                        rs.getString("hari"));

                jadwal.setJamMulai(
                        rs.getTime("jam_mulai"));

                jadwal.setJamSelesai(
                        rs.getTime("jam_selesai"));

                list.add(jadwal);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }
}