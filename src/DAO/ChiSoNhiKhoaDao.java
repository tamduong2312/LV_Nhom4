package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import MODEL.ChiSoNhiKhoa;

public class ChiSoNhiKhoaDao {
    private final String DB_URL = "jdbc:mysql://localhost:3306/dbphongkham";
    private final String USER = "root";
    private final String PASS = "";

  
    public boolean insert(ChiSoNhiKhoa nk) {
        String sql = "INSERT INTO chi_so_nhi_khoa (ma_phieu_kham, can_nang, chieu_cao, vong_dau, nhiet_do, nhip_tim, nhip_tho, " +
                     "kham_tai_mui_hong, kham_ho_hap, kham_da_niem_mac, tinh_trang_dinh_duong, tam_ly_hanh_vi, co_quan_khac, ghi_chu) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, nk.getMaPhieuKham());
            ps.setFloat(2, nk.getCanNang());
            ps.setFloat(3, nk.getChieuCao());
            ps.setFloat(4, nk.getVongDau());
            ps.setFloat(5, nk.getNhietDo());
            ps.setInt(6, nk.getNhipTim());
            ps.setInt(7, nk.getNhipTho());
            ps.setString(8, nk.getTaiMuiHong());
            ps.setString(9, nk.getHoHap());
            ps.setString(10, nk.getDaLieu());
            ps.setString(11, nk.getDinhDuong());
            ps.setString(12, nk.getTamLy());
            ps.setString(13, nk.getCoQuanKhac());
            ps.setString(14, nk.getGhiChu());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean update(ChiSoNhiKhoa nk) {
        String sql = "UPDATE chi_so_nhi_khoa SET can_nang=?, chieu_cao=?, vong_dau=?, nhiet_do=?, nhip_tim=?, nhip_tho=?, " +
                     "kham_tai_mui_hong=?, kham_ho_hap=?, kham_da_niem_mac=?, tinh_trang_dinh_duong=?, tam_ly_hanh_vi=?, co_quan_khac=?, ghi_chu=? WHERE ma_phieu_kham=?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setFloat(1, nk.getCanNang());
            ps.setFloat(2, nk.getChieuCao());
            ps.setFloat(3, nk.getVongDau());
            ps.setFloat(4, nk.getNhietDo());
            ps.setInt(5, nk.getNhipTim());
            ps.setInt(6, nk.getNhipTho());
            ps.setString(7, nk.getTaiMuiHong());
            ps.setString(8, nk.getHoHap());
            ps.setString(9, nk.getDaLieu());
            ps.setString(10, nk.getDinhDuong());
            ps.setString(11, nk.getTamLy());
            ps.setString(12, nk.getCoQuanKhac());
            ps.setString(13, nk.getGhiChu());
            ps.setInt(14, nk.getMaPhieuKham());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

 
    public ChiSoNhiKhoa getByMaPhieuKham(int maPhieu) {
        String sql = "SELECT * FROM chi_so_nhi_khoa WHERE ma_phieu_kham = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, maPhieu);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ChiSoNhiKhoa nk = new ChiSoNhiKhoa();
                nk.setId(rs.getInt("id"));
                nk.setMaPhieuKham(rs.getInt("ma_phieu_kham"));
                nk.setCanNang(rs.getFloat("can_nang"));
                nk.setChieuCao(rs.getFloat("chieu_cao"));
                nk.setVongDau(rs.getFloat("vong_dau"));
                nk.setNhietDo(rs.getFloat("nhiet_do"));
                nk.setNhipTim(rs.getInt("nhip_tim"));
                nk.setNhipTho(rs.getInt("nhip_tho"));
                nk.setTaiMuiHong(rs.getString("kham_tai_mui_hong"));
                nk.setHoHap(rs.getString("kham_ho_hap"));
                nk.setDaLieu(rs.getString("kham_da_niem_mac"));
                nk.setDinhDuong(rs.getString("tinh_trang_dinh_duong"));
                nk.setTamLy(rs.getString("tam_ly_hanh_vi"));
                nk.setCoQuanKhac(rs.getString("co_quan_khac"));
                nk.setGhiChu(rs.getString("ghi_chu"));
                return nk;
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