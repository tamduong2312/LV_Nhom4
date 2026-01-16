package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import MODEL.PhieuChuyenKhoa;

public class PhieuChuyenKhoaDao {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dbphongkham";
    private static final String USER = "root";
    private static final String PASS = "";
    
    
    public boolean luuPhieuChuyen(PhieuChuyenKhoa p) {
        String sql = "INSERT INTO phieu_chuyen_khoa (ma_phieu_kham, ma_benh_nhan, ma_nhan_vien, ma_ck_tu, ma_ck_den, tom_tat_lam_sang, ly_do_chuyen, id_dang_ky_moi) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, p.getMaPhieuKham()); 
            ps.setInt(2, p.getMaBenhNhan());
            ps.setInt(3, p.getMaNhanVien());
            ps.setInt(4, p.getMaCkTu());
            ps.setInt(5, p.getMaCkDen());
            ps.setString(6, p.getTomTatLamSang());
            ps.setString(7, p.getLyDoChuyen());
            ps.setInt(8, p.getIdDangKyMoi());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
