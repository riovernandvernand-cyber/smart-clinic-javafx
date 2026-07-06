package dao;

import database.DBConnection;
import model.PrediksiML;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PrediksiMLDAO {

    Connection conn = DBConnection.connect();

    // ==========================
    // SIMPAN DATA PREDIKSI
    // ==========================
    public void insert(PrediksiML p) {

        try {

            String sql =
                    "INSERT INTO prediksi_ml(" +
                    "pregnancies," +
                    "glucose," +
                    "blood_pressure," +
                    "skin_thickness," +
                    "insulin," +
                    "bmi," +
                    "dpf," +
                    "age," +
                    "hasil_prediksi," +
                    "probabilitas" +
                    ") VALUES (?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setInt(1, p.getPregnancies());
            ps.setInt(2, p.getGlucose());
            ps.setInt(3, p.getBloodPressure());
            ps.setInt(4, p.getSkinThickness());
            ps.setInt(5, p.getInsulin());
            ps.setDouble(6, p.getBmi());
            ps.setDouble(7, p.getDpf());
            ps.setInt(8, p.getAge());
            ps.setString(9, p.getHasilPrediksi());
            ps.setDouble(10, p.getProbabilitas());

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

    // ==========================
    // TAMPILKAN SEMUA DATA
    // ==========================
    public List<PrediksiML> getAll() {

        List<PrediksiML> list = new ArrayList<>();

        try {

            String sql =
                    "SELECT * FROM prediksi_ml ORDER BY id_prediksi DESC";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                PrediksiML p = new PrediksiML();

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