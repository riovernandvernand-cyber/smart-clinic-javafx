package dao;

import database.DBConnection;
import model.PrediksiML;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LaporanDAO {

    Connection conn = DBConnection.connect();

    // ==========================
    // TOTAL PASIEN
    // ==========================
    public int totalPasien() {

        try {

            String sql = "SELECT COUNT(*) FROM pasien";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                return rs.getInt(1);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return 0;

    }

    // ==========================
    // TOTAL DOKTER
    // ==========================
    public int totalDokter() {

        try {

            String sql = "SELECT COUNT(*) FROM dokter";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                return rs.getInt(1);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return 0;

    }

    // ==========================
    // TOTAL PEMERIKSAAN
    // ==========================
    public int totalPemeriksaan() {

        try {

            String sql = "SELECT COUNT(*) FROM pemeriksaan";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                return rs.getInt(1);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return 0;

    }

    // ==========================
    // TOTAL PREDIKSI
    // ==========================
    public int totalPrediksi() {

        try {

            String sql = "SELECT COUNT(*) FROM prediksi_ml";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            if (rs.next()) {

                return rs.getInt(1);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return 0;

    }

    // ==========================
    // RIWAYAT PREDIKSI
    // ==========================
    public List<PrediksiML> getRiwayatPrediksi() {

        List<PrediksiML> list =
                new ArrayList<>();

        try {

            String sql =
                    "SELECT * FROM prediksi_ml ORDER BY id_prediksi DESC";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            while (rs.next()) {

                PrediksiML p =
                        new PrediksiML();

                p.setIdPrediksi(
                        rs.getInt("id_prediksi"));

                p.setPregnancies(
                        rs.getInt("pregnancies"));

                p.setGlucose(
                        rs.getInt("glucose"));

                p.setBloodPressure(
                        rs.getInt("blood_pressure"));

                p.setSkinThickness(
                        rs.getInt("skin_thickness"));

                p.setInsulin(
                        rs.getInt("insulin"));

                p.setBmi(
                        rs.getDouble("bmi"));

                p.setDpf(
                        rs.getDouble("dpf"));

                p.setAge(
                        rs.getInt("age"));

                p.setHasilPrediksi(
                        rs.getString("hasil_prediksi"));

                p.setProbabilitas(
                        rs.getDouble("probabilitas"));

                list.add(p);

            }

        } catch (Exception e) {

            e.printStackTrace();

        }

        return list;

    }

}