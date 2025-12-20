package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import MODEL.KhamLamSang;

public class KhamLamSangDao {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/dbphongkham";
    private static final String USER = "root";
    private static final String PASS = "";

  
    public Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }


    public boolean themKhamLamSang(KhamLamSang kls) {
        String sql = "INSERT INTO kham_lam_sang " +
                     "(ma_phieu_kham, ly_do_kham, tien_su_ban_than, benh_su, " +
                     "chan_doan_so_bo, loi_dan_bac_si, ket_qua_kham_can_lam_sang, kham_lam_sang) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, kls.getMaPhieuKham());
            pstmt.setString(2, kls.getLyDoKham());
            pstmt.setString(3, kls.getTienSuBanThan());

            pstmt.setString(4, kls.getBenhSu());
            pstmt.setString(5, kls.getChanDoanSoBo());
            pstmt.setString(6, kls.getLoiDanBacSi());
            pstmt.setString(7, kls.getKetQuaKhamCanLamSang());
            pstmt.setString(8, kls.getKhamLamSang());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    
 
    public KhamLamSang getKhamLamSangByMaPhieu(int maPhieuKham) {
        KhamLamSang kls = null;
        String sql = "SELECT * FROM kham_lam_sang WHERE ma_phieu_kham = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             
            pstmt.setInt(1, maPhieuKham);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                kls = new KhamLamSang();
                kls.setId(rs.getInt("id"));
                kls.setMaPhieuKham(rs.getInt("ma_phieu_kham"));
                kls.setLyDoKham(rs.getString("ly_do_kham"));
                kls.setTienSuBanThan(rs.getString("tien_su_ban_than"));
       
                kls.setBenhSu(rs.getString("benh_su"));
                kls.setChanDoanSoBo(rs.getString("chan_doan_so_bo"));
                kls.setLoiDanBacSi(rs.getString("loi_dan_bac_si"));
                kls.setKetQuaKhamCanLamSang(rs.getString("ket_qua_kham_can_lam_sang"));
                kls.setKhamLamSang(rs.getString("kham_lam_sang"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return kls;
    }

    public boolean bsCanUpdateKhamLamSang(KhamLamSang kls) {
        String sql = "UPDATE kham_lam_sang SET "
                   + "ly_do_kham = ?, "
                   + "tien_su_ban_than = ?, "
            
                   + "benh_su = ?, "
                   + "chan_doan_so_bo = ?, "
                   + "loi_dan_bac_si = ?, "
                   + "ket_qua_kham_can_lam_sang = ?, "
                   + "kham_lam_sang = ? "
                   + "WHERE ma_phieu_kham = ?"; 
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, kls.getLyDoKham());
            pstmt.setString(2, kls.getTienSuBanThan());

            pstmt.setString(3, kls.getBenhSu());
            pstmt.setString(4, kls.getChanDoanSoBo());
            pstmt.setString(5, kls.getLoiDanBacSi());
            pstmt.setString(6, kls.getKetQuaKhamCanLamSang());
            pstmt.setString(7, kls.getKhamLamSang());
            
   
            pstmt.setInt(8, kls.getMaPhieuKham());

            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}