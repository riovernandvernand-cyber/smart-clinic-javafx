package dao;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Poli;

import java.sql.*;

public class PoliDAO {

    Connection conn = DBConnection.connect();

    // =====================
    // GET ALL
    // =====================
    public ObservableList<Poli> getAllPoli() {

        ObservableList<Poli> list =
                FXCollections.observableArrayList();

        try {

            String sql =
                    "SELECT * FROM poli";

            Statement st =
                    conn.createStatement();

            ResultSet rs =
                    st.executeQuery(sql);

            while (rs.next()) {

                list.add(
                        new Poli(
                                rs.getInt("id_poli"),
                                rs.getString("nama_poli"),
                                rs.getString("keterangan")
                        )
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list;
    }

    // =====================
    // INSERT
    // =====================
    public void insertPoli(Poli p) {

        try {

            String sql =
                    "INSERT INTO poli(nama_poli,keterangan) VALUES(?,?)";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1, p.getNamaPoli());
            ps.setString(2, p.getKeterangan());

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =====================
    // UPDATE
    // =====================
    public void updatePoli(Poli p) {

        try {

            String sql =
                    "UPDATE poli SET nama_poli=?, keterangan=? WHERE id_poli=?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1, p.getNamaPoli());
            ps.setString(2, p.getKeterangan());
            ps.setInt(3, p.getIdPoli());

            ps.executeUpdate();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // =====================
    // DELETE
    // =====================
    public void deletePoli(int id) {

        try {

            String sql =
                    "DELETE FROM poli WHERE id_poli=?";

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
    public ObservableList<Poli> searchPoli(String keyword) {

    ObservableList<Poli> list =
            FXCollections.observableArrayList();

    try {

        String sql =
                "SELECT * FROM poli " +
                "WHERE CAST(id_poli AS CHAR) LIKE ? " +
                "OR nama_poli LIKE ? " +
                "OR keterangan LIKE ?";

        PreparedStatement ps =
                conn.prepareStatement(sql);

        String cari = "%" + keyword + "%";

        ps.setString(1, cari);
        ps.setString(2, cari);
        ps.setString(3, cari);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            list.add(
                    new Poli(
                            rs.getInt("id_poli"),
                            rs.getString("nama_poli"),
                            rs.getString("keterangan")
                    )
            );
        }

    } catch (Exception e) {

        e.printStackTrace();
    }

    return list;
    }
}