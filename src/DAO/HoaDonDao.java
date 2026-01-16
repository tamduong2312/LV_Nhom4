package DAO;

import MODEL.CTHoaDon;
import MODEL.HoaDon;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDao {
    private final String DB_URL = "jdbc:mysql://localhost:3306/dbphongkham";
    private final String USER = "root";
    private final String PASS = "";


    public int taoHoaDon(HoaDon hd) {
        String sql = "INSERT INTO hoa_don(ma_phieu_kham, ma_nhan_vien, tong_tien, ngay_thanh_toan, ghi_chu, trang_thai) VALUES (?,?,?,NOW(),?,?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setInt(1, hd.getMaPhieuKham());
            ps.setInt(2, hd.getMaNhanVien());
            ps.setDouble(3, hd.getTongTien());
            ps.setString(4, hd.getGhiChu());
            ps.setString(5, "chưa thanh toán");

            if (ps.executeUpdate() > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) return rs.getInt(1);
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return -1;
    }


    public boolean themChiTietHoaDon(CTHoaDon ct) {
        String sql = "INSERT INTO ct_hoa_don(ma_hoa_don, noi_dung, loai_muc, id_goc, so_luong, don_gia, thanh_tien) VALUES (?,?,?,?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS); 
        	       PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, ct.getMaHoaDon());
            ps.setString(2, ct.getNoiDung());
            ps.setString(3, ct.getLoaiMuc());
            ps.setInt(4, ct.getIdGoc());
            ps.setInt(5, ct.getSoLuong());
            ps.setDouble(6, ct.getDonGia());
            ps.setDouble(7, ct.getThanhTien());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public boolean capNhatTrangThai(int maHD, String trangThai, double tongTien) {
        String sql = "UPDATE hoa_don SET trang_thai = ?, tong_tien = ? WHERE ma_hoa_don = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, trangThai);
            ps.setDouble(2, tongTien);
            ps.setInt(3, maHD);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { e.printStackTrace(); return false; }
    }
    
    public List<HoaDon> getAllHoaDon() {
        List<HoaDon> list = new ArrayList<>();
        String sql = "SELECT hd.*, bn.ho_ten AS ten_benh_nhan, nv.ho_ten AS ten_nhan_vien "
                + "FROM hoa_don hd "
                + "JOIN phieu_kham pk ON hd.ma_phieu_kham = pk.ma_phieu_kham "
                + "JOIN benh_nhan bn ON pk.ma_benh_nhan = bn.ma_benh_nhan "
                + "JOIN nhan_vien nv ON hd.ma_nhan_vien = nv.ma_nhan_vien "
                + "ORDER BY hd.ma_hoa_don DESC ";


        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                HoaDon hd = new HoaDon();
                hd.setMaHoaDon(rs.getInt("ma_hoa_don"));
                hd.setMaPhieuKham(rs.getInt("ma_phieu_kham"));
                hd.setMaNhanVien(rs.getInt("ma_nhan_vien"));
                hd.setTongTien(rs.getDouble("tong_tien"));
                hd.setNgayThanhToan(rs.getTimestamp("ngay_thanh_toan"));
                hd.setGhiChu(rs.getString("ghi_chu"));
                hd.setTrangThai(rs.getString("trang_thai"));
                hd.setTenBenhNhan(rs.getString("ten_benh_nhan"));
                list.add(hd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<CTHoaDon> getChiTietByMaHD(int maHoaDon) {
        List<CTHoaDon> list = new ArrayList<>();
        String sql = "SELECT * FROM ct_hoa_don WHERE ma_hoa_don = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maHoaDon);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CTHoaDon ct = new CTHoaDon();
                ct.setIdGoc(rs.getInt("id_goc")); 
                ct.setLoaiMuc(rs.getString("loai_muc")); 
                ct.setNoiDung(rs.getString("noi_dung")); 
                ct.setSoLuong(rs.getInt("so_luong"));
                ct.setDonGia(rs.getDouble("don_gia"));
                ct.setThanhTien(rs.getDouble("thanh_tien"));
                
                list.add(ct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
    public List<HoaDon> timKiemTheoText(String keyword) {
        List<HoaDon> list = new ArrayList<>();
        String sql = "SELECT hd.*, bn.ho_ten AS ten_benh_nhan, nv.ho_ten AS ten_nhan_vien "
                + "FROM hoa_don hd "
                + "JOIN phieu_kham pk ON hd.ma_phieu_kham = pk.ma_phieu_kham "
                + "JOIN benh_nhan bn ON pk.ma_benh_nhan = bn.ma_benh_nhan "
                + "JOIN nhan_vien nv ON hd.ma_nhan_vien = nv.ma_nhan_vien "
                + "WHERE CAST(hd.ma_hoa_don AS CHAR) LIKE ? OR CAST(hd.ma_phieu_kham AS CHAR) LIKE ? OR CAST(bn.ho_ten AS CHAR) LIKE ? "
                + "ORDER BY hd.ma_hoa_don DESC";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            String val = "%" + keyword + "%";
            ps.setString(1, val);
            ps.setString(2, val);
            ps.setString(3, val);
            
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRowToHoaDon(rs)); 
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

 
    public List<HoaDon> timKiemTheoNgay(String tuNgay, String denNgay) {
        List<HoaDon> list = new ArrayList<>();
        String sql = "SELECT hd.*, bn.ho_ten AS ten_benh_nhan, nv.ho_ten AS ten_nhan_vien "
                + "FROM hoa_don hd "
                + "JOIN phieu_kham pk ON hd.ma_phieu_kham = pk.ma_phieu_kham "
                + "JOIN benh_nhan bn ON pk.ma_benh_nhan = bn.ma_benh_nhan "
                + "JOIN nhan_vien nv ON hd.ma_nhan_vien = nv.ma_nhan_vien "
                + "WHERE DATE(hd.ngay_thanh_toan) BETWEEN ? AND ? "
                + "ORDER BY hd.ma_hoa_don DESC";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, tuNgay);
            ps.setString(2, denNgay);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRowToHoaDon(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }


    private HoaDon mapRowToHoaDon(ResultSet rs) throws SQLException {
        HoaDon hd = new HoaDon();
        hd.setMaHoaDon(rs.getInt("ma_hoa_don"));
        hd.setMaPhieuKham(rs.getInt("ma_phieu_kham"));
        hd.setMaNhanVien(rs.getInt("ma_nhan_vien"));
        hd.setTongTien(rs.getDouble("tong_tien"));
        hd.setTenBenhNhan(rs.getString("ten_benh_nhan"));
        hd.setNgayThanhToan(rs.getTimestamp("ngay_thanh_toan"));
        hd.setGhiChu(rs.getString("ghi_chu"));
        hd.setTrangThai(rs.getString("trang_thai"));
        return hd;
    }

    public boolean thanhToanTatCaPhieuCuaBNTrongNgay(int maBenhNhan) {

        String sql = """
            UPDATE phieu_kham 
            SET trang_thai = 'DA_THANH_TOAN' 
            WHERE ma_benh_nhan = ? 
              AND DATE(ngay_kham) = CURDATE() 
              AND trang_thai = 'DA_KHAM'
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, maBenhNhan);
            
            int rowsAffected = ps.executeUpdate();
            

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật trạng thái thanh toán tổng hợp: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
    public int getMaHoaDonByMaPhieuKham(int maPK) {
        int maHD = -1;

        String sql = "SELECT ma_hoa_don FROM hoa_don WHERE ma_phieu_kham = ? LIMIT 1";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            

            ps.setInt(1, maPK);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {

                    maHD = rs.getInt("ma_hoa_don");
                }
            }
        } catch (SQLException e) {
 
            System.err.println("Lỗi khi lấy mã hóa đơn từ maPK: " + e.getMessage());
            e.printStackTrace();
        }
        

        return maHD;
    }
    
    public boolean xoaChiTiet(int maHD, int idGoc, String loaiMuc) {
        String sql = "DELETE FROM ct_hoa_don WHERE ma_hoa_don = ? AND id_goc = ? AND loai_muc = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, maHD);
            pstmt.setInt(2, idGoc);
            pstmt.setString(3, loaiMuc);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean updateTongTien(int maHD, double tongTienMoi) {
        String sql = "UPDATE hoa_don SET tong_tien = ? WHERE ma_hoa_don = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setDouble(1, tongTienMoi);
            pstmt.setInt(2, maHD);
            
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    

    

    public boolean yeuCauHoanTien(int maPK, String lyDoHoanTien) {
        String sql = "UPDATE hoa_don SET trang_thai = ?, ghi_chu = ? WHERE ma_phieu_kham = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, "cần hoàn tiền");
            ps.setString(2, lyDoHoanTien);
            ps.setInt(3, maPK);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getGhiChuByMaPK(int maPK) {
        String ghiChu = "";
        String sql = "SELECT ghi_chu FROM hoa_don WHERE ma_phieu_kham = ? LIMIT 1";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maPK);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ghiChu = rs.getString("ghi_chu");

                    if (ghiChu == null) {
                        ghiChu = "";
                    }
                }
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy ghi chú hóa đơn: " + e.getMessage());
            e.printStackTrace();
        }
        return ghiChu;
    }
    
    

    public boolean updateTrangThaiByMaHD(int maHD) {
        String sql = "UPDATE hoa_don SET trang_thai = ? WHERE ma_hoa_don = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, "Đã hoàn tiền");
            ps.setInt(2, maHD);
            
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Lỗi khi cập nhật trạng thái hóa đơn: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}