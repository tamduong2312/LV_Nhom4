package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import MODEL.ChiSoTongQuat;
import MODEL.DanhMucBenhLy;

public class DanhMucBenhLyDao {

    private final String DB_URL = "jdbc:mysql://localhost:3306/dbphongkham";
    private final String USER = "root";
    private final String PASS = "";
    
    

    

    public List<DanhMucBenhLy> getListDanhMucBenhLy(String keyword) {
        List<DanhMucBenhLy> list = new ArrayList<>();
        String query = "SELECT ten_benh, trieu_chung_goi_y FROM danh_muc_benh_ly WHERE ten_benh LIKE ? LIMIT 20";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
                DanhMucBenhLy b = new DanhMucBenhLy();
                b.setTen_benh(rs.getString("ten_benh"));
                b.setTrieu_chung_goi_y(rs.getString("trieu_chung_goi_y"));
                list.add(b);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }
    
    public List<DanhMucBenhLy> getAll() {
        List<DanhMucBenhLy> list = new ArrayList<>();
        String sql = "SELECT * FROM danh_muc_benh_ly ORDER BY id DESC";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                DanhMucBenhLy b = new DanhMucBenhLy();
                b.setId(rs.getInt("id"));
                b.setMa_icd(rs.getString("ma_icd")); 
                b.setTen_benh(rs.getString("ten_benh"));
                b.setTrieu_chung_goi_y(rs.getString("trieu_chung_goi_y"));
                b.setChuyen_khoa_lien_quan(rs.getString("chuyen_khoa_lien_quan")); 

                list.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    //  Thêm mới 
    public boolean them(DanhMucBenhLy b) {
    
        String sql = "INSERT INTO danh_muc_benh_ly (ma_icd, ten_benh, trieu_chung_goi_y, chuyen_khoa_lien_quan) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, b.getMa_icd());
            ps.setString(2, b.getTen_benh());
            ps.setString(3, b.getTrieu_chung_goi_y());
            ps.setString(4, b.getChuyen_khoa_lien_quan());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //  Sửa 
    public boolean sua(DanhMucBenhLy b) {
        String sql = "UPDATE danh_muc_benh_ly SET ma_icd=?, ten_benh=?, trieu_chung_goi_y=?, chuyen_khoa_lien_quan=? WHERE id=?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, b.getMa_icd());
            ps.setString(2, b.getTen_benh());
            ps.setString(3, b.getTrieu_chung_goi_y());
            ps.setString(4, b.getChuyen_khoa_lien_quan());
            ps.setInt(5, b.getId()); // Điều kiện where

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa 
    public boolean xoa(int id) {
        String sql = "DELETE FROM danh_muc_benh_ly WHERE id=?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //  Tìm kiếm 
    public List<DanhMucBenhLy> timKiem(String keyword) {
        List<DanhMucBenhLy> list = new ArrayList<>();
     
        String sql = "SELECT * FROM danh_muc_benh_ly WHERE ten_benh LIKE ? OR CAST(ma_icd AS CHAR) LIKE ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String key = "%" + keyword + "%";
            ps.setString(1, key);
            ps.setString(2, key);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                DanhMucBenhLy b = new DanhMucBenhLy();
                b.setId(rs.getInt("id"));
                b.setMa_icd(rs.getString("ma_icd"));
                b.setTen_benh(rs.getString("ten_benh"));
                b.setTrieu_chung_goi_y(rs.getString("trieu_chung_goi_y"));
                b.setChuyen_khoa_lien_quan(rs.getString("chuyen_khoa_lien_quan"));

                list.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
