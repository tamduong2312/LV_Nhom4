package DAO;

import java.sql.*;
import MODEL.ChiSoRangHamMat;

public class ChiSoRangHamMatDao {
    private final String DB_URL = "jdbc:mysql://localhost:3306/dbphongkham";
    private final String USER = "root";
    private final String PASS = "";

    // 1. Thêm mới
    public boolean insert(ChiSoRangHamMat obj) {
        String sql = "INSERT INTO chi_so_rang_ham_mat (ma_phieu_kham, tinh_trang_rang, sau_rang, cao_rang, viem_nuou, " +
                     "khop_can, ghi_chu, niem_mac_mieng, do_lung_lay, phu_hinh_cu, benh_ly_khac) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, obj.getMaPhieuKham());
            ps.setString(2, obj.getTinhTrangRang());
            ps.setString(3, obj.getSauRang());
            ps.setString(4, obj.getCaoRang());
            ps.setString(5, obj.getViemNuou());
            ps.setString(6, obj.getKhopCan());
            ps.setString(7, obj.getGhiChu());
            ps.setString(8, obj.getNiemMacMieng());
            ps.setString(9, obj.getDoLungLay());
            ps.setString(10, obj.getPhuHinhCu());
            ps.setString(11, obj.getBenhLyKhac());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 2. Cập nhật
    public boolean update(ChiSoRangHamMat obj) {
        String sql = "UPDATE chi_so_rang_ham_mat SET tinh_trang_rang=?, sau_rang=?, cao_rang=?, viem_nuou=?, " +
                     "khop_can=?, ghi_chu=?, niem_mac_mieng=?, do_lung_lay=?, phu_hinh_cu=?, benh_ly_khac=? WHERE ma_phieu_kham=?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, obj.getTinhTrangRang());
            ps.setString(2, obj.getSauRang());
            ps.setString(3, obj.getCaoRang());
            ps.setString(4, obj.getViemNuou());
            ps.setString(5, obj.getKhopCan());
            ps.setString(6, obj.getGhiChu());
            ps.setString(7, obj.getNiemMacMieng());
            ps.setString(8, obj.getDoLungLay());
            ps.setString(9, obj.getPhuHinhCu());
            ps.setString(10, obj.getBenhLyKhac());
            ps.setInt(11, obj.getMaPhieuKham());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 3. Load dữ liệu cũ
    public ChiSoRangHamMat getByMaPhieuKham(int maPhieu) {
        String sql = "SELECT * FROM chi_so_rang_ham_mat WHERE ma_phieu_kham = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, maPhieu);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ChiSoRangHamMat obj = new ChiSoRangHamMat();
                obj.setId(rs.getInt("id"));
                obj.setMaPhieuKham(rs.getInt("ma_phieu_kham"));
                obj.setTinhTrangRang(rs.getString("tinh_trang_rang"));
                obj.setSauRang(rs.getString("sau_rang")); // Coi chừng DB là tinyint
                obj.setCaoRang(rs.getString("cao_rang"));
                obj.setViemNuou(rs.getString("viem_nuou"));
                obj.setKhopCan(rs.getString("khop_can"));
                obj.setGhiChu(rs.getString("ghi_chu"));
                obj.setNiemMacMieng(rs.getString("niem_mac_mieng"));
                obj.setDoLungLay(rs.getString("do_lung_lay"));
                obj.setPhuHinhCu(rs.getString("phu_hinh_cu"));
                obj.setBenhLyKhac(rs.getString("benh_ly_khac"));
                return obj;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 4. Cập nhật chẩn đoán vào bảng khám lâm sàng
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