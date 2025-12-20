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

                list.add(nv);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    // 2. Thêm phòng mới
    public boolean themPhong(PhongChucNang p) {
  
        String query = "INSERT INTO phong_chuc_nang (ten_phong, loai_phong) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, p.getTenphong());
            pstmt.setString(2, p.getLoaiphong());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 3. Sửa thông tin phòng
    public boolean suaPhong(PhongChucNang p) {
        String query = "UPDATE phong_chuc_nang SET ten_phong = ?, loai_phong = ? WHERE ma_phong = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, p.getTenphong());
            pstmt.setString(2, p.getLoaiphong());
            pstmt.setInt(3, p.getMaphong());

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 4. Xóa phòng
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

    // 5. Tìm kiếm phòng theo tên 
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