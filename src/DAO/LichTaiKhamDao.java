package DAO;

import MODEL.LichTaiKham;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LichTaiKhamDao {
    

    private final String DB_URL = "jdbc:mysql://localhost:3306/dbphongkham";
    private final String USER = "root";
    private final String PASS = "";

    public List<LichTaiKham> getAll() {
        List<LichTaiKham> list = new ArrayList<>();
        String sql = "SELECT * FROM lich_tai_kham ORDER BY ngay_tai_kham DESC";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                LichTaiKham ltk = new LichTaiKham();
                ltk.setId(rs.getInt("id"));
                ltk.setMaBenhNhan(rs.getInt("ma_benh_nhan"));
ltk.setMack(rs.getInt("ma_chuyen_khoa"));
                int mpk = rs.getInt("ma_phieu_kham");
                ltk.setMaPhieuKham(rs.wasNull() ? null : mpk);
                
                ltk.setMaNhanVien(rs.getInt("ma_nhan_vien"));
                ltk.setNgayTaiKham(rs.getDate("ngay_tai_kham"));
                ltk.setGhiChu(rs.getString("ghi_chu"));
                ltk.setTrangThai(rs.getString("trang_thai"));
                list.add(ltk);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public boolean themLichHen(LichTaiKham ltk) {
        String sql = "INSERT INTO lich_tai_kham (ma_benh_nhan,ma_chuyen_khoa, ma_phieu_kham, ma_nhan_vien, ngay_tai_kham, ghi_chu, trang_thai) VALUES (?,?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, ltk.getMaBenhNhan()); 
            pstm.setInt(2, ltk.getMack());
            
            if (ltk.getMaPhieuKham() != null) {
                pstm.setInt(3, ltk.getMaPhieuKham());
            } else {
                pstm.setNull(3, java.sql.Types.INTEGER);
            }

            pstm.setInt(4, ltk.getMaNhanVien()); 
            pstm.setDate(5, ltk.getNgayTaiKham());
            pstm.setString(6, ltk.getGhiChu());
            pstm.setString(7, ltk.getTrangThai());

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean suaLichHen(LichTaiKham ltk) {
        String sql = "UPDATE lich_tai_kham SET ngay_tai_kham = ?, ghi_chu = ?, trang_thai = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setDate(1, ltk.getNgayTaiKham());
            pstm.setString(2, ltk.getGhiChu());
            pstm.setString(3, ltk.getTrangThai());
            pstm.setInt(4, ltk.getId());

            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean xoaLichHen(int id) {
        String sql = "DELETE FROM lich_tai_kham WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, id);
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
 
    public List<LichTaiKham> getByMaBenhNhan(int maBenhNhan) {
        List<LichTaiKham> list = new ArrayList<>();
        String sql = "SELECT * FROM lich_tai_kham WHERE ma_benh_nhan = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                PreparedStatement pstm = conn.prepareStatement(sql)){
        		pstm.setInt(1, maBenhNhan);
                ResultSet rs = pstm.executeQuery() ;

               while (rs.next()) {
                   LichTaiKham ltk = new LichTaiKham();
                   ltk.setId(rs.getInt("id"));
                   ltk.setMaBenhNhan(rs.getInt("ma_benh_nhan"));
                   ltk.setMack(rs.getInt("ma_chuyen_khoa"));
                   
           
                   int mpk = rs.getInt("ma_phieu_kham");
                   ltk.setMaPhieuKham(rs.wasNull() ? null : mpk);
                   
                   ltk.setMaNhanVien(rs.getInt("ma_nhan_vien"));
                   ltk.setNgayTaiKham(rs.getDate("ngay_tai_kham"));
                   ltk.setGhiChu(rs.getString("ghi_chu"));
                   ltk.setTrangThai(rs.getString("trang_thai"));
                   list.add(ltk);
               }
           } catch (SQLException e) {
               e.printStackTrace();
           }
        return list;
    }
    
 
    public Date getNgayTaiKhamByMaPK(int maPK) {
        String sql = "SELECT ngay_tai_kham FROM lich_tai_kham WHERE ma_phieu_kham = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, maPK);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return rs.getDate("ngay_tai_kham");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<LichTaiKham> timKiemLichHen(String tuKhoa) {
        List<LichTaiKham> list = new ArrayList<>();

        String sql = """
            SELECT ltk.* 
            FROM lich_tai_kham ltk
            JOIN benh_nhan bn ON ltk.ma_benh_nhan = bn.ma_benh_nhan
            WHERE bn.ho_ten LIKE ? OR bn.so_dien_thoai LIKE ?
            ORDER BY ltk.ngay_tai_kham DESC
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            
            String searchVal = "%" + tuKhoa.trim() + "%";
            pstm.setString(1, searchVal);
            pstm.setString(2, searchVal);
            
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToModel(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public List<LichTaiKham> locLichHenTheoNgay(String tuNgay, String denNgay) {
        List<LichTaiKham> list = new ArrayList<>();
        String sql = """
            SELECT * FROM lich_tai_kham 
            WHERE ngay_tai_kham BETWEEN ? AND ? 
            ORDER BY ngay_tai_kham ASC
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            
            pstm.setString(1, tuNgay);
            pstm.setString(2, denNgay);
            
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                list.add(mapResultSetToModel(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    

    private LichTaiKham mapResultSetToModel(ResultSet rs) throws SQLException {
        LichTaiKham ltk = new LichTaiKham();
        ltk.setId(rs.getInt("id"));
        ltk.setMaBenhNhan(rs.getInt("ma_benh_nhan"));
        ltk.setMack(rs.getInt("ma_chuyen_khoa"));

        int mpk = rs.getInt("ma_phieu_kham");
        ltk.setMaPhieuKham(rs.wasNull() ? null : mpk);
        
        ltk.setMaNhanVien(rs.getInt("ma_nhan_vien"));
        ltk.setNgayTaiKham(rs.getDate("ngay_tai_kham"));
        ltk.setGhiChu(rs.getString("ghi_chu"));
        ltk.setTrangThai(rs.getString("trang_thai"));
        return ltk;
    }
    
}