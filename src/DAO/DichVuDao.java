package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import MODEL.DichVu;
import MODEL.Phong;

public class DichVuDao {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dbphongkham";
    private static final String USER = "root";
    private static final String PASS = "";

   
    public List<DichVu> getDichVu() {
        List<DichVu> list = new ArrayList<>();
        String query = "SELECT * FROM dich_vu";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                DichVu nv = new DichVu();
                nv.setMaDichVu(rs.getInt("ma_dich_vu"));
                nv.setTenDichVu(rs.getString("ten_dich_vu"));
                nv.setDonGia(rs.getLong("don_gia"));
                nv.setLoai_Dich_Vu(rs.getString("loai_dich_vu"));
                nv.setMaphong(rs.getInt("phong"));
                nv.setMachuyenkhoa(rs.getInt("ma_chuyen_khoa")); 
                list.add(nv);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public List<String> timKiemDichVu(String keyword) {
        List<String> list = new ArrayList<>();
        String query = "SELECT ten_dich_vu FROM dich_vu WHERE ten_dich_vu LIKE ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, "%" + keyword + "%");
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) { list.add(rs.getString("ten_dich_vu")); }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    public List<Phong> getAll() {
        List<Phong> list = new ArrayList<>();
        String sql = "SELECT * FROM phong_chuc_nang";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Phong p = new Phong();
                p.setMaphong(rs.getInt("ma_phong"));
                p.setTenphong(rs.getString("ten_phong"));
                p.setLoaiphong(rs.getString("loai_phong"));
                list.add(p);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }



    public boolean themDichVu(DichVu dv) {
        String sql = "INSERT INTO dich_vu (ten_dich_vu, don_gia, loai_dich_vu, phong, ma_chuyen_khoa) VALUES (?,?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dv.getTenDichVu());
            ps.setLong(2, (long) dv.getDonGia());
            ps.setString(3, dv.getLoai_Dich_Vu());
            ps.setInt(4, dv.getMaphong());
            ps.setInt(5, dv.getMachuyenkhoa());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public boolean suaDichVu(DichVu dv) {
        String sql = "UPDATE dich_vu SET ten_dich_vu=?, don_gia=?, loai_dich_vu=?, phong=?, ma_chuyen_khoa=? WHERE ma_dich_vu=?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, dv.getTenDichVu());
            ps.setLong(2, (long) dv.getDonGia());
            ps.setString(3, dv.getLoai_Dich_Vu());
            ps.setInt(4, dv.getMaphong());
            ps.setInt(5, dv.getMachuyenkhoa());
            ps.setInt(6, dv.getMaDichVu());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    public boolean xoaDichVu(int id) {
        String sql = "DELETE FROM dich_vu WHERE ma_dich_vu=?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }

    
    
    public List<DichVu> getDichVuByTen(String tendichvu) {
        List<DichVu> list = new ArrayList<>();
        String query = "SELECT * FROM dich_vu where ten_dich_vu = ? ";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)){
        	
        	pstmt.setString(1, tendichvu);
        		
        
             ResultSet rs = pstmt.executeQuery() ;

            while (rs.next()) {
            	DichVu nv = new DichVu();
                nv.setMaDichVu(rs.getInt("ma_dich_vu"));
                nv.setTenDichVu(rs.getString("ten_dich_vu"));
                nv.setDonGia(rs.getLong("don_gia"));
                nv.setLoai_Dich_Vu(rs.getString("loai_dich_vu"));
                nv.setMaphong(rs.getInt("phong"));
                nv.setMachuyenkhoa(rs.getInt("ma_chuyen_khoa"));
   
                list.add(nv);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    public List<DichVu> timKiemDichVuFull(String keyword) {
        List<DichVu> list = new ArrayList<>();
        String query = "SELECT * FROM dich_vu WHERE ten_dich_vu LIKE ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                DichVu nv = new DichVu();
                nv.setMaDichVu(rs.getInt("ma_dich_vu"));
                nv.setTenDichVu(rs.getString("ten_dich_vu"));
                nv.setDonGia(rs.getLong("don_gia"));
                nv.setLoai_Dich_Vu(rs.getString("loai_dich_vu"));
                nv.setMaphong(rs.getInt("phong"));
                nv.setMachuyenkhoa(rs.getInt("ma_chuyen_khoa"));
                list.add(nv);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
}