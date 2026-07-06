package dao;

import database.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Petugas;

import java.sql.*;

public class PetugasDAO {

    Connection conn = DBConnection.connect();

    // LOAD DATA
    public ObservableList<Petugas> getAllPetugas() {

        ObservableList<Petugas> list =
                FXCollections.observableArrayList();

        try {

            Statement st =
                    conn.createStatement();

            ResultSet rs =
                    st.executeQuery(
                            "SELECT * FROM petugas");

            while(rs.next()) {

                list.add(
                        new Petugas(
                                rs.getInt("id_petugas"),
                                rs.getString("nama"),
                                rs.getString("jabatan"),
                                rs.getString("no_hp")
                        )
                );
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    // INSERT
    public void insertPetugas(Petugas p){

        try{

            String sql =
                    "INSERT INTO petugas(nama,jabatan,no_hp) VALUES(?,?,?)";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1, p.getNama());
            ps.setString(2, p.getJabatan());
            ps.setString(3, p.getNoHP());

            ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // UPDATE
    public void updatePetugas(Petugas p){

        try{

            String sql =
                    "UPDATE petugas SET nama=?, jabatan=?, no_hp=? WHERE id_petugas=?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1, p.getNama());
            ps.setString(2, p.getJabatan());
            ps.setString(3, p.getNoHP());
            ps.setInt(4, p.getIdPetugas());

            ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // DELETE
    public void deletePetugas(int id){

        try{

            String sql =
                    "DELETE FROM petugas WHERE id_petugas=?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setInt(1, id);

            ps.executeUpdate();

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // SEARCH
    public ObservableList<Petugas> searchPetugas(String keyword){

        ObservableList<Petugas> list =
                FXCollections.observableArrayList();

        try{

            String sql =
                    "SELECT * FROM petugas WHERE nama LIKE ?";

            PreparedStatement ps =
                    conn.prepareStatement(sql);

            ps.setString(1, "%" + keyword + "%");

            ResultSet rs =
                    ps.executeQuery();

            while(rs.next()){

                list.add(
                        new Petugas(
                                rs.getInt("id_petugas"),
                                rs.getString("nama"),
                                rs.getString("jabatan"),
                                rs.getString("no_hp")
                        )
                );
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        return list;
    }
}