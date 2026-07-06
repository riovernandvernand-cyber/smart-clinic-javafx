package dao;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Pemeriksaan;
import model.RekamMedis;

import java.sql.*;

public class RekamMedisDAO {

    Connection conn = DBConnection.connect();

    // =====================
    // GET ALL
    // =====================
    public ObservableList<RekamMedis> getAll() {

    ObservableList<RekamMedis> list =
            FXCollections.observableArrayList();

    try {

        String sql =
                "SELECT rm.*, " +
                "p.id_periksa, " +
                "p.diagnosa, " +
                "p.hasil_prediksi, " +
                "p.catatan, " +
                "ps.nama " +
                "FROM rekam_medis rm " +
                "JOIN pemeriksaan p ON rm.id_periksa = p.id_periksa " +
                "JOIN pendaftaran pd ON p.id_daftar = pd.id_daftar " +
                "JOIN pasien ps ON pd.id_pasien = ps.id_pasien";

        Statement st = conn.createStatement();

        ResultSet rs = st.executeQuery(sql);

        while (rs.next()) {

            Pemeriksaan periksa =
                    new Pemeriksaan();

            periksa.setIdPeriksa(
                    rs.getInt("id_periksa"));

            periksa.setNamaPasien(
                    rs.getString("nama"));

            periksa.setDiagnosa(
                    rs.getString("diagnosa"));

            periksa.setHasilPrediksi(
                    rs.getString("hasil_prediksi"));

            periksa.setCatatan(
                    rs.getString("catatan"));

            RekamMedis rm =
                    new RekamMedis();

            rm.setIdRekam(
                    rs.getInt("id_rekam"));

            rm.setTanggal(
                    rs.getDate("tanggal"));

            rm.setRingkasan(
                    rs.getString("ringkasan"));

            rm.setPemeriksaan(periksa);

            list.add(rm);

        }

    } catch (Exception e) {

        e.printStackTrace();

    }

    return list;

}

    // =====================
    // INSERT
    // =====================
    public void insert(RekamMedis rm) {

        try {

            String sql =
                    "INSERT INTO rekam_medis(id_periksa,tanggal,ringkasan) VALUES(?,?,?)";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setInt(
                    1,
                    rm.getPemeriksaan().getIdPeriksa());

            ps.setDate(
                    2,
                    new java.sql.Date(
                            rm.getTanggal().getTime()));

            ps.setString(
                    3,
                    rm.getRingkasan());

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =====================
    // UPDATE
    // =====================
    public void update(RekamMedis rm) {

        try {

            String sql =
                    "UPDATE rekam_medis SET id_periksa=?,tanggal=?,ringkasan=? WHERE id_rekam=?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setInt(
                    1,
                    rm.getPemeriksaan().getIdPeriksa());

            ps.setDate(
                    2,
                    new java.sql.Date(
                            rm.getTanggal().getTime()));

            ps.setString(
                    3,
                    rm.getRingkasan());

            ps.setInt(
                    4,
                    rm.getIdRekam());

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =====================
    // DELETE
    // =====================
    public void delete(int id) {

        try {

            String sql =
                    "DELETE FROM rekam_medis WHERE id_rekam=?";

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
    public ObservableList<RekamMedis> search(String keyword) {

    ObservableList<RekamMedis> list =
            FXCollections.observableArrayList();

    try {

        String sql =
                "SELECT rm.*, " +
                "p.id_periksa, " +
                "p.diagnosa, " +
                "p.hasil_prediksi, " +
                "p.catatan, " +
                "ps.nama " +
                "FROM rekam_medis rm " +
                "JOIN pemeriksaan p ON rm.id_periksa = p.id_periksa " +
                "JOIN pendaftaran pd ON p.id_daftar = pd.id_daftar " +
                "JOIN pasien ps ON pd.id_pasien = ps.id_pasien " +
                "WHERE ps.nama LIKE ?";

        PreparedStatement ps =
                conn.prepareStatement(sql);

        ps.setString(
                1,
                "%" + keyword + "%");

        ResultSet rs =
                ps.executeQuery();

        while (rs.next()) {

            Pemeriksaan periksa =
                    new Pemeriksaan();

            periksa.setIdPeriksa(
                    rs.getInt("id_periksa"));

            periksa.setNamaPasien(
                    rs.getString("nama"));

            periksa.setDiagnosa(
                    rs.getString("diagnosa"));

            periksa.setHasilPrediksi(
                    rs.getString("hasil_prediksi"));

            periksa.setCatatan(
                    rs.getString("catatan"));

            RekamMedis rm =
                    new RekamMedis();

            rm.setIdRekam(
                    rs.getInt("id_rekam"));

            rm.setTanggal(
                    rs.getDate("tanggal"));

            rm.setRingkasan(
                    rs.getString("ringkasan"));

            rm.setPemeriksaan(periksa);

            list.add(rm);

        }

    } catch (Exception e) {

        e.printStackTrace();

    }

    return list;

}

}