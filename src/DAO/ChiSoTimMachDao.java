package DAO;

import java.sql.*;
import MODEL.ChiSoTimMach;

public class ChiSoTimMachDao {
    private final String DB_URL = "jdbc:mysql://localhost:3306/dbphongkham";
    private final String USER = "root";
    private final String PASS = "";

    public boolean insert(ChiSoTimMach obj) {
        String sql = "INSERT INTO chi_so_tim_mach (ma_phieu_kham, nhip_tim, huyet_ap_tam_thu, huyet_ap_tam_truong, " +
                     "cholesterol, triglyceride, duong_huyet, ecg_ket_qua, ghi_chu) VALUES (?,?,?,?,?,?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, obj.getMaPhieuKham());
            ps.setString(2, obj.getNhipTim());
            ps.setString(3, obj.getHuyetApTamThu());
            ps.setString(4, obj.getHuyetApTamTruong());
            ps.setString(5, obj.getCholesterol());
            ps.setFloat(6, obj.getTriglyceride());
            ps.setFloat(7, obj.getDuongHuyet());
            ps.setString(8, obj.getEcgKetQua());
            ps.setString(9, obj.getGhiChu());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(ChiSoTimMach obj) {
        String sql = "UPDATE chi_so_tim_mach SET nhip_tim=?, huyet_ap_tam_thu=?, huyet_ap_tam_truong=?, " +
                     "cholesterol=?, triglyceride=?, duong_huyet=?, ecg_ket_qua=?, ghi_chu=? WHERE ma_phieu_kham=?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, obj.getNhipTim());
            ps.setString(2, obj.getHuyetApTamThu());
            ps.setString(3, obj.getHuyetApTamTruong());
            ps.setString(4, obj.getCholesterol());
            ps.setFloat(5, obj.getTriglyceride());
            ps.setFloat(6, obj.getDuongHuyet());
            ps.setString(7, obj.getEcgKetQua());
            ps.setString(8, obj.getGhiChu());
            ps.setInt(9, obj.getMaPhieuKham());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ChiSoTimMach getByMaPhieuKham(int maPhieu) {
        String sql = "SELECT * FROM chi_so_tim_mach WHERE ma_phieu_kham = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, maPhieu);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ChiSoTimMach obj = new ChiSoTimMach();
                obj.setId(rs.getInt("id"));
                obj.setMaPhieuKham(rs.getInt("ma_phieu_kham"));
                obj.setNhipTim(rs.getString("nhip_tim"));
                obj.setHuyetApTamThu(rs.getString("huyet_ap_tam_thu"));
                obj.setHuyetApTamTruong(rs.getString("huyet_ap_tam_truong"));
                obj.setCholesterol(rs.getString("cholesterol"));
                obj.setTriglyceride(rs.getFloat("triglyceride"));
                obj.setDuongHuyet(rs.getFloat("duong_huyet"));
                obj.setEcgKetQua(rs.getString("ecg_ket_qua"));
                obj.setGhiChu(rs.getString("ghi_chu"));
                return obj;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public boolean updateChanDoanKhamLamSang(int maPhieu, String chanDoan) {
        String checkSql = "SELECT COUNT(*) FROM kham_lam_sang WHERE ma_phieu_kham = ?";
        String insertSql = "INSERT INTO kham_lam_sang (ma_phieu_kham, chan_doan_so_bo) VALUES (?, ?)";
        String updateSql = "UPDATE kham_lam_sang SET chan_doan_so_bo = ? WHERE ma_phieu_kham = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            PreparedStatement psCheck = conn.prepareStatement(checkSql);
            psCheck.setInt(1, maPhieu);
            ResultSet rs = psCheck.executeQuery();
            rs.next();
            boolean exists = rs.getInt(1) > 0;

            if (exists) {
                PreparedStatement psUpdate = conn.prepareStatement(updateSql);
                psUpdate.setString(1, chanDoan);
                psUpdate.setInt(2, maPhieu);
                return psUpdate.executeUpdate() > 0;
            } else {
                PreparedStatement psInsert = conn.prepareStatement(insertSql);
                psInsert.setInt(1, maPhieu);
                psInsert.setString(2, chanDoan);
                return psInsert.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}