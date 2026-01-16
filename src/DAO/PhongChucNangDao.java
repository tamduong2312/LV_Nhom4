package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import MODEL.PhongChucNang;

public class PhongChucNangDao {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/dbphongkham";
    private static final String USER = "root";
    private static final String PASS = "";


    public List<PhongChucNang> getPhongChucNang() {
        List<PhongChucNang> list = new ArrayList<>();
        String query = "SELECT * FROM phong_chuc_nang";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                PhongChucNang nv = new PhongChucNang();
                nv.setMaphong(rs.getInt("ma_phong"));
                nv.setTenphong(rs.getString("ten_phong"));
                nv.setLoaiphong(rs.getString("loai_phong"));
                nv.setMack(rs.getInt("ma_chuyen_khoa"));
                nv.setMachucvu(rs.getInt("ma_chuc_vu"));

                list.add(nv);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    public boolean themPhong(PhongChucNang p) {

        String query = "INSERT INTO phong_chuc_nang (ten_phong, loai_phong, ma_chuyen_khoa, ma_chuc_vu) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, p.getTenphong());
            pstmt.setString(2, p.getLoaiphong());

   
            if (p.getMack() > 0) {
                pstmt.setInt(3, p.getMack());
            } else {
                pstmt.setNull(3, java.sql.Types.INTEGER);
            }


            if (p.getMachucvu() > 0) {
                pstmt.setInt(4, p.getMachucvu());
            } else {
                pstmt.setNull(4, java.sql.Types.INTEGER);
            }

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
       	  throw new RuntimeException(e.getMessage());
        }
    }

    public boolean suaPhong(PhongChucNang p) {

        String query = "UPDATE phong_chuc_nang SET ten_phong = ?, loai_phong = ?, ma_chuyen_khoa = ?, ma_chuc_vu = ? WHERE ma_phong = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, p.getTenphong());
            pstmt.setString(2, p.getLoaiphong());


            if (p.getMack() > 0) {
                pstmt.setInt(3, p.getMack());
            } else {
                pstmt.setNull(3, java.sql.Types.INTEGER);
            }

       
            if (p.getMachucvu() > 0) {
                pstmt.setInt(4, p.getMachucvu());
            } else {
                pstmt.setNull(4, java.sql.Types.INTEGER);
            }


            pstmt.setInt(5, p.getMaphong());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
       	  throw new RuntimeException(e.getMessage());
        }
    }

    public boolean xoaPhong(int maPhong) {
        String query = "DELETE FROM phong_chuc_nang WHERE ma_phong = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, maPhong);

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<PhongChucNang> timKiemPhong(String keyword) {
        List<PhongChucNang> list = new ArrayList<>();
        String query = "SELECT * FROM phong_chuc_nang WHERE ten_phong LIKE ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                PhongChucNang nv = new PhongChucNang();
                nv.setMaphong(rs.getInt("ma_phong"));
                nv.setTenphong(rs.getString("ten_phong"));
                nv.setLoaiphong(rs.getString("loai_phong"));

                list.add(nv);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}