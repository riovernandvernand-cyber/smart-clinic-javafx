package dao;

import database.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DashboardDAO {

    private final Connection conn =
            DBConnection.connect();

    // ==========================
    // METHOD UMUM
    // ==========================

    private int getTotal(String table) {

        try {

            String sql =
                    "SELECT COUNT(*) FROM " + table;

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
    // TOTAL PASIEN
    // ==========================

    public int getTotalPasien() {

        return getTotal("pasien");

    }

    // ==========================
    // TOTAL REKAM MEDIS
    // ==========================

    public int getTotalRekam() {

        return getTotal("rekam_medis");

    }

    // ==========================
    // TOTAL PREDIKSI
    // ==========================

    public int getTotalPrediksi() {

        return getTotal("prediksi_ml");

    }

    // ==========================
    // TOTAL OBAT
    // ==========================

    public int getTotalObat() {

        return getTotal("obat");

    }

}