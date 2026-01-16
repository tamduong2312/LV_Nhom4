package DAO;

import MODEL.CTToaThuoc;
import MODEL.DonThuocCho;
import MODEL.ToaThuoc;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ToaThuocDao {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/dbphongkham";
    private static final String USER = "root";
    private static final String PASS = "";


    public int taoToaMoi(int maPhieuKham, String ghiChu) {
        int generatedId = 0;


        String sql = "INSERT INTO toa_thuoc(ma_phieu_kham, ghi_chu, ngay_tao) VALUES(?, ?, NOW())";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setInt(1, maPhieuKham);
            ps.setString(2, ghiChu);
            
            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getInt(1);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return generatedId;
    }


    public ToaThuoc getByPhieuKham(int maPhieuKham) {
        ToaThuoc toa = null;
        String sql = "SELECT * FROM toa_thuoc WHERE ma_phieu_kham = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setInt(1, maPhieuKham);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                toa = new ToaThuoc();
         
                try {
                    toa.setMaToaThuoc(rs.getInt("ma_toa_thuoc")); 
                } catch (SQLException e) {
                    toa.setMaToaThuoc(rs.getInt("id")); 
                }
                
                toa.setMaPhieuKham(rs.getInt("ma_phieu_kham"));
                
     
                toa.setGhiChu(rs.getString("ghi_chu")); 
                
         
                toa.setNgayTao(rs.getTimestamp("ngay_tao")); 
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return toa;
    }

  

    public List<CTToaThuoc> getChiTietByMaToa(int maToa) {
        List<CTToaThuoc> list = new ArrayList<>();
        String sql = "SELECT ct.*, t.ten_thuoc, t.don_vi_tinh, t.don_gia_ban " +
                     "FROM ct_toa_thuoc ct " +
                     "JOIN thuoc t ON ct.ma_thuoc = t.ma_thuoc " +
                     "WHERE ct.ma_toa_thuoc = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maToa);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CTToaThuoc ct = new CTToaThuoc();
                ct.setId(rs.getInt("id"));
                ct.setMaToaThuoc(rs.getInt("ma_toa_thuoc"));
                ct.setMaThuoc(rs.getInt("ma_thuoc"));
                
          
                ct.setSang(rs.getString("sang"));
                ct.setTrua(rs.getString("trua"));
                ct.setChieu(rs.getString("chieu"));
                ct.setToi(rs.getString("toi"));
                ct.setSoNgay(rs.getInt("so_ngay"));
                ct.setThoiDiemDung(rs.getString("thoi_diem_dung"));
                ct.setCachDung(rs.getString("cach_dung"));
                
   
                ct.setTenThuoc(rs.getString("ten_thuoc"));
                ct.setDonViTinh(rs.getString("don_vi_tinh"));
                ct.setDonGia(rs.getDouble("don_gia_ban"));
                
                list.add(ct);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public boolean themChiTiet(CTToaThuoc ct) {
        String sql = "INSERT INTO ct_toa_thuoc(ma_toa_thuoc, ma_thuoc, sang, trua, chieu, toi, so_ngay, thoi_diem_dung, cach_dung) VALUES(?,?,?,?,?,?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, ct.getMaToaThuoc());
            ps.setInt(2, ct.getMaThuoc());
            ps.setString(3, ct.getSang());
            ps.setString(4, ct.getTrua());
            ps.setString(5, ct.getChieu());
            ps.setString(6, ct.getToi());
            ps.setInt(7, ct.getSoNgay());
            ps.setString(8, ct.getThoiDiemDung());
            ps.setString(9, ct.getCachDung());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

    public boolean xoaChiTiet(int id) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement("DELETE FROM ct_toa_thuoc WHERE id=?")) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }
    
    public boolean capNhatChiTiet(CTToaThuoc ct) {
        String sql = "UPDATE ct_toa_thuoc SET sang=?, trua=?, chieu=?, toi=?, so_ngay=?, thoi_diem_dung=?, cach_dung=? WHERE id=?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setString(1, ct.getSang());
            ps.setString(2, ct.getTrua());
            ps.setString(3, ct.getChieu());
            ps.setString(4, ct.getToi());
            ps.setInt(5, ct.getSoNgay());
            ps.setString(6, ct.getThoiDiemDung());
            ps.setString(7, ct.getCachDung());
            ps.setInt(8, ct.getId()); 
            
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public List<CTToaThuoc> getChiTietByMaPhieuKham(int maPhieuKham) {
        List<CTToaThuoc> list = new ArrayList<>();
        
    
        String sql = """
            SELECT ct.*, t.ten_thuoc, t.don_vi_tinh, t.don_gia_ban
            FROM ct_toa_thuoc ct
            JOIN toa_thuoc tt ON ct.ma_toa_thuoc = tt.ma_toa_thuoc
            JOIN thuoc t ON ct.ma_thuoc = t.ma_thuoc
            WHERE tt.ma_phieu_kham = ?
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setInt(1, maPhieuKham);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                CTToaThuoc ct = new CTToaThuoc();
                ct.setId(rs.getInt("id"));
                ct.setMaToaThuoc(rs.getInt("ma_toa_thuoc"));
                ct.setMaThuoc(rs.getInt("ma_thuoc"));
                
                ct.setSang(rs.getString("sang"));
                ct.setTrua(rs.getString("trua"));
                ct.setChieu(rs.getString("chieu"));
                ct.setToi(rs.getString("toi"));
                ct.setSoNgay(rs.getInt("so_ngay"));
                ct.setThoiDiemDung(rs.getString("thoi_diem_dung"));
                ct.setCachDung(rs.getString("cach_dung"));
                
                ct.setTenThuoc(rs.getString("ten_thuoc"));
                ct.setDonViTinh(rs.getString("don_vi_tinh"));
                ct.setDonGia(rs.getDouble("don_gia_ban"));
                
                list.add(ct);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    

    public List<ToaThuoc> getListToaThuocByBenhNhan(int maBenhNhan) {
    	 List<ToaThuoc> list = new ArrayList<>();
    	    
    	    String sql = "SELECT tt.ma_toa_thuoc, tt.ngay_tao, tt.ghi_chu " +
    	                 "FROM toa_thuoc tt " +
    	                 "JOIN phieu_kham pk ON tt.ma_phieu_kham = pk.ma_phieu_kham " +
    	                 "WHERE pk.ma_benh_nhan = ? " +
    	                 "AND DATE(tt.ngay_tao) < CURDATE() " + 
    	                 "ORDER BY tt.ngay_tao DESC";

    	    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
    	         PreparedStatement ps = conn.prepareStatement(sql)) {
    	        
    	        ps.setInt(1, maBenhNhan);
    	        ResultSet rs = ps.executeQuery();
    	        
    	        while (rs.next()) {
    	            ToaThuoc toa = new ToaThuoc();
    	            toa.setMaToaThuoc(rs.getInt("ma_toa_thuoc"));
    	            toa.setNgayTao(rs.getTimestamp("ngay_tao"));
    	            toa.setGhiChu(rs.getString("ghi_chu"));
    	            
    	            list.add(toa);
    	        }
    	    } catch (Exception e) {
    	        e.printStackTrace();
    	    }
    	    return list;
    	}

    public boolean updateToaThuocByPhieuKham(String tongtien, int mapk) {
        String sql = "UPDATE toa_thuoc SET  tong_tien = ? WHERE ma_phieu_kham = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
        
            ps.setString(1, tongtien);
            ps.setInt(2, mapk);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<DonThuocCho> getAllLichSuCapPhat() {
        List<DonThuocCho> list = new ArrayList<>();
        String sql = """
            SELECT pk.ma_phieu_kham, tt.ma_toa_thuoc, bn.ho_ten AS ten_benh_nhan, nv.ho_ten AS ten_bac_si, pk.ngay_kham
            FROM phieu_kham pk
            JOIN toa_thuoc tt ON pk.ma_phieu_kham = tt.ma_phieu_kham
            JOIN benh_nhan bn ON pk.ma_benh_nhan = bn.ma_benh_nhan
            JOIN nhan_vien nv ON pk.ma_nhan_vien = nv.ma_nhan_vien
            WHERE pk.trang_thai = 'DA_CAP_THUOC'
            ORDER BY pk.ngay_kham DESC
        """;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRowToModel(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }


    public List<DonThuocCho> timKiemLichSuTheoTuKhoa(String keyword) {
        List<DonThuocCho> list = new ArrayList<>();
        String sql = """
            SELECT pk.ma_phieu_kham, tt.ma_toa_thuoc, bn.ho_ten AS ten_benh_nhan, nv.ho_ten AS ten_bac_si, pk.ngay_kham
            FROM phieu_kham pk
            JOIN toa_thuoc tt ON pk.ma_phieu_kham = tt.ma_phieu_kham
            JOIN benh_nhan bn ON pk.ma_benh_nhan = bn.ma_benh_nhan
            JOIN nhan_vien nv ON pk.ma_nhan_vien = nv.ma_nhan_vien
            WHERE pk.trang_thai = 'DA_CAP_THUOC'
              AND (bn.ho_ten LIKE ? OR CAST(tt.ma_toa_thuoc AS CHAR) LIKE ?)
            ORDER BY pk.ngay_kham DESC
        """;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String val = "%" + keyword + "%";
            ps.setString(1, val);
            ps.setString(2, val);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRowToModel(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }


    public List<DonThuocCho> timKiemLichSuTheoNgay(String tuNgay, String denNgay) {
        List<DonThuocCho> list = new ArrayList<>();
        String sql = """
            SELECT pk.ma_phieu_kham, tt.ma_toa_thuoc, bn.ho_ten AS ten_benh_nhan, nv.ho_ten AS ten_bac_si, pk.ngay_kham
            FROM phieu_kham pk
            JOIN toa_thuoc tt ON pk.ma_phieu_kham = tt.ma_phieu_kham
            JOIN benh_nhan bn ON pk.ma_benh_nhan = bn.ma_benh_nhan
            JOIN nhan_vien nv ON pk.ma_nhan_vien = nv.ma_nhan_vien
            WHERE pk.trang_thai = 'DA_CAP_THUOC'
              AND DATE(pk.ngay_kham) BETWEEN ? AND ?
            ORDER BY pk.ngay_kham DESC
        """;
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tuNgay);
            ps.setString(2, denNgay);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRowToModel(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }


    private DonThuocCho mapRowToModel(ResultSet rs) throws SQLException {
        DonThuocCho dt = new DonThuocCho();
        dt.setMaPhieuKham(rs.getInt("ma_phieu_kham"));
        dt.setMaToaThuoc(rs.getInt("ma_toa_thuoc"));
        dt.setTenBenhNhan(rs.getString("ten_benh_nhan"));
        dt.setTenBacSi(rs.getString("ten_bac_si"));
        dt.setThoiGian(rs.getTimestamp("ngay_kham"));
        return dt;
    }
    
    





    public void appendGhiChuHoanTien(int maPK, String thongTinThem) {
        String sql = "UPDATE toa_thuoc SET ghi_chu = CONCAT(IFNULL(ghi_chu,''), ?) WHERE ma_phieu_kham = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "\n- " + thongTinThem);
            ps.setInt(2, maPK);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }
    
    public boolean updateTrangThaiToaThuoc(int maPK, String trangThaiMoi) {
        String sql = "UPDATE toa_thuoc SET trang_thai = ? WHERE ma_phieu_kham = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, trangThaiMoi);
            ps.setInt(2, maPK);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public ToaThuoc getTrangThaiVaGhiChuByMaPK(int maPK) {
       ToaThuoc tt = null;
        String sql = "SELECT trang_thai, ghi_chu FROM toa_thuoc WHERE ma_phieu_kham = ? ";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maPK);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                tt = new MODEL.ToaThuoc();
                tt.setTrangThai(rs.getString("trang_thai"));
                tt.setGhiChu(rs.getString("ghi_chu"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tt;
    }
}