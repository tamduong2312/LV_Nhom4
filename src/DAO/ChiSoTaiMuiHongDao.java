package DAO;

import java.sql.*;
import MODEL.ChiSoTaiMuiHong;

public class ChiSoTaiMuiHongDao {
    private final String DB_URL = "jdbc:mysql://localhost:3306/dbphongkham";
    private final String USER = "root";
    private final String PASS = "";

    // 1. Thêm mới
    public boolean insert(ChiSoTaiMuiHong obj) {
        String sql = "INSERT INTO chi_so_tai_mui_hong (ma_phieu_kham, thinh_luc_tai_trai, thinh_luc_tai_phai, ong_tai, " +
                     "mang_nhi_phai, mang_nhi_trai, tinh_trang_mui, vach_ngan, cuon_mui, khe_mui, " +
                     "soi_tai_mui_hong, tinh_trang_hong, amidan, thanh_quan, ghi_chu) " +
                     "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, obj.getMaPhieuKham());
            ps.setString(2, obj.getThinhLucTaiTrai());
            ps.setString(3, obj.getThinhLucTaiPhai());
            ps.setString(4, obj.getOngTai());
            ps.setString(5, obj.getMangNhiPhai());
            ps.setString(6, obj.getMangNhiTrai());
            ps.setString(7, obj.getTinhTrangMui());
            ps.setString(8, obj.getVachNgan());
            ps.setString(9, obj.getCuonMui());
            ps.setString(10, obj.getKheMui());
            ps.setString(11, obj.getSoiTaiMuiHong());
            ps.setString(12, obj.getTinhTrangHong());
            ps.setString(13, obj.getAmidan());
            ps.setString(14, obj.getThanhQuan());
            ps.setString(15, obj.getGhiChu());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 2. Cập nhật
    public boolean update(ChiSoTaiMuiHong obj) {
        String sql = "UPDATE chi_so_tai_mui_hong SET thinh_luc_tai_trai=?, thinh_luc_tai_phai=?, ong_tai=?, " +
                     "mang_nhi_phai=?, mang_nhi_trai=?, tinh_trang_mui=?, vach_ngan=?, cuon_mui=?, khe_mui=?, " +
                     "soi_tai_mui_hong=?, tinh_trang_hong=?, amidan=?, thanh_quan=?, ghi_chu=? WHERE ma_phieu_kham=?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, obj.getThinhLucTaiTrai());
            ps.setString(2, obj.getThinhLucTaiPhai());
            ps.setString(3, obj.getOngTai());
            ps.setString(4, obj.getMangNhiPhai());
            ps.setString(5, obj.getMangNhiTrai());
            ps.setString(6, obj.getTinhTrangMui());
            ps.setString(7, obj.getVachNgan());
            ps.setString(8, obj.getCuonMui());
            ps.setString(9, obj.getKheMui());
            ps.setString(10, obj.getSoiTaiMuiHong());
            ps.setString(11, obj.getTinhTrangHong());
            ps.setString(12, obj.getAmidan());
            ps.setString(13, obj.getThanhQuan());
            ps.setString(14, obj.getGhiChu());
            ps.setInt(15, obj.getMaPhieuKham());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // 3. Load dữ liệu cũ
    public ChiSoTaiMuiHong getByMaPhieuKham(int maPhieu) {
        String sql = "SELECT * FROM chi_so_tai_mui_hong WHERE ma_phieu_kham = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, maPhieu);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ChiSoTaiMuiHong obj = new ChiSoTaiMuiHong();
                obj.setId(rs.getInt("id"));
                obj.setMaPhieuKham(rs.getInt("ma_phieu_kham"));
                obj.setThinhLucTaiTrai(rs.getString("thinh_luc_tai_trai"));
                obj.setThinhLucTaiPhai(rs.getString("thinh_luc_tai_phai"));
                obj.setOngTai(rs.getString("ong_tai"));
                obj.setMangNhiPhai(rs.getString("mang_nhi_phai"));
                obj.setMangNhiTrai(rs.getString("mang_nhi_trai"));
                obj.setTinhTrangMui(rs.getString("tinh_trang_mui"));
                obj.setVachNgan(rs.getString("vach_ngan"));
                obj.setCuonMui(rs.getString("cuon_mui"));
                obj.setKheMui(rs.getString("khe_mui"));
                obj.setSoiTaiMuiHong(rs.getString("soi_tai_mui_hong"));
                obj.setTinhTrangHong(rs.getString("tinh_trang_hong"));
                obj.setAmidan(rs.getString("amidan"));
                obj.setThanhQuan(rs.getString("thanh_quan"));
                obj.setGhiChu(rs.getString("ghi_chu"));
                return obj;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 4. Cập nhật chẩn đoán vào bảng khám lâm sàng chung
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