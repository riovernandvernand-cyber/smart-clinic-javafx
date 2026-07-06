package dao;

import database.DBConnection;
import model.PrediksiML;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class PrediksiMLDAO {

    Connection conn = DBConnection.connect();

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
            ps.setDouble(2, p.getGlucose());
            ps.setDouble(3, p.getBloodPressure());
            ps.setDouble(4, p.getSkinThickness());
            ps.setDouble(5, p.getInsulin());
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

}